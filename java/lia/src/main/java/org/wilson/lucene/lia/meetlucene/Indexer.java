package org.wilson.lucene.lia.meetlucene;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.io.FileReader;
import java.nio.file.Paths;

/**
 * Created by wilson on 16/3/21.
 */
public class Indexer {
    private IndexWriter writer;

    public Indexer(String indexDir) throws IOException {
        Directory dir = FSDirectory.open(Paths.get(indexDir));
        Analyzer analyzer = new StandardAnalyzer();
        IndexWriterConfig iwc = new IndexWriterConfig(analyzer);
        iwc.setOpenMode(IndexWriterConfig.OpenMode.CREATE);
        this.writer = new IndexWriter(dir, iwc);
    }

    public void close() throws IOException {
        this.writer.close();
    }

    public int index(String dataDir, FileFilter filter) throws Exception {
        System.out.println(new File(dataDir).getParent());
        File[] files = new File(dataDir).listFiles();
        for (File f: files) {
            if (!f.isDirectory() && !f.isHidden() && f.exists() && f.canRead() && (filter == null || filter.accept(f))) {
                this.indexFile(f);
            }
        }
        return this.writer.numDocs();
    }

    private void indexFile(File f) throws  Exception {
        System.out.println("Indexing " + f.getCanonicalPath());
        Document doc = this.getDocument(f);
        this.writer.addDocument(doc);
    }

    protected Document getDocument(File f) throws Exception {
        Document doc = new Document();
        doc.add(new TextField("contents", new BufferedReader(new FileReader(f))));
        doc.add(new StringField("filename", f.getName(), Field.Store.YES));
        doc.add(new StringField("fullpath", f.getCanonicalPath(), Field.Store.YES));
        return doc;
    }

    private static class TextFilesFilter implements FileFilter {
        public boolean accept(File file) {
            return file.getName().toLowerCase().endsWith(".txt");
        }
    }

    public static void main(String[] args) throws Exception {
        if (args.length != 2) {
            throw new IllegalArgumentException("Usage java: " + Indexer.class.getName() + " <index dir> <data dir>");
        }

        String indexDir = args[0];
        String dataDir = args[1];

        long start = System.currentTimeMillis();
        Indexer indexer = new Indexer(indexDir);
        int numIndexed;
        try {
            numIndexed = indexer.index(dataDir, new TextFilesFilter());
        } finally {
            indexer.close();
        }
        long end = System.currentTimeMillis();
        System.out.println("Indexing " + numIndexed + " files took " + (end - start) + " milliseconds");
    }
}

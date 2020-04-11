package org.wilson.springbootinaction.ch02;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * Created by wilson on 2020/4/8.
 */
@Controller
@RequestMapping("/readingList")
public class ReadingListController {
    private static final String READER = "craig";

    private ReadingListRepository readingListRepository;

    @Autowired
    public ReadingListController(ReadingListRepository readingListRepository) {
        this.readingListRepository = readingListRepository;
    }

    @GetMapping
    public String readersBooks(Model model) {
        List<Book> readingList = this.readingListRepository.findByReader(ReadingListController.READER);
        if (readingList != null) {
            model.addAttribute("books", readingList);
        }
        return "readingList";
    }

    @PostMapping
    public String addToReadingList(Book book) {
        book.setReader(ReadingListController.READER);
        this.readingListRepository.save(book);
        return "redirect:/readingList";
    }
}

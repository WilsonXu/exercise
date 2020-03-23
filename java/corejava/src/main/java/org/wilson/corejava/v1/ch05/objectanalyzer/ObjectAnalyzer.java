package org.wilson.corejava.v1.ch05.objectanalyzer;

import java.util.ArrayList;

/**
 * Created by wilson on 2020/3/23.
 */
public class ObjectAnalyzer {
    private ArrayList<Object> visited = new ArrayList<>();

    public String toString(Object obj) {
        if (obj == null) return "null";
        if (this.visited.contains(obj)) return "...";
        visited.add(obj);

        return "";
    }
}

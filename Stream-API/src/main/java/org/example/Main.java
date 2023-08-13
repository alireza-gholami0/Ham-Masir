package org.example;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        TextAnalyzer textAnalyzer = new TextAnalyzer();
        System.out.println(textAnalyzer.wordRepeat().get("end"));
    }
}
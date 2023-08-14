package org.example;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        TextAnalyzer textAnalyzer = new TextAnalyzer();
        System.out.println(textAnalyzer.numLines());
        System.out.println(textAnalyzer.numWordLines());
        System.out.println(textAnalyzer.wordRepeat().get("end"));
        System.out.println(textAnalyzer.numWordSentences().get(2));
        System.out.println(textAnalyzer.WordsInLines().get("really"));
        System.out.println(textAnalyzer.adjustedWordCount());
        System.out.println(textAnalyzer.sentencesMappedByFirstWord().get("Adelaïda"));
        System.out.println(textAnalyzer.averageCharactersPerSentence());
    }
}
package org.example;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class TextAnalyzer {
    public Path path = Paths.get("C:\\Users\\ALIREZA\\Documents\\GitHub\\Ham-Masir\\Stream-API\\src\\main\\java\\org\\example\\TheBrothersKaramazov.txt");
    public static List<String> lines = new ArrayList<>();
    public  Path pathStop = Paths.get("C:\\Users\\ALIREZA\\Documents\\GitHub\\Ham-Masir\\Stream-API\\src\\main\\java\\org\\example\\StopWords.txt");
    public  List<String> stopWords = new ArrayList<>();
    public  List<String> sentences = new ArrayList<>();
    public TextAnalyzer() throws IOException {
        this.lines = Files.lines(Paths.get(path.toUri())).toList();
        this.lines = lines.stream().filter(x -> (!x.isEmpty())).map(line->
                line.replaceAll("[\\p{Punct}&&[^.!?]]", "").
                        replaceAll("—", " ").
                        replaceAll("[“”’]", "").
                        replaceAll("\\? ", "?").
                        replace(". ", ".").
                        replace(" .", ".").
                        replaceAll("‘", "")).toList();
        stopWords = Files.lines(Paths.get(pathStop.toUri())).toList();
        String text =(String) lines.stream().reduce("", String::concat);
        sentences = (List<String>) Arrays.stream(text.split("\\."))
                .map(String::trim)
                .filter(s -> !s.isEmpty())
                .collect(Collectors.toList());
    }
    public long numLines(){
        return lines.stream().count();
    }
    public long numWordLines() throws IOException {
        long wordCount =  lines.stream().map(line -> Math.toIntExact(Arrays.stream(line.split("[.?! ]")).
                filter(word -> !stopWords.contains(word)).count())).reduce(0,(i, j)-> i+j);
        return wordCount;
    }
    public  List<Integer> numWordSentences() throws IOException {
        return (List<Integer>) sentences.stream().map(sentence -> Math.toIntExact(Arrays.stream(sentence.split("[.?! ]")).
                filter(word -> !stopWords.contains(word)).count())).toList();
    }
    public Map<String,Long> wordRepeat(){
        Map<String, Long> wordCounts = lines.stream()
                .flatMap(s -> Arrays.stream(s.split("[.?! ]")))
                .filter(w -> !stopWords.contains(w.toLowerCase()))
                .collect(Collectors.groupingBy(w->w, Collectors.counting()));
        return wordCounts;
    }

    public Map<String, List<Integer>> WordsInLines() {
        return IntStream.range(0, lines.size())
                .mapToObj(i -> new AbstractMap.SimpleEntry<>(i, lines.get(i).split("[.?! ]")))
                .flatMap(entry -> IntStream.range(0, entry.getValue().length)
                        .mapToObj(j -> new AbstractMap.SimpleEntry<>(entry.getValue()[j], entry.getKey())))
                .collect(Collectors.groupingBy(Map.Entry::getKey, Collectors.mapping(Map.Entry::getValue, Collectors.toList())));
    }

    public double averageCharactersPerSentence(){
        return sentences.stream()
                .mapToInt(String::length)
                .average()
                .orElse(0);
    }
    public long adjustedWordCount(){
        return sentences.stream()
                .mapToLong(line -> Arrays.stream(line.split("[.?! ]")).count())
                .reduce(0,(a,b)->b%2 == 0 ? a+b : a-b);
    }

    public Map<String,String> sentencesMappedByFirstWord(){
        return sentences.stream()
                .collect(Collectors.toMap(
                        line -> line.split("[.?! ]")[0],
                        line -> line,
                        (line1, line2) -> line1
                ));
    }
}

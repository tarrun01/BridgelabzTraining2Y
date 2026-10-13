package com.gla.searching.linear;

public class SearchWordInSentences {

    public static String searchWord(String[] sentences, String word) {
        for (int i = 0; i < sentences.length; i++) {
            if (sentences[i].contains(word)) {
                return sentences[i];
            }
        }
        return "Not Found";
    }

    public static void main(String[] args) {
        String[] sentences = {"Java is a programming language.", "Binary search is efficient.", "Linear search checks each element."};
        System.out.println("Search search: " + searchWord(sentences, "search"));
        System.out.println("Search Python: " + searchWord(sentences, "Python"));
    }
}

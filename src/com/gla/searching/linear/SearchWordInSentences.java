package com.gla.searching.linear;

/**
 * Linear search for the first sentence containing a word.
 */
public class SearchWordInSentences {

    public static String searchWord(String[] sentences, String word) {
        if (sentences == null || word == null) {
            return "Not Found";
        }
        for (String sentence : sentences) {
            if (sentence != null && sentence.contains(word)) {
                return sentence;
            }
        }
        return "Not Found";
    }

    public static void main(String[] args) {
        String[] sentences = {
            "Java is a programming language.",
            "Binary search is efficient.",
            "Linear search checks each element.",
            "File handling is important in Java."
        };
        String word1 = "search";
        String word2 = "Python";

        System.out.println("Searching for \"" + word1 + "\": " + searchWord(sentences, word1));
        System.out.println("Searching for \"" + word2 + "\": " + searchWord(sentences, word2));
    }
}

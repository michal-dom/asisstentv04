package com.example.michal.asisstantv04.AlgorithmStrategies;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class NGrams {
    public static List<String> nGrams(String str, int n){
        List<String> ngrams = new ArrayList<>();
        if(n == -1){
            return wordGram(str);
        }
        for (int i = 0; i < str.length() - n + 1; i++)
            ngrams.add(str.substring(i, i + n));
        return ngrams;
    }

    public static List<String> monoGrams(String str) {
        int n = 1;
        List<String> ngrams = new ArrayList<>();
        for (int i = 0; i < str.length() - n + 1; i++)
            ngrams.add(str.substring(i, i + n));
        return ngrams;
    }

    public static List<String> biGrams(String str) {
        int n = 2;
        List<String> ngrams = new ArrayList<>();
        for (int i = 0; i < str.length() - n + 1; i++)
            ngrams.add(str.substring(i, i + n));
        return ngrams;
    }

    public static List<String> triGrams(String str) {
        int n = 3;
        List<String> ngrams = new ArrayList<>();
        for (int i = 0; i < str.length() - n + 1; i++)
            ngrams.add(str.substring(i, i + n));
        return ngrams;
    }

    public static List<String> wordGram(String str){
        List<String> ngrams;
        ngrams = Arrays.asList(str.split(" "));
        return ngrams;
    }
}

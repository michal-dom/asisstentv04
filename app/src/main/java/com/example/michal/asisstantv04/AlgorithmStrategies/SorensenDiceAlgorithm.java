package com.example.michal.asisstantv04.AlgorithmStrategies;

import java.util.ArrayList;
import java.util.List;

public class SorensenDiceAlgorithm implements IAlgorithm {

    @Override
    public double similarity(String s1, String s2, int n) {
        if (s1 == null) { throw new NullPointerException("s1 must not be null"); }
        if (s2 == null) { throw new NullPointerException("s2 must not be null"); }
        if (s1.equals(s2)) { return 0; }
        if (s1.length() == 0) { return s2.length(); }
        if (s2.length() == 0) { return s1.length(); }

        List<String> s1_grams = NGrams.nGrams(s1, n);
        List<String> s2_grams = NGrams.nGrams(s2, n);

        int s1_len = s1_grams.size();
        int s2_len = s2_grams.size();

        List<String> intersection = new ArrayList<String>();

        for(String s : s1_grams){
            if(s2_grams.contains(s)){
                intersection.add(s);
            }
        }

        double weight = (2.0 * (double)intersection.size()) / (double)(s1_len + s2_len);

        return weight;
    }
}

package com.example.michal.asisstantv04.AlgorithmStrategies;

import java.util.List;

public class MongeElkanAlgorithm implements IAlgorithm {

    IAlgorithm algorithm;

    public void setAlgorithm(IAlgorithm algorithm) {
        this.algorithm = algorithm;
    }

    @Override
    public double similarity(String s1, String s2, int n) {
        algorithm = new LevenshteinAlgorithm();
        if (s1 == null) { throw new NullPointerException("s1 must not be null"); }
        if (s2 == null) { throw new NullPointerException("s2 must not be null"); }
        if (s1.equals(s2)) { return 0; }
        if (s1.length() == 0) { return s2.length(); }
        if (s2.length() == 0) { return s1.length(); }

        List<String> s1_grams = NGrams.nGrams(s1, -1);
        List<String> s2_grams = NGrams.nGrams(s2, -1);

        double sum = 0.0;

        for (String s1_elem: s1_grams){
            double max = 0.0;
            for (String s2_elem : s2_grams) {

                max = Math.max(max, this.algorithm.similarity(s1_elem, s2_elem, 1));
            }
            sum += Math.pow(max, 2);
        }

        double arithmetic = sum / s1_grams.size();
        double generalized = Math.pow(arithmetic, 0.5);

        return generalized;
    }
}

package com.example.michal.asisstantv04.AlgorithmStrategies;

import java.util.List;

public class LevenshteinAlgorithm implements IAlgorithm {
    @Override
    public double similarity(String s1, String s2, int n) {

        if (s1 == null) { throw new NullPointerException("s1 must not be null"); }
        if (s2 == null) { throw new NullPointerException("s2 must not be null"); }
        if (s1.equals(s2)) { return 1; }
        if (s1.length() == 0) { return s2.length(); }
        if (s2.length() == 0) { return s1.length(); }

        if(s2.length() > s1.length()){
            String temp = s1;
            s1 = s2;
            s2 = temp;
        }

        List<String> s1_grams = NGrams.nGrams(s1, n);
        List<String> s2_grams = NGrams.nGrams(s2, n);

        int[] v0 = new int[s1_grams.size() + 1];
        int[] v1 = new int[s2_grams.size() + 1];
        int[] vtemp;

        for (int i = 0; i < v0.length; i++) {
            v0[i] = i;
        }

        for (int i = 0; i < s1_grams.size(); i++) {
            v1[0] = i + 1;
            int minv1 = v1[0];
            for (int j = 0; j < s2_grams.size(); j++) {
                int cost = 1;
                if (s1_grams.get(i).equals(s2_grams.get(j))) {
                    cost = 0;
                }
                v1[j + 1] = Math.min(
                        v1[j] + 1,
                        Math.min(
                                v0[j + 1] + 1,
                                v0[j] + cost));

                minv1 = Math.min(minv1, v1[j + 1]);
            }
            vtemp = v0;
            v0 = v1;
            v1 = vtemp;
        }

        double weight = 1.0 - ((double)v0[s2_grams.size()]/(double)Math.max(s1_grams.size(), s2_grams.size()));

        return weight;
    }
}

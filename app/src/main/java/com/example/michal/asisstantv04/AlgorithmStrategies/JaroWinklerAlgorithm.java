package com.example.michal.asisstantv04.AlgorithmStrategies;

import java.util.Arrays;
import java.util.List;

public class JaroWinklerAlgorithm implements IAlgorithm {

    @Override
    public double similarity(String s1, String s2, int n){
        if (s1 == null) { throw new NullPointerException("s1 must not be null"); }
        if (s2 == null) { throw new NullPointerException("s2 must not be null"); }
        if (s1.equals(s2)) { return 0; }
        if (s1.length() == 0) { return s2.length(); }
        if (s2.length() == 0) { return s1.length(); }

        List<String> s1_grams = NGrams.nGrams(s1, n);
        List<String> s2_grams = NGrams.nGrams(s2, n);

        int s1_len = s1_grams.size();
        int s2_len = s2_grams.size();

        int min_len = Math.max(s1_len, s2_len);

        int search_range = (min_len / 2);
        search_range--;
        if (search_range < 0){
            search_range = 0;
        }

        boolean[] s1_flags = new boolean[s1_len];
        boolean[] s2_flags = new boolean[s2_len];
        Arrays.fill(s1_flags, false);
        Arrays.fill(s2_flags, false);

        int common_chars = 0;

        for(int i = 0; i < s1_len; i++){
            int low = i > search_range ? i - search_range : 0;
            int hi = i + search_range < s2_len ? i + search_range : s2_len - 1;

            for (int j = low; j < hi+1; j++){
                if(!s2_flags[j] && s2_grams.get(j).equals(s1_grams.get(i))){
                    s1_flags[i] = true;
                    s2_flags[j] = true;
                    common_chars ++;
                    break;
                }
            }
        }

        if(common_chars == 0){
            return 0.0;
        }

        int k = 0;
        int trans_count = 0;

        for(int i = 0; i < s1_flags.length; i++ ){
            if(s1_flags[i]){
                int j;
                for(j = k; j < s2_flags.length; j++ ){
                    if(s2_flags[j]){
                        k = j + 1;
                        break;
                    }
                }
                if(!s1_grams.get(i).equals(s2_grams.get(j))){
                    trans_count++;
                }
            }
        }
        trans_count /= 2;

        double temp = (double) common_chars;
        double weight = (temp / s1_len) + (temp / s2_len);
        weight += (temp - trans_count) / temp;
        weight /= 3;

        if(weight <= 0.7 || s1_len <= 3 || s2_len <= 3){
            return weight;
        }

        int j = Math.min(min_len, 4);
        int i = 0;

        while (i<j && s1_grams.get(i).equals(s2_grams.get(i)) && s1_grams.get(i) != null){
            i++;
        }

        if (i > 0){
            weight += i * 0.1 * (1.0 - weight);
        }

        return weight;

    }

}

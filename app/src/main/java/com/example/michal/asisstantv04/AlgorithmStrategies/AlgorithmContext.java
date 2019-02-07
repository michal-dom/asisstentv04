package com.example.michal.asisstantv04.AlgorithmStrategies;

public class AlgorithmContext {
    private static AlgorithmContext ourInstance = new AlgorithmContext();

    public static AlgorithmContext getInstance() {
        return ourInstance;
    }

    public static final int LEVENSHTEIN_ALG = 1;
    public static final int JARO_WINKLER_ALG = 2;
    public static final int SORENSEN_DICE_ALG = 3;
    public static final int MONGE_ELKAN_ALG = 4;

    private IAlgorithm IAlgorithm;

    private AlgorithmContext() {
        IAlgorithm = new LevenshteinAlgorithm();
    }

    public IAlgorithm getIAlgorithm() {
        return IAlgorithm;
    }

    public void setIAlgorithm(int s) {
        if(s == LEVENSHTEIN_ALG){
            IAlgorithm = new LevenshteinAlgorithm();
        }
        if(s == JARO_WINKLER_ALG){
            IAlgorithm = new JaroWinklerAlgorithm();
        }
        if(s == SORENSEN_DICE_ALG){
            IAlgorithm = new SorensenDiceAlgorithm();
        }
        if(s == MONGE_ELKAN_ALG){
            IAlgorithm = new MongeElkanAlgorithm();
        }
    }
}

package org.example;

import java.util.ArrayList;
import java.util.Arrays;

public class Counter {
    private TokenCreator token_generator;
    private ArrayList<String> tokens;
    private double result = 0;
    private ArrayList<String> bin_ops = new ArrayList<>(Arrays.asList("*","/","+","-"));

    private ArrayList<String> get_slice(ArrayList<String> a, int x, int y){
        ArrayList<String> slice = new ArrayList<String>();
        for(int i=x;i<y;i++){
            slice.add(a.get(i));
        }
        return slice;
    }

    public double count(ArrayList<String> tokens){
        int brackets = count_matches(tokens, "(");
        for(int i=0;i<brackets;i++){
            int lb = tokens.lastIndexOf("(");
            int rb = tokens.indexOf(")");
            ArrayList<String> br_exp = get_slice(tokens, lb+1, rb);
            ArrayList<String> lslice = get_slice(tokens, 0, lb);
            ArrayList<String> rslice = get_slice(tokens, rb+1, tokens.size());
            lslice.add(String.valueOf(count_nonbr(br_exp)));
            if(!(rslice.isEmpty())){
                lslice.addAll(lslice.size(), rslice);
            }
            tokens = lslice;
        }
        return count_nonbr(tokens);
    }

    private double count_nonbr(ArrayList<String> tokens) {
        if(!(tokens.size() == 0)) {
            if (tokens.size() == 1) {
                return (double) Double.parseDouble(tokens.get(0));
            } else {
                for (String bin_op : bin_ops) {
                    if (tokens.indexOf(bin_op) == -1) {
                        continue;
                    } else {
                        switch (bin_op) {
                            case "*":
                                result = count_nonbr(get_slice(tokens, 0, tokens.indexOf("*"))) * count_nonbr(get_slice(tokens, tokens.indexOf("*") + 1, tokens.size()));
                                break;
                            case "/":
                                result = count_nonbr(get_slice(tokens, 0, tokens.indexOf("/"))) / count_nonbr(get_slice(tokens, tokens.indexOf("/") + 1, tokens.size()));
                                break;
                            case "+":
                                result = count_nonbr(get_slice(tokens, 0, tokens.indexOf("+"))) + count_nonbr(get_slice(tokens, tokens.indexOf("+") + 1, tokens.size()));
                                break;
                            case "-":
                                result = count_nonbr(get_slice(tokens, 0, tokens.indexOf("-"))) - count_nonbr(get_slice(tokens, tokens.indexOf("-") + 1, tokens.size()));
                                break;
                            }
                        }
                    }
                }
            }
        return result;

        }


    private int count_matches(ArrayList<String> tokens, String m){
        int i = 0;
        for(String s: tokens){
            if(s.equals(m)){
                i++;
            }
        }
        return i;
    }

    // setters, getters, constructors
    public double getResult() {
        return result;
    }

    public TokenCreator getToken_generator() {
        return token_generator;
    }

    public void setToken_generator(TokenCreator token_generator) {
        this.token_generator = token_generator;
    }

    public ArrayList<String> getTokens() {
        return tokens;
    }

    public void setTokens(ArrayList<String> tokens) {
        this.tokens = tokens;
    }

    public Counter(TokenCreator token_generator) {
        this.token_generator = token_generator;
        this.tokens = token_generator.get_tokens();
    }
    public Counter(ArrayList<String> tokens) {
        this.token_generator = null;
        this.tokens = tokens;
    }
    public Counter() {
        this.token_generator = null;
        this.tokens = null;
    }

}

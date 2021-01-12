package org.example;

import java.util.ArrayList;
import org.example.*;
public class Main {

    public static void main(String[] args) {
        for(String s: args){
            Counter result = new Counter(new TokenCreator(s));
            double res = result.count(result.getTokens());
            System.out.print(s);
            System.out.print(" = ");
            System.out.println(res);
        }
    }
}

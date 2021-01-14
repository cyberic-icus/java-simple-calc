package org.example;

import static org.junit.Assert.*;
import org.junit.Test;
import org.example.Counter;
import org.example.TokenCreator;
import java.util.ArrayList;
import java.util.Arrays;


public class CounterTest{
    private ArrayList<String> exps = new ArrayList<>(Arrays.asList("2+((-2)*2+3*(123-3*9))*(10-10)","1+(3*(-1)+2)*2","(-2)*2+3*96.0","2+2*2","2+(12*(-1)+10)*(2-1)","11+(3*(-1)+2)-10","(-1)*(-1)-1","2+(-2.0)*1.0"));
    private double results[] = {2.0, -1.0, 284.0, 6.0, 0.0, 0.0, 0.0, 0.0};

    @Test
    public void testResult() {
        int i = 0;
        for(String s: exps ){
            Counter c = new Counter(new TokenCreator(s));
            assertTrue(c.count(c.getTokens()) == results[i]);
            i++;
        }
    }

}
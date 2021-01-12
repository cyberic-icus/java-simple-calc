package org.example;

import java.util.ArrayList;
import org.example.*;

public class TokenCreator {
    private String exp;
    private ArrayList<String> raw_tokens;
    private ArrayList<String> tokens;

    public ArrayList<String> get_tokens(){
        create_tokens();
        return tokens;
    }

    private ArrayList<String> create_raw_tokens(String exp) {
        ArrayList<String> raw_tokens = new ArrayList<String>();

        for (String s : exp.split("")) {
            raw_tokens.add(s);
        }
        return raw_tokens;
    }

    private ArrayList<String> get_slice(ArrayList<String> a, int x, int y){
        ArrayList<String> slice = new ArrayList<String>();
        for(int i=x;i<y;i++){
            slice.add(a.get(i));
        }
        return slice;
    }

    private int get_length(ArrayList<String> iterable){
        int i = 0;
        for(String m: iterable){
            i++;
        }
        return i;
    }

    private ArrayList<String> create_tokens() {
        String buf_tokens = "";
        int first_position = -1, last_position = -1;
        int i = 0, j = 0, tokens_length = 0;
        for (String c : this.raw_tokens) {

            if (Character.isDigit(c.charAt(0)) & first_position == -1) {
                first_position = i;
            } else if (!(Character.isDigit(c.charAt(0))) & !(c.charAt(0) == '.')) {
                last_position = i;

                if(first_position ==-1){ first_position = 0; }
                ArrayList<String> slice = get_slice(this.raw_tokens, first_position, last_position);
                if (!(slice.size() == 0)) {
                    String exp = "";
                    for(String s: slice){
                        exp = exp+s;
                    }
                    if(!(exp.equals("")) | !(exp.equals(" "))){
                        tokens.add(exp);
                    }
                    tokens_length++;
                    j++;
                }
                tokens.add(c);
                j++;
                first_position = last_position + 1;
            }
            if (i == get_length(this.raw_tokens)-1) {
                last_position = get_length(this.raw_tokens);
                ArrayList<String> slice = get_slice(this.raw_tokens, first_position, last_position);
                String exp = "";
                for(String s: slice){
                    exp = exp+s;
                }
                tokens.add(exp);

            }
            i++;
            last_position = -1;
        }

        int br_counter = 0;
        for (int jj=0;jj<get_length(tokens);jj++) {
            try{
                String a = tokens.get(jj);
                String b = tokens.get(jj+3);
                if ((a.equals("(")) & (b.equals(")"))) {
                    ArrayList<String> lslice = get_slice(tokens,0, jj);
                    lslice.add("-"+tokens.get(jj+2));
                    if(!(get_slice(tokens, jj+4, get_length(tokens)).size()==1)){
                        lslice.addAll(get_length(lslice), get_slice(tokens, jj+4, get_length(tokens)));
                    }

                    tokens = lslice;
                    continue;

                }
            } catch(Exception e){ }

        }
        return tokens;
    }

    TokenCreator(){
        this.exp = "1+1";
        this.raw_tokens = create_raw_tokens(exp);
        this.tokens = new ArrayList<String>();
    }
    TokenCreator(String exp){
        this.exp = exp;
        this.raw_tokens = create_raw_tokens(exp);
        this.tokens = new ArrayList<String>();
    }
}

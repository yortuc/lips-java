package com.yortuc.lips.tokenizer;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Tokenizer {

    private String w = "";
    private ArrayList<Token> tokens = new ArrayList<Token>();

    public String cleanExpression(String expression) {
        String newLine = System.getProperty("line.separator");
        String[] lines = expression.split(newLine);

        // remove line comments
        String[] filteredLines = Arrays.stream(lines)
                .filter(x -> !x.startsWith(";") && x.length() > 0)
                .toArray(String[]::new);

        String combined = String.join(" ", filteredLines);

        // reduce multiple spaces to one
        while (combined.indexOf("  ") > 0) {
            combined = combined.replace("  ", " ");
        }

        return combined;
    }

    private void addWord(){
        if (!w.equals("")){
            tokens.add(new Token("word", w));
            w = "";
        }
    }

    public ArrayList<Token> tokenize(String expression) {
        // clean expression
        String exp = cleanExpression(expression);

        boolean listOpen = false;
        boolean stringOpen = false;

        int index = 0;

        while (index < exp.length()){
            char curChar = exp.charAt(index);

            if (curChar == '('){
                tokens.add(new Token("open", ""));
            }
            else if (curChar == ')') {
                addWord();
                tokens.add(new Token("close", ""));
            }
            else if (curChar == ' ' && !stringOpen) {
                addWord();
            }
            else if (curChar == '"'){
                if(stringOpen) { // close string
                    addWord();
                }
                stringOpen = !stringOpen;
            }
            else {
                w += curChar;
            }

            index += 1;
        }

        return tokens;
    }
}

package com.yortuc.lips.parser;

import com.yortuc.lips.tokenizer.Token;

import java.util.ArrayList;

public class Func extends Token {
    String name;
    ArrayList<Token> params;

    public Func(String name, ArrayList<Token> params){
        super("Func", "");
        this.name = name;
        this.params = params;
    }
//
//    @Override
//    public String toString(){
//        return String.format("")
//    }
}

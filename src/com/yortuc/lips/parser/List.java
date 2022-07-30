package com.yortuc.lips.parser;

import com.yortuc.lips.tokenizer.Token;

import java.util.ArrayList;

public class List extends Token {

    public ArrayList<Token> items;

    public List(ArrayList<Token> items) {
        super("list", "");
        this.items = items;
    }
}

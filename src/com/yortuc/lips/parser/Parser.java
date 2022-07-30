package com.yortuc.lips.parser;

import com.yortuc.lips.tokenizer.Token;

import java.util.ArrayList;
import java.util.Stack;

public class Parser {

    public Token parseTokens(ArrayList<Token> tokens) {
        Stack<Token> stack = new Stack<>();

        for (Token token: tokens)
        {
            if (token.type.equals("open") || token.type.equals("word") || token.type.equals("list_open")){
                stack.add(token);
            }
            else if (token.type.equals("close")){
                ArrayList<Token> subTokens = new ArrayList<>();

                while (true){
                    Token cur = stack.pop();

                    if (cur.type.equals("open")){
                        break;
                    }
                    else{
                        subTokens.add(0, cur);
                    }
                }

                ArrayList<Token> params = new ArrayList<>(subTokens.subList(1, subTokens.size()));
                Token result = new Func(subTokens.get(0).value, params);

                stack.add(result);
            }
            else if(token.type.equals("list_close")){
                ArrayList<Token> listItems = new ArrayList<>();

                while (true){
                    Token cur = stack.pop();

                    if (cur.type.equals("list_open")){
                        break;
                    }
                    else{
                        listItems.add(0, cur);
                    }
                }

                Token result = new List(listItems);
                stack.add(result);
            }
        }

        if (stack.size() == 1){
            // expression evaluated into one single node
            return stack.pop();
        }
        else{
            // list of expressions
            return new List(new ArrayList<>(stack));
        }
    }

}

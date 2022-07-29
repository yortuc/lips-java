package com.yortuc.lips.tokenizer;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class TokenizerTest {
    String newLine = System.getProperty("line.separator");

    @Test
    void testTokenizeEmptyString(){
        String expression = "";

        Tokenizer tokenizer = new Tokenizer();
        ArrayList<Token> result = tokenizer.tokenize(expression);
        ArrayList<Token> expected = new ArrayList<Token>();

        assertIterableEquals(expected, result);
    }

    @Test
    void testTokenizeSimpleParans(){
        String expression = "(hello world)";

        Tokenizer tokenizer = new Tokenizer();
        ArrayList<Token> result = tokenizer.tokenize(expression);

        System.out.println(result);

        ArrayList<Token> expected = new ArrayList<Token>(Arrays.asList(
            new Token("open"),
            new Token("word", "hello"),
            new Token("word", "world"),
            new Token("close")
        ));

        assertIterableEquals(expected, result);
    }

    @Test
    void testIgnoreCommentLines(){
        String expression = ";this is a comment line" +
                newLine +
                "(print)";

        Tokenizer tokenizer = new Tokenizer();
        ArrayList<Token> result = tokenizer.tokenize(expression);

        ArrayList<Token> expected = new ArrayList<Token>(Arrays.asList(
                new Token("open"),
                new Token("word", "print"),
                new Token("close")
        ));

        assertIterableEquals(expected, result);
    }

    @Test
    void testTokenizeListOfExpressions(){
        String expression = "(print 1) (exit)";

        Tokenizer tokenizer = new Tokenizer();
        ArrayList<Token> result = tokenizer.tokenize(expression);

        ArrayList<Token> expected = new ArrayList<Token>(Arrays.asList(
            new Token("open"), new Token("word", "print"), new Token("word", "1"), new Token("close"),
            new Token("open"), new Token("word", "exit"), new Token("close")
        ));

        assertIterableEquals(expected, result);
    }

    @Test
    void testTokenizeWithSpaces(){
        String expression = "(mul" +
            newLine +
            "2" +
            newLine +
            "3" +
            newLine +
            ")";

        Tokenizer tokenizer = new Tokenizer();
        ArrayList<Token> result = tokenizer.tokenize(expression);

        ArrayList<Token> expected = new ArrayList<Token>(Arrays.asList(
            new Token("open"),
            new Token("word", "mul"),
            new Token("word", "2"),
            new Token("word", "3"),
            new Token("close")
        ));

        assertIterableEquals(expected, result);
    }

    @Test
    void testTokenizeString(){
        String expression = "(print \"hello world!\")";

        Tokenizer tokenizer = new Tokenizer();
        ArrayList<Token> result = tokenizer.tokenize(expression);

        ArrayList<Token> expected = new ArrayList<Token>(Arrays.asList(
            new Token("open"),
            new Token("word", "print"),
            new Token("word", "hello world!"),
            new Token("close")
        ));

        assertIterableEquals(expected, result);
    }

    @Test
    void testTokenizeList(){
        String expression = "(sum [1 2])";

        Tokenizer tokenizer = new Tokenizer();
        ArrayList<Token> result = tokenizer.tokenize(expression);

        ArrayList<Token> expected = new ArrayList<Token>(Arrays.asList(
                new Token("open"),
                new Token("word", "sum"),
                new Token("list_open"),
                new Token("word", "1"), new Token("word", "2"),
                new Token("list_close"),
                new Token("close")
        ));

        assertIterableEquals(expected, result);
    }
}
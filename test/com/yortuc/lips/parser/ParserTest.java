package com.yortuc.lips.parser;

import com.yortuc.lips.tokenizer.Token;
import com.yortuc.lips.tokenizer.Tokenizer;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

class ParserTest {

    @Test
    void testParseSimpleTokens(){
        String expression = "(add 1 2)";

        Tokenizer tokenizer = new Tokenizer();
        ArrayList<Token> tokens = tokenizer.tokenize(expression);

        Parser parser = new Parser();
        Func parseResult = (Func)parser.parseTokens(tokens);

        assert parseResult.name.equals("add");

        assertIterableEquals(
            parseResult.params,
            new ArrayList<Token>(Arrays.asList(
                new Token("word", "1"),
                new Token("word", "2")
            )
        ));
    }

    @Test
    void testCascadedParans(){
        String expression = "(add (mul 3 4) 5)";

        Tokenizer tokenizer = new Tokenizer();
        ArrayList<Token> tokens = tokenizer.tokenize(expression);

        Parser parser = new Parser();
        Func parseResult = (Func)parser.parseTokens(tokens);

        assert parseResult.name.equals("add");

        Func firstParam = (Func)parseResult.params.get(0);

        assert firstParam.name.equals("mul");
        assertIterableEquals(
            firstParam.params,
            new ArrayList<>(Arrays.asList(
                new Token("word", "3"),
                new Token("word", "4")
            )
        ));

        assert Objects.equals(
            parseResult.params.get(1),
            new Token("word", "5")
        );
    }

    @Test
    void testParseList(){
        String expression = "(sum [1 2])";

        Tokenizer tokenizer = new Tokenizer();
        ArrayList<Token> tokens = tokenizer.tokenize(expression);

        Parser parser = new Parser();
        Func parseResult = (Func) parser.parseTokens(tokens);

        List listResult = ((List)parseResult.params.get(0));

        assertIterableEquals(
            listResult.items,
            new ArrayList<>(Arrays.asList(
                new Token("word", "1"),
                new Token("word", "2")
            )
        ));
    }

    @Test
    void testParseListOfExpressions(){
        String expression = "(print) (exit)";

        Tokenizer tokenizer = new Tokenizer();
        ArrayList<Token> tokens = tokenizer.tokenize(expression);

        Parser parser = new Parser();
        List parseResult = (List) parser.parseTokens(tokens);

        assert parseResult.items.size() == 2;
        assert ((Func)parseResult.items.get(0)).name.equals("print");
        assert ((Func)parseResult.items.get(1)).name.equals("exit");
    }
}

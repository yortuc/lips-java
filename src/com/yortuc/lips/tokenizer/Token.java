package com.yortuc.lips.tokenizer;

import java.util.Objects;

public class Token {
    public String type;
    public String value = "";

    public Token(String type, String value){
        this.type = type;
        this.value = value;
    }

    public Token(String type){
        assert type.equals("open") || type.equals("close") || type.equals("list_open") || type.equals("list_close");
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {

        if (o == this) return true;

        if (!(o instanceof Token)) {
            return false;
        }

        Token token = (Token) o;

        return token.type.equals(type) &&
               token.value.equals(value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, value);
    }

    @Override
    public String toString(){
        return String.format("Token<%s:%s>", type, value);
    }
}

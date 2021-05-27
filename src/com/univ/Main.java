package com.univ;

import com.univ.lexer.Lexer;
import com.univ.lexer.token.Token;

import java.io.IOException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class TestMatcher {
    public static void main(String[] args) {
        String line = "1234asdfabc4321sadf";
        Pattern p = Pattern.compile("abc", Pattern.CASE_INSENSITIVE);
        Matcher matcher = p.matcher(line);
        if (matcher.find(0)) {
            System.out.println(matcher.start());
            System.out.println(matcher.end());
            System.out.println(line.substring(matcher.start(), matcher.end()));
        }
    }
}

public class Main {

    public static void main(String[] args) {
        if (args.length != 1) {
            throw new IllegalArgumentException("Please, pass a file to the program");
        }

        Lexer lexer = new Lexer();
        try {
            List<Token> tokens = lexer.tokenize(args[0]);
            for (Token token : tokens) {
                System.out.println(token.toString());
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}

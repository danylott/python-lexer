package com.univ.lexer;

import com.univ.automate.Automate;
import com.univ.automate.StateMachine;
import com.univ.automate.MultilineAutomate;
import com.univ.helper.Pair;
import com.univ.lexer.token.Location;
import com.univ.lexer.token.Token;
import com.univ.lexer.token.TokenName;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Lexer {
    private static char END_OF_LINE = '$';
    private List<Token> tokens;
    private Stack<Integer> indentionLengths;

    public Lexer() {
        tokens = new ArrayList<>();
        indentionLengths = new Stack<>();
        indentionLengths.push(0);
    }

    public List<Token> tokenize(String filepath) throws IOException {
        tokens.clear();

        int curLineNum = 1;
        int multilineStringStartLine = -1;
        int multilineStringStartColumn = -1;
        StringBuffer multilineStringValue = new StringBuffer();
        BufferedReader reader = new BufferedReader(new FileReader(filepath));
        String line = reader.readLine();
        while (line != null) {
            String curLine = line;
            for (int i = 0; i < curLine.length(); ) {
                boolean isMatched = false;
                for (Pair patternPair : Patterns.patterns) {
                    if (patternPair.getFirst() instanceof String) {
                        Pattern p = Pattern.compile((String) patternPair.getFirst(), Pattern.CASE_INSENSITIVE);
                        Matcher matcher = p.matcher(curLine);
                        if (matcher.find(i) && matcher.start() == i) {
                            String matchedText = curLine.substring(matcher.start(), matcher.end());
                            isMatched = true;
                            i = matcher.end();
                            Location begin = new Location(curLineNum, matcher.start() + 1);
                            tokens.add(new Token((TokenName) patternPair.getSecond(), matchedText, begin));
                            break;
                        }
                    } else if (patternPair.getFirst() instanceof MultilineAutomate) {
                        MultilineAutomate multilineAutomate = (MultilineAutomate) patternPair.getFirst();
                        Pair<Integer, Integer> matchPos = multilineAutomate.match(curLine, i);
                        if (matchPos.getFirst() != null) {
                            multilineStringValue.append(curLine, matchPos.getFirst(), matchPos.getSecond());
                            if (multilineStringStartLine == -1) {
                                multilineStringStartLine = curLineNum;
                            }
                            tokens.add(new Token(TokenName.MULTILINE_STRING, multilineStringValue.toString(),
                                    new Location(multilineStringStartLine, matchPos.getFirst() + 1)));
                            multilineStringValue = new StringBuffer();
                            multilineStringStartLine = multilineStringStartColumn = -1;
                            isMatched = true;
                            i = curLine.length();
                            break;
                        } else if (multilineAutomate.started()) {
                            multilineStringValue.append(curLine, i, curLine.length());
                            if (multilineStringStartLine == -1) {
                                multilineStringStartLine = curLineNum;
                                multilineStringStartColumn = i + 1;
                            }
                            isMatched = true;
                            i = curLine.length();
                            break;
                        }
                    } else {
                        Automate automate = new Automate((StateMachine) patternPair.getFirst());
                        Pair<Integer, Integer> matchPos = automate.match(curLine, i);
                        if (matchPos.getFirst() != null & matchPos.getSecond() != null) {
                            String matchedText = curLine.substring(matchPos.getFirst(), matchPos.getSecond());
                            i = matchPos.getSecond();
                            isMatched = true;
                            Location begin = new Location(curLineNum, matchPos.getFirst() + 1);
                            if (patternPair.getSecond() == TokenName.IDENTIFIER) {
                                if (Patterns.isKeyword(matchedText)) {
                                    tokens.add(new Token(TokenName.KEYWORD, matchedText, begin));
                                } else if (Patterns.isDataType(matchedText)) {
                                    tokens.add(new Token(TokenName.DATA_TYPE, matchedText, begin));
                                } else {
                                    tokens.add(new Token(TokenName.IDENTIFIER, matchedText, begin));
                                }
                            } else {
                                tokens.add(new Token((TokenName) patternPair.getSecond(), matchedText, begin));
                            }
                            break;
                        }
                    }
                }
                if (!isMatched) {
                    Location location = new Location(curLineNum, i + 1);
                    tokens.add(new Token(TokenName.ERROR_TOKEN, String.valueOf(curLine.charAt(i)), location));
                    ++i;
                }
            }
            // tokens.add(new Token(TokenName.NEW_LINE, null, new Location(curLineNum, line.length() + 1)));
            line = reader.readLine();
            curLineNum++;
        }

        if (((MultilineAutomate) Patterns.patterns[0].getFirst()).started()) {
            tokens.add(new Token(TokenName.NOT_ENDED_MULTILINE_STRING_ERROR,
                    multilineStringValue.toString(), new Location(multilineStringStartLine, multilineStringStartColumn)));
        }

        reader.close();

        return tokens;
    }
}

package com.univ.lexer;

import com.univ.automate.StateMachineFactory;
import com.univ.helper.Pair;
import com.univ.lexer.token.TokenName;

public class Patterns {
    public static final Pair[] patterns = new Pair[]{
            new Pair("#.*(\\r|\\n|\\r\\n|$)", TokenName.COMMENT),
            new Pair(StateMachineFactory.comparisonOperatorStateMachine(), TokenName.COMPARISON_OPERATOR),
            new Pair(StateMachineFactory.operatorStateMachine(), TokenName.OPERATOR),
            new Pair(StateMachineFactory.separatorStateMachine(), TokenName.SEPARATOR),
            new Pair(StateMachineFactory.bracketStateMachine(), TokenName.BRACKET),
            new Pair(StateMachineFactory.dotStateMachine(), TokenName.DOT),
            new Pair(StateMachineFactory.numberStateMachine(), TokenName.NUMBER),
            new Pair(StateMachineFactory.identifierStateMachine(), TokenName.IDENTIFIER),
            new Pair(StateMachineFactory.doubleQuoteStringStateMachine(), TokenName.STRING),
            new Pair(StateMachineFactory.singleQuoteStringStateMachine(), TokenName.STRING),
    };
    // https://+www.geeksforgeeks.org/python-keywords-and-identifiers/
    public static final String[] keywords = {"import", "as", "class", "def", "pass", "if", "elif", "else", "try", "except", "throw",
            "for", "while", "in", "is", "await", "None", "raise", "True", "False", "return", "and", "or", "lambda", "break", "continue", "from",
            "assert", "with", "not", "async", "yield", "global", "del"};

    public static final String[] dataTypes = {"int", "str", "bool", "float", "complex"};
}

package com.univ.lexer;

import com.univ.automate.StateMachineFactory;
import com.univ.helper.Pair;
import com.univ.lexer.token.TokenName;

public class Patterns {
    public static final Pair[] patterns = new Pair[]{
            new Pair("#.*(\\r|\\n|\\r\\n|$)", TokenName.COMMENT),
            new Pair(StateMachineFactory.operatorStateMachine(), TokenName.KEYWORD),
            new Pair(StateMachineFactory.newLineStateMachine(), TokenName.NEW_LINE)
    };
}

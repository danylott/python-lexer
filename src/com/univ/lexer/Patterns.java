package com.univ.lexer;

import com.univ.automate.StateMachineFactory;
import com.univ.helper.Pair;
import com.univ.lexer.token.TokenName;

public class Patterns {
    public static final Pair[] patterns = new Pair[]{
            new Pair("#.*(\\r|\\n|\\r\\n|$)", TokenName.COMMENT),
            new Pair(StateMachineFactory.comparisonOperatorStateMachine(),TokenName.COMPARISON_OPERATOR),
            new Pair(StateMachineFactory.operatorStateMachine(),TokenName.OPERATOR),
            new Pair(StateMachineFactory.separatorStateMachine(),TokenName.SEPARATOR),
            new Pair(StateMachineFactory.bracketStateMachine(),TokenName.BRACKET),
            new Pair(StateMachineFactory.dotStateMachine(),TokenName.DOT),
            new Pair(StateMachineFactory.numberStateMachine(),TokenName.NUMBER)
    };
}

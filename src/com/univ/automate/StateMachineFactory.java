package com.univ.automate;

public class StateMachineFactory {
    public static StateMachine operatorStateMachine() {
        State initial = new State(false);
        State plus = new State(true);
        State minus = new State(true);
        State mult = new State(true);
        State pow = new State(true);
        State div = new State(true);
        State amp = new State(true);
        State xor = new State(true);
        State line = new State(true);
        State eq = new State(true);

        plus.addTransition('=', eq);
        minus.addTransition('=', eq);
        mult.addTransition('=', eq);
        pow.addTransition('=', eq);
        div.addTransition('=', eq);
        amp.addTransition('=', eq);
        xor.addTransition('=', eq);
        line.addTransition('=', eq);

        mult.addTransition('*', pow);

        initial.addTransition('+', plus);
        initial.addTransition('-', minus);
        initial.addTransition('*', mult);
        initial.addTransition('/', div);
        initial.addTransition('&', amp);
        initial.addTransition('^', xor);
        initial.addTransition('|', line);
        initial.addTransition('=', eq);

        return new StateMachine(initial);
    }

    public static StateMachine newLineStateMachine() {
        State initial = new State(false);
        State newLine = new State(true);
        initial.addTransition('\n', newLine);
        return new StateMachine(initial);
    }

    public static StateMachine comparisonOperatorStateMachine() {
        State initial = new State(false);
        State lt = new State(true);
        State gt = new State(true);
        State nt = new State(true);
        State eq = new State(false);
        State tw_eq = new State(true);

        lt.addTransition('=', eq);
        gt.addTransition('=', eq);
        nt.addTransition('=', eq);
        eq.addTransition('=', tw_eq);

        initial.addTransition('<', lt);
        initial.addTransition('>', gt);
        initial.addTransition('!', nt);
        initial.addTransition('=', eq);

        return new StateMachine(initial);
    }

    public static StateMachine bracketStateMachine() {
        State initial = new State(false);
        State lb = new State(true);
        State rb = new State(true);
        State lfb = new State(true);
        State rfb = new State(true);
        State lsb = new State(true);
        State rsb = new State(true);

        initial.addTransition('(', lb);
        initial.addTransition(')', rb);
        initial.addTransition('{', lfb);
        initial.addTransition('}', rfb);
        initial.addTransition('[', lsb);
        initial.addTransition(']', rsb);

        return new StateMachine(initial);
    }

    public static StateMachine separatorStateMachine() {
        State initial = new State(false);
        State tp = new State(true);
        State c = new State(true);
        State sem = new State(true);

        initial.addTransition(':', tp);
        initial.addTransition(',', c);
        initial.addTransition(';', sem);

        return new StateMachine(initial);
    }
}

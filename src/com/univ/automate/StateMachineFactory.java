package com.univ.automate;

public class StateMachineFactory {
    public static StateMachine operatorStateMachine() {
        State initial = new State(false);
        State plus = new State(true);
        State minus = new State(true);
        State mult = new State(true);
        State pow = new State(true);
        State div = new State(true);

        mult.addTransition('*', pow);

        initial.addTransition('+', plus);
        initial.addTransition('-', minus);
        initial.addTransition('*', mult);
        initial.addTransition('/', div);

        return new StateMachine(initial);
    }

    public static StateMachine newLineStateMachine() {
        State initial = new State(false);
        State newLine = new State(true);
        initial.addTransition('\n', newLine);
        return new StateMachine(initial);
    }
}

package com.univ.automate;

public class StateMachine {
    private State currentState;

    public StateMachine(State initialState) {
        currentState = initialState;
    }

    public State switchState(char symbol) {
        if (currentState.isPossibleTransitionBy(symbol)) {
            currentState = currentState.getNextStateByTransition(symbol);
            return currentState;
        }
        return null;
    }

    public boolean canStop() {
        return currentState.getIsFinal();
    }
}

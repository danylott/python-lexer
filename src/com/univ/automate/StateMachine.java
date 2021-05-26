package com.univ.automate;

public class StateMachine {
    private State initialState;
    private State currentState;

    public StateMachine(State initialState) {
        this.initialState = initialState;
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

    public void reset() {
        currentState = initialState;
    }
}

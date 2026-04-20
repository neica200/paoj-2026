package com.pao.laboratory07.exercise1;
import com.pao.laboratory07.exercise1.exceptions.*;
import java.util.Stack;

public class Order {
    private OrderState state;
    private Stack<OrderState> history = new Stack<>();

    public Order(OrderState initialState) {
        this.state = initialState;
    }

    public OrderState getState() {
        return state;
    }

    public void nextState() throws OrderIsAlreadyFinalException {
        if (state.isFinal()) {
            throw new OrderIsAlreadyFinalException();
        }

        history.push(state);

        switch (state) {
            case PLACED -> state = OrderState.PROCESSED;
            case PROCESSED -> state = OrderState.SHIPPED;
            case SHIPPED -> state = OrderState.DELIVERED;
        }
    }

    public void cancel() throws CannotCancelFinalOrderException {
        if (state.isFinal()) {
            throw new CannotCancelFinalOrderException();
        }

        history.push(state);
        state = OrderState.CANCELED;
    }

    public void undoState() throws CannotRevertInitialOrderStateException {
        if (history.isEmpty()) {
            throw new CannotRevertInitialOrderStateException();
        }

        state = history.pop();
    }


}

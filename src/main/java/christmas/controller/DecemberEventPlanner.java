package christmas.controller;

import christmas.model.EventResult;
import christmas.model.Order;

public interface DecemberEventPlanner {

    EventResult calculateEventResult(Order order, int visitDate);
}
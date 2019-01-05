package oop.assignment.restaurant.observers;

import oop.assignment.linkedlist.LinkList;
import oop.assignment.restaurant.objects.Order;
import oop.assignment.restaurant.observables.OrderList;

public class OrderListObserver implements IObserver {
    OrderList orderList;
    double total;

    public OrderListObserver(OrderList orderList){
        this.orderList = orderList;
        total = 0;
    }

    private double calculateTotal(){
        LinkList<Order> orderLinkList = orderList.getOrderLinkList();
        double newTotal = 0;
        for(int i = 0; i < orderLinkList.size(); i++){
            newTotal += orderLinkList.get(i).calculatePrice();
        }
        return newTotal;
    }

    public double getTotal() {
        return total;
    }

    @Override
    public void update() {
        total = calculateTotal();
    }
}

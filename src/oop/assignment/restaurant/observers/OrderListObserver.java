package oop.assignment.restaurant.observers;

import oop.assignment.restaurant.observables.OrderList;

public class OrderListObserver implements IObserver {
    private OrderList orderList;
    private double total;

    public OrderListObserver(OrderList orderList){
        this.orderList = orderList;
        total = 0;

        orderList.register(this);
    }

    private double calculateTotal(){
        double total = 0;
        for(int i = 0; i < orderList.size(); i++){
            total += orderList.getOrder(i).calculatePrice();
        }
        return total;
    }

    public double getTotal() {
        return total;
    }

    @Override
    public void update() {
        this.total = calculateTotal();
    }
}

package oop.assignment.restaurant.objects;

import oop.assignment.restaurant.exceptions.NonExistentOrderTypeException;

public enum OrderType {
    DELIVERY("delivery"),
    TAKEAWAY("takeaway"),
    DELIVERYANDTAKEAWAY("both");

    private final String text;

    OrderType(String text){
        this.text = text;
    }

    @Override
    public String toString() {
        return text;
    }

    public boolean isDelivery(){
        return this == OrderType.DELIVERY || this == OrderType.DELIVERYANDTAKEAWAY;
    }

    public boolean isTakeaway(){
        return this == OrderType.TAKEAWAY || this == OrderType.DELIVERYANDTAKEAWAY;
    }

    public static boolean areCompatible(OrderType orderType1, OrderType orderType2){
        return (orderType1.isDelivery() && orderType2.isDelivery())
            || (orderType1.isTakeaway() && orderType2.isTakeaway());
    }

    public static OrderType convertString(String orderTypeText){
        for(OrderType orderType: OrderType.values()){
            if(orderType.toString().equals(orderTypeText)){
                return orderType;
            }
        }
        throw new NonExistentOrderTypeException(orderTypeText);
    }
}

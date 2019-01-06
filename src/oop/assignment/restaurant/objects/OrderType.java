package oop.assignment.restaurant.objects;

import oop.assignment.restaurant.exceptions.NonExistentOrderTypeException;

public enum OrderType {
    DELIVERY,
    TAKEAWAY,
    BOTH;

    public boolean isDelivery(){
        return this == OrderType.DELIVERY || this == OrderType.BOTH;
    }

    public boolean isTakeaway(){
        return this == OrderType.TAKEAWAY || this == OrderType.BOTH;
    }

    public static boolean areCompatible(OrderType orderType1, OrderType orderType2){
        return (orderType1.isDelivery() && orderType2.isDelivery())
            || (orderType1.isTakeaway() && orderType2.isTakeaway());
    }

    public static OrderType convertString(String orderTypeText){
        String orderTypeTextUpper = orderTypeText.toUpperCase();
        for(OrderType orderType: OrderType.values()){
            if(orderType.name().equals(orderTypeTextUpper)){
                return orderType;
            }
        }
        throw new NonExistentOrderTypeException(orderTypeText);
    }
}

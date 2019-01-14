package oop.assignment.restaurant.objects;

import oop.assignment.restaurant.exceptions.NonExistentOrderTypeException;

/**
 * The types of order offered by restaurants in this application, namely DELIVERY, TAKEAWAY, and BOTH
 */
public enum OrderType {
    DELIVERY,
    TAKEAWAY,
    BOTH;

    /**
     * @return true if this is a DELIVERY or BOTH
     */
    public boolean isDelivery(){
        return this == OrderType.DELIVERY || this == OrderType.BOTH;
    }

    /**
     * @return true if this is a TAKEAWAY or BOTH
     */
    public boolean isTakeaway(){
        return this == OrderType.TAKEAWAY || this == OrderType.BOTH;
    }

    /**
     * Checks if the operands are of compatible types,
     * that is, both are delivery or both are takeaway.
     *
     * @param orderType1 the first operand
     * @param orderType2 the second operand
     * @return true if the order types are compatible
     */
    public static boolean areCompatible(OrderType orderType1, OrderType orderType2){
        return (orderType1.isDelivery() && orderType2.isDelivery())
            || (orderType1.isTakeaway() && orderType2.isTakeaway());
    }

    /**
     * Checks if the string passed is an order type and returns it if it is,
     * throwing a NonExistentOrderTypeException otherwise.
     *
     * @param orderTypeText the string to convert to an OrderType
     * @return the OrderType version of the string
     */
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

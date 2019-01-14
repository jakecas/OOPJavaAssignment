package oop.assignment.linkedlist.exceptions;

public class NullHeadException extends RuntimeException {
    public NullHeadException(){
        super("Head element is null, list is empty..");
    }

}

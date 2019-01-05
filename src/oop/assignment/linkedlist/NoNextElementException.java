package oop.assignment.linkedlist;

public class NoNextElementException extends RuntimeException{
    public NoNextElementException(){
        super("Reached end of list, there is no next element.");
    }
}

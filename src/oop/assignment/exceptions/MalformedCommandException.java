package oop.assignment.exceptions;

public class MalformedCommandException extends RuntimeException {
    public MalformedCommandException(String reason){
        super("Malformed command: " + reason);
    }
}

package oop.assignment.exceptions;

public class InvalidFormatException extends MalformedCommandException {
    public InvalidFormatException(String reason){
        super("Invalid format: " + reason);
    }
}

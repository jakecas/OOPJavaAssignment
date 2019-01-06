package oop.assignment.exceptions;

public class MisplacedCommandException extends MalformedCommandException {
    public MisplacedCommandException(String reason){
        super("Invalid state: " + reason);
    }
}

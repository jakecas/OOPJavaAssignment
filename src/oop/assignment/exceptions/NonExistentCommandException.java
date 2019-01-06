package oop.assignment.exceptions;

public class NonExistentCommandException extends MalformedCommandException {
    public NonExistentCommandException(String command){
        super ("Command '" + command + "' does not exist.");
    }
}

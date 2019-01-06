package oop.assignment.exceptions;

public class ArgumentNumberException extends MalformedCommandException {
    public ArgumentNumberException(String reason, int argc){
        super(reason + argc);
    }
}

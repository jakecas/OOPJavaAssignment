package oop.assignment.exceptions;

/**
 * The exception thrown when an invalid format is used for an argument to a command.
 */
public class InvalidFormatException extends MalformedCommandException {
    /**
     * Passes the reason prepended with "Invalid format: " to the parent constructor
     *
     * @param reason the reason for the invalid format
     */
    public InvalidFormatException(String reason){
        super("Invalid format: " + reason);
    }
}

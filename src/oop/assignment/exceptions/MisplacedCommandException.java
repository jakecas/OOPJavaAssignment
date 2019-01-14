package oop.assignment.exceptions;

/**
 * The exception thrown when a command is used in an incorrect order.
 */
public class MisplacedCommandException extends MalformedCommandException {
    /**
     * Passes the reason prepended by "Invalid state: " to the parent constructor.
     *
     * @param reason the reason the command is out of order
     */
    public MisplacedCommandException(String reason){
        super("Invalid state: " + reason);
    }
}

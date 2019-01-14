package oop.assignment.exceptions;

/**
 * The parent exception of all exceptions that describe invalid commands,
 * used for the purposes of organizing them all into one umbrella exception.
 *
 * It extends RuntimeException rather than simply Exception.
 * as all exceptions in this package were designed to be unchecked exceptions.
 * This is due to the errors described being user-dependent, and thus unpredictable.
 */
public class MalformedCommandException extends RuntimeException {
    /**
     * Passes the message to the parent constructor.
     *
     * @param reason the message the exception will give
     */
    public MalformedCommandException(String reason){
        super("Malformed command: " + reason);
    }
}

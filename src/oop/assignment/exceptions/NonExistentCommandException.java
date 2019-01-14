package oop.assignment.exceptions;

/**
 * The exception thrown when a non existent command is used.
 */
public class NonExistentCommandException extends MalformedCommandException {
    /**
     * Passes a built string to the parent constructor.
     *
     * @param command the non-existent command used
     */
    public NonExistentCommandException(String command){
        super ("Command '" + command + "' does not exist.");
    }
}

package oop.assignment.exceptions;

/**
 * The exception thrown when an invalid number of arguments is passed with a command.
 */
public class ArgumentNumberException extends MalformedCommandException {
    /**
     * Passes the reason and argc as a string to the parent constructor.
     *
     * @param reason the command that was attempted
     * @param argc the number of arguments
     */
    public ArgumentNumberException(String reason, int argc){
        super(reason + argc);
    }
}

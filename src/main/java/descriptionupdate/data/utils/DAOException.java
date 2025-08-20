package descriptionupdate.data.utils;

import java.io.Serial;

// This is a runtime exception we define to wrap all the exceptions coming from
// the DAO objects we're going to define.
//
// This way we won't have `SQLException`s bubbling up in all other functions.
//
public final class DAOException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * Constructs a new DAOException with no detail message.
     * This constructor is used when the exception is thrown without a specific message.
     */
    public DAOException(String message) {
        super(message);
    }

    /**
     * Constructs a new DAOException with the specified cause.
     * This constructor is used when the exception is thrown due to another exception.
     *
     * @param cause the cause of the exception
     */
    public DAOException(Throwable cause) {
        super(cause);
    }

    /**
     * Constructs a new DAOException with the specified detail message and cause.
     * This constructor is used when the exception is thrown with both a specific message and a cause.
     *
     * @param message the detail message
     * @param cause   the cause of the exception
     */
    public DAOException(String message, Throwable cause) {
        super(message, cause);
    }
}

package exception;

public class ExameException extends Exception {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ExameException(String message) {
        super(message);
    }

    public ExameException(String message, Throwable cause) {
        super(message, cause);
    }
}

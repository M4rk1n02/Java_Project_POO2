package exception;

public class PacienteException extends Exception {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public PacienteException(String message) {
        super(message);
    }

    public PacienteException(String message, Throwable cause) {
        super(message, cause);
    }
}
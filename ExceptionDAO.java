package Exception;


public class ExceptionDAO extends Exception {
	private static final long serialVersionUID = 1L;

	// Construtores
    public ExceptionDAO() {
        super();
    }

    public ExceptionDAO(String message) {
        super(message);
    }

    public ExceptionDAO(String message, Throwable cause) {
        super(message, cause);
    }

    public ExceptionDAO(Throwable cause) {
        super(cause);
    }
}



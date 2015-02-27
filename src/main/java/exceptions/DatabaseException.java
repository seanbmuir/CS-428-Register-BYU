package exceptions;

/**
 * Created by josephhoehne on 2/27/15.
 */
public class DatabaseException extends RuntimeException
{
	public DatabaseException() {
		super();
	}

	public DatabaseException(String message) {
		super(message);
	}

	public DatabaseException(String message, Exception cause) {
		super(message, cause);
	}
}

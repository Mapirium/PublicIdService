package ch.mapirium.server.publicidservice.exception;

/**
 * Diese Exception wird ausgelöst, wenn kein eindeutiger Schlüssel erstellt werden konnte
 */
public class UniqueKeyGenerationFailedException extends RuntimeException {
    public UniqueKeyGenerationFailedException() {
    }

    public UniqueKeyGenerationFailedException(String message) {
        super(message);
    }

    public UniqueKeyGenerationFailedException(String message, Throwable cause) {
        super(message, cause);
    }
}

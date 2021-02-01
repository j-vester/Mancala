package mancala.domain;

@SuppressWarnings("serial")
public class MancalaException extends RuntimeException {
    
    public MancalaException(String errorMessage) {
        super(errorMessage);
    }

    public MancalaException(String errorMessage, Throwable err) {
        super(errorMessage, err);
    }
}

package edu.kpi.hotel.model.exception;

public class SignInFailedException extends HotelException {
    public SignInFailedException() {
    }

    public SignInFailedException(String message) {
        super(message);
    }

    public SignInFailedException(String message, Throwable cause) {
        super(message, cause);
    }

    public SignInFailedException(Throwable cause) {
        super(cause);
    }

}

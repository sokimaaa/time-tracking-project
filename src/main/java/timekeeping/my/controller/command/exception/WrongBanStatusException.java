package timekeeping.my.controller.command.exception;

public class WrongBanStatusException extends Exception {
    public WrongBanStatusException(String message) {
        super(message);
    }
}

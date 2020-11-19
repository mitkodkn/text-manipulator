package bg.tusofia;

public class InvalidOptionException extends Exception {
    @Override
    public String getMessage() {
        return "You have selected an invalid option.";
    }
}

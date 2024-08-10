package main.utils.Exception;

public class DataNotFoundException extends Exception {
    public DataNotFoundException() {
        super();
    }

    public DataNotFoundException(String msg) {
        super(msg);
    }
}
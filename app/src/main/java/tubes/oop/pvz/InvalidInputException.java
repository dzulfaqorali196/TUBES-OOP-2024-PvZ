package tubes.oop.pvz;

class InvalidInputMainException extends java.lang.Exception {
    private String message;

    public InvalidInputMainException(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
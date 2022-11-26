package io.armazenadorarquivos.rest.messages;

public class ResponseMessage {
    private String message;
    
    public ResponseMessage(StringBuilder message) {
        this.message = String.valueOf(message);
    }
    
    public String getMessage() {
        return message;
    }
    
    public void setMessage(String message) {
        this.message = message;
    }
}

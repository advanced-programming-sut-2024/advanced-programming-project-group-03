package ir.ap.probending.Model;

public class Message {
    private String username;
    private String message;
    private String time;
    private boolean isReplied = false;
    private String replyMessage;
    private String replyUsername;

    public Message(String username, String message, String time) {
        this.username = username;
        this.message = message;
        this.time = time;
    }

    public Message(String username, String message, String time, Message message1) {
        this.username = username;
        this.message = message;
        this.time = time;
        isReplied = true;
        replyMessage = message1.message;
        replyUsername = message1.username;
    }


    public String getUsername() {
        return username;
    }

    public String getMessage() {
        return message;
    }

    public String getTime() {
        return time;
    }

    public boolean isReplied() {
        return isReplied;
    }

    public String getReplyMessage() {
        return replyMessage;
    }

    public String getReplyUsername() {
        return replyUsername;
    }
}

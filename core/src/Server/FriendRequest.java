package Server;

import ir.ap.probending.Model.User;

public class FriendRequest {
    static int counter = 0;
    private String state;
    private User sender;
    private User receiver;
    private int id;
    public FriendRequest(User sender, User receiver) {
        this.sender = sender;
        this.receiver = receiver;
        state = "pending";
        id = counter++;
        receiver.addFriendRequest(this);
    }
    public String getState() {
        return state;
    }
    public void setState(String state) {
        this.state = state;
    }
    public User getSender() {
        return sender;
    }
    public User getReceiver() {
        return receiver;
    }
    public int getId() {
        return id;
    }
}

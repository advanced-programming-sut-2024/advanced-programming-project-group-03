package Server;

import ir.ap.probending.Model.User;

public class FriendRequest {
    static int counter = 0;
    private String state;
    private String receiver;
    private int id;
    public FriendRequest(String receiver) {
        this.receiver = receiver;
        state = "pending";
        id = counter++;
    }
    public String getState() {
        return state;
    }
    public void setState(String state) {
        this.state = state;
    }
    public String  getReceiver() {
        return receiver;
    }
    public int getId() {
        return id;
    }
}

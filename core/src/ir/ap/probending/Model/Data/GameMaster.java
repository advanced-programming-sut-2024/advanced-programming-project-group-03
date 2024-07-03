package ir.ap.probending.Model.Data;

import com.google.gson.Gson;
import ir.ap.probending.Model.User;
import ir.ap.probending.ProBending;

public class GameMaster {
    private static GameMaster gameMaster = new GameMaster();
    private User loggedInUser1;
    private User loggedInUser2;
    private final User guestUser1 = new User("Guest1", "Guest1", "Guest1", "Guest1");
    private final User guestUser2 = new User("Guest2", "Guest2", "Guest2", "Guest2");

    private GameMaster() {
        loggedInUser1 = guestUser1;
        loggedInUser2 = guestUser2;
    }
    //getters and setters

    public User getLoggedInUser1() {
        String response = ProBending.client.communicate("getUser");
        if (response.equals("User not found")) {
            loggedInUser1 = guestUser1;
            return loggedInUser1;
        }
        Gson gson = new Gson();
        loggedInUser1 = gson.fromJson(ProBending.client.communicate("getUser"), User.class);
        return loggedInUser1;
    }

    public void setLoggedInUser1(User loggedInUser1) {
        this.loggedInUser1 = loggedInUser1;
    }

    public User getLoggedInUser2() {
        return loggedInUser2;
    }

    public void setLoggedInUser2(User loggedInUser2) {
        this.loggedInUser2 = loggedInUser2;
    }

    public static GameMaster getGameMaster() {
        return gameMaster;
    }

    public User getGuestUser1() {
        return guestUser1;
    }

    public User getGuestUser2() {
        return guestUser2;
    }
}

package ir.ap.probending.Model.Data;

import ir.ap.probending.Model.User;

import java.util.List;

public class GameMaster {
    private static GameMaster gameMaster = new GameMaster();
    private User loggedInUser1;
    private User loggedInUser2;
    private final User guestUser1 = new User("Guest1", "Guest1", "Guest1", "Guest1");
    private final User guestUser2 = new User("Guest2", "Guest2", "Guest2", "Guest2");

    private GameMaster() {
        loggedInUser1 = guestUser1;
        loggedInUser2 = guestUser2;

        try {
            List<User> users = SaveUser.loadUsers();
            if (users != null) {
                for (User user : users) {
                    if (user.isRememberMe()) {
                        loggedInUser1 = user;
                        break;
                    }
                }
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    //getters and setters

    public User getLoggedInUser1() {
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

    public static void setNewGameMaster(){
        gameMaster = new GameMaster();
    }
}

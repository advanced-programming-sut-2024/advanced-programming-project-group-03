package ir.ap.probending.Control;

import ir.ap.probending.Model.Data.GameHistory;
import ir.ap.probending.Model.Data.GameMaster;
import ir.ap.probending.Model.User;

import java.util.ArrayList;
import java.util.Collections;

public class MenuController {
    private static MenuController menuController;


    private MenuController(){

    }

    public User submitUsernameForForgetPassword(ArrayList<User> users , String username){
        for (User user : users) {
            if (user.getUsername().equals(username)) {
                return user;
            }
        }
        return null;
    }

    public ArrayList<User> sortUsersByScore(ArrayList<User> users){
        for (int i = 0; i < users.size(); i++) {
            for (int j = 0; j < users.size(); j++) {
                if (users.get(i).getScore() > users.get(j).getScore()) {
                    User temp = users.get(i);
                    users.set(i, users.get(j));
                    users.set(j, temp);
                }
            }
        }
        return users;
    }

    public ArrayList<String> getDates(ArrayList<User> users){
        ArrayList<String> dates = new ArrayList<>();
        for (User user : users){
            if (GameMaster.getGameMaster().getLoggedInUser1().getUsername() != null && user.getUsername().equals(GameMaster.getGameMaster().getLoggedInUser1().getUsername())){
                for (GameHistory gameHistory : user.getGameHistories()){
                    dates.add(gameHistory.getDate());
                }
                break;
            }
        }
        Collections.sort(dates);
        return dates;
    }

    //getters and setters

    public static MenuController getMenu(){
        if(menuController == null){
            menuController = new MenuController();
        }
        return menuController;
    }

    public static void setMenu(MenuController menuController){
        MenuController.menuController = menuController;
    }
}

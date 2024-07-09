package ir.ap.probending.Control;

import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import ir.ap.probending.Model.Data.SecurityQuestions;
import ir.ap.probending.Model.User;

import java.util.ArrayList;

public class Menu {
    private static Menu menu ;


    private Menu(){

    }

    public User submitUsernameForForgetPassword(ArrayList<User> users , String username){
        for (User user : users) {
            if (user.getUsername().equals(username)) {
                return user;
            }
        }
        return null;
    }

    //getters and setters

    public static Menu getMenu(){
        if(menu == null){
            menu = new Menu();
        }
        return menu;
    }

    public static void setMenu(Menu menu){
        Menu.menu = menu;
    }
}

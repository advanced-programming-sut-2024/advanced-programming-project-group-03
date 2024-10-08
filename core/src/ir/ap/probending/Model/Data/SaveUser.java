package ir.ap.probending.Model.Data;

import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonWriter;
import ir.ap.probending.Model.User;

import java.util.ArrayList;
import java.util.List;

public class SaveUser {
    public static void saveUser(User newUser){
        try {
            Json json = new Json();
            json.setOutputType(JsonWriter.OutputType.json);

            FileHandle file = new FileHandle(GameAssetManager.getGameAssetManager().getSaveDataUserLocation());

            String loadedUser = file.readString();
            List<User> users = json.fromJson(ArrayList.class, User.class , loadedUser);

            try {
                users.add(newUser);
            }
            catch (NullPointerException e){
                users = new ArrayList<>();
                users.add(newUser);
            }

            String usersJson = json.prettyPrint(users);
            file.writeString(usersJson, false);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static List<User> loadUsers(){
        Json json = new Json();
        json.setOutputType(JsonWriter.OutputType.json);

        FileHandle file = new FileHandle(GameAssetManager.getGameAssetManager().getSaveDataUserLocation());

        String loadedUser = file.readString();
        try {
            List<User> users = json.fromJson(ArrayList.class, User.class, loadedUser);
            return users;
        }
        catch (Exception e){
            List<User> users = new ArrayList<>();
            String usersJson = json.prettyPrint(users);
            file.writeString(usersJson, false);
        }
        return new ArrayList<>();
    }

    public static void updateUser(User user){
        try {
            Json json = new Json();
            json.setOutputType(JsonWriter.OutputType.json);

            FileHandle file = new FileHandle(GameAssetManager.getGameAssetManager().getSaveDataUserLocation());

            String loadedUser = file.readString();
            List<User> users = json.fromJson(ArrayList.class, User.class , loadedUser);

            for (int i = 0; i < users.size(); i++) {
                if (users.get(i).getUsername().equals(user.getUsername())){
                    users.set(i, user);
                    break;
                }
            }

            String usersJson = json.prettyPrint(users);
            file.writeString(usersJson, false);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}

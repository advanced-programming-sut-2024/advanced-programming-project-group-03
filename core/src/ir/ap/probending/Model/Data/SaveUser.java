package ir.ap.probending.Model.Data;

import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.JsonWriter;
import ir.ap.probending.Model.User;

import java.util.ArrayList;
import java.util.List;

public class SaveUser {
    public static void saveUser(User newUser){
        Json json = new Json();
        json.setOutputType(JsonWriter.OutputType.json);

        FileHandle file = new FileHandle(GameAssetManager.getInstance().getSaveDataUserLocation());

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

    public static List<User> loadUsers(){
        Json json = new Json();
        json.setOutputType(JsonWriter.OutputType.json);

        FileHandle file = new FileHandle(GameAssetManager.getInstance().getSaveDataUserLocation());

        String loadedUser = file.readString();
        ArrayList users = json.fromJson(ArrayList.class, User.class , loadedUser);

        return users;
    }
}

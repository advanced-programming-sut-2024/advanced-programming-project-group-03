package ir.ap.probending.Model.Data;

public class RandomPasswordGenerator {
    public static String generatePassword() {
        String password = "";
        for (int i = 0; i < 8; i++) {
            password += (char) (Math.random() * 26 + 97);
        }
        for (int i = 0; i < 8; i++) {
            password += (char) (Math.random() * 26 + 65);
        }
        password += "!69";
        return password;
    }
}

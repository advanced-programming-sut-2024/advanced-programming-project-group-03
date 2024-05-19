package ir.ap.probending.Model;

public class User {
    private String username;
    private String password;
    private String email;
    private String question;
    private String answer;

    public User(String username, String password, String email, String question, String answer) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.question = question;
        this.answer = answer;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public String getQuestion() {
        return question;
    }

    public String getAnswer() {
        return answer;
    }
}

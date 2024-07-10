package Server;

import com.google.gson.Gson;
import okhttp3.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class MailSender {

    private static final String API_TOKEN = "b7b46c0ae9de573c74a5e24e2becda90";

    public static void sendEmail(String recipientEmail, String subject, String text) throws IOException {
        OkHttpClient client = new OkHttpClient().newBuilder().build();
        MediaType mediaType = MediaType.parse("application/json");

        // Construct JSON body using a Map
        Map<String, Object> jsonBody = new HashMap<>();
        Map<String, String> from = new HashMap<>();
        from.put("email", "mailtrap@demomailtrap.com");
        from.put("name", "Mailtrap Test");
        jsonBody.put("from", from);
        Map<String, String> to = new HashMap<>();
        to.put("email", recipientEmail);
        jsonBody.put("to", new Map[]{to});
        jsonBody.put("subject", subject);
        jsonBody.put("text", text);
        jsonBody.put("category", "Integration Test");

        // Convert the Map to JSON string using Gson
        Gson gson = new Gson();
        String jsonString = gson.toJson(jsonBody);

        RequestBody body = RequestBody.create(mediaType, jsonString);
        Request request = new Request.Builder()
                .url("https://send.api.mailtrap.io/api/send")
                .method("POST", body)
                .addHeader("Authorization", "Bearer " + API_TOKEN)
                .addHeader("Content-Type", "application/json")
                .build();
        Response response = client.newCall(request).execute();
        System.out.println("Response from Mailtrap API: " + response.body().string());
    }

    public static void main(String[] args) throws IOException {
        String recipientEmail = "probendingavatar@gmail.com";
        String subject = "You are awesome!";
        String text = "Congrats for sending test email with Mailtrap!";

        sendEmail(recipientEmail, subject, text);
    }
}
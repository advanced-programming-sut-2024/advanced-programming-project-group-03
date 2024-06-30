package Server;

import okhttp3.*;

import java.io.IOException;

public class MailSender {

    private static final String API_TOKEN = "beadc9803cac741eeb5df956d1964c15";

    public static void sendEmail(String recipientEmail, String subject, String text) throws IOException {
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        MediaType mediaType = MediaType.parse("application/json");
        RequestBody body = RequestBody.create(mediaType, "{\"from\":{\"email\":\"mailtrap@demomailtrap.com\",\"name\":\"Mailtrap Test\"},\"to\":[{\"email\":\"" + recipientEmail + "\"}],\"subject\":\"" + subject + "\",\"text\":\"" + text + "\",\"category\":\"Integration Test\"}");
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
        String recipientEmail = "hekmatinima@gmail.com";
        String subject = "You are awesome!";
        String text = "Congrats for sending test email with Mailtrap!";

        sendEmail(recipientEmail, subject, text);
    }
}
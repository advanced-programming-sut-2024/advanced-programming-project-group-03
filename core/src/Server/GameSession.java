package Server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class GameSession extends Thread {
    private Socket player1Socket;
    private Socket player2Socket;
    private DataInputStream player1Input;
    private DataOutputStream player1Output;
    private DataInputStream player2Input;
    private DataOutputStream player2Output;

    public GameSession(Socket player1Socket, Socket player2Socket) throws IOException {
        this.player1Socket = player1Socket;
        this.player2Socket = player2Socket;
        this.player1Input = new DataInputStream(player1Socket.getInputStream());
        this.player1Output = new DataOutputStream(player1Socket.getOutputStream());
        this.player2Input = new DataInputStream(player2Socket.getInputStream());
        this.player2Output = new DataOutputStream(player2Socket.getOutputStream());
    }

    @Override
    public void run() {
        try {
            while (true) {
                if (player1Input.available() > 0) {
                    String messageFromPlayer1 = player1Input.readUTF();
                    player2Output.writeUTF(messageFromPlayer1);
                    if (messageFromPlayer1.equals("exitGameSession"))
                        break;
                    player2Output.flush();
                }

                if (player2Input.available() > 0) {
                    String messageFromPlayer2 = player2Input.readUTF();
                    if (messageFromPlayer2.equals("exitGameSession"))
                        break;
                    player1Output.writeUTF(messageFromPlayer2);
                    player1Output.flush();
                }

                Thread.sleep(100); // Sleep briefly to prevent busy waiting
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        } finally {
            try {
                player1Socket.close();
                player2Socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

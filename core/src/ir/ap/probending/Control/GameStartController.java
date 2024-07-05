package ir.ap.probending.Control;

import ir.ap.probending.Model.Data.GameMaster;
import ir.ap.probending.ProBending;

public class GameStartController extends Thread {

    @Override
    public void run() {
        while (true) {
            String response = ProBending.client.communicate("isUserInGame");
            if (response.equals("yes")) {
                ProBending.client.sendGameMessage(GameMaster.getGameMaster().getLoggedInUser1().getUsername());
            }
            try {
                sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}

package ir.ap.probending.Model;

public class GameBoard {
    private static Board player1Board;
    private static User player1;
    private static Board player2Board;
    private static User player2;

    public static Board getPlayer1Board() {
        if (player1Board == null)
            player1Board = new Board();
        return player1Board;
    }

    public static Board getPlayer2Board() {
        if (player2Board == null)
            player2Board = new Board();
        return player2Board;
    }

    public static void swapBoards() {
        Board temp = player1Board;
        player1Board = player2Board;
        player2Board = temp;

        User tempUser = player1;
        player1 = player2;
        player2 = tempUser;
    }

    public static User getPlayer1() {
        return player1;
    }

    public static void setPlayer1(User player1) {
        GameBoard.player1 = player1;
    }

    public static User getPlayer2() {
        return player2;
    }

    public static void setPlayer2(User player2) {
        GameBoard.player2 = player2;
    }
}

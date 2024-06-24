package ir.ap.probending.Model.Game;

public class GameBoard {
    private static Board player1Board;
    private static Player player1;
    private static Board player2Board;
    private static Player player2;

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

        Player tempUser = player1;
        player1 = player2;
        player2 = tempUser;
    }

    //getters and setters

    public static Player getPlayer1() {
        return player1;
    }

    public static void setPlayer1(Player player1) {
        GameBoard.player1 = player1;
    }

    public static Player getPlayer2() {
        return player2;
    }

    public static void setPlayer2(Player player2) {
        GameBoard.player2 = player2;
    }

    public static void setPlayer1Board(Board player1Board) {
        GameBoard.player1Board = player1Board;
    }

    public static void setPlayer2Board(Board player2Board) {
        GameBoard.player2Board = player2Board;
    }
}

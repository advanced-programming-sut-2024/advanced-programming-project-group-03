package ir.ap.probending.Model.Game;

public class GameBoard {
    private Board player1Board;
    private Player player1;
    private Board player2Board;
    private Player player2;

    public GameBoard(Player player1, Player player2 , Board player1Board, Board player2Board) {
        this.player1 = player1;
        this.player2 = player2;
        this.player1Board = player1Board;
        this.player2Board = player2Board;
    }

    public Board getPlayer1Board() {
        if (player1Board == null)
            player1Board = new Board();
        return player1Board;
    }

    public Board getPlayer2Board() {
        if (player2Board == null)
            player2Board = new Board();
        return player2Board;
    }

    public void swapBoards() {
        Board temp = player1Board;
        player1Board = player2Board;
        player2Board = temp;

        Player tempUser = player1;
        player1 = player2;
        player2 = tempUser;
    }

    //getters and setters

    public  Player getPlayer1() {
        return player1;
    }

    public  void setPlayer1(Player player1) {
        this.player1 = player1;
    }

    public  Player getPlayer2() {
        return player2;
    }

    public  void setPlayer2(Player player2) {
        this.player2 = player2;
    }

    public void setPlayer1Board(Board player1Board) {
        this.player1Board = player1Board;
    }

    public void setPlayer2Board(Board player2Board) {
        this.player2Board = player2Board;
    }
}

package connect4;

public class Connect4 {

    private final Board board;
    private final Player player1;
    private final Player player2;
    private boolean gameOver;
    private boolean player1Turn;

    public Connect4(Board board, Player player1, Player player2) {
        this.board = board;
        this.player1 = player1;
        this.player2 = player2;
        this.player1Turn = true;
        this.gameOver = false;
    }

    public Player play() {
        while (!gameOver) {
            System.out.println(board.displayBoard());
            if (player1Turn) {
                board.makeMove(player1.play(board), player1.getToken());
                gameOver = board.isSolved(player1.getToken());
            } else {
                board.makeMove(player2.play(board), player2.getToken());
                gameOver = board.isSolved(player2.getToken());
            }
            player1Turn = !player1Turn;
        }

        return player1Turn ? player2 : player1;
    }
}

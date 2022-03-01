package connect4;

public class GamePlay {
    public static void main(String[] args) {
        Connect4 game = new Connect4(
                new Board(6, 7),
                new HumanPlayer("foo", Token.RED),
                new RandomAIPlayer("bar", Token.YELLOW)
        );

        System.out.println(game.play().toString());
    }
}

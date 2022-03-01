package connect4;

import java.util.Random;

public class RandomAIPlayer implements Player {
    private final String name;
    private final Token token;
    private final Random rand;

    public RandomAIPlayer(String name, Token token) {
        this.name = name;
        this.token = token;
        rand = new Random();
    }

    @Override
    public Integer play(Board board) {
        int move = rand.nextInt(board.getCols());
        while (!board.isValidMove(move)) {
            move = rand.nextInt(board.getCols());
        }
        return move;
    }

    @Override
    public Token getToken() {
        return token;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "RandomAIPlayer{" +
                "name='" + name + '\'' +
                '}';
    }
}

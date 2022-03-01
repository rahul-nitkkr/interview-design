package connect4;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class HumanPlayer implements Player {
    private final String name;
    private final Token token;
    private final BufferedReader reader;

    public HumanPlayer(String name, Token token) {
        this.name = name;
        this.token = token;
        this.reader = new BufferedReader(new InputStreamReader(System.in));
    }

    @Override
    public Integer play(Board board) {
        String move = null;
        try {
            move = reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(String.format("Player : %s Move : %s", name, move));
        return Integer.parseInt(move);
    }

    @Override
    public Token getToken() {
        return token;
    }

    @Override
    public String toString() {
        return name;
    }
}

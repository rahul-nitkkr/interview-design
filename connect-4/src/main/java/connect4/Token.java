package connect4;

public enum Token {

    RED(1),
    YELLOW(2);

    private final Integer tokenValue;

    Token(Integer tokenValue) {
        this.tokenValue = tokenValue;
    }

    public Integer getTokenValue() {
        return tokenValue;
    }
}

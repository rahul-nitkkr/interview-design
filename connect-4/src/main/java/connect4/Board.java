package connect4;

public class Board {
    private static final int SENTINEL = 0;

    private final Integer rows;
    private final Integer cols;
    private final int[][] board;

    public Board(Integer rows, Integer cols) {
        this.rows = rows;
        this.cols = cols;
        this.board = new int[rows][cols];
    }

    public Integer getRows() {
        return rows;
    }

    public Integer getCols() {
        return cols;
    }

    public boolean isSolved(Token token) {
        int piece = token.getTokenValue();
        return solvedHorizontally(piece) || solvedVertically(piece) || solvedForwardDiagonal(piece) || solvedReverseDiagonal(piece);
    }

    public void makeMove(int col, Token token) {
        board[findAvailableRowForCol(col)][col] = token.getTokenValue();
    }

    public String displayBoard() {
        StringBuilder finalBoard = new StringBuilder();
        for (int i = 0; i < rows; i++) {
            StringBuilder thisRow = new StringBuilder();
            for (int j = 0; j < cols; j++) {
                thisRow.append(String.valueOf(board[i][j])).append(" ");
            }
            finalBoard.append(thisRow);
            finalBoard.append("\n");
        }
        return finalBoard.toString();
    }

    public boolean isValidMove(int column) {
        return board[findAvailableRowForCol(column)][column] == SENTINEL;
    }


    private boolean solvedHorizontally(int piece) {
        for (int c = 0; c < cols - 3; c++) {
            for (int r = 0; r < rows; r++) {
                if (board[r][c] == piece && board[r][c + 1] == piece && board[r][c + 2] == piece && board[r][c + 3] == piece)
                    return true;
            }
        }
        return false;
    }

    private boolean solvedVertically(int piece) {
        for (int c = 0; c < cols; c++) {
            for (int r = 0; r < rows - 3; r++) {
                if (board[r][c] == piece && board[r + 1][c] == piece && board[r + 2][c] == piece && board[r + 3][c] == piece)
                    return true;
            }
        }
        return false;
    }

    private boolean solvedForwardDiagonal(int piece) {
        for (int c = 0; c < cols - 3; c++) {
            for (int r = 0; r < rows - 3; r++) {
                if (board[r][c] == piece && board[r + 1][c + 1] == piece && board[r + 2][c + 2] == piece && board[r + 3][c + 3] == piece)
                    return true;
            }
        }
        return false;
    }

    private boolean solvedReverseDiagonal(int piece) {
        for (int c = 0; c < cols - 3; c++) {
            for (int r = 3; r < rows; r++) {
                if (board[r][c] == piece && board[r - 1][c + 1] == piece && board[r - 2][c + 2] == piece && board[r - 3][c + 3] == piece)
                    return true;
            }
        }
        return false;
    }

    private int findAvailableRowForCol(int col) {
        for (int i = 0; i < rows; i++) {
            if (board[i][col] != SENTINEL)
                return i - 1;
        }
        return rows - 1;
    }
}

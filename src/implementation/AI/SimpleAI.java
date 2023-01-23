package implementation.AI;

import java.util.Random;

import implementation.Field;
import implementation.Move;
import implementation.PenguAI;

public class SimpleAI extends PenguAI {

    private Random random;


    private int opponentPiece;

    private int place;

    public SimpleAI() {
        random = new Random();
    }

    @Override
    public Move makeMove(Field[][] board, boolean firstPlayer, boolean[] firstPlayedPieces,
            boolean[] secondPlayedPieces) {

        int playPiece = 8;
        boolean[] played;
        boolean[] opponentPlayed;

        if (firstPlayer) {
            played = firstPlayedPieces;
            opponentPlayed = secondPlayedPieces;
        }
        else {
            played = secondPlayedPieces;
            opponentPlayed = firstPlayedPieces;
        }

        for (int x = 0;  x < played.length; x++) {
            if (!played[x]) {
                playPiece = x;
            }
        }

        for (int x = 0;  x < opponentPlayed.length; x++) {
            if (!opponentPlayed[x]) {
                opponentPiece = x;
            }
        }

        // for the win
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == null) {
                    board[i][j] = new Field(playPiece, firstPlayer);
                    if (won(board, firstPlayer)) {
                        return new Move(i ,j, playPiece);
                    }
                    board[i][j] = null;
                }
                else if (board[i][j] != null && playPiece > board[i][j].value() && board[i][j].firstPlayer() == !firstPlayer) {
                    Field temp = board[i][j];
                    board[i][j] = new Field(playPiece, firstPlayer);
                    if (won(board, firstPlayer)) {
                        return new Move(i ,j, playPiece);
                    }
                    board[i][j] = temp;
                }
            }
        }

        // prevent win from opponent
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == null) {
                    board[i][j] = new Field(opponentPiece, !firstPlayer);
                    if (won(board, !firstPlayer)) {
                        return new Move(i ,j, playPiece);
                    }
                    board[i][j] = null;
                }
                else if (board[i][j] != null && opponentPiece > board[i][j].value() && board[i][j].firstPlayer() == firstPlayer) {
                    Field temp = board[i][j];
                    board[i][j] = new Field(opponentPiece, !firstPlayer);
                    if (won(board, !firstPlayer)) {
                        if (place == 1 && i + 1 < 3 && j - 1 >= 0 && board[i+1][j-1] != null && board[i+1][j-1].firstPlayer() == !firstPlayer) {
                            return new Move(i+1, j-1, playPiece);
                        }
                        if (place == 1 && i - 1 >= 0 && j + 1 < 3 && board[i-1][j+1] != null && board[i-1][j+1].firstPlayer() == !firstPlayer) {
                            return new Move(i-1, j+1, playPiece);
                        }
                        if (place == 2 && i + 1 < 3 && j - 1 >= 0 && board[i+1][j-1] != null && board[i+1][j-1].firstPlayer() == !firstPlayer) {
                            return new Move(i+1, j-1, playPiece);
                        }
                        if (place == 2 && i - 1 >= 0 && j + 1 < 3 && board[i-1][j+1] != null && board[i-1][j+1].firstPlayer() == !firstPlayer) {
                            return new Move(i-1, j+1, playPiece);
                        }
                        if (place == 4 && i + 1 < 3 && board[i+1][j] != null && board[i+1][j].firstPlayer() == !firstPlayer) {
                            return new Move(i+1, j, playPiece);
                        }
                        if (place == 4 && i - 1 >= 0 && board[i-1][j] != null && board[i-1][j].firstPlayer() == !firstPlayer) {
                            return new Move(i-1, j, playPiece);
                        }
                        if (place == 3 && j - 1 >= 0 && board[i][j-1] != null && board[i][j-1].firstPlayer() == !firstPlayer) {
                            return new Move(i, j-1, playPiece);
                        }
                        if (place == 3 && j + 1 < 3 && board[i][j+1] != null && board[i][j+1].firstPlayer() == !firstPlayer) {
                            return new Move(i, j+1, playPiece);
                        }
                    }
                    board[i][j] = temp;
                }
            }
        }

        // generate random coordinates
        int value = 8;

        while (played[value]) {
            value--;
        }

        while (true) {

            int x = random.nextInt(3);

            int y = random.nextInt(3);

            if (board[x][y] == null) {
                return new Move(x, y, value);
            }

            else if (board[x][y] != null && board[x][y].firstPlayer() != firstPlayer && board[x][y].value() < value) {
                return new Move(x, y, value);

            }
        }
    }

    // check if a player would win
    private boolean won(Field[][] board, boolean firstPlayer) {

        if (board[0][2] != null && board[0][2].firstPlayer() == firstPlayer
                && board[1][1] != null && board[1][1].firstPlayer() == firstPlayer
                && board[2][0] != null && board[2][0].firstPlayer() == firstPlayer) {
            place = 1;
            return true;
        }

        if (board[0][0] != null && board[0][0].firstPlayer() == firstPlayer
                && board[1][1] != null && board[1][1].firstPlayer() == firstPlayer
                && board[2][2] != null && board[2][2].firstPlayer() == firstPlayer) {
            place = 2;
            return true;
        }

        for (int i = 0; i < 3; i++) {

            if (board[i][0] != null && board[i][0].firstPlayer() == firstPlayer
                    && board[i][1] != null && board[i][1].firstPlayer() == firstPlayer
                    && board[i][2] != null && board[i][2].firstPlayer() == firstPlayer) {
                place = 3;
                return true;
            }
            if (board[0][i] != null && board[0][i].firstPlayer() == firstPlayer
                    && board[1][i] != null && board[1][i].firstPlayer() == firstPlayer
                    && board[2][i] != null && board[2][i].firstPlayer() == firstPlayer) {
                place = 4;
                return true;
            }
        }
        return false;
    }
}

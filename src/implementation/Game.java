package implementation;

import implementation.AI.HumanPlayer;
import implementation.AI.SimpleAI;

public class Game {

    private PenguAI first;
    private PenguAI second;

    private PenguAI winner;

    public Field board[][];

    private boolean[] firstPlayedPieces;

    private boolean[] secondPlayedPieces;

    public Game(PenguAI first, PenguAI second) {

        this.first = first;
        this.second = second;
        board = new Field[3][3];
        firstPlayedPieces = new boolean[9];
        secondPlayedPieces = new boolean[9];

    }

    public PenguAI getWinner() {

        if (winner == first) {
            return first;
        }

        if (winner == second) {
            return second;
        }

        if (board[2][0] != null && board[1][1] != null && board[0][2] != null) {
            if (board[2][0].firstPlayer() && board[1][1].firstPlayer() && board[0][2].firstPlayer()) {
                return first;
            }
            if (!board[2][0].firstPlayer() && !board[1][1].firstPlayer() && !board[0][2].firstPlayer()) {
                return second;
            }
        }

        if (board[0][0] != null && board[1][1] != null && board[2][2] != null) {
            if (board[0][0].firstPlayer() && board[1][1].firstPlayer() && board[2][2].firstPlayer()) {
                return first;
            }
            if (!board[0][0].firstPlayer() && !board[1][1].firstPlayer() && !board[2][2].firstPlayer()) {
                return second;
            }
        }


        for (int i = 0; i < 3; i++) {

            if (board[0][i] != null && board[1][i] != null && board[2][i] != null) {
                if (board[0][i].firstPlayer() && board[1][i].firstPlayer() && board[2][i].firstPlayer()) {
                    return first;
                }
                if (!board[0][i].firstPlayer() && !board[1][i].firstPlayer() && !board[2][i].firstPlayer()) {
                    return second;
                }
            }

            if (board[i][0] != null && board[i][1] != null && board[i][2] != null) {
                if (board[i][0].firstPlayer() && board[i][1].firstPlayer() && board[i][2].firstPlayer()) {
                    return first;
                }
                if (!board[i][0].firstPlayer() && !board[i][1].firstPlayer() && !board[i][2].firstPlayer()) {
                    return second;
                }
            }

        }

        return null;
    }

    public void playGame() {

        PenguAI current = first;

        while (true) {

            // check draw
            boolean full = true;

            for (int i = 0; i < firstPlayedPieces.length; i++) {
                if (!firstPlayedPieces[i]) {
                    full = false;
                    break;
                }
            }

            for (int i = 0; i < secondPlayedPieces.length; i++) {
                if (!secondPlayedPieces[i]) {
                    full = false;
                    break;
                }
            }

            if (full) {
                return;
            }

            // check, whether player has won
            if (board[2][0] != null && board[2][0].firstPlayer() && board[1][1] != null && board[1][1].firstPlayer() && board[0][2] != null && board[0][2].firstPlayer()) {
                return;
            }

            if (board[0][0] != null && board[0][0].firstPlayer() && board[1][1] != null && board[1][1].firstPlayer() && board[2][2] != null && board[2][2].firstPlayer()) {
                return;
            }

            if (board[2][0] != null && !board[2][0].firstPlayer() && board[1][1] != null && !board[1][1].firstPlayer() && board[0][2] != null && !board[0][2].firstPlayer()) {
                return;
            }

            if (board[0][0] != null && !board[0][0].firstPlayer() && board[1][1] != null && !board[1][1].firstPlayer() && board[2][2] != null && !board[2][2].firstPlayer()) {
                return;
            }

            for (int i = 0; i < 3; i++) {

                if (board[0][i] != null && board[0][i].firstPlayer() && board[1][i] != null && board[1][i].firstPlayer() && board[2][i] != null && board[2][i].firstPlayer() ||
                        board[0][i] != null && !board[0][i].firstPlayer() && board[1][i] != null && !board[1][i].firstPlayer() && board[2][i] != null && !board[2][i].firstPlayer()) {
                    return;
                }

                if (board[i][0] != null && board[i][0].firstPlayer() && board[i][1] != null && board[i][1].firstPlayer() && board[i][2] != null && board[i][2].firstPlayer() ||
                        board[i][0] != null && !board[i][0].firstPlayer() && board[i][1] != null && !board[i][1].firstPlayer() && board[i][2] != null && !board[i][2].firstPlayer()) {
                    return;
                }
            }

            int value = 8;
            boolean full2 = true;

            if (current == first) {
                while (firstPlayedPieces[value]) {
                    value--;
                }
            }
            else {
                while (secondPlayedPieces[value]) {
                    value--;
                }
            }
            for (int x = 0; x < 3; x++) {
                for (int y = 0; y < 3; y++) {
                    if (board[x][y] == null || board[x][y] != null && !board[x][y].firstPlayer() && board[x][y].value() < value) {
                        full2 = false;
                        break;
                    }
                }
                if (!full2) {
                    break;
                }
            }

            if (full2 && current == first) {
                winner = second;
                return;
            }

            if (full2 && current == second) {
                winner = first;
                return;
            }

            Move move;

            if (current == first) {
                move = first.makeMove(board, true, firstPlayedPieces, secondPlayedPieces);
            }
            else {
                move = second.makeMove(board, false, firstPlayedPieces, secondPlayedPieces);
            }

            if (move.x() < 0 || move.x() >= 3 || move.y() < 0 || move.y() >= 3) {
                if (current == first) {
                    winner = second;
                }
                else {
                    winner = first;
                }
                return;
            }

            if (move.value() < 0 || move.value() > 8 || current == second && secondPlayedPieces[move.value()] || current == first && firstPlayedPieces[move.value()]) {
                if (current == first) {
                    winner = second;
                }
                else {
                    winner = first;
                }
                return;
            }

            if (board[move.x()][move.y()] != null && board[move.x()][move.y()].firstPlayer() && current == first) {
                winner = second;
                return;
            }

            if (board[move.x()][move.y()] != null && !board[move.x()][move.y()].firstPlayer() && current == second) {
                winner = first;
                return;
            }


            if (board[move.x()][move.y()] != null && board[move.x()][move.y()].firstPlayer() && current == second && board[move.x()][move.y()].value() >= move.value()) {
                winner = first;
                return;
            }

            if (board[move.x()][move.y()] != null && !board[move.x()][move.y()].firstPlayer() && current != second && board[move.x()][move.y()].value() >= move.value()) {
                winner = second;
                return;
            }

            Field newField;

            if (current == first) {
                newField = new Field(move.value(), true);
            }
            else {
                newField = new Field(move.value(), false);
            }

            board[move.x()][move.y()] = newField;

            if (current == first) {
                firstPlayedPieces[move.value()] = true;
            }
            else {
                secondPlayedPieces[move.value()] = true;
            }

            if (current == first) {
                current = second;
            }
            else {
                current = first;
            }
        }
    }

    public static void printBoard(Field[][] board) {
        System.out.println("┏━━━┳━━━┳━━━┓");
        for (int y = 0; y < board.length; y++) {
            System.out.print("┃");
            for (int x = 0; x < board.length; x++) {
                if (board[x][y] != null) {
                    System.out.print(board[x][y] + "┃");
                } else {
                    System.out.print("   ┃");
                }
            }
            System.out.println();
            if (y != board.length - 1) {
                System.out.println("┣━━━╋━━━╋━━━┫");
            }
        }
        System.out.println("┗━━━┻━━━┻━━━┛");
    }

    public static void main(String[] args) {
        PenguAI firstPlayer = new HumanPlayer();
        PenguAI secondPlayer = new HumanPlayer();
        //PenguAI firstPlayer = new SimpleAI();
        //PenguAI secondPlayer = new SimpleAI();
        Game game = new Game(firstPlayer, secondPlayer);
        game.playGame();
        if(firstPlayer == game.getWinner()) {
            System.out.println("Herzlichen Glückwunsch erster Spieler");
        } else if(secondPlayer == game.getWinner()) {
            System.out.println("Herzlichen Glückwunsch zweiter Spieler");
        } else {
            System.out.println("Unentschieden");
        }
    }
}

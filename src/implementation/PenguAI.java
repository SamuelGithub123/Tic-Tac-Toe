package implementation;

public abstract class PenguAI {

    public abstract Move makeMove(Field[][] board, boolean firstPlayer, boolean[] firstPlayedPieces,
            boolean[] secondPlayedPieces);
}

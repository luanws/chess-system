package chess;

import boardgame.Board;
import boardgame.Piece;
import boardgame.Position;
import chess.pieces.King;
import chess.pieces.Rook;

public class ChessMatch {
    private Board board;

    public ChessMatch() {
        board = new Board(8, 8);
        initialSetup();
    }

    public ChessPiece performChessMove(ChessPosition sourcePosition, ChessPosition targetPosition) {
        Position source = sourcePosition.toPosition();
        Position target = targetPosition.toPosition();
        validateSourcePosition(source);
        validateTargetPosition(source, target);
        Piece capturedPiece = makeMove(source, target);
        return (ChessPiece) capturedPiece;
    }

    private Piece makeMove(Position source, Position target) {
        Piece piece = board.removePiece(source);
        Piece capturedPiece = board.removePiece(target);
        board.placePiece(piece, target);
        return capturedPiece;
    }

    private void validateSourcePosition(Position position) {
        if (!board.thereIsAPiece(position)) {
            throw new ChessException("There is piece on source position");
        }
        if (!board.getPiece(position).isThereAnyPossibleMove()) {
            throw new ChessException("There is no possible moves for the chosen piece");
        }
    }

    private void validateTargetPosition(Position source, Position target) {
        if (!board.getPiece(source).possibleMove(target)) {
            throw new ChessException("The chosen piece can't move to target position");
        }
    }

    private void placePiece(char column, int row, ChessPiece piece) {
        board.placePiece(piece, new ChessPosition(column, row).toPosition());
    }

    private void initialSetup() {
        placePiece('c', 1, new Rook(board, Color.WHITE));
        placePiece('c', 2, new Rook(board, Color.WHITE));
        placePiece('d', 2, new Rook(board, Color.WHITE));
        placePiece('e', 2, new Rook(board, Color.WHITE));
        placePiece('e', 1, new Rook(board, Color.WHITE));
        placePiece('d', 1, new King(board, Color.WHITE));

        placePiece('c', 7, new Rook(board, Color.BLACK));
        placePiece('c', 8, new Rook(board, Color.BLACK));
        placePiece('d', 7, new Rook(board, Color.BLACK));
        placePiece('e', 7, new Rook(board, Color.BLACK));
        placePiece('e', 8, new Rook(board, Color.BLACK));
        placePiece('d', 8, new King(board, Color.BLACK));
    }

    public ChessPiece[][] getPieces() {
        ChessPiece[][] chessPieces = new ChessPiece[board.getRows()][board.getColumns()];
        for (int i = 0; i < board.getRows(); i++) {
            for (int j = 0; j < board.getColumns(); j++) {
                chessPieces[i][j] = (ChessPiece) board.getPiece(i, j);
            }
        }

        return chessPieces;
    }
}

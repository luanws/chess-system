package chess.pieces;

import boardgame.Board;
import boardgame.Position;
import chess.ChessPiece;
import chess.Color;

public class Rook extends ChessPiece {
    public Rook(Board board, Color color) {
        super(board, color);
    }

    @Override
    public String toString() {
        return "R";
    }

    @Override
    public boolean[][] possibleMoves() {
        Board board = getBoard();
        boolean[][] mat = new boolean[board.getRows()][board.getColumns()];

        Position position = new Position(0, 0);

        position.setValues(position.getRow() - 1, position.getColumn());
        while (board.positionExists(position) && !board.thereIsAPiece(position)) {
            mat[position.getRow()][position.getColumn()] = true;
            position.setRow(position.getRow() - 1);
        }
        if (board.positionExists(position) && isThereOpponentPiece(position)) {
            mat[position.getRow()][position.getColumn()] = true;
        }

        position.setValues(position.getRow(), position.getColumn() - 1);
        while (board.positionExists(position) && !board.thereIsAPiece(position)) {
            mat[position.getRow()][position.getColumn()] = true;
            position.setColumn(position.getColumn() - 1);
        }
        if (board.positionExists(position) && isThereOpponentPiece(position)) {
            mat[position.getRow()][position.getColumn()] = true;
        }

        position.setValues(position.getRow(), position.getColumn() + 1);
        while (board.positionExists(position) && !board.thereIsAPiece(position)) {
            mat[position.getRow()][position.getColumn()] = true;
            position.setColumn(position.getColumn() + 1);
        }
        if (board.positionExists(position) && isThereOpponentPiece(position)) {
            mat[position.getRow()][position.getColumn()] = true;
        }

        position.setValues(position.getRow() + 1, position.getColumn());
        while (board.positionExists(position) && !board.thereIsAPiece(position)) {
            mat[position.getRow()][position.getColumn()] = true;
            position.setRow(position.getRow() + 1);
        }
        if (board.positionExists(position) && isThereOpponentPiece(position)) {
            mat[position.getRow()][position.getColumn()] = true;
        }

        return mat;
    }
}

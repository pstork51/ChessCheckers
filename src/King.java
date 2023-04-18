import javax.swing.*;
import java.awt.*;

public class King extends Piece implements Drawable, Moveable{


    public King(boolean isWhite, int x, int y) {
        super(isWhite);
        this.x = x;
        this.y = y;
        this.x_loc = x * 75 + 5;
        this.y_loc = y * 75 + 5;
        if(isWhite){
            image = new ImageIcon("Images/WhiteKing.png");
        }
        else{
            image = new ImageIcon("Images/BlackKing.png");
        }

    }

    @Override
    public boolean canMove(Board board, BoardSquare startMove, BoardSquare endMove){
        if(this.x == endMove.getY() || this.x == endMove.getY() + 1 || this.x == endMove.getY() - 1) {
            if(this.y == endMove.getX() || this.y == endMove.getX() + 1 || this.y == endMove.getX() -1) {
                return true;
            }
        }
        else if (!this.hasMoved) {
            if(this.getIsWhite()) {
                //white castling
                if(endMove.getY() == 2 && endMove.getX() == this.y) {
                    if(board.getSquare(0,7).getPiece() != null && board.getSquare(0,7).getPiece().getClass() == Rook.class) {
                        if(!board.getSquare(0,7).getPiece().hasMoved){
                            if(board.getSquare(1,7).getPiece() == null && board.getSquare(2,7).getPiece() == null) {
                                //rook that has not moved
                                board.getSquare(3, 7).setPiece(board.getSquare(0, 7).getPiece());
                                board.getSquare(3, 7).getPiece().hasMoved = true;
                                board.getSquare(3,7).getPiece().setX_loc(3 * SQUARESIZE + 5);
                                board.getSquare(3,7).getPiece().setY_loc(7 * SQUARESIZE + 5);
                                this.hasMoved = true;
                                board.getSquare(0, 7).setPiece(null);
                                return true;
                            }
                        }
                    }
                }
                else if(endMove.getY() == 6 && endMove.getX() == this.y) {
                    if(board.getSquare(7,7).getPiece() != null && board.getSquare(7,7).getPiece().getClass() == Rook.class) {
                        if(!board.getSquare(0,7).getPiece().hasMoved){
                            if(board.getSquare(6,7).getPiece() == null && board.getSquare(5,7).getPiece() == null) {
                                //rook that has not moved
                                board.getSquare(5, 7).setPiece(board.getSquare(7, 7).getPiece());
                                board.getSquare(5, 7).getPiece().hasMoved = true;
                                board.getSquare(5,7).getPiece().setX_loc(5 * SQUARESIZE + 5);
                                board.getSquare(5,7).getPiece().setY_loc(7 * SQUARESIZE + 5);
                                this.hasMoved = true;
                                board.getSquare(7, 7).setPiece(null);

                                return true;
                            }
                        }
                    }
                }
            }
            else {
                //black piece
                //white castling
                if(endMove.getY() == 2 && endMove.getX() == this.y) {
                    if(board.getSquare(0,0).getPiece() != null && board.getSquare(0,0).getPiece().getClass() == Rook.class) {
                        if(!board.getSquare(0,0).getPiece().hasMoved){
                            if(board.getSquare(1,0).getPiece() == null && board.getSquare(2,7).getPiece() == null) {
                                //rook that has not moved
                                board.getSquare(3, 0).setPiece(board.getSquare(0, 0).getPiece());
                                board.getSquare(3, 0).getPiece().hasMoved = true;
                                board.getSquare(3,0).getPiece().setX_loc(3 * SQUARESIZE + 5);
                                board.getSquare(3,0).getPiece().setY_loc(0 * SQUARESIZE + 5);
                                this.hasMoved = true;
                                board.getSquare(0, 0).setPiece(null);
                                return true;
                            }
                        }
                    }
                }
                else if(endMove.getY() == 6 && endMove.getX() == this.y) {
                    if(board.getSquare(7,0).getPiece() != null && board.getSquare(7,0).getPiece().getClass() == Rook.class) {
                        if(!board.getSquare(0,0).getPiece().hasMoved){
                            if(board.getSquare(6,0).getPiece() == null && board.getSquare(5,0).getPiece() == null) {
                                //rook that has not moved
                                board.getSquare(5, 0).setPiece(board.getSquare(7, 0).getPiece());
                                board.getSquare(5, 0).getPiece().hasMoved = true;
                                board.getSquare(5,0).getPiece().setX_loc(5 * SQUARESIZE + 5);
                                board.getSquare(5,0).getPiece().setY_loc(0 * SQUARESIZE + 5);
                                this.hasMoved = true;
                                board.getSquare(7, 0).setPiece(null);
                                return true;
                            }
                        }
                    }
                }
            }
        }
        return false;
    }

}

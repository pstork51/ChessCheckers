import javax.swing.*;
import java.awt.*;
import java.awt.image.ImageObserver;

import static java.lang.Math.abs;

public class Bishop extends Piece implements Drawable, Moveable{
    public Bishop(boolean isWhite, int x, int y) {
        super(isWhite);
        this.x = x;
        this.y = y;
        this.x_loc = x * 75 + 5;
        this.y_loc = y * 75 + 5;
        if(isWhite){
            image = new ImageIcon("Images/WhiteBishop.png");
        }
        else{
            image = new ImageIcon("Images/BlackBishop.png");
        }
    }

    @Override
    public boolean canMove(Board board, BoardSquare startMove, BoardSquare endMove){
        if(abs(this.x - endMove.getY()) == abs(this.y - endMove.getX())) {
            //this checks if the square is on the diagonal
            if(this.x - endMove.getY() > 0) {
                if(this.y - endMove.getX() > 0){
                    //moving left and up
                    for(int i = this.x - 1; i > endMove.getY(); i--) {
                        if(board.getSquare(i,this.y - (this.x - i )).getPiece() != null) {
                            return false;
                        }
                    }

                }
                else if(this.y - endMove.getX() < 0){
                    //moving left and down
                    for(int i = this.x - 1; i > endMove.getY(); i--) {
                        if(board.getSquare(i,this.y + (this.x - i )).getPiece() != null) {
                            return false;
                        }
                    }
                }
            }
            else if (this.x - endMove.getY() < 0) {
                if(this.y - endMove.getX() > 0){
                    //moving right and up
                    for(int i = this.x + 1; i < endMove.getY(); i++) {
                        if(board.getSquare(i,this.y - (i - this.x )).getPiece() != null) {
                            return false;
                        }
                    }
                }
                else if(this.y - endMove.getX() < 0){
                    //moving right and down
                    for(int i = this.x + 1; i < endMove.getY(); i++) {
                        if(board.getSquare(i,this.y + (i - this.x )).getPiece() != null) {
                            return false;
                        }
                    }
                }

            }
            return true;
        }
        return false;
    }
}

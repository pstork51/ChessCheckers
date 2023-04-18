import javax.swing.*;
import java.awt.*;

import static java.lang.Math.abs;

public class Queen extends Piece implements Drawable, Moveable{
    public Queen(boolean isWhite, int x, int y) {
        super(isWhite);
        this.x = x;
        this.y = y;
        this.x_loc = x * 75 + 5;
        this.y_loc = y * 75 + 5;
        if(isWhite){
            image = new ImageIcon("Images/WhiteQueen.png");
        }
        else{
            image = new ImageIcon("Images/BlackQueen.png");
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
        else if(this.y == endMove.getX()) {
            System.out.println("Rook moving horizontally");
            //moving horizontally
            if(this.x - endMove.getY() > 0){
                //moving left
                for(int i = this.x - 1; i > endMove.getY(); i--) {
                    if(board.getSquare(i,this.y).getPiece() != null) {
                        return false;
                    }
                }
            }
            else if (this.x - endMove.getY() < 0) {
                //moving right
                for(int i = this.x + 1; i < endMove.getY(); i++) {
                    if(board.getSquare(i,this.y).getPiece() != null) {
                        return false;
                    }
                }
            }
            return true;
        }
        else if (this.x == endMove.getY()){
            if(this.y - endMove.getX() < 0) {
                //moving down
                for(int i = this.y + 1; i < endMove.getX(); i++) {
                    if(board.getSquare(this.x,i).getPiece() != null){
                        return false;
                    }
                }
            }
            else if(this.y - endMove.getX() > 0) {
                //moving up
                for(int i = this.y - 1; i > endMove.getX(); i--) {
                    if(board.getSquare(this.x,i).getPiece() != null){
                        return false;
                    }
                }
            }
            return true;
        }

        return false;
    }
}

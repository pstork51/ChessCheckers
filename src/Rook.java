import javax.swing.*;
import java.awt.*;

public class Rook extends Piece implements Drawable, Moveable{
    private boolean hasMoved;
    public Rook(boolean isWhite, int x, int y) {
        super(isWhite);
        this.hasMoved = false;
        this.x = x;
        this.y = y;
        this.x_loc = x * 75 + 5;
        this.y_loc = y * 75 + 5;
        if(isWhite){
            image = new ImageIcon("Images/WhiteRook.png");
        }
        else{
            image = new ImageIcon("Images/BlackRook.png");
        }
    }

    //I am still working on the rook canMove function (Parker)
    @Override
    public boolean canMove(Board board, BoardSquare startMove, BoardSquare endMove){
        if(this.y == endMove.getX()) {
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
        }

        return true;
    }
}

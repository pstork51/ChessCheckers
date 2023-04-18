import javax.swing.*;
import java.awt.*;

public class Pawn extends Piece implements Drawable, Moveable{
    private boolean firstMove;

    public Pawn(boolean isWhite, int x, int y) {

        super(isWhite);
        this.x = x;
        this.y = y;
        this.x_loc = x * 75 + 5;
        this.y_loc = y * 75 + 5;
        this.firstMove = true;
        if(isWhite){
            image = new ImageIcon("Images/WhitePawn.png");
        }
        else{
            image = new ImageIcon("Images/BlackPawn.png");
        }

    }
    @Override
    public boolean canMove(Board board, BoardSquare startMove, BoardSquare endMove){
        // For some reason the x and y values refer to different things in the pieces and squares,
        // so they need to be flipped here
        if(this.x == endMove.getY())
         {
             if(endMove.getPiece() == null) {
                 if(this.firstMove) {
                     if (this.getIsWhite() && (this.y == endMove.getX() + 1 || this.y == endMove.getX() + 2)) {
                         this.firstMove = false;
                         return true;
                     } else if (!this.getIsWhite() && (this.y == endMove.getX() - 1 || this.y == endMove.getX() - 2)) {
                         this.firstMove = false;
                         return true;
                     } else {
                         return false;
                     }
                 }
                 else{
                     if (this.getIsWhite() && (this.y == endMove.getX() + 1)) {
                         this.firstMove = false;
                         return true;
                     } else if (!this.getIsWhite() && (this.y == endMove.getX() - 1)) {
                         this.firstMove = false;
                         return true;
                     } else {
                         return false;
                     }
                 }
             }

        }
        else if (this.x == endMove.getY() + 1 || this.x == endMove.getY() - 1) {
            if(!this.getIsWhite()) {
                if(this.y == endMove.getX() - 1) {
                    if(endMove.getPiece() != null) {
                        this.firstMove = false;
                        return true;
                    }
                    else{
                        return false;
                    }
                }
            }
            else {
                if(this.y == endMove.getX() + 1) {
                    if(endMove.getPiece() != null) {
                        this.firstMove = false;
                        return true;
                    }
                    else{
                        return false;
                    }
                }
            }
        }
        return false;
    }

}

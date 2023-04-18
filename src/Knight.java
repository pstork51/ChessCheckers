import javax.swing.*;
import java.awt.*;

public class Knight extends Piece implements Drawable, Moveable{

    public Knight(boolean isWhite,int x, int y) {
        super(isWhite);
        this.x = x;
        this.y = y;
        this.x_loc = x * 75 + 5;
        this.y_loc = y * 75 + 5;
        if(isWhite){
            image = new ImageIcon("Images/WhiteKnight.png");
        }
        else{
            image = new ImageIcon("Images/BlackKnight.png");
        }
    }

    @Override
    public boolean canMove(Board board, BoardSquare startMove, BoardSquare endMove){
        if(this.x == endMove.getY() + 1 || this.x == endMove.getY() -1) {
            if(this.y == endMove.getX() + 2 || this.y == endMove.getX() -2) {
                return true;
            }
        }
        else if(this.y == endMove.getX() + 1 || this.y == endMove.getX() - 1) {
            if(this.x == endMove.getY() + 2 || this.x == endMove.getY() -2) {
                return true;
            }
        }
        return false;
    }
}

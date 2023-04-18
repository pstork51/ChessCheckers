import javax.swing.*;
import java.awt.*;

public class Piece implements Drawable, Moveable{
    public final int SQUARESIZE = 75;
    public final int X_OFFSET = 5;
    public final int Y_OFFSET = 5;

    private boolean isCheckerKing;

    protected boolean hasMoved;
    protected int x_loc, y_loc;
    private boolean isWhite;
    private boolean isCaptured;
    protected ImageIcon image;
    protected int x, y;


    public Piece(boolean isWhite) {
        this.hasMoved = false;
        this.isWhite = isWhite;
        this.isCaptured = false;
        this.isCheckerKing = false;
    }

    public boolean getIsWhite() {
        return this.isWhite;
    }
    public boolean getIsCaptured() { return this.isCaptured; }
    public ImageIcon getImage() {return this.image;}
    public void setIsCaptured(boolean isCapturedrhs) {
        this.isCaptured = isCapturedrhs;
    }
    public boolean canMove(BoardSquare end) {
        return !end.isOccupied;
    }
    public void setX(int x){this.x = x;}
    public int getX() {return x;}
    public void setY(int y) {this.y = y;}
    public int getY(){return y;}
    public int getX_loc(){return x_loc;}
    public void setX_loc(int loc){this.x_loc = loc;}
    public int getY_loc(){return y_loc;}
    public void setY_loc(int loc){this.y_loc = loc;}

    @Override
    public void draw(Graphics g){
        if(!isCaptured) {
            g.drawImage(image.getImage(), this.x_loc,
                    this.y_loc, null);
        }
    }

    public void setIsKing(boolean x) {}

    @Override
    public boolean canMove(Board board, BoardSquare startMove, BoardSquare endMove){
        return true;
    }
}
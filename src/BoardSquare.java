import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BoardSquare implements Drawable{
    private final int SQUARESIZE = 75;
    private Color color;
    boolean isOccupied;
    boolean isSelected;
    private int x;
    private int y;
    private Piece piece;
    private boolean needsRedrawn;

    public BoardSquare(int xrhs, int yrhs, Color colorrhs, Piece piecerhs) {
        this.color = colorrhs;
        this.isOccupied = false;
        this.isSelected = false;
        this.x = xrhs;
        this.y = yrhs;
        this.piece = piecerhs;
    }

    public void setPiece(Piece piecerhs) {
        this.piece = piecerhs;
    }

    @Override
    public void draw(Graphics g){
        //g.drawRect(x * SQUARESIZE + 7, y * SQUARESIZE + 32, SQUARESIZE, SQUARESIZE);
        g.setColor(color);
        g.fillRect(x * SQUARESIZE, y * SQUARESIZE, SQUARESIZE, SQUARESIZE);
    }
    public void setColor(Color colorrhs) {this.color = colorrhs;}

    public void setIsOccupied(boolean isOccupiedrhs) {this.isOccupied = isOccupiedrhs;}

    public void setIsSelected(boolean isSelectedrhs) {this.isSelected = isSelectedrhs;}

    public Color getColor() {return this.color;}

    public boolean getIsOccupied() {return this.isOccupied;}

    public boolean getIsSelected() {return this.isSelected;}

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public Piece getPiece() {
        if(this.piece != null){
            return this.piece;
        }
        else {
            return null;
        }
    }


}
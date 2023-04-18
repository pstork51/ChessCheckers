import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class RenderGame extends JPanel {
    public final int SQUARESIZE = 75;
    private ArrayList<Drawable> DrawableList;

    RenderGame(){
        this.DrawableList = new ArrayList<>();
        this.setDoubleBuffered(true);

    }


    public void paint(Graphics g)
    {
        Graphics2D g2D = (Graphics2D) g;

        for(Drawable drawable: this.DrawableList){
            drawable.draw(g2D);
        }
    }


    void addSquares(Board board) {
        for(int i = 0; i < 8; i++) {
            for(int j = 0; j < 8; j++) {
                this.DrawableList.add(board.getSquare(j,i));
            }
        }
    }

    void addPieces(Board board) {
        for(int i = 0; i < 8; i++) {
            for(int j = 0; j < 8; j++) {
                if(board.getSquare(i,j).getPiece() != null) {
                    this.DrawableList.add(board.getSquare(i,j).getPiece());
                }
            }
        }
    }

    public void updateBoard(Board board) {
        this.DrawableList.clear();
        this.addSquares(board);
        this.addPieces(board);

    }

}

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class CheckersGame extends JFrame{
    public final int SQUARESIZE = 75;

    public char winner;
    private CheckersBoard checkersBoard;
    private RenderGame renderGame;
    private Point mousePT;
    private BoardSquare startMove;
    private BoardSquare endMove;

    public CheckersGame() {

        this.winner = 'N';
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.getContentPane().setPreferredSize(new Dimension(600,600));
        this.pack();
        this.setResizable(false);
        this.checkersBoard = new CheckersBoard();
        this.renderGame = new RenderGame();
        this.renderGame.addSquares(this.checkersBoard);
        this.renderGame.addPieces(this.checkersBoard);
        this.add(this.renderGame);
        this.renderGame.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
            }

            @Override
            public void mousePressed(MouseEvent e) {
                mousePT = e.getPoint();
                int x_square = ((e.getX()) / SQUARESIZE);
                int y_square = ((e.getY()) / SQUARESIZE);
                startMove = checkersBoard.getSquare(x_square, y_square);
                System.out.println(x_square + " " + y_square);
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                if(startMove.getPiece() != null) {
                    handleMouseClick(e.getX(), e.getY());
                }
            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });
        this.renderGame.addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseDragged(MouseEvent e) {
                int x_diff = e.getX() - mousePT.x;
                int y_diff = e.getY() - mousePT.y;
                if(startMove.getPiece() != null){
                    Piece currentPiece = startMove.getPiece();
                    currentPiece.setX_loc(currentPiece.getX_loc() + x_diff);
                    currentPiece.setY_loc(currentPiece.getY_loc() + y_diff);
                    mousePT = e.getPoint();
                    repaint();
                }
            }

            @Override
            public void mouseMoved(MouseEvent e) {

            }
        });

        //this.setVisible(true);
    }

    private void handleMouseClick(int x, int y){
        System.out.println(x + " " + y);

        x -= 0;
        x /= SQUARESIZE;
        y -= 0;
        y /= SQUARESIZE;

        System.out.println(x + " " + y);

        this.endMove = this.checkersBoard.getSquare(x,y);
        if(startMove == endMove) {
            startMove.getPiece().setX_loc(x * SQUARESIZE + 5);
            startMove.getPiece().setY_loc(y * SQUARESIZE + 5);
        }
        this.checkersBoard.movePieces(startMove, endMove);
        if(this.checkersBoard.checkGameOver()) {
            System.out.println("Checking if game is over");
            if(this.checkersBoard.redPieces == 0) {
                this.winner = 'B';
            }
            else{
                this.winner = 'R';
            }
        }

        this.renderGame.repaint();
    }

    public void reset() {
        this.checkersBoard.resetBoard();
        this.renderGame.updateBoard(this.checkersBoard);
        this.winner = 'N';
    }
}

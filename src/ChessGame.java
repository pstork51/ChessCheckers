import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class ChessGame extends JFrame{

    public final int SQUARESIZE = 75;

    private boolean whiteMove;
    public boolean pieceCaptured;
    private Board board;
    public RenderGame renderGame;
    private Point mousePT;
    private BoardSquare startMove;
    private BoardSquare endMove;

    public ChessGame() {
        this.whiteMove = true;
        this.pieceCaptured = false;
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.getContentPane().setPreferredSize(new Dimension(600,600));
        this.pack();
        this.setResizable(false);
        this.board = new ChessBoard();
        this.renderGame = new RenderGame();
        this.renderGame.addSquares(this.board);
        this.renderGame.addPieces(this.board);
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
                startMove = board.getSquare(x_square, y_square);
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
                    //board.printBoard();
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
        Piece startpiece = startMove.getPiece();

        x -= 0;
        x /= SQUARESIZE;
        y -= 0;
        y /= SQUARESIZE;

        System.out.println(x + " " + y);

        if(x <= 7 && y <= 7) {
            this.endMove = this.board.getSquare(x,y);
        }

        if(startMove == endMove) {
            startMove.getPiece().setX_loc(x * SQUARESIZE + 5);
            startMove.getPiece().setY_loc(y * SQUARESIZE + 5);
        }
        else if(this.whiteMove && startMove.getPiece().getIsWhite()) {
            //whites turn and white piece being moved
            if(this.board.movePieces(startMove, endMove)){
                startMove.getPiece().setX_loc(endMove.getY() * SQUARESIZE + 5);
                startMove.getPiece().setY_loc(endMove.getX() * SQUARESIZE + 5);
                this.pieceCaptured = true;
            }
            if(startpiece == endMove.getPiece() || this.pieceCaptured) {
                this.whiteMove = !this.whiteMove;
            }
        }
        else if (!this.whiteMove && !startMove.getPiece().getIsWhite()) {
            if(this.board.movePieces(startMove, endMove)){
                startMove.getPiece().setX_loc(endMove.getY() * SQUARESIZE + 5);
                startMove.getPiece().setY_loc(endMove.getX() * SQUARESIZE + 5);
                this.pieceCaptured = true;
            }
            if(startpiece == endMove.getPiece() || this.pieceCaptured) {
                this.whiteMove = !this.whiteMove;
            }
        }
        else{
            startMove.getPiece().setX_loc(startMove.getY() * SQUARESIZE + 5);
            startMove.getPiece().setY_loc(startMove.getX() * SQUARESIZE + 5);
        }
        this.renderGame.repaint();
    }

    public void capturePiece(boolean whiteWins) {
        if(whiteWins && startMove.getPiece().getIsWhite()){
            endMove.getPiece().setIsCaptured(true);
            endMove.setPiece(startMove.getPiece());
            startMove.setPiece(null);
        }
        else if (whiteWins && endMove.getPiece().getIsWhite()) {
            startMove.getPiece().setIsCaptured(true);
            startMove.setPiece(null);
        }
        else if (!whiteWins && endMove.getPiece().getIsWhite()) {
            endMove.getPiece().setIsCaptured(true);
            endMove.setPiece(startMove.getPiece());
            startMove.setPiece(null);
        }
        else {
            startMove.getPiece().setIsCaptured(true);
            startMove.setPiece(null);
        }
        endMove.getPiece().setX(endMove.getY());
        endMove.getPiece().setY(endMove.getX());
    }

    public void printGame() {
        this.board.printBoard();
    }
}

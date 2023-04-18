import java.awt.*;
import java.util.ArrayList;

public class Board {
    BoardSquare[][] BoardSquares = new BoardSquare[8][8];
    private boolean redMove;

    public Board() {
        this.redMove = true;
    }

    protected void addSquares() {}

    protected void addPieces() {}

    protected boolean movePieces(BoardSquare startSquare, BoardSquare endSquare) {return true;}

    public BoardSquare getSquare(int x, int y) {
        return BoardSquares[x][y];
    }

    public void printBoard() {
        for(int i = 0; i < 8; i++) {
            for(int j = 0; j < 8; j++) {
                if(this.BoardSquares[j][i].getPiece() != null) {
                    if (this.BoardSquares[j][i].getPiece().getClass() == Rook.class) {
                        System.out.print("R");
                    } else if (this.BoardSquares[j][i].getPiece().getClass() == Knight.class) {
                        System.out.print("N");
                    } else if (this.BoardSquares[j][i].getPiece().getClass() == Bishop.class) {
                        System.out.print("B");
                    } else if (this.BoardSquares[j][i].getPiece().getClass() == Queen.class) {
                        System.out.print("Q");
                    } else if (this.BoardSquares[j][i].getPiece().getClass() == King.class) {
                        System.out.print("K");
                    } else if (this.BoardSquares[j][i].getPiece().getClass() == Pawn.class) {
                        System.out.print("P");
                    }
                }
                else{
                    System.out.print("_");
                }
            }
            System.out.println();
        }
    }


    //the following are setters and getters needed for the CheckerBoard class
    //again, bad practice but it's a quick fix for allowing polymorphism for the CheckerBoard classes
    //without changing all the rest of the code
    public void setRedPieces(int x){

    }
    public int getRedPieces() {
        return 0;
    }
    public void setBlackPieces(int x) {
    }
    public int getBlackPieces() {
        return 0;
    }
    public void setRedMove(boolean x) {
        this.redMove = x;
    }
    public boolean getRedMove() {
        return this.redMove;
    }
}

import java.awt.*;

public class CheckersBoard extends Board{
    BoardSquare[][] CheckersBoardSquares = new BoardSquare[8][8];
    boolean blackWins;
    int blackPieces;
    int redPieces;

    public CheckersBoard()
    {
        addSquares();
        addPieces();
        this.blackWins = false;
        this.redPieces = 12;
        this.blackPieces = 12;
    }

    @Override
    protected void addSquares(){
        Color lightBrown = new Color(198,140,83);
        Color darkBrown = new Color(102,51,0);
        for(int i = 0; i < 8; i++) {
            for(int j = 0; j < 8; j++){
                BoardSquare square;
                if((i + j) % 2 == 0){
                    square = new BoardSquare(i, j, lightBrown, null);
                }
                else{
                    square = new BoardSquare(i, j, darkBrown, null);
                }
                this.CheckersBoardSquares[j][i] = square;
            }
        }
    }

    @Override
    protected void addPieces(){
        for(int i = 0; i < 8; i++){
            for(int j = 0; j < 8; j++){
                if(i == 0 && j % 2 == 1) {
                    Checker redChecker = new Checker(false, j, i);
                    this.CheckersBoardSquares[j][i].setPiece(redChecker);
                }
                else if(i == 1 && j % 2 == 0) {
                    Checker redChecker = new Checker(false,j, i);
                    this.CheckersBoardSquares[j][i].setPiece(redChecker);
                }
                else if(i == 2 && j % 2 == 1) {
                    Checker redChecker = new Checker(false,j, i);
                    this.CheckersBoardSquares[j][i].setPiece(redChecker);
                }
                else if(i == 5 && j % 2 == 0) {
                    Checker blackChecker = new Checker(true,j, i);
                    this.CheckersBoardSquares[j][i].setPiece(blackChecker);
                }
                else if(i == 6 && j % 2 == 1) {
                    Checker blackChecker = new Checker(true,j, i);
                    this.CheckersBoardSquares[j][i].setPiece(blackChecker);
                }
                else if(i == 7 && j % 2 == 0) {
                    Checker blackChecker = new Checker(true,j, i);
                    this.CheckersBoardSquares[j][i].setPiece(blackChecker);
                }
            }
        }
    }

    @Override
    public BoardSquare getSquare(int x, int y) {
        return CheckersBoardSquares[x][y];
    }


    @Override
    protected boolean movePieces(BoardSquare startSquare, BoardSquare endSquare) {
        //returns true if a piece is captured
        boolean pieceCaptured = false;
        if(startSquare.getPiece() != null) {
            if(startSquare.getPiece().canMove(this,startSquare,endSquare))
            {
                //this means it is a legal move
                if (endSquare.getPiece() == null) {
                    if(startSquare.getPiece().getIsWhite() && endSquare.getX() == 0){
                        startSquare.getPiece().setIsKing(true);
                    }
                    else if(!startSquare.getPiece().getIsWhite() && endSquare.getX() == 7){
                        startSquare.getPiece().setIsKing(true);
                    }
                    endSquare.setPiece(startSquare.getPiece());
                    startSquare.setPiece(null);
                    endSquare.getPiece().setX(endSquare.getY());
                    endSquare.getPiece().setY(endSquare.getX());
                    endSquare.getPiece().setX_loc(endSquare.getY() * 75 + 5);
                    endSquare.getPiece().setY_loc(endSquare.getX() * 75 + 5);
                    System.out.println("Black Pieces: " + blackPieces + "\nRed Pieces: " + redPieces);
                } else if((startSquare.getPiece().getIsWhite() && endSquare.getPiece().getIsWhite())
                        ||(!startSquare.getPiece().getIsWhite() && !endSquare.getPiece().getIsWhite()) ){
                    startSquare.getPiece().setX_loc(startSquare.getY() * 75 + 5);
                    startSquare.getPiece().setY_loc(startSquare.getX() * 75 + 5);
                } else if (startSquare.getPiece().getIsWhite() && !endSquare.getPiece().getIsWhite()) {
                    redPieces--;
                    endSquare.getPiece().setIsCaptured(true);
                    endSquare.setPiece(startSquare.getPiece());
                    startSquare.setPiece(null);
                    endSquare.getPiece().setX(endSquare.getY());
                    endSquare.getPiece().setY(endSquare.getX());
                    pieceCaptured = true;
                    System.out.println("Black Pieces: " + blackPieces + "\nRed Pieces: " + redPieces);
                } else if (!startSquare.getPiece().getIsWhite() && endSquare.getPiece().getIsWhite()) {
                    blackPieces--;
                    endSquare.getPiece().setIsCaptured(true);
                    endSquare.setPiece(startSquare.getPiece());
                    startSquare.setPiece(null);
                    endSquare.getPiece().setX(endSquare.getY());
                    endSquare.getPiece().setY(endSquare.getX());
                    pieceCaptured = true;
                    System.out.println("Black Pieces: " + blackPieces + "\nRed Pieces: " + redPieces);
                }
            }
            else{
                startSquare.getPiece().setX_loc(startSquare.getY() * 75 + 5);
                startSquare.getPiece().setY_loc(startSquare.getX() * 75 + 5);
            }
        }
        return pieceCaptured;
    }

    public void printBoard() {
        for(int i = 0; i < 8; i++) {
            for(int j = 0; j < 8; j++) {
                if(this.CheckersBoardSquares[j][i].getPiece() != null) {
                    System.out.print("C");
                }
                else{
                    System.out.print("_");
                }
            }
            System.out.println();
        }
    }

    public boolean checkGameOver(){
        if(this.redPieces == 0 || this.blackPieces == 0) {
            System.out.println("The game is over");
            return true;
        }
        else {
            System.out.println("The game is not over");
            return false;

        }
    }

    public void resetBoard() {
        for(int i = 0; i < 8; i++) {
            for(int j = 0; j < 8; j++) {
                this.CheckersBoardSquares[i][j].setPiece(null);
            }
        }
        for(int i = 0; i < 8; i++){
            for(int j = 0; j < 8; j++){
                if(i == 0 && j % 2 == 1) {
                    Checker redChecker = new Checker(false, j, i);
                    this.CheckersBoardSquares[j][i].setPiece(redChecker);
                }
                else if(i == 1 && j % 2 == 0) {
                    Checker redChecker = new Checker(false,j, i);
                    this.CheckersBoardSquares[j][i].setPiece(redChecker);
                }
                else if(i == 2 && j % 2 == 1) {
                    Checker redChecker = new Checker(false,j, i);
                    this.CheckersBoardSquares[j][i].setPiece(redChecker);
                }
                else if(i == 5 && j % 2 == 0) {
                    Checker blackChecker = new Checker(true,j, i);
                    this.CheckersBoardSquares[j][i].setPiece(blackChecker);
                }
                else if(i == 6 && j % 2 == 1) {
                    Checker blackChecker = new Checker(true,j, i);
                    this.CheckersBoardSquares[j][i].setPiece(blackChecker);
                }
                else if(i == 7 && j % 2 == 0) {
                    Checker blackChecker = new Checker(true,j, i);
                    this.CheckersBoardSquares[j][i].setPiece(blackChecker);
                }
            }
        }
        this.blackPieces = 12;
        this.redPieces = 12;
        printBoard();
    }

    @Override
    public void setRedPieces(int x){
        this.redPieces = x;
    }
    @Override
    public int getRedPieces() {
        return this.redPieces;
    }
    @Override
    public void setBlackPieces(int x) {
        this.blackPieces = x;
    }
    @Override
    public int getBlackPieces() {
        return this.blackPieces;
    }
}

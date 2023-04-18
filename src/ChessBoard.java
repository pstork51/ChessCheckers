import java.awt.*;

public class ChessBoard extends Board{
    BoardSquare[][] ChessBoardSquares = new BoardSquare[8][8];
    public ChessBoard()
    {
        addSquares();
        addPieces();
    }
    @Override
    protected void addSquares() {
        Color whiteColor = new Color(255,255,255);
        Color greenColor = new Color(100, 160, 100);
        for(int i = 0; i < 8; i++) {
            for(int j = 0; j < 8; j++){
                BoardSquare square;
                if((i + j) % 2 == 0){
                    square = new BoardSquare(i, j, whiteColor, null);
                }
                else{
                square = new BoardSquare(i, j, greenColor, null);
                }
                this.ChessBoardSquares[j][i] = square;
            }
        }
    }
    @Override
    protected void addPieces() {
            for(int i = 0; i < 8; i++){
                    for(int j = 0; j < 8; j++){
                            if(i == 0 && j == 0 || (i == 0 && j == 7)){
                                    Rook blackRook = new Rook(false, j, i);
                                    this.ChessBoardSquares[j][i].setPiece(blackRook);
                            }
                            else if(i == 0 && j == 1 || (i == 0 && j == 6)) {
                                    Knight blackKnight = new Knight(false, j, i);
                                    this.ChessBoardSquares[j][i].setPiece(blackKnight);
                            }
                            else if(i == 0 && j == 2 || (i == 0 && j == 5)) {
                                    Bishop blackBishop = new Bishop(false, j, i);
                                    this.ChessBoardSquares[j][i].setPiece(blackBishop);
                            }
                            else if(i == 0 && j == 4) {
                                    King blackKing = new King(false, j, i);
                                    this.ChessBoardSquares[j][i].setPiece(blackKing);
                            }
                            else if(i == 0 && j == 3) {
                                    Queen blackQueen = new Queen(false, j, i);
                                    this.ChessBoardSquares[j][i].setPiece(blackQueen);
                            }
                            else if(i == 1){
                                    Pawn blackPawn = new Pawn(false, j, i);
                                    this.ChessBoardSquares[j][i].setPiece(blackPawn);
                            }
                            else if(i == 6){
                                    Pawn whitePawn = new Pawn(true, j, i);
                                    this.ChessBoardSquares[j][i].setPiece(whitePawn);
                            }
                            else if(i == 7 && j == 0 || (i == 7 && j == 7)){
                                    Rook whiteRook = new Rook(true, j, i);
                                    this.ChessBoardSquares[j][i].setPiece(whiteRook);
                            }
                            else if(i == 7 && j == 2 || (i == 7 && j == 5)) {
                                    Bishop whiteBishop = new Bishop(true, j, i);
                                    this.ChessBoardSquares[j][i].setPiece(whiteBishop);
                            }
                            else if(i == 7 && j == 1 || (i == 7 && j == 6)) {
                                    Knight whiteKnight = new Knight(true, j, i);
                                    this.ChessBoardSquares[j][i].setPiece(whiteKnight);
                            }
                            else if(i == 7 && j == 4) {
                                    King whiteKing = new King(true, j, i);
                                    this.ChessBoardSquares[j][i].setPiece(whiteKing);
                            }
                            else if(i == 7 && j == 3) {
                                    Queen whiteQueen = new Queen(true, j, i);
                                    this.ChessBoardSquares[j][i].setPiece(whiteQueen);
                            }
                    }
            }
    }
    @Override
    protected boolean movePieces(BoardSquare startSquare, BoardSquare endSquare) {
        //returns true if a piece is captured
        boolean pieceCaptured = false;
            if(startSquare.getPiece() != null) {
                if(startSquare.getPiece().canMove(this,startSquare,endSquare))
                    {
                        if (endSquare.getPiece() == null) {
                            endSquare.setPiece(startSquare.getPiece());
                            startSquare.setPiece(null);
                            endSquare.getPiece().setX(endSquare.getY());
                            endSquare.getPiece().setY(endSquare.getX());
                            endSquare.getPiece().setX_loc(endSquare.getY() * 75 + 5);
                            endSquare.getPiece().setY_loc(endSquare.getX() * 75 + 5);
                        } else if((startSquare.getPiece().getIsWhite() && endSquare.getPiece().getIsWhite())
                            ||(!startSquare.getPiece().getIsWhite() && !endSquare.getPiece().getIsWhite()) ){
                            startSquare.getPiece().setX_loc(startSquare.getY() * 75 + 5);
                            startSquare.getPiece().setY_loc(startSquare.getX() * 75 + 5);
                        } else if (startSquare.getPiece().getIsWhite() && !endSquare.getPiece().getIsWhite()) {
                            /*endSquare.getPiece().setIsCaptured(true);
                            endSquare.setPiece(startSquare.getPiece());
                            startSquare.setPiece(null);
                            endSquare.getPiece().setX(endSquare.getY());
                            endSquare.getPiece().setY(endSquare.getX());*/
                            pieceCaptured = true;
                        } else if (!startSquare.getPiece().getIsWhite() && endSquare.getPiece().getIsWhite()) {
                            /*endSquare.getPiece().setIsCaptured(true);
                            endSquare.setPiece(startSquare.getPiece());
                            startSquare.setPiece(null);
                            endSquare.getPiece().setX(endSquare.getY());
                            endSquare.getPiece().setY(endSquare.getX());*/
                            pieceCaptured = true;
                        }
                        printBoard();
                    }
                    else{
                         startSquare.getPiece().setX_loc(startSquare.getY() * 75 + 5);
                         startSquare.getPiece().setY_loc(startSquare.getX() * 75 + 5);
                    }
                }
            return pieceCaptured;
        }

    public BoardSquare getSquare(int x, int y) {
            return ChessBoardSquares[x][y];
            }

    public void printBoard() {
            for(int i = 0; i < 8; i++) {
                for(int j = 0; j < 8; j++) {
                    if(this.ChessBoardSquares[j][i].getPiece() != null) {
                    if (this.ChessBoardSquares[j][i].getPiece().getClass() == Rook.class) {
                        System.out.print("R");
                    } else if (this.ChessBoardSquares[j][i].getPiece().getClass() == Knight.class) {
                        System.out.print("N");
                    } else if (this.ChessBoardSquares[j][i].getPiece().getClass() == Bishop.class) {
                        System.out.print("B");
                    } else if (this.ChessBoardSquares[j][i].getPiece().getClass() == Queen.class) {
                        System.out.print("Q");
                    } else if (this.ChessBoardSquares[j][i].getPiece().getClass() == King.class) {
                        System.out.print("K");
                    } else if (this.ChessBoardSquares[j][i].getPiece().getClass() == Pawn.class) {
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


}

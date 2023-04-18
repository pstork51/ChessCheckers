import javax.swing.*;
import java.awt.*;

public class Checker extends Piece implements Drawable{
    private boolean isKing;

    public Checker(boolean isBlack, int x, int y){
        super(isBlack);

        this.x = x;
        this.y = y;
        this.x_loc = x * 75 + 5;
        this.y_loc = y * 75 + 5;

        if(isBlack){
            if(this.isKing){
                image = new ImageIcon("Images/RedCheckerKing.png");
            }
            else{
                image = new ImageIcon("Images/RedChecker.png");
            }
        }
        else {
            if(this.isKing){
                image = new ImageIcon("Images/BlackCheckerKing.png");
            }
            else {
                image = new ImageIcon("Images/BlackChecker.png");
            }
        }
    }

    @Override
    public void setIsKing(boolean x) {
        //this is very bad practice, but I am unable to add this function in Checkers class
        //so the Piece class will contain this method that is only used for one of its children
        this.isKing = x;
        System.out.println(" isCheckerKing: " + this.isKing);
        if(this.getIsWhite()) {
            this.image = new ImageIcon("RedCheckerKing.png");
        }
        else {
            this.image = new ImageIcon("BlackCheckerKing.png");
        }
    }


    @Override
    public boolean canMove(Board board, BoardSquare startMove, BoardSquare endMove) {
        if(startMove.getPiece().getIsWhite() && board.getRedMove()) {
            //redPiece
            if(this.isKing) {
                System.out.println("Trying to move a king");
                //means this piece is a king
                //above is normal functionality of checkers piece, below is additional for king
                if ((startMove.getY() == endMove.getY() + 1 || startMove.getY() == endMove.getY() - 1)
                        && (startMove.getX() == endMove.getX() - 1)) {
                    //one away from starting move and moving down on diagonal
                    if (endMove.getPiece() != null) {
                        // square is occupied
                        return false;
                    } else {
                        board.setRedMove(!board.getRedMove());
                        return true;
                    }
                } else if (((startMove.getY() - 2) == endMove.getY()) && ((startMove.getX() + 2) == endMove.getX())) {
                    //two diagonals away and backward (left)
                    if (board.getSquare(startMove.getY() - 2, startMove.getX() + 2).getPiece() != null) {
                        //checks if square 2 diagonals away is occupied
                        return false;
                    }

                    if (board.getSquare(startMove.getY() - 1, startMove.getX() + 1).getPiece() == null) {
                        return false;
                    } else if (board.getSquare(startMove.getY() - 1, startMove.getX() + 1).getPiece().getIsWhite()) {
                        return false;
                    } else {
                        board.getSquare(startMove.getY() - 1, startMove.getX() + 1).getPiece().setIsCaptured(true);
                        board.getSquare(startMove.getY() - 1, startMove.getX() + 1).setPiece(null);
                        board.setBlackPieces(board.getBlackPieces() - 1);
                        board.setRedMove(!board.getRedMove());
                        return true;
                    }
                } else if (((startMove.getY() + 2) == endMove.getY()) && ((startMove.getX() + 2) == endMove.getX())) {
                    //two diagonals away and forward (right)
                    if (board.getSquare(startMove.getY() + 2, startMove.getX() + 2).getPiece() != null) {
                        //checks if square 2 diagonals away is occupied
                        return false;
                    }

                    if (board.getSquare(startMove.getY() + 1, startMove.getX() + 1).getPiece() == null) {
                        return false;
                    } else if (board.getSquare(startMove.getY() + 1, startMove.getX() + 1).getPiece().getIsWhite()) {
                        return false;
                    } else {
                        board.getSquare(startMove.getY() + 1, startMove.getX() + 1).getPiece().setIsCaptured(true);
                        board.getSquare(startMove.getY() + 1, startMove.getX() + 1).setPiece(null);
                        board.setBlackPieces(board.getBlackPieces() - 1);
                        board.setRedMove(!board.getRedMove());
                        return true;
                    }
                }
            }
            if ((startMove.getY() == endMove.getY() + 1 || startMove.getY() == endMove.getY() - 1)
                    && (startMove.getX() == endMove.getX() + 1)) {
                //still in red pieces
                //one away from starting move and moving forward on diagonal
                if (endMove.getPiece() != null) {
                    // square is occupied
                    return false;
                } else {
                    board.setRedMove(!board.getRedMove());
                    return true;
                }
            } else if (((startMove.getY() - 2) == endMove.getY()) && ((startMove.getX() - 2) == endMove.getX())) {
                //two diagonals away and forward (left)
                if (board.getSquare(startMove.getY() - 2, startMove.getX() - 2).getPiece() != null) {
                    //checks if square 2 diagonals away is occupied
                    return false;
                }

                if (board.getSquare(startMove.getY() - 1, startMove.getX() - 1).getPiece() == null) {
                    return false;
                } else if (board.getSquare(startMove.getY() - 1, startMove.getX() - 1).getPiece().getIsWhite()) {
                    return false;
                } else {
                    board.getSquare(startMove.getY() - 1, startMove.getX() - 1).getPiece().setIsCaptured(true);
                    board.getSquare(startMove.getY() - 1, startMove.getX() - 1).setPiece(null);
                    board.setBlackPieces(board.getBlackPieces() - 1);
                    board.setRedMove(!board.getRedMove());
                    return true;
                }
            } else if (((startMove.getY() + 2) == endMove.getY()) && ((startMove.getX() - 2) == endMove.getX())) {
                //two diagonals away and forward (right)
                if (board.getSquare(startMove.getY() + 2, startMove.getX() - 2).getPiece() != null) {
                    //checks if square 2 diagonals away is occupied
                    return false;
                }

                if (board.getSquare(startMove.getY() + 1, startMove.getX() - 1).getPiece() == null) {
                    return false;
                } else if (board.getSquare(startMove.getY() + 1, startMove.getX() - 1).getPiece().getIsWhite()) {
                    return false;
                } else {
                    board.getSquare(startMove.getY() + 1, startMove.getX() - 1).getPiece().setIsCaptured(true);
                    board.getSquare(startMove.getY() + 1, startMove.getX() - 1).setPiece(null);
                    board.setBlackPieces(board.getBlackPieces() - 1);
                    board.setRedMove(!board.getRedMove());
                    return true;
                }
            }
        }

        else if (!startMove.getPiece().getIsWhite() && !board.getRedMove())
        {
            //black piece
            if(this.isKing) {
                //means this piece is a king
                //above is normal functionality of checkers piece, below is additional for king
                if ((startMove.getY() == endMove.getY() + 1 || startMove.getY() == endMove.getY() - 1)
                        && (startMove.getX() == endMove.getX() + 1)) {
                    //one away from starting move and moving down on diagonal
                    if (endMove.getPiece() != null) {
                        // square is occupied
                        return false;
                    } else {
                        board.setRedMove(!board.getRedMove());
                        return true;
                    }
                } else if (((startMove.getY() - 2) == endMove.getY()) && ((startMove.getX() - 2) == endMove.getX())) {
                    //two diagonals away and backward (left)
                    if (board.getSquare(startMove.getY() - 2, startMove.getX() - 2).getPiece() != null) {
                        //checks if square 2 diagonals away is occupied
                        return false;
                    }

                    if (board.getSquare(startMove.getY() - 1, startMove.getX() - 1).getPiece() == null) {
                        return false;
                    } else if (!board.getSquare(startMove.getY() - 1, startMove.getX() - 1).getPiece().getIsWhite()) {
                        return false;
                    } else {
                        board.getSquare(startMove.getY() - 1, startMove.getX() - 1).getPiece().setIsCaptured(true);
                        board.getSquare(startMove.getY() - 1, startMove.getX() - 1).setPiece(null);
                        board.setRedPieces(board.getRedPieces() - 1);
                        board.setRedMove(!board.getRedMove());
                        return true;
                    }
                } else if (((startMove.getY() + 2) == endMove.getY()) && ((startMove.getX() - 2) == endMove.getX())) {
                    //two diagonals away and forward (right)
                    if (board.getSquare(startMove.getY() + 2, startMove.getX() - 2).getPiece() != null) {
                        //checks if square 2 diagonals away is occupied
                        return false;
                    }

                    if (board.getSquare(startMove.getY() + 1, startMove.getX() - 1).getPiece() == null) {
                        return false;
                    } else if (!board.getSquare(startMove.getY() + 1, startMove.getX() - 1).getPiece().getIsWhite()) {
                        return false;
                    } else {
                        board.getSquare(startMove.getY() + 1, startMove.getX() - 1).getPiece().setIsCaptured(true);
                        board.getSquare(startMove.getY() + 1, startMove.getX() - 1).setPiece(null);
                        board.setRedPieces(board.getRedPieces() - 1);
                        board.setRedMove(!board.getRedMove());
                        return true;
                    }
                }
            }

            if ((startMove.getY() == endMove.getY() + 1 || startMove.getY() == endMove.getY() - 1)
                    && (startMove.getX() == endMove.getX() - 1)) {
                //one away from starting move and moving down on diagonal
                if (endMove.getPiece() != null) {
                    // square is occupied
                    return false;
                } else {
                    board.setRedMove(!board.getRedMove());
                    return true;
                }
            } else if (((startMove.getY() - 2) == endMove.getY()) && ((startMove.getX() + 2) == endMove.getX())) {
                //two diagonals away and backward (left)
                if (board.getSquare(startMove.getY() - 2, startMove.getX() + 2).getPiece() != null) {
                    //checks if square 2 diagonals away is occupied
                    return false;
                }

                if (board.getSquare(startMove.getY() - 1, startMove.getX() + 1).getPiece() == null) {
                    return false;
                } else if (!board.getSquare(startMove.getY() - 1, startMove.getX() + 1).getPiece().getIsWhite()) {
                    return false;
                } else {
                    board.getSquare(startMove.getY() - 1, startMove.getX() + 1).getPiece().setIsCaptured(true);
                    board.getSquare(startMove.getY() - 1, startMove.getX() + 1).setPiece(null);
                    board.setRedPieces(board.getRedPieces() - 1);
                    board.setRedMove(!board.getRedMove());
                    return true;
                }
            } else if (((startMove.getY() + 2) == endMove.getY()) && ((startMove.getX() + 2) == endMove.getX())) {
                //two diagonals away and forward (right)
                if (board.getSquare(startMove.getY() + 2, startMove.getX() + 2).getPiece() != null) {
                    //checks if square 2 diagonals away is occupied
                    return false;
                }

                if (board.getSquare(startMove.getY() + 1, startMove.getX() + 1).getPiece() == null) {
                    return false;
                } else if (!board.getSquare(startMove.getY() + 1, startMove.getX() + 1).getPiece().getIsWhite()) {
                    return false;
                } else {
                    board.getSquare(startMove.getY() + 1, startMove.getX() + 1).getPiece().setIsCaptured(true);
                    board.getSquare(startMove.getY() + 1, startMove.getX() + 1).setPiece(null);
                    board.setRedPieces(board.getRedPieces() - 1);
                    board.setRedMove(!board.getRedMove());
                    return true;
                }
            }
        }
    return false;
    }
}


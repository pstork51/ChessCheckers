public class Game {
    ChessGame chessGame;
    CheckersGame checkersGame;
    boolean displayChess;

    public Game() {

        this.displayChess = true;
        this.checkersGame = new CheckersGame();
        this.chessGame = new ChessGame();
    }

    public boolean playGame(){
        //first while loop checks for a piece captured in chess game
        if(this.displayChess) {
            this.checkersGame.setVisible(false);
            this.chessGame.setLocation(this.checkersGame.getLocation());
            this.chessGame.setVisible(true);
            while(!this.chessGame.pieceCaptured){
                try{
                    Thread.sleep(500);
                }
                catch(java.lang.InterruptedException e) {
                    System.out.println(e);
                }
            }
            this.displayChess = false;
            return true;
        }
        else {
            this.chessGame.setVisible(false);
            this.checkersGame.setLocation(this.chessGame.getLocation());
            this.checkersGame.setVisible(true);
            while(this.checkersGame.winner == 'N'){
                try{
                    Thread.sleep(500);
                }
                catch(java.lang.InterruptedException e) {
                    System.out.println(e);
                }
            }
            if(this.checkersGame.winner == 'R'){
                this.chessGame.capturePiece(true);
            }
            else{
                this.chessGame.capturePiece(false);
            }
            this.chessGame.pieceCaptured = false;
            this.displayChess = true;
            this.checkersGame.reset();
            this.chessGame.printGame();
            return true;
        }
    }
}

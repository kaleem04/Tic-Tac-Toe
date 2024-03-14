package com.example.tic_tac_toe;

public class GameLogic {

    private int[][] gameBoard;
    private int player;

    GameLogic() {

        gameBoard = new int[3][3];

        for (int r = 0; r < 3; r++) {

            for (int c = 0; c < 3; c++) {

                gameBoard[r][c] = 0;
            }

        }
    }


    public int[][] getGameBoard() {
        return gameBoard;
    }

    public boolean updateGame(int row, int col) {

        if (gameBoard[row - 1][col - 1] == 0) {
            gameBoard[row - 1][col - 1] = player;
            return true;
        } else {

            return false;

        }
    }


    public void setPlayer(int player) {
        this.player = player;
    }

    public int getPlayer(){
        return player;
    }
}



package com.example.chromatic.objects;

import android.util.Log;

import com.example.chromatic.R;

import static android.content.ContentValues.TAG;

public class Board {

    public int[][] boardId = new int[7][7];
    public Color[][] boardColor = new Color[7][7];
    private ColorManager colorManager = new ColorManager();

    public Board(){
        setBoardId();
        setBoardColor();
    }

    private void setBoardId(){

        boardId[1][1] = R.id.b11;
        boardId[1][2] = R.id.b12;
        boardId[1][3] = R.id.b13;
        boardId[1][4] = R.id.b14;

        boardId[2][1] = R.id.b21;
        boardId[2][2] = R.id.b22;
        boardId[2][3] = R.id.b23;
        boardId[2][4] = R.id.b24;

        boardId[3][1] = R.id.b31;
        boardId[3][2] = R.id.b32;
        boardId[3][3] = R.id.b33;
        boardId[3][4] = R.id.b34;

        boardId[4][1] = R.id.b41;
        boardId[4][2] = R.id.b42;
        boardId[4][3] = R.id.b43;
        boardId[4][4] = R.id.b44;
    }

    private void setBoardColor(){
        for(int i = 1; i <= 4; i++)
            for(int j = 1; j <= 4; j++){
                boardColor[i][j] = colorManager.cellDefault;
            }
        for(int i = 0; i <= 5; i++)
            boardColor[i][0] = boardColor[0][i] = boardColor[i][5] = boardColor[5][i] = colorManager.black;

    }

    public int getCellId(int i, int j){
        return boardId[i][j];
    }

    public Color getCellColor(int i, int j){
        return boardColor[i][j];
    }

    public void setCellColor(int i, int j, Color newColor){
        boardColor[i][j] = newColor;
    }

    public int getNrEmptyCells(){
        int nrEmptyCells = 0;
        for(int i = 1; i <= 4; i++)
            for(int  j = 1; j <= 4; j++){
                if(colorManager.areEquals(boardColor[i][j],colorManager.cellDefault))
                    nrEmptyCells ++;
            }
        return nrEmptyCells;
    }

}

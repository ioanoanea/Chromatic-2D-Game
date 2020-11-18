package com.example.chromatic.game;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.chromatic.R;
import com.example.chromatic.modules.OnSwipeTouchListener;
import com.example.chromatic.objects.Board;
import com.example.chromatic.objects.Color;
import com.example.chromatic.objects.ColorManager;
import com.example.chromatic.objects.ScoreManager;

import java.util.Random;

public class PlayScreen extends AppCompatActivity {

    private Button back;
    private TextView scoreView, highScoreView;
    private Button cell;
    private int score = 0;
    private Dialog gameOverDialog;
    ColorManager colorManager = new ColorManager();
    Board board = new Board();
    ScoreManager scoreManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_screen);

        scoreManager = new ScoreManager(this);
        gameOverDialog = new Dialog(this);

        setViews();

        colorRandomCell(5,5,colorManager.red);
        colorRandomCell(5,5,colorManager.blue);
        colorRandomCell(5,5,colorManager.yellow);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PlayScreen.super.onBackPressed();
            }
        });

    }

    private void setViews(){
        scoreView = findViewById(R.id.score);
        highScoreView = findViewById(R.id.high_score);
        back = findViewById(R.id.back);
        scoreView.setText("Score: 0");
        highScoreView.setText("Best: " + String.valueOf(scoreManager.getHighScore()));
    }

    private void setCellColor(final int i, final int j, final Color color){
        cell = findViewById(board.getCellId(i,j));
        if(colorManager.areEquals(color,colorManager.red)) {
            cell.setBackgroundResource(R.drawable.red);
        } else if(colorManager.areEquals(color,colorManager.blue)) {
            cell.setBackgroundResource(R.drawable.blue);
        } else if(colorManager.areEquals(color,colorManager.yellow)) {
            cell.setBackgroundResource(R.drawable.yellow);
        } else if(colorManager.areEquals(color,colorManager.green)) {
            cell.setBackgroundResource(R.drawable.green);
            score += 10;
            scoreView.setText("Score:" + String.valueOf(score));
        } else if(colorManager.areEquals(color,colorManager.orange)) {
            cell.setBackgroundResource(R.drawable.orange);
            score += 10;
            scoreView.setText("Score:" + String.valueOf(score));
        } else if(colorManager.areEquals(color,colorManager.purple)) {
            cell.setBackgroundResource(R.drawable.purple);
            score += 10;
            scoreView.setText("Score:" + String.valueOf(score));
        } else if(colorManager.areEquals(color,colorManager.black)) {
            cell.setBackgroundResource(R.drawable.black);
            score += 20;
            scoreView.setText("Score:" + String.valueOf(score));
            setAnimation(cell,R.drawable.black_color_animation);
        } else cell.setBackgroundResource(R.drawable.box);

        if(colorManager.areEquals(color,colorManager.black))
            board.setCellColor(i,j,colorManager.cellDefault);
        else board.setCellColor(i,j,color);
        enableAction(i,j,cell);

    }

    private void enableAction(final int i, final int j, View view){
        view.setOnTouchListener(new OnSwipeTouchListener(this){
            @Override
            public void onSwipeLeft() {
                moveLeft(i,j);
            }

            @Override
            public void onSwipeRight() {
                moveRight(i,j);
            }

            @Override
            public void onSwipeTop() {
                moveUp(i,j);
            }

            @Override
            public void onSwipeBottom() {
                moveDown(i,j);
            }
        });
    }

    private void moveLeft(int i, int j){
        Color newColor = colorManager.sumColor(board.getCellColor(i,j),board.getCellColor(i,j-1));
        if(!colorManager.areEquals(board.getCellColor(i,j),colorManager.cellDefault) && colorManager.checkColor(newColor)){
            setSlideAnimation(i,j,R.anim.slide_left);
            setCellColor(i,j-1,newColor);
            colorRandomCell(5,5,colorManager.getRandomColor());
            if(gameOver()) {
                showgameOverPopUp();
                //Toast.makeText(this, "Game Over!", Toast.LENGTH_SHORT).show();
              if(score > scoreManager.getHighScore())
                  scoreManager.updateHighScore(score);
            }
        }
    }

    private void moveRight(int i, int j){
        Color newColor = colorManager.sumColor(board.getCellColor(i,j),board.getCellColor(i,j+1));
        if(!colorManager.areEquals(board.getCellColor(i,j),colorManager.cellDefault) && colorManager.checkColor(newColor)){
            setSlideAnimation(i,j,R.anim.slide_right);
            setCellColor(i, j+1,newColor);
            colorRandomCell(5,5,colorManager.getRandomColor());
            if(gameOver()) {
                showgameOverPopUp();
                //Toast.makeText(this, "Game Over!", Toast.LENGTH_SHORT).show();
                if(score > scoreManager.getHighScore())
                    scoreManager.updateHighScore(score);
            }
        }
    }

    private void moveUp(int i, int j){
        Color newColor = colorManager.sumColor(board.getCellColor(i,j),board.getCellColor(i-1,j));
        if(!colorManager.areEquals(board.getCellColor(i,j),colorManager.cellDefault) && colorManager.checkColor(newColor)){
            setSlideAnimation(i,j,R.anim.slide_up);
            setCellColor(i-1,j,newColor);
            colorRandomCell(5,5,colorManager.getRandomColor());
            if(gameOver()) {
                showgameOverPopUp();
                //Toast.makeText(this, "Game Over!", Toast.LENGTH_SHORT).show();
                if(score > scoreManager.getHighScore())
                    scoreManager.updateHighScore(score);
            }
        }
    }

    private void moveDown(int i, int j){
        Color newColor = colorManager.sumColor(board.getCellColor(i,j),board.getCellColor(i+1,j));
        if(!colorManager.areEquals(board.getCellColor(i,j),colorManager.cellDefault) && colorManager.checkColor(newColor)){
            setSlideAnimation(i,j,R.anim.slide_down);
            setCellColor(i+1,j,newColor);
            colorRandomCell(5,5,colorManager.getRandomColor());
            if(gameOver()) {
                showgameOverPopUp();
                //Toast.makeText(this, "Game Over!", Toast.LENGTH_SHORT).show();
                if(score > scoreManager.getHighScore())
                    scoreManager.updateHighScore(score);
            }
        }
    }


    private void colorRandomCell(int x, int y, Color color){
        Random random = new Random();

        int i = random.nextInt(y);
        int j = random.nextInt(x);

        if(board.getNrEmptyCells() > 0)
            if (!colorManager.areEquals(board.getCellColor(i, j), colorManager.cellDefault))
                colorRandomCell(x, y, color);
            else setCellColor(i, j, color);

    }

    private Boolean gameOver(){
        for(int i = 1; i <= 4; i++)
            for(int j = 1; j <= 4; j++){
               if(colorManager.checkColor(colorManager.sumColor(board.getCellColor(i,j),board.getCellColor(i,j-1)))
               || colorManager.checkColor(colorManager.sumColor(board.getCellColor(i,j),board.getCellColor(i,j+1)))
               || colorManager.checkColor(colorManager.sumColor(board.getCellColor(i,j),board.getCellColor(i-1,j)))
               || colorManager.checkColor(colorManager.sumColor(board.getCellColor(i,j),board.getCellColor(i+1,j))))
                   return false;
            }

        return true;
    }

    private void setAnimation(View view, int resource){
        AnimationDrawable animation;
        view.setBackgroundResource(resource);
        animation = (AnimationDrawable) view.getBackground();

        animation.start();
    }

    public void setSlideAnimation(int i, int j, int resource){
        Button button = findViewById(board.getCellId(i,j));
        Animation animation = AnimationUtils.loadAnimation(this,resource);
        button.startAnimation(animation);
        setCellColor(i,j,colorManager.cellDefault);
    }

    private void showgameOverPopUp(){
        Button replay, exit;
        gameOverDialog.setContentView(R.layout.game_over);
        replay = gameOverDialog.findViewById(R.id.play_again);
        exit = gameOverDialog.findViewById(R.id.exit);

        replay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gameOverDialog.dismiss();
            }
        });
        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PlayScreen.super.onBackPressed();
            }
        });

        gameOverDialog.show();
    }
}

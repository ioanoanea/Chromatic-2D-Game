package com.example.chromatic.objects;

import android.content.Context;
import android.content.SharedPreferences;

public class ScoreManager {

    SharedPreferences sharedPreferences;
    public  SharedPreferences.Editor editor;
    public Context context;

    public ScoreManager(Context context){
        this.context = context;
        sharedPreferences = context.getSharedPreferences("HIGH_SCORE",0);
        editor = sharedPreferences.edit();
    }

    public void updateHighScore(int score){
        editor.putInt("high_score",score);
        editor.apply();
    }

    public int getHighScore(){
        return sharedPreferences.getInt("high_score",0);
    }
}

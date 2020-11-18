package com.example.chromatic;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.chromatic.game.PlayScreen;
import com.example.chromatic.objects.ScoreManager;

public class HomeScreenActivity extends AppCompatActivity {

    private Button play;
    private TextView highScore;
    private ScoreManager scoreManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);

        scoreManager = new ScoreManager(this);
        setViews();
        highScore.setText("HighScore: \n" + String.valueOf(scoreManager.getHighScore()));

        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeScreenActivity.this, PlayScreen.class);
                startActivity(intent);
            }
        });
    }

    private void setViews(){
        play = findViewById(R.id.play);
        highScore = findViewById(R.id.high_score);
    }
}

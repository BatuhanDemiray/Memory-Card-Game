package com.example.hafizaoyunu;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class ResultWin extends AppCompatActivity {

    TextView txtScoreWin, txtFailWin;
    Button btnMenuWin, btnExitWin;
    Context context = this;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE); //will hide the title
        getSupportActionBar().hide(); // hide the title bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN); //enable full screen
        setContentView(R.layout.result_win);

        txtScoreWin = findViewById(R.id.txtScoreWin);
        txtFailWin = findViewById(R.id.txtFailWin);
        btnMenuWin = findViewById(R.id.btnMenuWin);
        btnExitWin = findViewById(R.id.btnExitWin);

        Intent intent = getIntent();
        txtScoreWin.setText("Skorunuz: " + intent.getIntExtra("score",0));
        txtFailWin.setText("Hata Sayısı: " + intent.getIntExtra("fail",0));

        btnMenuWin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, Menu.class);
                startActivity(intent);
            }
        });

        btnExitWin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                moveTaskToBack(true);
                android.os.Process.killProcess(android.os.Process.myPid());
                System.exit(1);
            }
        });
    }

    @Override
    public void onBackPressed(){
        // Geri tuşunu pasif hale getirme //
    }
}

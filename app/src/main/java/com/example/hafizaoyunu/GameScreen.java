package com.example.hafizaoyunu;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.GridLayout;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Locale;

public class GameScreen extends AppCompatActivity {

    int lastCard = 0;
    int score = 0;
    int fail = 0;
    GridLayout glCards;
    TextView txtTimer;
    ImageButton btnBack;
    CountDownTimer countDownTimer;
    Context context = this;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE); //will hide the title
        getSupportActionBar().hide(); // hide the title bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN); //enable full screen
        setContentView(R.layout.gamescreen);


        glCards = findViewById(R.id.glCards);
        txtTimer = findViewById(R.id.txtTimer);
        btnBack = findViewById(R.id.btnBack);


        Intent intent = getIntent();
        int num = intent.getIntExtra("numbersId", 0);
        int flo = intent.getIntExtra("flowersId", 0);
        int emo = intent.getIntExtra("emoteId", 0);
        int spo = intent.getIntExtra("sportsId", 0);
        int shp = intent.getIntExtra("shapeId", 0);
        int dck = intent.getIntExtra("deckId", 0);

        int milisec = intent.getIntExtra("milisec",0);

        if (num == 1)
            numbers();
        else if (flo == 2)
            flowers();
        else if (emo == 3)
            emote();
        else if (spo == 4)
            sports();
        else if (shp == 5)
            shape();
        else if (dck == 6)
            deck();

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    this.finalize();
                    countDownTimer.cancel();
                    Intent intent = new Intent(context, Menu.class);
                    startActivity(intent);
                }
                catch (Throwable throwable){
                    throwable.printStackTrace();
                }
            }
        });

                        // CountDown Timer //
        countDownTimer = new CountDownTimer(milisec, 1000) {
            @Override
            public void onTick(long l) {
                int min = (int) (l / 1000) / 60;
                int sec = (int) (l / 1000) % 60;
                String timerformat = String.format(Locale.getDefault(), "%02d:%02d", min, sec);
                txtTimer.setText("Kalan Süre: " + timerformat);
            }

            @Override
            public void onFinish() {
                Toast.makeText(context, "Süre Bitti", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(context, ResultLose.class);
                intent.putExtra("score_lose", score);
                intent.putExtra("fail_lose", fail);
                startActivity(intent);
            }
        }.start();

    }

    @Override
    public void onBackPressed(){
        // Geri tuşunu pasif hale getirme //
    } // Back Button Disable

    public void numbers()
    {
        glCards = findViewById(R.id.glCards);
        final card_numbers cards[] = new card_numbers[16];

        for (int i = 1; i <= 16; i++) {
            cards[i-1] = new card_numbers(context, i);
            cards[i-1].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) { // Açık-Kapalı Durum Ayarlama
                    final card_numbers c = (card_numbers)view;
                    c.turn();
                    if (lastCard > 0)
                    {
                        final card_numbers c2 = (card_numbers)findViewById(lastCard);

                        if (c.frontID == c2.frontID && c.getId() != c2.getId())
                        {
                            // Eşleştiler
                            c.isTurn = false;
                            c2.isTurn = false;
                            score++;
                            lastCard = 0;
                            if (score == 8){
                                try {
                                    this.finalize();
                                    countDownTimer.cancel();
                                    Intent intent = new Intent(context, ResultWin.class);
                                    intent.putExtra("score",score);
                                    intent.putExtra("fail",fail);
                                    startActivity(intent);
                                }
                                catch (Throwable throwable){
                                    throwable.printStackTrace();
                                }
                            }
                        }
                        else
                        {
                            //Eşleşmediler geri çevir
                            Handler h = new Handler();
                            h.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    c.turn();
                                    c2.turn();
                                }
                            },500);
                            lastCard = 0;
                            fail++;
                        }
                    }
                    else
                    {
                        lastCard = c.getId();
                    }
                }
            });
        }

        // Karıştır
        for (int j = 0; j < 16; j++) {
            int random = (int)(Math.random() * 16);
            card_numbers c = cards[random];
            cards[random] = cards[j];
            cards[j] = c;
        }

        // Yazdır
        for (int j = 0; j < 16; j++) {
            glCards.addView(cards[j]);
        }
    }

    public void flowers()
    {
        glCards = findViewById(R.id.glCards);

        card_flowers cards[] = new card_flowers[16];

        for (int i = 1; i <= 16; i++) {
            cards[i-1] = new card_flowers(context, i);
            cards[i-1].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) { // Açık-Kapalı Durum Ayarlama
                    final card_flowers c = (card_flowers)view;
                    c.turn();
                    if (lastCard > 0)
                    {
                        final card_flowers c2 = (card_flowers)findViewById(lastCard);

                        if (c.frontID == c2.frontID && c.getId() != c2.getId())
                        {
                            // Eşleştiler
                            c.isTurn = false;
                            c2.isTurn = false;
                            score++;
                            lastCard = 0;
                            if (score == 8){
                                try {
                                    this.finalize();
                                    countDownTimer.cancel();
                                    Intent intent = new Intent(context, ResultWin.class);
                                    intent.putExtra("score",score);
                                    intent.putExtra("fail",fail);
                                    startActivity(intent);
                                }
                                catch (Throwable throwable){
                                    throwable.printStackTrace();
                                }
                            }
                        }
                        else
                        {
                            //Eşleşmediler geri çevir
                            Handler h = new Handler();
                            h.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    c.turn();
                                    c2.turn();
                                }
                            },500);
                            lastCard = 0;
                            fail++;
                        }
                    }
                    else
                    {
                        lastCard = c.getId();
                    }
                }
            });
        }

        // Karıştır
        for (int j = 0; j < 16; j++) {
            int random = (int)(Math.random() * 16);
            card_flowers c = cards[random];
            cards[random] = cards[j];
            cards[j] = c;
        }

        // Yazdır
        for (int j = 0; j < 16; j++) {
            glCards.addView(cards[j]);
        }
    }

    public void emote()
    {
        glCards = findViewById(R.id.glCards);

        card_emote cards[] = new card_emote[16];

        for (int i = 1; i <= 16; i++) {
            cards[i-1] = new card_emote(context, i);
            cards[i-1].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) { // Açık-Kapalı Durum Ayarlama
                    final card_emote c = (card_emote)view;
                    c.turn();
                    if (lastCard > 0)
                    {
                        final card_emote c2 = (card_emote)findViewById(lastCard);

                        if (c.frontID == c2.frontID && c.getId() != c2.getId())
                        {
                            // Eşleştiler
                            c.isTurn = false;
                            c2.isTurn = false;
                            score++;
                            lastCard = 0;
                            if (score == 8){
                                try {
                                    this.finalize();
                                    countDownTimer.cancel();
                                    Intent intent = new Intent(context, ResultWin.class);
                                    intent.putExtra("score",score);
                                    intent.putExtra("fail",fail);
                                    startActivity(intent);
                                }
                                catch (Throwable throwable){
                                    throwable.printStackTrace();
                                }
                            }
                        }
                        else
                        {
                            //Eşleşmediler geri çevir
                            Handler h = new Handler();
                            h.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    c.turn();
                                    c2.turn();
                                }
                            },500);
                            lastCard = 0;
                            fail++;
                        }
                    }
                    else
                    {
                        lastCard = c.getId();
                    }
                }
            });
        }

        // Karıştır
        for (int j = 0; j < 16; j++) {
            int random = (int)(Math.random() * 16);
            card_emote c = cards[random];
            cards[random] = cards[j];
            cards[j] = c;
        }

        // Yazdır
        for (int j = 0; j < 16; j++) {
            glCards.addView(cards[j]);
        }
    }

    public void sports()
    {
        glCards = findViewById(R.id.glCards);

        card_sports cards[] = new card_sports[16];

        for (int i = 1; i <= 16; i++) {
            cards[i-1] = new card_sports(context, i);
            cards[i-1].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) { // Açık-Kapalı Durum Ayarlama
                    final card_sports c = (card_sports)view;
                    c.turn();
                    if (lastCard > 0)
                    {
                        final card_sports c2 = (card_sports)findViewById(lastCard);

                        if (c.frontID == c2.frontID && c.getId() != c2.getId())
                        {
                            // Eşleştiler
                            c.isTurn = false;
                            c2.isTurn = false;
                            score++;
                            lastCard = 0;
                            if (score == 8){
                                try {
                                    this.finalize();
                                    countDownTimer.cancel();
                                    Intent intent = new Intent(context, ResultWin.class);
                                    intent.putExtra("score",score);
                                    intent.putExtra("fail",fail);
                                    startActivity(intent);
                                }
                                catch (Throwable throwable){
                                    throwable.printStackTrace();
                                }
                            }
                        }
                        else
                        {
                            //Eşleşmediler geri çevir
                            Handler h = new Handler();
                            h.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    c.turn();
                                    c2.turn();
                                }
                            },500);
                            lastCard = 0;
                            fail++;
                        }
                    }
                    else
                    {
                        lastCard = c.getId();
                    }
                }
            });
        }

        // Karıştır
        for (int j = 0; j < 16; j++) {
            int random = (int)(Math.random() * 16);
            card_sports c = cards[random];
            cards[random] = cards[j];
            cards[j] = c;
        }

        // Yazdır
        for (int j = 0; j < 16; j++) {
            glCards.addView(cards[j]);
        }
    }

    public void shape()
    {
        glCards = findViewById(R.id.glCards);

        card_shape cards[] = new card_shape[16];

        for (int i = 1; i <= 16; i++) {
            cards[i-1] = new card_shape(context, i);
            cards[i-1].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) { // Açık-Kapalı Durum Ayarlama
                    final card_shape c = (card_shape)view;
                    c.turn();
                    if (lastCard > 0)
                    {
                        final card_shape c2 = (card_shape)findViewById(lastCard);

                        if (c.frontID == c2.frontID && c.getId() != c2.getId())
                        {
                            // Eşleştiler
                            c.isTurn = false;
                            c2.isTurn = false;
                            score++;
                            lastCard = 0;
                            if (score == 8){
                                try {
                                    this.finalize();
                                    countDownTimer.cancel();
                                    Intent intent = new Intent(context, ResultWin.class);
                                    intent.putExtra("score",score);
                                    intent.putExtra("fail",fail);
                                    startActivity(intent);
                                }
                                catch (Throwable throwable){
                                    throwable.printStackTrace();
                                }
                            }
                        }
                        else
                        {
                            //Eşleşmediler geri çevir
                            Handler h = new Handler();
                            h.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    c.turn();
                                    c2.turn();
                                }
                            },500);
                            lastCard = 0;
                            fail++;
                        }
                    }
                    else
                    {
                        lastCard = c.getId();
                    }
                }
            });
        }

        // Karıştır
        for (int j = 0; j < 16; j++) {
            int random = (int)(Math.random() * 16);
            card_shape c = cards[random];
            cards[random] = cards[j];
            cards[j] = c;
        }

        // Yazdır
        for (int j = 0; j < 16; j++) {
            glCards.addView(cards[j]);
        }
    }

    public void deck()
    {
        glCards = findViewById(R.id.glCards);

        card_deck cards[] = new card_deck[16];

        for (int i = 1; i <= 16; i++) {
            cards[i-1] = new card_deck(context, i);
            cards[i-1].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) { // Açık-Kapalı Durum Ayarlama
                    final card_deck c = (card_deck)view;
                    c.turn();
                    if (lastCard > 0)
                    {
                        final card_deck c2 = (card_deck)findViewById(lastCard);

                        if (c.frontID == c2.frontID && c.getId() != c2.getId())
                        {
                            // Eşleştiler
                            c.isTurn = false;
                            c2.isTurn = false;
                            score++;
                            lastCard = 0;
                            if (score == 8){
                                try {
                                    this.finalize();
                                    countDownTimer.cancel();
                                    Intent intent = new Intent(context, ResultWin.class);
                                    intent.putExtra("score",score);
                                    intent.putExtra("fail",fail);
                                    startActivity(intent);
                                }
                                catch (Throwable throwable){
                                    throwable.printStackTrace();
                                }
                            }
                        }
                        else
                        {
                            //Eşleşmediler geri çevir
                            Handler h = new Handler();
                            h.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    c.turn();
                                    c2.turn();
                                }
                            },500);
                            lastCard = 0;
                            fail++;
                        }
                    }
                    else
                    {
                        lastCard = c.getId();
                    }
                }
            });
        }

        // Karıştır
        for (int j = 0; j < 16; j++) {
            int random = (int)(Math.random() * 16);
            card_deck c = cards[random];
            cards[random] = cards[j];
            cards[j] = c;
        }

        // Yazdır
        for (int j = 0; j < 16; j++) {
            glCards.addView(cards[j]);
        }
    }
}

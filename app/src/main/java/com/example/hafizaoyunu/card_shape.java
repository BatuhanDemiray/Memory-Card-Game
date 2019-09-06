package com.example.hafizaoyunu;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.Drawable;

import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatDrawableManager;

public class card_shape extends AppCompatButton {

    boolean isOpen = false;
    boolean isTurn = true;
    int backID;
    int frontID;
    Drawable back, front;

    @SuppressLint("RestrictedApi")
    public card_shape(Context context, int id) {
        super(context);
        setId(id);
        backID = R.drawable.cardback;

        if (id % 8 == 1)
            frontID = R.drawable.shp1;
        else if (id % 8 == 2)
            frontID = R.drawable.shp2;
        else if (id % 8 == 3)
            frontID = R.drawable.shp3;
        else if (id % 8 == 4)
            frontID = R.drawable.shp4;
        else if (id % 8 == 5)
            frontID = R.drawable.shp5;
        else if (id % 8 == 6)
            frontID = R.drawable.shp6;
        else if (id % 8 == 7)
            frontID = R.drawable.shp7;
        else if (id % 8 == 0)
            frontID = R.drawable.shp8;

        back = AppCompatDrawableManager.get().getDrawable(context,backID);
        front = AppCompatDrawableManager.get().getDrawable(context,frontID);
        setBackground(back);
    }

    public void turn()
    {
        if (isTurn)
        {
            if (!isOpen) // Kapalı ise
            {
                setBackground(front);
                isOpen = true;
            }
            else
            {
                setBackground(back);
                isOpen = false;
            }
        }
    }
}

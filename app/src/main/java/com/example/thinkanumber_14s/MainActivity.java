package com.example.thinkanumber_14s;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private ImageView imageViewElet1, imageViewElet2,
            imageViewElet3, imageViewElet4, imageViewElet5;
    private TextView textViewTipp;
    private Button buttonNovel, buttonCsokkent,
            buttonTipp, buttonKonnyu, buttonNehez;
    private int gondoltSzam, tippeltSzam, elet, maxSzam;
    private AlertDialog.Builder builderJatekVege, builderNehezseg;
    private Toast egyediToast;
    private boolean nehezseg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();

        //ujJatek();

        buttonTipp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (gondoltSzam < tippeltSzam) {
                    Toast.makeText(MainActivity.this,
                            "A gondolt szám kisebbb", Toast.LENGTH_SHORT).show();
                    eletLevon();
                } else if (gondoltSzam > tippeltSzam) {
                    Toast.makeText(MainActivity.this,
                            "A gondolt szám nagyobb", Toast.LENGTH_SHORT).show();
                    eletLevon();
                } else {
                    //játék végét megváltoztatni (setTitle - Nyertél)
                    builderJatekVege.show();
                }
            }
        });

        buttonCsokkent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (tippeltSzam > 1) {
                    tippeltSzam--;
                    textViewTipp.setText(String.valueOf(tippeltSzam));
                } else {
                    Toast.makeText(MainActivity.this,
                            "A szám nem lehet kisebb mint 1", Toast.LENGTH_SHORT).show();
                }
            }
        });

        buttonNovel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (tippeltSzam < maxSzam) {
                    tippeltSzam++;
                    textViewTipp.setText(String.valueOf(tippeltSzam));
                } else {
                    Toast.makeText(MainActivity.this,
                            "A szám nem lehet kisebb mint " + maxSzam, Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void eletLevon() {

        //okosítás pénteken!
        switch (elet) {
            case 5:
                imageViewElet5.setImageResource(R.drawable.heart1);
                break;
            case 4:
                imageViewElet4.setImageResource(R.drawable.heart1);
                break;
            case 3:
                imageViewElet3.setImageResource(R.drawable.heart1);
                break;
            case 2:
                imageViewElet2.setImageResource(R.drawable.heart1);
                break;
            case 1:
                imageViewElet1.setImageResource(R.drawable.heart1);
                break;
            default:
                break;
        }
        elet--;
        if (elet < 1) {
            //játék végét megváltoztatni (setTitle - Vesztettél)
            builderJatekVege.show();
        }
    }

    private void init() {
        //ImageView
        imageViewElet1 = findViewById(R.id.imageElet1);
        imageViewElet2 = findViewById(R.id.imageElet2);
        imageViewElet3 = findViewById(R.id.imageElet3);
        imageViewElet4 = findViewById(R.id.imageElet4);
        imageViewElet5 = findViewById(R.id.imageElet5);
        //TextView
        textViewTipp = findViewById(R.id.textViewTipp);
        //Button
        buttonCsokkent = findViewById(R.id.buttonCsokkent);
        buttonKonnyu = findViewById(R.id.buttonKonnyu);
        buttonNehez = findViewById(R.id.buttonNehez);
        buttonNovel = findViewById(R.id.buttonNovel);
        buttonTipp = findViewById(R.id.buttonTipp);
        //Kezdeti értékek az Integereknek:
        maxSzam = 10;
        Random random = new Random();
        gondoltSzam = random.nextInt(maxSzam) + 1;
        elet = 5;
        tippeltSzam = 0;
        //Játékvége felugró ablak megalkotása
        builderJatekVege = new AlertDialog.Builder(MainActivity.this);
        builderJatekVege.setCancelable(false)
                .setTitle("Nyert / Vesztet")
                .setMessage("Szeretne új játékot játszani?")
                .setNegativeButton("Nem", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        finish();
                    }
                })
                .setPositiveButton("Igen", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        //ujJatek();
                    }
                })
                .create();
        //Játék nehézség felugró ablak megalkotása
        builderNehezseg = new AlertDialog.Builder(MainActivity.this);
        builderNehezseg.setCancelable(false)
                .setTitle("Nehéz / Könnyű")
                .setMessage("Szeretnéd változtatni a játék nehézségét?")
                .setNegativeButton("Nem", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                })
                .setPositiveButton("Igen", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        nehezseg = true;
                        //ujJatek();
                    }
                })
                .create();
    }
}
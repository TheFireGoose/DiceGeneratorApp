package com.example.projekt_2;

import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Gravity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    EditText editTextLiczbaKostek;
    Button buttonLosuj;
    Button  buttonRosnaco;
    Button  buttonMalejaco;
    Button  buttonSuma;
    GridLayout gridKostki;
    TextView textViewWyniki;
    TextView  textViewSuma;

    ArrayList<Integer> wyniki = new ArrayList<>();
    Random random = new Random();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextLiczbaKostek = findViewById(R.id.editTextLiczbaKostek);
        buttonLosuj = findViewById(R.id.buttonLosuj);
        buttonRosnaco = findViewById(R.id.buttonRosnaco);
        buttonMalejaco = findViewById(R.id.buttonMalejaco);
        buttonSuma = findViewById(R.id.buttonSuma);
        gridKostki = findViewById(R.id.gridKostki);
        textViewWyniki = findViewById(R.id.textViewWyniki);
        textViewSuma = findViewById(R.id.textViewSuma);

        buttonLosuj.setOnClickListener(v -> losuj());
        buttonRosnaco.setOnClickListener(v -> sortujRosnaco());
        buttonMalejaco.setOnClickListener(v -> sortujMalejaco());
        buttonSuma.setOnClickListener(v -> policzSume());
    }

    private void losuj() {
        String tekst = editTextLiczbaKostek.getText().toString().trim();

        if (tekst.isEmpty()) {
            Toast.makeText(this, "Pole nie moze byc puste", Toast.LENGTH_SHORT).show();
            return;
        }

        int liczba = Integer.parseInt(tekst);
        if (liczba <= 0) {
            Toast.makeText(this, "Liczba musi byc wieksza od zera", Toast.LENGTH_SHORT).show();
            return;
        }

        if (liczba > 100) {
            Toast.makeText(this, "Maksymalnie 100 kostek", Toast.LENGTH_SHORT).show();
            return;
        }



        for (int i = 0; i < liczba; i++) {
            wyniki.add(random.nextInt(6) + 1);
        }
        wyniki.clear();
        textViewSuma.setText("Suma: ");
        odswiezWidok();
    }

    private void sortujRosnaco() {
        if (wyniki.isEmpty()) {
            Toast.makeText(this, "Najpierw wylosuj kostki", Toast.LENGTH_SHORT).show();
            return;

        }

        for (int i = 0; i < wyniki.size() - 1; i++) {
            for (int j = 0; j < wyniki.size() - 1 - i; j++) {
                if (wyniki.get(j) > wyniki.get(j + 1)) {
                    int temp = wyniki.get(j);
                    wyniki.set(j, wyniki.get(j + 1));
                    wyniki.set(j + 1, temp);
                }
            }
        }
        odswiezWidok();
    }

    private void sortujMalejaco() {
        if (wyniki.isEmpty()) {
            Toast.makeText(this, "Najpierw wylosuj kostki", Toast.LENGTH_SHORT).show();
            return;

        }

        for (int i = 0; i < wyniki.size() - 1; i++) {
            for (int j = 0; j < wyniki.size() - 1 - i; j++) {
                if (wyniki.get(j) < wyniki.get(j + 1)) {
                    int temp = wyniki.get(j);
                    wyniki.set(j, wyniki.get(j + 1));
                    wyniki.set(j + 1, temp);
                }
            }
        }
        odswiezWidok();
    }

    private void policzSume() {
        if (wyniki.isEmpty()) {
            Toast.makeText(this, "Najpierw wylosuj kostki", Toast.LENGTH_SHORT).show();
            return;
        }
        int suma = 0;
        for (int liczba : wyniki) {
            suma += liczba;
        }

        textViewSuma.setText("Suma: " + suma);
    }

    private void odswiezWidok() {
        textViewWyniki.setText(TextUtils.join(", ", wyniki));

        gridKostki.removeAllViews();
        gridKostki.setColumnCount(5);

        for (int liczba : wyniki) {
            TextView kostka = new TextView(this);
            kostka.setText(String.valueOf(liczba));
            kostka.setTextSize(24);
            kostka.setGravity(Gravity.CENTER);
            kostka.setTextColor(Color.BLACK);
            kostka.setBackgroundColor(Color.LTGRAY);
            kostka.setWidth(150);
            kostka.setHeight(150);
            gridKostki.addView(kostka);
        }
    }
}
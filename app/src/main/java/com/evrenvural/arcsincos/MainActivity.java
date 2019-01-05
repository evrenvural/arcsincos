package com.evrenvural.arcsincos;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    String array_spinner[];
    Switch mySwitch;

    Spinner spFonkiyonlar;
    EditText etDerece;
    TextView result;
    MyMath myMath;

    boolean tersMi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mySwitch = findViewById(R.id.mySwitch);
        etDerece = findViewById(R.id.etDerece);
        result = findViewById(R.id.tvResult);

        myMath = new MyMath();


        mySwitch.setChecked(false);
        spinnerEkle(false);
        tersMi = false;
        mySwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                spinnerEkle(isChecked);
                tersMi=isChecked;
                degerleriGir();
            }
        });

        etDerece.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                degerleriGir();

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }

    private void degerleriGir() {
        if (etDerece.getText().toString().equals("-") || etDerece.getText().toString().equals("-.")||
                etDerece.getText().toString().equals(".-") || etDerece.getText().toString().equals("."))
            return;

        double derece = 0.0;
        double tersi = 0.0;
        if (!etDerece.getText().toString().equals("")){
            derece = Double.parseDouble(etDerece.getText().toString());
        }
        if (!etDerece.getText().toString().equals("")){
            tersi = Double.parseDouble(etDerece.getText().toString());
        }

        String secilenFonksiyon = spFonkiyonlar.getSelectedItem().toString();
        double sonuc = 0.0;

        if (tersMi){
            etDerece.setHint("Please, enter result");
            myMath.setTersi(tersi);

            switch (secilenFonksiyon) {
                case "Arcsine":
                    sonuc = myMath.arcSinus();
                    break;
                case "Arccosine":
                    sonuc = myMath.arcCosinus();
                    break;
                case "Arctangent":
                    sonuc = myMath.arcTanjant();
                    break;
            }
            result.setText("Degree: " + sonuc + "°");
        }
        else {
            etDerece.setHint("Please, enter degree");
            myMath.setDerece(derece);

            switch (secilenFonksiyon){
                case "Sine": sonuc = myMath.sinus();
                    break;
                case "Cosine": sonuc = myMath.cosinus();
                    break;
                case "Tangent": sonuc = myMath.tanjant();
                    break;
                case "Cotangent": sonuc = myMath.cotanjant();
                    break;
                case "Secant": sonuc = myMath.secant();
                    break;
                case "Cosecant": sonuc = myMath.cosecant();
                    break;
            }
            result.setText("Result: " + sonuc);
        }

    }

    void spinnerEkle(boolean tersMi){
       if (tersMi){
           int arrayUzunluk = getResources().getStringArray(R.array.ters_fonksiyonlar).length;
           array_spinner = new String[arrayUzunluk];
           array_spinner = getResources().getStringArray(R.array.ters_fonksiyonlar);
           spFonkiyonlar = findViewById(R.id.spFonksiyonlar);
           ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,array_spinner);
           spFonkiyonlar.setAdapter(arrayAdapter);
       }
       else{
           int arrayUzunluk = getResources().getStringArray(R.array.fonksiyonlar).length;
           array_spinner = new String[arrayUzunluk];
           array_spinner = getResources().getStringArray(R.array.fonksiyonlar);
           spFonkiyonlar = findViewById(R.id.spFonksiyonlar);
           ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,array_spinner);
           spFonkiyonlar.setAdapter(arrayAdapter);
       }
    }

}


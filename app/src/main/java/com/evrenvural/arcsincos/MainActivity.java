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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mySwitch = findViewById(R.id.mySwitch);
        etDerece = findViewById(R.id.etDerece);
        result = findViewById(R.id.tvResult);

        myMath = new MyMath();


        mySwitch.setChecked(true);
        spinnerEkle(true);
        mySwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                spinnerEkle(isChecked);
            }
        });

        etDerece.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                double derece = 0.0;
                if (!etDerece.getText().toString().equals("")){
                    derece = Double.parseDouble(etDerece.getText().toString());
                }

                myMath.setDerece(derece);
                String secilenFonksiyon = spFonkiyonlar.getSelectedItem().toString();
                double sonuc = 0.0;

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

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

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


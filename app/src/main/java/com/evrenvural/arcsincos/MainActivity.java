package com.evrenvural.arcsincos;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;

import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

import hotchemi.android.rate.AppRate;
import hotchemi.android.rate.OnClickButtonListener;

public class MainActivity extends AppCompatActivity {

    String array_spinner[];
    Switch mySwitch;

    Spinner spFonkiyonlar;
    EditText etDerece;
    TextView result;
    MyMath myMath;

    boolean tersMi;

    AdView mAdView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //ADS
        MobileAds.initialize(this,"ca-app-pub-2599972005525985~6800535275");
        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        //AdRequest adRequest = new AdRequest.Builder().addTestDevice(AdRequest.DEVICE_ID_EMULATOR).build();
        mAdView.loadAd(adRequest);

        //Rate me!
        AppRate.with(this)
                .setInstallDays(0)
                .setLaunchTimes(3)
                .setShowLaterButton(true)
                .setDebug(false)
                .setOnClickButtonListener(new OnClickButtonListener() {
                    @Override
                    public void onClickButton(int which) {
                        Log.d(MainActivity.class.getName(),Integer.toString(which));
                    }
                }).monitor();
        AppRate.showRateDialogIfMeetsConditions(this);

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
            }
        });

        spFonkiyonlar.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                degerleriGir();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

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
            tersi = Double.parseDouble(etDerece.getText().toString());
        }
        else{
            if (tersMi){
                result.setText("Degree: ");
                etDerece.setHint("Please, enter result");
            }
            else{
                result.setText("Result: ");
                etDerece.setHint("Please, enter degree");
            }
            return;
        }

        String secilenFonksiyon = spFonkiyonlar.getSelectedItem().toString();
        double sonuc = 0.0;

        if (tersMi){

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


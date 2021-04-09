package com.example.firstapp;


import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;


public class Fragment1 extends Fragment {

    Button button;
    EditText currencyToBeConverted, currencyConverted;
    RadioButton us, eu, rus, pol;
    private static String url = "https://api.privatbank.ua/p24api/pubinfo?json&exchange&coursid=5";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment1_layout, container, false);

        currencyConverted = (EditText) view.findViewById(R.id.editTextNumberDecimal2);
        currencyToBeConverted = (EditText) view.findViewById(R.id.editTextNumberDecimal);
        us = (RadioButton) view.findViewById(R.id.radioButton);
        eu = (RadioButton) view.findViewById(R.id.radioButton2);
        rus = (RadioButton) view.findViewById(R.id.radioButton3);
        pol = (RadioButton) view.findViewById(R.id.radioButton4);
        button = (Button) view.findViewById(R.id.button);

        button.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        double currency = Double.parseDouble(currencyToBeConverted.getText().toString());
                        RadioGroup radioGroup = view.findViewById(R.id.radioGroup);
                        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                            @Override
                            public void onCheckedChanged(RadioGroup group, int checkedId) {
                                double result = currency;
                                switch (checkedId) {
                                    case R.id.radioButton: {
                                        result = currency / 28.0609;
                                        currencyConverted.setText(String.valueOf(result));
                                    }
                                    break;
                                    case R.id.radioButton2: {
                                        result = currency / 33.9888;
                                        currencyConverted.setText(String.valueOf(result));
                                    }
                                    break;
                                    case R.id.radioButton3: {
                                        result = currency * 2.61;
                                        currencyConverted.setText(String.valueOf(result));
                                    }
                                    break;
                                    case R.id.radioButton4: {
                                        result = currency / 7.5836;
                                        currencyConverted.setText(String.valueOf(result));
                                    }
                                    break;

                                    default:
                                        break;
                                }
                            }
                        });
                    }
                }
        );
        return view;
    }
}


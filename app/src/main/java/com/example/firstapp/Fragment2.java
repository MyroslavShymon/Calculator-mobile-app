package com.example.firstapp;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;



public class Fragment2 extends Fragment {
    private EditText editTextNumberDecimal4;
    private Button ac, power, back, div, one, two, three, four, five, six, seven, eight, nine, multiply, minus, plus, zero, ans, dot, result, sin, cos, tan, ctan, sqrt2, log, ln;
    private CalculatorModel calculator;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment2_layout,container,false);
        editTextNumberDecimal4 = view.findViewById(R.id.editTextNumberDecimal4);
        int[] numberIds = new int[]{
                R.id.dot,
                R.id.one,
                R.id.two,
                R.id.three,
                R.id.four,
                R.id.five,
                R.id.six,
                R.id.seven,
                R.id.eight,
                R.id.nine,
                R.id.ac,
                R.id.back,
                R.id.zero
        };
        int[] actionIds = new int[]{
                R.id.power,
                R.id.sqrt,
                R.id.plus,
                R.id.minus,
                R.id.multiply,
                R.id.div,
                R.id.result
        };
        calculator = new CalculatorModel();
        ac = (Button) view.findViewById(R.id.ac);
        ac.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editTextNumberDecimal4.setText("");

            }
        });
        sin = (Button) view.findViewById(R.id.sin);
        sin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String sin = String.valueOf(Math.sin(Double.parseDouble(editTextNumberDecimal4.getText().toString())));
                editTextNumberDecimal4.setText(sin);

            }
        });
        cos = (Button) view.findViewById(R.id.cos);
        cos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String cos = String.valueOf(Math.cos(Double.parseDouble(editTextNumberDecimal4.getText().toString())));
                editTextNumberDecimal4.setText(cos);

            }
        });
        tan = (Button) view.findViewById(R.id.tan);
        tan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tan = String.valueOf(Math.tan(Double.parseDouble(editTextNumberDecimal4.getText().toString())));
                editTextNumberDecimal4.setText(tan);

            }
        });
        ctan = (Button) view.findViewById(R.id.ctan);
        ctan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ctan = String.valueOf(1 / Math.tan(Double.parseDouble(editTextNumberDecimal4.getText().toString())));
                editTextNumberDecimal4.setText(ctan);

            }
        });
        sqrt2 = (Button) view.findViewById(R.id.sqrt2);
        sqrt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String sqrt2 = String.valueOf(Math.sqrt(Double.parseDouble(editTextNumberDecimal4.getText().toString())));
                editTextNumberDecimal4.setText(sqrt2);

            }
        });
        log = (Button) view.findViewById(R.id.log);
        log.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String log10 = String.valueOf(Math.log10(Double.parseDouble(editTextNumberDecimal4.getText().toString())));
                editTextNumberDecimal4.setText(log10);

            }
        });
        ln = (Button) view.findViewById(R.id.ln);
        ln.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String log = String.valueOf(Math.log(Double.parseDouble(editTextNumberDecimal4.getText().toString())));
                editTextNumberDecimal4.setText(log);

            }
        });
        View.OnClickListener numberButtonClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculator.OnNumPressed(v.getId());
                editTextNumberDecimal4.setText(calculator.getText());
            }
        };
        View.OnClickListener actionButtonClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculator.OnActionPressed(v.getId());
                editTextNumberDecimal4.setText(calculator.getText());
            }
        };
        for (int i = 0; i < numberIds.length; i++){
            view.findViewById(numberIds[i]).setOnClickListener(numberButtonClickListener);
        }
        for (int i = 0; i < actionIds.length; i++){
            view.findViewById(actionIds[i]).setOnClickListener(actionButtonClickListener);
        }
        return view;
    }
}
class CalculatorModel {
    private double firstArg;
    private double secondArg;
    StringBuilder inputStr = new StringBuilder();

    private int actionSelected;
    private State state;

    private enum State{
        firstArgInput,
        secondArgInput,
        resultShow
    }

    public CalculatorModel(){
        state = State.firstArgInput;
    }

    public void OnNumPressed(int buttonId){
        if (state == State.resultShow){
            state = State.firstArgInput;
            inputStr.setLength(0);
        }
        if(inputStr.length() < 9){
            switch (buttonId){
                case R.id.zero:
                    inputStr.append("0");
                    break;
                case R.id.one:
                    inputStr.append("1");
                    break;
                case R.id.two:
                    inputStr.append("2");
                    break;
                case R.id.three:
                    inputStr.append("3");
                    break;
                case R.id.four:
                    inputStr.append("4");
                    break;
                case R.id.five:
                    inputStr.append("5");
                    break;
                case R.id.six:
                    inputStr.append("6");
                    break;
                case R.id.seven:
                    inputStr.append("7");
                    break;
                case R.id.eight:
                    inputStr.append("8");
                    break;
                case R.id.nine:
                    inputStr.append("9");
                    break;
                case R.id.dot:
                    inputStr.append(".");
                    break;
                case R.id.ac:
                    firstArg = 0;
                    secondArg = 0;
                    inputStr.delete(0, 9);
                    break;
                case  R.id.back:
                    inputStr.setLength(inputStr.length() - 1);
                    break;
            }
        }
    }

    public void OnActionPressed(int actionId){
        if(actionId == R.id.result && state == State.secondArgInput){
            secondArg = Double.parseDouble(inputStr.toString());
            state = State.resultShow;
            inputStr.setLength(0);
            switch (actionSelected){
                case R.id.plus:
                    inputStr.append(firstArg + secondArg);
                    break;
                case R.id.minus:
                    inputStr.append(firstArg - secondArg);
                    break;
                case R.id.multiply:
                    inputStr.append(firstArg * secondArg);
                    break;
                case R.id.div:
                    if(secondArg == 0){
                        inputStr.append("Cannot be divided by zero");
                    }
                    else {
                        inputStr.append(firstArg / secondArg);
                    }
                    break;
                case R.id.power:
                    inputStr.append(Math.pow(firstArg, secondArg));
                    break;
                case R.id.sqrt:
                    inputStr.append(Math.pow(firstArg, 1 / secondArg));
                    break;
            }
        }
        else if(inputStr.length() > 0 && state == State.firstArgInput) {
            firstArg = Double.parseDouble(inputStr.toString());
            state = State.secondArgInput;
            inputStr.setLength(0);
            switch (actionId) {
                case R.id.plus:
                    actionSelected = R.id.plus;
                    break;
                case R.id.minus:
                    actionSelected = R.id.minus;
                    break;
                case R.id.multiply:
                    actionSelected = R.id.multiply;
                    break;
                case R.id.div:
                    actionSelected = R.id.div;
                    break;
                case R.id.power:
                    actionSelected = R.id.power;
                    break;
                case R.id.sqrt:
                    actionSelected = R.id.sqrt;
                    break;
            }
        }
    }
    public String getText(){
        return inputStr.toString();
    }
}
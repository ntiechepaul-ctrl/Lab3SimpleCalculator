package com.example.lab3simplecalculator;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private enum Operator { none, add, minus, multiply, divide }
    private double data1 = 0, data2 = 0;
    private Operator optr = Operator.none;
    private boolean hasDot = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClickNumericalButton(View view) {
        int pressID = view.getId();
        TextView curText = (TextView) findViewById(R.id.resultEdit);
        if (pressID == R.id.buttonDot) {
            if (!hasDot) { curText.setText(curText.getText() + "."); hasDot = true; }
            return;
        }
        String digit = "";
        if (pressID == R.id.button00) digit = "0";
        else if (pressID == R.id.button01) digit = "1";
        else if (pressID == R.id.button02) digit = "2";
        else if (pressID == R.id.button03) digit = "3";
        else if (pressID == R.id.button04) digit = "4";
        else if (pressID == R.id.button05) digit = "5";
        else if (pressID == R.id.button06) digit = "6";
        else if (pressID == R.id.button07) digit = "7";
        else if (pressID == R.id.button08) digit = "8";
        else if (pressID == R.id.button09) digit = "9";
        curText.setText(curText.getText() + digit);
    }

    public void onClickFunctionButton(View view) {
        int pressID = view.getId();
        TextView curText = (TextView) findViewById(R.id.resultEdit);
        if (pressID == R.id.buttonCE) {
            optr = Operator.none;
            curText.setText("");
            data1 = 0; data2 = 0; hasDot = false;
            return;
        }
        if (pressID == R.id.buttonEq) {
            if (optr != Operator.none) {
                data2 = Double.parseDouble(curText.getText().toString());
                double result = 0;
                if (optr == Operator.add)      result = data1 + data2;
                else if (optr == Operator.minus)    result = data1 - data2;
                else if (optr == Operator.multiply) result = data1 * data2;
                else if (optr == Operator.divide)   result = data1 / data2;
                optr = Operator.none;
                data1 = result;
                hasDot = false;
                if ((result - (int) result) != 0)
                    curText.setText(String.valueOf(result));
                else
                    curText.setText(String.valueOf((int) result));
            }
            return;
        }
        String dataText = curText.getText().toString();
        data1 = dataText.length() > 0 ? Double.parseDouble(dataText) : 0;
        curText.setText("");
        hasDot = false;
        if (pressID == R.id.buttonAdd)  optr = Operator.add;
        else if (pressID == R.id.buttonSub)  optr = Operator.minus;
        else if (pressID == R.id.buttonMult) optr = Operator.multiply;
        else if (pressID == R.id.buttonDiv)  optr = Operator.divide;
    }
}
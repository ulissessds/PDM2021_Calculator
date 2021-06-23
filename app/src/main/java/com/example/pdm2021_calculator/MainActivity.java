package com.example.pdm2021_calculator;

import androidx.appcompat.app.AppCompatActivity;
import org.mariuszgromada.math.mxparser.*;
import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private EditText display;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        display = findViewById(R.id.input);
        display.setShowSoftInputOnFocus(false);

        display.setOnClickListener(this::onClick);
    }

    private void updateText(String newStr) {
        String oldStr = display.getText().toString();
        int cursorPos = display.getSelectionStart();
        String leftStr = oldStr.substring(0, cursorPos);
        String rightStr = oldStr.substring(cursorPos);
        display.setText(isPlaceholder() ? newStr : String.format("%s%s%s", leftStr, newStr, rightStr));
        display.setSelection(cursorPos + 1);
    }

    public void zeroBTN(View v) {
        updateText("0");
    }

    public void oneBTN(View v) {
        updateText("1");
    }

    public void twoBTN(View v) {
        updateText("2");
    }

    public void threeBTN(View v) {
        updateText("3");
    }

    public void fourBTN(View v) {
        updateText("4");
    }

    public void fiveBTN(View v) {
        updateText("5");
    }

    public void sixBTN(View v) {
        updateText("6");
    }

    public void sevenBTN(View v) {
        updateText("7");
    }

    public void eightBTN(View v) {
        updateText("8");
    }

    public void nineBTN(View v) {
        updateText("9");
    }

    public void addBTN(View v) {
        updateText("+");
    }

    public void subtractBTN(View v) {
        updateText("-");
    }

    public void multiplyBTN(View v) {
        updateText("×");
    }

    public void divideBTN(View v) {
        updateText("÷");
    }

    public void clearBTN(View v) {
        display.setText("");
    }

    public void backspaceBTN(View v) {
        int cursorPos = display.getSelectionStart();
        int textLen = display.getText().length();

        if (cursorPos != 0 && textLen != 0) {
            SpannableStringBuilder selection = (SpannableStringBuilder) display.getText();
            selection.replace(cursorPos - 1, cursorPos, "");
            display.setText(selection);
            display.setSelection(cursorPos - 1);
        }
    }

    public void calculateBTN(View v) {
        String userExp = display.getText().toString();

        userExp = userExp.replaceAll("÷", "/");
        userExp = userExp.replaceAll("×", "*");

        Expression exp = new Expression(userExp);

        String result = String.valueOf(exp.calculate());

        display.setText(result);
        display.setSelection(result.length());
    }

    private void onClick(View v) {
        if (isPlaceholder())
            display.setText("");
    }

    private boolean isPlaceholder() {
        return getString(R.string.display).equals(display.getText().toString());
    }
}
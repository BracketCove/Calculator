package com.wiseass.calculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class CalculatorActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);

        DisplayFragment displayFragment = (DisplayFragment) getSupportFragmentManager()
                .findFragmentById(R.id.frag_display);

        InputFragment inputFragment = (InputFragment) getSupportFragmentManager()
                .findFragmentById(R.id.frag_input);

        CalculatorPresenter presenter = new CalculatorPresenter(displayFragment);
        displayFragment.setPresenter(presenter);
        inputFragment.setPresenter(presenter);


    }
}

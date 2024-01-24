package com.tamerlan.cafe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class OrderDetailActivity extends AppCompatActivity {
    TextView textViewName;
    TextView textViewDrink;
    TextView textViewDrinkType;
    TextView textViewAdditives;
    public static final String EXTRA_USER_NAME = "userName";
    public static final String EXTRA_DRINK = "drink";
    public static final String EXTRA_ADDITIVES = "additives";
    public static final String EXTRA_DRINK_TYPE = "drinkType";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_detail);
        initViews();
        textViewName.setText(getIntent().getStringExtra(EXTRA_USER_NAME));
        textViewDrink.setText(getIntent().getStringExtra(EXTRA_DRINK));
        textViewDrinkType.setText(getIntent().getStringExtra(EXTRA_DRINK_TYPE));
        textViewAdditives.setText(getIntent().getStringExtra(EXTRA_ADDITIVES));
    }

    private void initViews() {
        textViewName = findViewById(R.id.textViewName);
        textViewDrink = findViewById(R.id.textViewDrink);
        textViewDrinkType = findViewById(R.id.textViewDrinkType);
        textViewAdditives = findViewById(R.id.textViewAdditives);
    }

    public static Intent newIntent(Context context, String userName, String drink, String additives, String drinkType) {
        Intent intent = new Intent(context, OrderDetailActivity.class);
        intent.putExtra(EXTRA_USER_NAME, userName);
        intent.putExtra(EXTRA_DRINK, drink);
        intent.putExtra(EXTRA_ADDITIVES, additives);
        intent.putExtra(EXTRA_DRINK_TYPE, drinkType);
        return intent;
    }
}
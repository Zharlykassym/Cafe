package com.tamerlan.cafe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ClipData;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.io.Serializable;
import java.util.ArrayList;

public class OrderDetailActivity extends AppCompatActivity {
    TextView textViewName;
    TextView textViewDrink;
    TextView textViewDrinkType;
    TextView textViewAdditives;
    Button buttonShowCheck;
    LinearLayout linearLayoutCheck;
    public static final String EXTRA_USER_NAME = "userName";
    public static final String EXTRA_DRINK = "drink";
    public static final String EXTRA_ADDITIVES = "additives";
    public static final String EXTRA_DRINK_TYPE = "drinkType";
    public static final String EXTRA_CART = "cart";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_detail);
        initViews();
        textViewName.setText(getIntent().getStringExtra(EXTRA_USER_NAME));
        textViewDrink.setText(getIntent().getStringExtra(EXTRA_DRINK));
        textViewAdditives.setText(getIntent().getStringExtra(EXTRA_ADDITIVES));
        textViewDrinkType.setText(getIntent().getStringExtra(EXTRA_DRINK_TYPE));
        buttonShowCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                linearLayoutCheck.setVisibility(View.VISIBLE);
            }
        });
    }

    private void initViews() {
        textViewName = findViewById(R.id.textViewName);
        textViewDrink = findViewById(R.id.textViewDrink);
        textViewDrinkType = findViewById(R.id.textViewDrinkType);
        textViewAdditives = findViewById(R.id.textViewAdditives);
        buttonShowCheck = findViewById(R.id.buttonShowCheck);
        linearLayoutCheck = findViewById(R.id.linearLayoutCheck);
    }

    public static Intent newIntent(Context context, String userName, ArrayList<Item> cart) {

        Intent intent = new Intent(context, OrderDetailActivity.class);
        intent.putExtra(EXTRA_USER_NAME, userName);
        intent.putExtra(EXTRA_DRINK, cart.get(cart.size()-1).getProduct());
        intent.putExtra(EXTRA_ADDITIVES, cart.get(cart.size()-1).getAdditives());
        intent.putExtra(EXTRA_DRINK_TYPE, cart.get(cart.size()-1).getType());
        return intent;
    }
}
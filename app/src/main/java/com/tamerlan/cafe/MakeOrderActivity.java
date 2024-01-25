package com.tamerlan.cafe;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MakeOrderActivity extends AppCompatActivity {

    private static final String EXTRA_USER_NAME = "userName";
    private TextView textViewGreetings;
    private RadioGroup radioGroupDrinks;
    private RadioGroup radioGroupCategory;

    private ScrollView scrollViewDrinks;
    private ScrollView scrollViewPizza;

    private RadioButton radioButtonTea;
    private RadioButton radioButtonCoffee;
    private RadioButton radioButtonWater;
    private RadioButton radioButtonJuice;

    private RadioButton radioButtonDrinks;
    private RadioButton radioButtonPizza;
    private RadioButton radioButtonSnacks;
    private RadioButton radioButtonDesserts;


    private TextView textViewAdditives;
    private CheckBox checkBoxSugar;
    private CheckBox checkBoxMilk;
    private CheckBox checkBoxLemon;

    private Spinner spinnerTea;
    private Spinner spinnerCoffee;
    private Spinner spinnerJuice;
    private Button buttonMakeOrder;
    private Button buttonAddToCart;

    private String userName;
    private String drink;

    ArrayList<Item> cart = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_make_order);
        initViews();
        setupUserName();
        radioGroupDrinks.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == radioButtonTea.getId()) {
                    onUserChoseTea();
                } else if (checkedId == radioButtonCoffee.getId()) {
                    onUserChoseCoffee();
                } else if (checkedId == radioButtonWater.getId()) {
                    onUserChoseWater();
                } else if (checkedId == radioButtonJuice.getId()) {
                    onUserChoseJuice();
                }
            }
        });

        radioGroupCategory.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == radioButtonDrinks.getId()) {
                    scrollViewPizza.setVisibility(View.INVISIBLE);
                    scrollViewDrinks.setVisibility(View.VISIBLE);
                } else if (checkedId == radioButtonPizza.getId()) {
                    scrollViewDrinks.setVisibility(View.INVISIBLE);
                    scrollViewPizza.setVisibility(View.VISIBLE);

                }
            }
        });

        radioButtonTea.setChecked(true);
        radioButtonDrinks.setChecked(true);

        buttonAddToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onDrinksChecked();// Добавить в корзину

            }
        });

        buttonMakeOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!cart.isEmpty()) {
                    onUserMadeOrder();// Перейти к оформлению
                } else {
                    Toast.makeText(MakeOrderActivity.this, R.string.cartIsEmpty, Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void onUserMadeOrder() {
        Intent intent = OrderDetailActivity.newIntent(this,
                userName, cart);
        startActivity(intent);

    }


    private void onDrinksChecked() {
        if (radioButtonDrinks.isChecked()) {
            ArrayList<String> additives = new ArrayList<>();
            if (checkBoxSugar.isChecked()) {
                additives.add(checkBoxSugar.getText().toString());
            }
            if (checkBoxMilk.isChecked()) {
                additives.add(checkBoxMilk.getText().toString());
            }
            if (radioButtonTea.isChecked() && checkBoxLemon.isChecked()) {
                additives.add(checkBoxLemon.getText().toString());
            }

            String drinkType = "";
            if (radioButtonTea.isChecked()) {
                drinkType = spinnerTea.getSelectedItem().toString();
            } else if (radioButtonCoffee.isChecked()) {
                drinkType = spinnerCoffee.getSelectedItem().toString();
            }
            cart.add(new Item(drink, String.join(", ", additives), drinkType));
        }
    }

    private void onUserChoseTea() {
        drink = getString(R.string.tea);
        String additivesLabel = getString(R.string.additives, drink);
        textViewAdditives.setText(additivesLabel);
        checkBoxLemon.setVisibility(View.VISIBLE);
        spinnerTea.setVisibility(View.VISIBLE);
        checkBoxSugar.setVisibility(View.VISIBLE);
        checkBoxMilk.setVisibility(View.VISIBLE);
        spinnerCoffee.setVisibility(View.INVISIBLE);
        spinnerTea.setVisibility(View.VISIBLE);
        spinnerJuice.setVisibility(View.INVISIBLE);
    }

    private void onUserChoseCoffee() {
        drink = getString(R.string.coffee);
        String additivesLabel = getString(R.string.additives, drink);
        textViewAdditives.setText(additivesLabel);
        checkBoxLemon.setVisibility(View.INVISIBLE);
        checkBoxMilk.setVisibility(View.VISIBLE);
        checkBoxSugar.setVisibility(View.VISIBLE);
        spinnerCoffee.setVisibility(View.VISIBLE);
        spinnerTea.setVisibility(View.INVISIBLE);
        spinnerJuice.setVisibility(View.INVISIBLE);
    }

    private void onUserChoseWater() {
        drink = getString(R.string.water);
        String additivesLabel = getString(R.string.additives, drink);
        textViewAdditives.setText(additivesLabel);
        checkBoxLemon.setVisibility(View.VISIBLE);
        checkBoxMilk.setVisibility(View.INVISIBLE);
        checkBoxSugar.setVisibility(View.INVISIBLE);
        spinnerCoffee.setVisibility(View.INVISIBLE);
        spinnerTea.setVisibility(View.INVISIBLE);
        spinnerJuice.setVisibility(View.INVISIBLE);
    }

    private void onUserChoseJuice() {
        drink = getString(R.string.juice);
        String additivesLabel = getString(R.string.additives, drink);
        textViewAdditives.setText(additivesLabel);
        checkBoxLemon.setVisibility(View.INVISIBLE);
        checkBoxMilk.setVisibility(View.INVISIBLE);
        checkBoxSugar.setVisibility(View.INVISIBLE);
        spinnerCoffee.setVisibility(View.INVISIBLE);
        spinnerTea.setVisibility(View.INVISIBLE);
        spinnerJuice.setVisibility(View.VISIBLE);
    }

    private void initViews() {
        textViewGreetings = findViewById(R.id.textViewGreetings);
        radioGroupDrinks = findViewById(R.id.radioGroupDrinks);
        radioGroupCategory = findViewById(R.id.radioGroupCategory);
        scrollViewDrinks = findViewById(R.id.scrollViewDrinks);
        scrollViewPizza = findViewById(R.id.scrollViewPizza);

        radioButtonTea = findViewById(R.id.radioButtonTea);
        radioButtonCoffee = findViewById(R.id.radioButtonCoffee);
        radioButtonWater = findViewById(R.id.radioButtonWater);
        radioButtonJuice = findViewById(R.id.radioButtonJuice);

        radioButtonDrinks = findViewById(R.id.radioButtonDrinks);
        radioButtonPizza = findViewById(R.id.radioButtonPizza);
        radioButtonSnacks = findViewById(R.id.radioButtonSnacks);
        radioButtonDesserts = findViewById(R.id.radioButtonDesserts);

        textViewAdditives = findViewById(R.id.textViewAdditives);

        checkBoxSugar = findViewById(R.id.checkBoxSugar);
        checkBoxMilk = findViewById(R.id.checkBoxMilk);
        checkBoxLemon = findViewById(R.id.checkBoxLemon);

        spinnerTea = findViewById(R.id.spinnerTea);
        spinnerCoffee = findViewById(R.id.spinnerCoffee);
        spinnerJuice = findViewById(R.id.spinnerJuice);
        buttonMakeOrder = findViewById(R.id.buttonMakeOrder);
        buttonAddToCart = findViewById(R.id.buttonAddToCart);
    }

    private void setupUserName() {
        userName = getIntent().getStringExtra(EXTRA_USER_NAME);
        String greetings = getString(R.string.greetings, userName);
        textViewGreetings.setText(greetings);
    }

    public static Intent newIntent(Context context, String userName) {
        Intent intent = new Intent(context, MakeOrderActivity.class);
        intent.putExtra(EXTRA_USER_NAME, userName);
        return intent;
    }
}
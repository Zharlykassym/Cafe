package com.tamerlan.cafe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import java.text.Format;

public class MakeOrderActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_make_order);
        Intent intent = getIntent();
        String name = intent.getStringExtra("userName");
        TextView textViewGreetings = findViewById(R.id.textViewGreetings);
        textViewGreetings.setText(String.format(getString(R.string.greetings),name));
    }

    public static Intent newIntent(Context context, String userName){
        Intent intent = new Intent(context, MakeOrderActivity.class);
        intent.putExtra("userName", userName);
        return intent;
    }
}
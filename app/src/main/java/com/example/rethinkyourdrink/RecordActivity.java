package com.example.rethinkyourdrink;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class RecordActivity extends AppCompatActivity {

    private Spinner daySpinner, typeSpinner;
    private EditText ETamount, ETothers;
    private Button btnSave;

    public static final String DAY = "day";
    public static final String TYPE = "type";
    public static final String AMOUNT = "amount";
    public static final String OTHER = "other";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record);

        // Initialize views
        daySpinner = findViewById(R.id.daySpinner);
        typeSpinner = findViewById(R.id.typeSpinner);
        ETamount = findViewById(R.id.ETamount);
        ETothers = findViewById(R.id.ETothers);
        btnSave = findViewById(R.id.BtnSave);

        // Populate daySpinner with options "Day 1", "Day 2", and "Day 3"
        ArrayAdapter<String> dayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item,
                new String[]{"Day 1", "Day 2", "Day 3"});
        dayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        daySpinner.setAdapter(dayAdapter);

        // Populate daySpinner with options "Day 1", "Day 2", and "Day 3"
        ArrayAdapter<String> typeAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item,
                new String[]{"Plain", "non-Sweetened", "Sweetened"});
        typeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        typeSpinner.setAdapter(typeAdapter);


        btnSave.setOnClickListener(view -> {
            Intent replyIntent = new Intent();
            String day = daySpinner.getSelectedItem().toString();
            String type = typeSpinner.getSelectedItem().toString();
            int amount;

            amount = Integer.parseInt(ETamount.getText().toString());

            String others = ETothers.getText().toString();

            if(TextUtils.isEmpty(ETothers.getText())){
                others = "none";
            }
            boolean dayNight = true;
            if (TextUtils.isEmpty(day) || TextUtils.isEmpty(type) ||TextUtils.isEmpty(ETamount.getText()) )
                setResult(RESULT_CANCELED, replyIntent);
            else {
                replyIntent.putExtra(DAY, day);
                replyIntent.putExtra(TYPE, type);
                replyIntent.putExtra(AMOUNT, Integer.toString(amount));
                replyIntent.putExtra(OTHER, others);
                setResult(RESULT_OK, replyIntent);
            }
            finish();
        });
    }
}
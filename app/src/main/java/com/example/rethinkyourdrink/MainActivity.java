package com.example.rethinkyourdrink;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

public class MainActivity extends AppCompatActivity {
    public static final int ADD_RECORD_REQUEST_CODE = 1;
    private RecordViewModel mRecordViewModel;
    TextView plainText,nonSweetenedText,sweetenedText,totalText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

      Button summaryBtn = findViewById(R.id.SummaryBtn);
         plainText = findViewById(R.id.plainWaterTotal);
         nonSweetenedText = findViewById(R.id.nonSweetenedTotal);
         sweetenedText = findViewById(R.id.SweetenedTotal);
         totalText = findViewById(R.id.totalWater);
        FloatingActionButton addRecordBtn = findViewById(R.id.AddRecord);
        mRecordViewModel = new ViewModelProvider(this).get(RecordViewModel.class);

     setTextViews();

        mRecordViewModel.getTotalToday().observe(this,integerValue ->{
            if (integerValue != null) {
                // Use the integerValue here
                int intValue = integerValue;
                // Perform any actions with intValue
                totalText.setText(intValue + " ml");
            }
                });


//        AtomicInteger totalPlain = new AtomicInteger();
//        AtomicInteger totalNonSweet = new AtomicInteger();
//        AtomicInteger totalSweet = new AtomicInteger();
//        mRecordViewModel.getmAllRecords().observe(this, recordEntities -> {
//            for (RecordEntity recordEntity : recordEntities) {
//                // Check if the day is "Day 1"
//                if ("Day 1".equals(recordEntity.getDay())) {
//
//                    if ("Plain".equals(recordEntity.getType())) {
//                        totalPlain.addAndGet(recordEntity.getAmount());
//                    } else if ("non-Sweetened".equals(recordEntity.getType())) {
//                        totalNonSweet.addAndGet(recordEntity.getAmount());
//                    } else {
//                        totalSweet.addAndGet(recordEntity.getAmount());
//                    }
//                }
//                plainText.setText(totalPlain + " ml");
//                nonSweetenedText.setText(totalNonSweet + " ml");
//                sweetenedText.setText(totalSweet + " ml");
//
//                totalPlain.set(0);
//                totalNonSweet.set(0);
//                totalSweet.set(0);
//                // Now 'totalAmountForDay1' contains the sum of amounts for records with day equal to "Day 1"
//                Log.d("MainActivity", "Total Amount for Day 1: " + totalPlain);
//            }
//        });




        addRecordBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, RecordActivity.class);
                startActivityForResult(intent, ADD_RECORD_REQUEST_CODE);
            }
        });

        CardView summaryDetails = findViewById(R.id.cardView2);
        summaryDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SummaryActivity.class);
                startActivity(intent);
            }
        });


        summaryBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SummaryActivity.class);
                startActivity(intent);
            }
        });


    }

    public void setTextViews() {
        mRecordViewModel.getTotalAmountForPlainToday(plainText);
        mRecordViewModel.getTotalAmountForNonSweetToday(nonSweetenedText);
        mRecordViewModel.getTotalAmountForSweetToday(sweetenedText);
      //  mRecordViewModel.getTotalAmountForToday(totalText);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == ADD_RECORD_REQUEST_CODE && resultCode == RESULT_OK) {

            String day = data.getStringExtra(RecordActivity.DAY);
            String type = data.getStringExtra(RecordActivity.TYPE);
            int amount = Integer.parseInt(Objects.requireNonNull(data.getStringExtra(RecordActivity.AMOUNT)));
            String others = data.getStringExtra(RecordActivity.OTHER);

            RecordEntity note = new RecordEntity(day, type, amount, others);
            mRecordViewModel.insert(note);
          
        } else {

        }
    }
}
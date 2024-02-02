package com.example.rethinkyourdrink;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.TextView;

public class SummaryActivity extends AppCompatActivity {

    private RecordViewModel mRecordViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_summary);

        TextView totalWater = findViewById(R.id.totalWater);
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        final RecordListAdapter adapter = new RecordListAdapter(new RecordListAdapter.NoteDiff());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecordViewModel = new ViewModelProvider(this).get(RecordViewModel.class);

        mRecordViewModel.getmAllRecords().observe(this, adapter::submitList);
        mRecordViewModel.getTotalThreeDay().observe(this, total -> {
            totalWater.setText(total + " ml");
        });

    }
}
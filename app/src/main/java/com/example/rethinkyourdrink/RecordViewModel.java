package com.example.rethinkyourdrink;

import android.app.Application;
import android.widget.TextView;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class RecordViewModel extends AndroidViewModel {

    //Added a private member variable to hold a reference to the repository.
    private RecordRepository mRepository;
    Application application;
    private final LiveData<List<RecordEntity>> mAllRecords;

    //Implemented a constructor that creates the MoodNoteRepository.
    //In the constructor, initialized the allNotes LiveData using the repository.

    public RecordViewModel (Application application) {
        super(application);
        this.application = application;
        mRepository = new RecordRepository(application);
        mAllRecords = mRepository.getAllRecords();
    }
    //Added a getAllNotes() method to return a cached list of words.
    LiveData<List<RecordEntity>> getmAllRecords() { return mAllRecords; }
    LiveData<Integer> getTotalToday() { return mRepository.getTodayLivaData(); }
    LiveData<Integer> getTotalThreeDay() { return mRepository.getTotalThreeDay(); }
    LiveData<Integer> getAmountForPlain() {
        return mRepository.getAmountForPlain();
    }
    LiveData<Integer> getAmountForNonSweet() {
        return mRepository.getAmountForSweet();
    }
    LiveData<Integer> getAmountForSweet() {return mRepository.getAmountForSweet();

    }
    // Created a wrapper insert() method that calls the Repository's insert() method.
    // In this way, the implementation of insert() is encapsulated from the UI.
    public void insert(RecordEntity record) { mRepository.insert(record);}

    public void getTotalAmountForPlainToday(TextView plainText) {
        mRepository.getTotalAmountForPlainToday("Day 1", new RecordRepository.OnTotalAmountResult() {
            @Override
            public void onResult(String totalAmount) {
                plainText.setText(totalAmount + " ml");
            }
        }) ;

    }

    public void getTotalAmountForNonSweetToday(TextView nonSweetenedText) {

        mRepository.getTotalAmountForNonSweetToday("Day 1", new RecordRepository.OnTotalAmountResult() {
            @Override
            public void onResult(String totalAmount) {
                nonSweetenedText.setText(totalAmount + " ml");
            }
        });

    }

    public void getTotalAmountForSweetToday(TextView sweetenedText) {
        mRepository.getTotalAmountForSweetToday("Day 1", new RecordRepository.OnTotalAmountResult() {
            @Override
            public void onResult(String totalAmount) {
                sweetenedText.setText(totalAmount + " ml");
            }
        });

    }
    public void getTotalAmountForToday(TextView todayText) {
        mRepository.getTotalAmountForToday("Day 1", new RecordRepository.OnTotalAmountResult() {
            final String[] amount = new String[1];
            @Override
            public void onResult(String totalAmount) {
                todayText.setText(totalAmount + " ml");
            }
        });

    }


    // Callback interface to handle the result
    public interface OnTotalAmountResult {
        void onResult( LiveData<Integer> totalAmount);
    }

    }


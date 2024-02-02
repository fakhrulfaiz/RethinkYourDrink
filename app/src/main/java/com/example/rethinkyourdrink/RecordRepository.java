package com.example.rethinkyourdrink;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.LiveData;

import java.util.List;

public class RecordRepository {

    private RecordDao mRecordDao;
    private LiveData<List<RecordEntity>> mAllRecords;

    private LiveData<Integer> todayLivaData,totalThreeDay, todayPlainData , todayNotSweetData, todaySweetData;

    RecordRepository(Application application) {
        RecordRoomDatabase db = RecordRoomDatabase.getDatabase(application);
        mRecordDao = db.recordDao();
        mAllRecords = mRecordDao.getAllRecord();
        todayLivaData = mRecordDao.getTotalForDay("Day 1");
        totalThreeDay = mRecordDao.getTotalForThreeDay();
    }

    void assignDay(String day){
        todayLivaData = mRecordDao.getTotalForDay("Day 1");
        todayPlainData = mRecordDao.getTotalbyCategory("Day 1","Plain");
        todayNotSweetData= mRecordDao.getTotalbyCategory("Day 1", "non-Sweetened");
        todaySweetData= mRecordDao.getTotalbyCategory("Day 1", "Sweetened");
    }

    LiveData<List<RecordEntity>> getAllRecords() {
        return mAllRecords;
    }
    LiveData<Integer> getAmountForPlain() {
        return todayPlainData;
    }
    LiveData<Integer> getAmountForNonSweet() {
        return todayNotSweetData;
    }
    LiveData<Integer> getAmountForSweet() {
        return todaySweetData;
    }
    LiveData<Integer> getTodayLivaData() {
        return todayLivaData;
    }
    LiveData<Integer> getTotalThreeDay() {
        return totalThreeDay;
    }
    void insert(RecordEntity record) {
        RecordRoomDatabase.databaseWriteExecutor.execute(() -> {
            mRecordDao.insert(record);
        });
    }

    void getTotalAmountForPlainToday(String specificDay, OnTotalAmountResult callback) {
        RecordRoomDatabase.databaseWriteExecutor.execute(() -> {
            int totalAmount = mRecordDao.getTotalAmountForCategory(specificDay, "Plain");
            // Convert the integer to String
            String totalAmountString = String.valueOf(totalAmount);
            Log.d("RecordRepository", "getTotalAmountForPlainToday: " + totalAmountString);
            // Callback to the UI thread with the result
            callback.onResult(totalAmountString);
        });
    }

    void getTotalAmountForNonSweetToday(String specificDay, OnTotalAmountResult callback) {
        RecordRoomDatabase.databaseWriteExecutor.execute(() -> {
            int totalAmount = mRecordDao.getTotalAmountForCategory(specificDay, "non-Sweetened");
            // Convert the integer to String
            String totalAmountString = String.valueOf(totalAmount);
            // Callback to the UI thread with the result
            callback.onResult(totalAmountString);
        });
    }

    void getTotalAmountForSweetToday(String specificDay, OnTotalAmountResult callback) {
        RecordRoomDatabase.databaseWriteExecutor.execute(() -> {
            int totalAmount = mRecordDao.getTotalAmountForCategory(specificDay, "Sweetened");
            // Convert the integer to String
            String totalAmountString = String.valueOf(totalAmount);
            // Callback to the UI thread with the result
            callback.onResult(totalAmountString);
        });

    }
    void getTotalAmountForToday(String specificDay, OnTotalAmountResult callback) {
        RecordRoomDatabase.databaseWriteExecutor.execute(() -> {

            int totalAmount = mRecordDao.getTotalAmountForDay(specificDay);
            // Convert the integer to String
            String totalAmountString = String.valueOf(totalAmount);

            // Callback to the UI thread with the result
            callback.onResult(totalAmountString);
        });

    }

    // Callback interface to handle the result
    public interface OnTotalAmountResult {
        void onResult(String totalAmount);
    }
}

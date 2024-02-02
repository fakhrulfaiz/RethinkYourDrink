package com.example.rethinkyourdrink;


import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface RecordDao {


    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(RecordEntity note);

    // the query method
    @Query("DELETE FROM RecordEntity")
    void deleteAll();

    // LiveData works with Room database to get instant update whenever there is any changes
    @Query("SELECT * FROM RecordEntity ORDER BY recordID DESC")
    LiveData<List<RecordEntity>> getAllRecord();

    @Query("SELECT SUM(amount) FROM RecordEntity WHERE day = :specificDay")
    int getTotalAmountForDay(String specificDay);

    @Query("SELECT SUM(amount) FROM RecordEntity WHERE day = :specificDay")
    LiveData<Integer> getTotalForDay(String specificDay);

    @Query("SELECT SUM(amount) FROM RecordEntity")
    LiveData<Integer> getTotalForThreeDay();

    @Query("SELECT SUM(amount) FROM RecordEntity WHERE day = :specificDay AND type = :category")
    int  getTotalAmountForCategory(String specificDay, String category);

    @Query("SELECT SUM(amount) FROM RecordEntity WHERE day = :specificDay AND type = :category")
    LiveData<Integer> getTotalbyCategory(String specificDay, String category);

    @Query("SELECT * FROM RecordEntity")
    LiveData<List<RecordEntity>> getAllRecords();
}

package com.example.rethinkyourdrink;


import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class RecordEntity {
    @PrimaryKey(autoGenerate = true)
    int recordID;

    String day;
    String type;

    int amount;

    String others;
    public int getRecordID() {
        return recordID;
    }

    public String getDay() {
        return day;
    }

    public String getType() {
        return type;
    }

    public int getAmount() {
        return amount;
    }

    public String getOthers() {
        return others;
    }

    public RecordEntity(String day, String type, int amount, String others) {
        this.day = day;
        this.type = type;
        this.amount = amount;
        this.others = others;
    }


}

package com.example.kotlin_mvvm.model
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "country")
 class Country(
    @ColumnInfo(name = "name")
    var name:String,
    @ColumnInfo(name = "flag")
    var flag:String,
    @ColumnInfo(name = "capital")
    var capital:String) {

    @PrimaryKey(autoGenerate = true)
    var uuid:Int =0

}
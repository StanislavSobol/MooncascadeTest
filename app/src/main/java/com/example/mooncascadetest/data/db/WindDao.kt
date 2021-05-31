package com.example.mooncascadetest.data.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import java.util.*

@Dao
interface WindDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertAll(windEntities: List<WindEntity>)

    @Query("DELETE FROM WindEntity")
    fun deleteAll()

    @Deprecated("selectForDate")
    @Query("SELECT * FROM WindEntity WHERE date=:date ORDER BY windId")
    fun selectForDateLiveData(date: Date): LiveData<List<WindEntity>>

    @Query("SELECT * FROM WindEntity WHERE date=:date ORDER BY windId")
    fun selectForDate(date: Date): List<WindEntity>

    @Query("SELECT * FROM WindEntity WHERE windId =:windId")
    fun getByWindId(windId: Long): WindEntity
}
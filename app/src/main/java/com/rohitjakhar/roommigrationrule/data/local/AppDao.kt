package com.rohitjakhar.roommigrationrule.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.rohitjakhar.roommigrationrule.data.model.UserAmountEntity
import com.rohitjakhar.roommigrationrule.data.model.UserProfileEntity

@Dao
interface AppDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUser(userProfileEntity: UserProfileEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAmount(userAmountEntity: UserAmountEntity)
}

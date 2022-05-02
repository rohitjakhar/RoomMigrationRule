package com.rohitjakhar.roommigrationrule.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "User Amount")
data class UserAmountEntity(
    @PrimaryKey
    @ColumnInfo(name = "user_amount")
    val amount: Int
)

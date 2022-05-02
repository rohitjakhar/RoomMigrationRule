package com.rohitjakhar.roommigrationrule.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "User History")
data class UserHistoryEntity(
    @PrimaryKey
    @ColumnInfo(name = "user id")
    val userId: Long,

    @ColumnInfo(name = "old name")
    val oldName: String
)

package com.rohitjakhar.roommigrationrule.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "User Note")
data class UserNoteEntitiy(
    @PrimaryKey
    @ColumnInfo(name = "key")
    val key: Long,
    @ColumnInfo(name = "name")
    val note: String
)

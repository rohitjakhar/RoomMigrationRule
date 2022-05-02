package com.rohitjakhar.roommigrationrule.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "User Profile")
data class UserProfileEntity(
    @PrimaryKey()
    @ColumnInfo(name = "user_id")
    val userId: Long,

    @ColumnInfo(name = "user_name_last")
    val userName: String,

    @ColumnInfo(name = "user_mobile")
    val userMobile: Int?,
)

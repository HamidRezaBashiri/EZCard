package com.hamidrezabashiri.ezcard.data.dataModel

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "user_table",
    indices = [Index(value = ["name"], unique = true)]
)
data class User(
    @PrimaryKey(autoGenerate = true) val id: Int=0,
    @ColumnInfo(name = "name") val name: String="admin",
    val password: String = "",
    val salt: String = "",
    val isAdmin: Boolean = false
)

package com.hamidrezabashiri.ezcard.data.dataModel

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_table")
data class User(@PrimaryKey val id: Int, val name: String, val password: String)

package com.csstudent.manager.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorite_repositories")
data class FavoriteRepository(
    @PrimaryKey
    val name: String,
    val ownerLogin: String,
    val description: String?,
    val language: String?,
    val stars: Int,
    val forks: Int,
    val htmlUrl: String,
    val addedAt: Long = System.currentTimeMillis()
)

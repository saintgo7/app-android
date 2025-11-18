package com.csstudent.manager.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.csstudent.manager.data.FavoriteRepository
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoriteRepositoryDao {
    @Query("SELECT * FROM favorite_repositories ORDER BY addedAt DESC")
    fun getAllFavorites(): Flow<List<FavoriteRepository>>

    @Query("SELECT * FROM favorite_repositories WHERE name = :name")
    suspend fun getFavoriteByName(name: String): FavoriteRepository?

    @Query("SELECT EXISTS(SELECT 1 FROM favorite_repositories WHERE name = :name)")
    suspend fun isFavorite(name: String): Boolean

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(repository: FavoriteRepository)

    @Delete
    suspend fun delete(repository: FavoriteRepository)

    @Query("DELETE FROM favorite_repositories WHERE name = :name")
    suspend fun deleteByName(name: String)

    @Query("DELETE FROM favorite_repositories")
    suspend fun deleteAll()
}

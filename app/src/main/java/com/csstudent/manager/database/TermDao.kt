package com.csstudent.manager.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.csstudent.manager.data.Term
import kotlinx.coroutines.flow.Flow

@Dao
interface TermDao {
    @Query("SELECT * FROM terms ORDER BY term ASC")
    fun getAllTerms(): Flow<List<Term>>

    @Query("SELECT * FROM terms WHERE category = :category ORDER BY term ASC")
    fun getTermsByCategory(category: String): Flow<List<Term>>

    @Query("SELECT * FROM terms WHERE term LIKE '%' || :query || '%' OR definition LIKE '%' || :query || '%' ORDER BY term ASC")
    fun searchTerms(query: String): Flow<List<Term>>

    @Query("SELECT * FROM terms WHERE id = :id")
    suspend fun getTermById(id: String): Term?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(term: Term)

    @Update
    suspend fun update(term: Term)

    @Delete
    suspend fun delete(term: Term)

    @Query("DELETE FROM terms WHERE id = :id")
    suspend fun deleteById(id: String)

    @Query("DELETE FROM terms")
    suspend fun deleteAll()
}

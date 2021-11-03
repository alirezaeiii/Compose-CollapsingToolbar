package com.android.sample.core.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

/**
 * Data Access Object for the show table.
 */
@Dao
interface DisneyDao {

    /**
     * Select all shows from the posters table.
     *
     * @return all posters.
     */
    @Query("SELECT * FROM posters")
    suspend fun getPosters(): List<DatabasePoster>

    /**
     * Insert shows in the database. If the show already exists, replace it.
     *
     * @param posters the posters to be inserted.
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(vararg posters: DatabasePoster)
}
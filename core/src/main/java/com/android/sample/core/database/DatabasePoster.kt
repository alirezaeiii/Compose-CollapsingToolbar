package com.android.sample.core.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.android.sample.core.response.Poster

@Entity(tableName = "posters")
class DatabasePoster(
    @PrimaryKey val id: Long,
    val name: String,
    val release: String,
    val playtime: String,
    val description: String,
    val plot: String,
    val poster: String
)

fun List<DatabasePoster>.asDomainModel(): List<Poster> {
    return map {
        Poster(
            id = it.id,
            name = it.name,
            release = it.release,
            playtime = it.playtime,
            description = it.description,
            plot = it.plot,
            poster = it.poster
        )
    }
}

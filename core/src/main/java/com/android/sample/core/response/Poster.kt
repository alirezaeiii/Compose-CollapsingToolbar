package com.android.sample.core.response

import android.os.Parcelable
import com.android.sample.core.database.DatabasePoster
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Poster(
    val id: Long,
    val name: String,
    val release: String,
    val playtime: String,
    val description: String,
    val plot: String,
    val poster: String
) : Parcelable

fun List<Poster>.asDatabaseModel(): Array<DatabasePoster> {
    return map {
        DatabasePoster(
            id = it.id,
            name = it.name,
            release = it.release,
            playtime = it.playtime,
            description = it.description,
            plot = it.plot,
            poster = it.poster
        )
    }.toTypedArray()
}
package com.jommaa.data.dataSource.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.jommaa.data.dataSource.local.dao.AlbumsDao
import com.jommaa.data.dataSource.local.entities.AlbumEntity

@Database(entities = arrayOf(AlbumEntity::class), version = 1)
abstract class AlbumDB : RoomDatabase() {
    abstract fun AlbumsDao(): AlbumsDao

    companion object {
        @Volatile
        private var instance: AlbumDB? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
            instance ?: buildDatabase(context).also { instance = it }
        }

        private fun buildDatabase(context: Context) = Room.databaseBuilder(
            context,
            AlbumDB::class.java, "albums.db"
        )
            .build()

         fun close(){
            instance?.close()
        }
    }


}
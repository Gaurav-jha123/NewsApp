package com.example.newsapp.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.TypeConverters
import androidx.room.RoomDatabase
import com.example.newsapp.models.Article

@Database(
    entities = [Article::class],
    version = 1 // will update when there will be further changes in the database vrooo

)
@TypeConverters(Converters::class)
abstract class ArticleDatabase : RoomDatabase() {

        abstract fun getArticleDao() : ArticleDao

        companion object{
             // The volatile keyword tells the JVM that it may be modified by
            // another thread. Each thread has its own stack,
            // and so its own copy of variables it can access.
            // When a thread is created, it copies the value
            // of all accessible variables in its own memory.
             @Volatile
             private var instance  : ArticleDatabase ?= null
            private val lock = Any()  // to check for single instance of our database at one time

            operator fun invoke(context: Context) = instance ?: synchronized(lock){
                    instance ?: createDatabase(context).also{
                        instance = it
                    }
            }

            private fun createDatabase(context: Context) =
                                Room.databaseBuilder(
                                            context.applicationContext,
                                    ArticleDatabase::class.java,
                                    "article_db.db"
                                ).build()

        }


}
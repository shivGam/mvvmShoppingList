package edu.first.mvvmshopping.data.db.entities

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.internal.synchronized

@Database(
    entities = [ShoppingItem::class],
    version = 1
)
abstract class ShoppingDatabase : RoomDatabase() {

    abstract fun getShopDao() : ShopDao

    companion object{
        @Volatile
        private var instance : ShoppingDatabase?= null
        private val LOCK = Any()

        @OptIn(InternalCoroutinesApi::class)
        fun getInstance(context: Context) = instance ?: synchronized(LOCK) {
            instance ?: createDB(context).also { instance = it }
        }

        private fun createDB(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                ShoppingDatabase::class.java,
                name = "ShoppingDB.db"
            ).build()
    }
}
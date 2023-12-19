package edu.first.mvvmshopping.data.db.entities

import androidx.lifecycle.LiveData
import androidx.room.*
import edu.first.mvvmshopping.data.db.entities.ShoppingItem

@Dao
interface ShopDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(item: ShoppingItem)

    @Delete
    suspend fun delete(item: ShoppingItem)

    @Query(value = "SELECT * FROM shopping_table")
    fun getShoppingList() : LiveData<List<ShoppingItem>>
}
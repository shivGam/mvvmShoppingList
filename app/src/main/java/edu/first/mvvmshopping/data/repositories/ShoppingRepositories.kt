package edu.first.mvvmshopping.data.repositories

import edu.first.mvvmshopping.data.db.entities.ShoppingDatabase
import edu.first.mvvmshopping.data.db.entities.ShoppingItem

class ShoppingRepositories (private val db: ShoppingDatabase) {
    suspend fun upsert(item: ShoppingItem) = db.getShopDao().upsert(item)
    suspend fun delete(item: ShoppingItem) = db.getShopDao().delete(item)
    fun getAllShoppingItem() = db.getShopDao().getShoppingList()
}
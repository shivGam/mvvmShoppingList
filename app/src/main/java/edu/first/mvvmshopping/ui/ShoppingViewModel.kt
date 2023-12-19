package edu.first.mvvmshopping.ui

import androidx.lifecycle.ViewModel
import edu.first.mvvmshopping.data.db.entities.ShoppingItem
import edu.first.mvvmshopping.data.repositories.ShoppingRepositories
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ShoppingViewModel(private val repositories: ShoppingRepositories) :ViewModel() {

    fun upsert(item: ShoppingItem) = CoroutineScope(Dispatchers.Main).launch {
        repositories.upsert(item)
    }

    fun delete(item: ShoppingItem) = CoroutineScope(Dispatchers.Main).launch {
        repositories.delete(item)
    }

    fun getAllShoppingItem() = repositories.getAllShoppingItem()
}
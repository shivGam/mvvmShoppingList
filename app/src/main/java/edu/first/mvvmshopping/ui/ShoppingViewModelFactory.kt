package edu.first.mvvmshopping.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import edu.first.mvvmshopping.data.repositories.ShoppingRepositories

@Suppress("UNCHECKED_CAST")
class ShoppingViewModelFactory(
    private val repositories: ShoppingRepositories
): ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ShoppingViewModel(repositories) as T
    }
}
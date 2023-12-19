package edu.first.mvvmshopping.ui

import edu.first.mvvmshopping.data.db.entities.ShoppingItem

interface AddDialogListener {
    fun onAddButtonClicked(item: ShoppingItem)
}
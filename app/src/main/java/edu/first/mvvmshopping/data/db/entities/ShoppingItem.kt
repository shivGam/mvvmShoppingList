package edu.first.mvvmshopping.data.db.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "shopping_table")
data class ShoppingItem(
    @ColumnInfo(name = "Item_name")
    val name : String,
    @ColumnInfo(name = "Item_amount")
    var amount : Int
){
    @PrimaryKey(autoGenerate = true)
    var id : Int ? = null
}

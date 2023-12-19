package edu.first.mvvmshopping.ui

import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDialog
import edu.first.mvvmshopping.R
import edu.first.mvvmshopping.data.db.entities.ShoppingItem
import kotlinx.android.synthetic.main.dialog_add_item.*

class AddItemDialog(context: Context,var addDialogListener : AddDialogListener) :AppCompatDialog(context) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dialog_add_item)
        tvAdd?.setOnClickListener{
            val name = etName.text.toString()
            val amount = etAmount.text.toString()

            if(name.isEmpty() || amount.isEmpty())
            {
                Toast.makeText(context,"All fields required",Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val item = ShoppingItem(name, amount.toInt())
            addDialogListener.onAddButtonClicked(item)
            dismiss()
        }
        tvCancel?.setOnClickListener{
            cancel()
        }
    }

}
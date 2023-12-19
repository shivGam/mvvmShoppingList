package edu.first.mvvmshopping.ui

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import edu.first.mvvmshopping.R
import edu.first.mvvmshopping.data.db.entities.ShoppingDatabase
import edu.first.mvvmshopping.data.db.entities.ShoppingItem
import edu.first.mvvmshopping.data.others.ShoppingItemAdapter
import edu.first.mvvmshopping.data.repositories.ShoppingRepositories
import kotlinx.android.synthetic.main.activity_shop.*
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.kodein
import org.kodein.di.generic.instance

class ShopActivity : AppCompatActivity() ,KodeinAware {

    override val kodein by kodein()
    private val factory:ShoppingViewModelFactory by instance()

    @SuppressLint("NotifyDataSetChanged")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shop)

        val viewModel = ViewModelProviders.of(this,factory)[ShoppingViewModel::class.java]

        val adapter = ShoppingItemAdapter(listOf(),viewModel)
        rvShoppingItems.layoutManager = LinearLayoutManager(this)
        rvShoppingItems.adapter = adapter

        viewModel.getAllShoppingItem().observe(this, Observer {
            adapter.items=it
            adapter.notifyDataSetChanged()
        })
        val swipeGesture= object :SwipeGesture(){
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                when(direction){
                    ItemTouchHelper.LEFT->{
                        adapter.deleteItem(viewHolder.adapterPosition)
                    }
                }
            }
        }
        val touchHelper = ItemTouchHelper(swipeGesture)
        touchHelper.attachToRecyclerView(rvShoppingItems)

        fab.setOnClickListener{
            AddItemDialog(this,
            object : AddDialogListener {
                override fun onAddButtonClicked(item: ShoppingItem) {
                    viewModel.upsert(item)
                }

            }).show()
        }
    }
}
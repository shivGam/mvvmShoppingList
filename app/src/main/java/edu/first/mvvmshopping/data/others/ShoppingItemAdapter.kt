package edu.first.mvvmshopping.data.others

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import edu.first.mvvmshopping.R
import edu.first.mvvmshopping.data.db.entities.ShoppingItem
import edu.first.mvvmshopping.ui.ShoppingViewModel
import kotlinx.android.synthetic.main.shoppingitem.view.*

class ShoppingItemAdapter(
    var items: List<ShoppingItem>,
    private val viewModel: ShoppingViewModel
) : RecyclerView.Adapter<ShoppingItemAdapter.ShoppingViewHolder>(){

    fun deleteItem(i :Int){
        viewModel.delete(items[i])
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShoppingViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.shoppingitem,parent,false)
        return ShoppingViewHolder(view)
    }

    override fun onBindViewHolder(holder: ShoppingViewHolder, position: Int) {
        val currPos=items[position]
        holder.itemView.tvName.text= currPos.name
        holder.itemView.tvAmount.text= "${currPos.amount}"

//        holder.itemView.ivDelete.setOnClickListener{
//            viewModel.delete(currPos)
//        }
        holder.itemView.ivPlus.setOnClickListener{
            currPos.amount++
            viewModel.upsert(currPos)
        }
        holder.itemView.ivMinus.setOnClickListener{
            if(currPos.amount>0)
            {
                currPos.amount--
                viewModel.upsert(currPos)
            }
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    inner class ShoppingViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}
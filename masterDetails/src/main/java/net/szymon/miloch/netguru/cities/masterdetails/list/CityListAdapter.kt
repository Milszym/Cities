package net.szymon.miloch.netguru.cities.masterdetails.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.master_details_city_list_item.view.*
import net.szymon.miloch.netguru.cities.city.color.CityColor
import net.szymon.miloch.netguru.cities.masterdetails.R

class CityListAdapter(
    private var items: List<CityColor>,
    private val onCityColorClick: (CityColor) -> Unit
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = items[position]
        holder.itemView.city.text = item.city
        holder.itemView.city.setTextColor(item.color)
        holder.itemView.dateTime.text = item.emissionDate.toString()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.master_details_city_list_item, parent, false).apply {
                layoutParams = ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
                )
            }
        val viewHolder = ViewHolderCreator(view)
        view.setOnClickListener {
            val position = viewHolder.adapterPosition
            onCityColorClick(items[position])
        }
        return viewHolder
    }

    override fun getItemCount(): Int = items.size

    fun updateItems(newItems: List<CityColor>) {
        val diffCallback = CityColorDiffUtilCallback(items, newItems)
        val result = DiffUtil.calculateDiff(diffCallback)
        items = newItems
        result.dispatchUpdatesTo(this)
    }
}

class ViewHolderCreator(itemView: View) : RecyclerView.ViewHolder(itemView)
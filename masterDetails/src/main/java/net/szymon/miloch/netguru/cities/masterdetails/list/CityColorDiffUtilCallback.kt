package net.szymon.miloch.netguru.cities.masterdetails.list

import androidx.recyclerview.widget.DiffUtil
import net.szymon.miloch.netguru.cities.city.color.CityColor

/**
 * Used for list animations.
 */
class CityColorDiffUtilCallback(
    private val oldCityColors: List<CityColor>,
    private val newCityColors: List<CityColor>
) : DiffUtil.Callback() {
    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldCityColors[oldItemPosition] == newCityColors[newItemPosition]
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldCityColors[oldItemPosition] == newCityColors[newItemPosition]
    }

    override fun getNewListSize(): Int = newCityColors.size
    override fun getOldListSize(): Int = oldCityColors.size
}
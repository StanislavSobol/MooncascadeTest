package com.example.mooncascadetest.presentation.placesandwinds

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.example.mooncascadetest.R
import com.example.mooncascadetest.databinding.ItemPlaceAndWindBinding
import com.example.mooncascadetest.databinding.ItemTitleBinding
import com.example.mooncascadetest.presentation.placesandwinds.model.BasePlaceAndWindItem
import com.example.mooncascadetest.presentation.placesandwinds.model.PlaceAndWindsItemDelegate
import com.example.mooncascadetest.presentation.placesandwinds.model.PlaceAndWindsItemDelegateType
import com.example.mooncascadetest.presentation.placesandwinds.model.TitlePlaceAndWindsLisItem

class PlacesAndWindsAdapter : RecyclerView.Adapter<PlacesAndWindsAdapter.Holder>() {

    var itemOnClick: ((BasePlaceAndWindItem) -> (Unit))? = null

    private val items = mutableListOf<PlaceAndWindsItemDelegate>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        return when (viewType) {
            PlaceAndWindsItemDelegateType.PLACE.typeInt,
            PlaceAndWindsItemDelegateType.WIND.typeInt -> Holder(
                ItemPlaceAndWindBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            )
            PlaceAndWindsItemDelegateType.TITLE.typeInt -> Holder(
                ItemTitleBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            )
            else -> throw IllegalStateException("Cannot create a Holder: unknown type of PlaceAndWindsItemDelegateType")
        }
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemViewType(position: Int) = items[position].type.typeInt

    fun setItems(items: List<PlaceAndWindsItemDelegate>) {
        this.items.run {
            clear()
            addAll(items)
        }
        notifyDataSetChanged()
    }

    inner class Holder(private val binding: ViewBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: PlaceAndWindsItemDelegate) {
            when (item.type) {
                PlaceAndWindsItemDelegateType.PLACE,
                PlaceAndWindsItemDelegateType.WIND -> {
                    (binding as? ItemPlaceAndWindBinding)?.let {
                        bindItem(item as BasePlaceAndWindItem, binding)
                    }
                }
                PlaceAndWindsItemDelegateType.TITLE -> {
                    (binding as? ItemTitleBinding)?.let {
                        bindTitleDate(
                            item as TitlePlaceAndWindsLisItem,
                            binding
                        )
                    }
                }
            }
        }

        private fun bindItem(item: BasePlaceAndWindItem, binding: ItemPlaceAndWindBinding) {
            with(binding) {
                nameTextView.text = item.name
                dayRangeTextView.text = item.dayRange
                nightRangeTextView.text = item.nightRange
                imageView.setImageResource(
                    if (item.type == PlaceAndWindsItemDelegateType.WIND) {
                        R.drawable.ic_baseline_call_made_24
                    } else {
                        R.drawable.ic_baseline_location_city_24
                    }
                )
                itemView.setOnClickListener { itemOnClick?.invoke(item) }
            }
        }

        private fun bindTitleDate(item: TitlePlaceAndWindsLisItem, binding: ItemTitleBinding) {
            binding.titleTextView.text = item.title
        }
    }
}
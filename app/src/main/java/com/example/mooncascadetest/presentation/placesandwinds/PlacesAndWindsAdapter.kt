package com.example.mooncascadetest.presentation.placesandwinds

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.example.mooncascadetest.databinding.ItemPlaceAndWindBinding
import com.example.mooncascadetest.databinding.ItemTitleBinding

class PlacesAndWindsAdapter : RecyclerView.Adapter<PlacesAndWindsAdapter.Holder>() {

    var placesAndWindsOnClick: ((Int) -> (Unit))? = null

    private val items = mutableListOf<PlaceAndWindsItemDelegate>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        return when (viewType) {
            PlaceAndWindsItemDelegate.PlaceAndWindsItemDelegateType.ITEM.type -> Holder(
                ItemPlaceAndWindBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            )
            PlaceAndWindsItemDelegate.PlaceAndWindsItemDelegateType.TITLE.type -> Holder(
                ItemTitleBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            )
            else -> throw IllegalStateException("Cannot create a Holder: unknown type of PlaceAndWindsItemDelegateType")
        }
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemViewType(position: Int) = items[position].type.type

    fun setItems(items: List<PlaceAndWindsItemDelegate>) {
        Log.d("SSS", "places and winds setItems ${items.size}")

        this.items.run {
            clear()
            addAll(items)
        }
        notifyDataSetChanged()
    }

    inner class Holder(private val binding: ViewBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: PlaceAndWindsItemDelegate) {
            when (item.type) {
                PlaceAndWindsItemDelegate.PlaceAndWindsItemDelegateType.ITEM -> {
                    (binding as? ItemPlaceAndWindBinding)?.let {
                        bindItem(item as PlaceAndWindsItem, binding)
                    }
                }
                PlaceAndWindsItemDelegate.PlaceAndWindsItemDelegateType.TITLE -> {
                    (binding as? ItemTitleBinding)?.let {
                        bindTitleDate(
                            item as TitlePlaceAndWindsLisItem,
                            binding
                        )
                    }
                }
            }
        }

        private fun bindItem(item: PlaceAndWindsItem, binding: ItemPlaceAndWindBinding) {
            with(binding) {
                nameTextView.text = item.name
                rangeTextView.text = item.range
            }
        }

        private fun bindTitleDate(item: TitlePlaceAndWindsLisItem, binding: ItemTitleBinding) {
            binding.titleTextView.text = item.title
        }
    }

    companion object {
        private const val DATE_FORMAT = "dd.MM.yyyy"
    }
}
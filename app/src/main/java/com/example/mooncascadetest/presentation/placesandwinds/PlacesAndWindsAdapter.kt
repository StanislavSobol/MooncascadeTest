package com.example.mooncascadetest.presentation.placesandwinds

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.example.mooncascadetest.databinding.ItemPlaceAndWindBinding
import com.example.mooncascadetest.databinding.ItemTitleBinding

class PlacesAndWindsAdapter : RecyclerView.Adapter<PlacesAndWindsAdapter.Holder>() {

    var itemOnClick: ((BasePlaceAndWindItem) -> (Unit))? = null
//    var itemOnClick: ((PlaceAndWindsItemDelegate.PlaceAndWindsItemDelegateType, Int) -> (Unit))? = null
//    var placeOnClick: ((Int) -> (Unit))? = null
//    var windOnClick: ((Int) -> (Unit))? = null

    private val items = mutableListOf<PlaceAndWindsItemDelegate>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        return when (viewType) {
            PlaceAndWindsItemDelegate.PlaceAndWindsItemDelegateType.PLACE.type,
            PlaceAndWindsItemDelegate.PlaceAndWindsItemDelegateType.WIND.type -> Holder(
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
        this.items.run {
            clear()
            addAll(items)
        }
        notifyDataSetChanged()
    }

    inner class Holder(private val binding: ViewBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: PlaceAndWindsItemDelegate) {
            when (item.type) {
                PlaceAndWindsItemDelegate.PlaceAndWindsItemDelegateType.PLACE,
                PlaceAndWindsItemDelegate.PlaceAndWindsItemDelegateType.WIND -> {
                    (binding as? ItemPlaceAndWindBinding)?.let {
                        bindItem(item as BasePlaceAndWindItem, binding)
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

        private fun bindItem(item: BasePlaceAndWindItem, binding: ItemPlaceAndWindBinding) {
            binding.nameTextView.text = item.name
            binding.rangeTextView.text = item.range
            itemView.setOnClickListener { itemOnClick?.invoke(item) }
//            when (item.type) {
//                PlaceAndWindsItemDelegate.PlaceAndWindsItemDelegateType.PLACE ->
//                    itemView.setOnClickListener { placeOnClick?.invoke(item.id) }
//                PlaceAndWindsItemDelegate.PlaceAndWindsItemDelegateType.WIND ->
//                    itemView.setOnClickListener { windOnClick?.invoke(item.id) }
//                else -> {
//                    throw java.lang.IllegalStateException("Wrong type to set the item click listener")
//                }
//            }
        }

        private fun bindTitleDate(item: TitlePlaceAndWindsLisItem, binding: ItemTitleBinding) {
            binding.titleTextView.text = item.title
        }
    }
}
package com.example.mooncascadetest.presentation.mainscreen

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.example.mooncascadetest.databinding.ItemCurrentDayForecastBinding
import com.example.mooncascadetest.databinding.ItemFutureDayForecastBinding
import com.example.mooncascadetest.databinding.ItemTitleBinding

class MainScreenItemsAdapter : RecyclerView.Adapter<MainScreenItemsAdapter.Holder>() {

    // TODO !!!
    var itemOnClick: ((Int) -> (Unit))? = null

    private val items = mutableListOf<MainScreenListItemDelegate>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        return when (viewType) {
            MainScreenListItemDelegateType.CURRENT.type -> Holder(
                ItemCurrentDayForecastBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            )
            MainScreenListItemDelegateType.FUTURE.type -> Holder(
                ItemFutureDayForecastBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            )
            MainScreenListItemDelegateType.TITLE.type -> Holder(
                ItemTitleBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            )
            else -> throw IllegalStateException("Cannot create a Holder: unknown type of MainScreenListItemDelegateType")
        }
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemViewType(position: Int) = items[position].type.type

    fun setItems(items: List<MainScreenListItemDelegate>) {
        this.items.run {
            clear()
            addAll(items)
        }
        notifyDataSetChanged()
    }

    inner class Holder(private val binding: ViewBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: MainScreenListItemDelegate) {
            when (item.type) {
                MainScreenListItemDelegateType.CURRENT -> {
                    (binding as? ItemCurrentDayForecastBinding)?.let {
                        bindCurrentDateItem(
                            item as DayForecastMainScreenListItem,
                            binding
                        )
                    }
                }
                MainScreenListItemDelegateType.FUTURE -> {
                    (binding as? ItemFutureDayForecastBinding)?.let {
                        bindFutureDateItem(
                            item as DayForecastMainScreenListItem,
                            binding
                        )
                    }
                }
                MainScreenListItemDelegateType.TITLE -> {
                    (binding as? ItemTitleBinding)?.let {
                        bindTitleDateItem(
                            item as TitleMainScreenLisItem,
                            binding
                        )
                    }
                }
            }
        }


        private fun bindCurrentDateItem(item: DayForecastMainScreenListItem, binding: ItemCurrentDayForecastBinding) {
            with(binding) {
                // TODO date
                binding.dateTextView.text = item.date.toString()
                binding.dayPhenomenonTextView.text = item.dayPhenomenon
            }
        }

        private fun bindFutureDateItem(item: DayForecastMainScreenListItem, binding: ItemFutureDayForecastBinding) {
            with(binding) {
                // TODO date
                binding.dateTextView.text = item.date.toString()
            }
        }

        private fun bindTitleDateItem(item: TitleMainScreenLisItem, binding: ItemTitleBinding) {
            binding.titleTextView.text = item.title
        }
    }
}
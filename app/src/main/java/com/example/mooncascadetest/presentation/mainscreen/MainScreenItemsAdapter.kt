package com.example.mooncascadetest.presentation.mainscreen

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.example.mooncascadetest.databinding.ItemCurrentDayForecastBinding
import com.example.mooncascadetest.databinding.ItemFutureDayForecastBinding
import com.example.mooncascadetest.databinding.ItemTitleBinding
import java.text.SimpleDateFormat
import java.util.*

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

        // TODO bindCurrentDateItem + bindFutureDateItem ?
        private fun bindCurrentDateItem(item: DayForecastMainScreenListItem, binding: ItemCurrentDayForecastBinding) {
            with(binding) {
                dateTextView.text = SimpleDateFormat(DATE_FORMAT, Locale.getDefault()).format(item.date)

                dayPhenomenonTextView.setTextWithVisibility(item.dayPhenomenon)
                dayTempTextView.setTextWithVisibility(item.dayTempRange)
                dayMinTempWordsTextView.setTextWithVisibility(item.dayMinTempWords)
                dayMaxTempWordsTextView.setTextWithVisibility(item.dayMaxTempWords)
                dayTextTextView.setTextWithVisibility(item.dayText)
                daySeaTextView.setTextWithVisibility(item.daySea)
                dayPeipsiTextView.setTextWithVisibility(item.dayPeipsi)

                nightPhenomenonTextView.setTextWithVisibility(item.nightPhenomenon)
                nightTempTextView.setTextWithVisibility(item.nightTempRange)
                nightMinTempWordsTextView.setTextWithVisibility(item.dayMinTempWords)
                nightMaxTempWordsTextView.setTextWithVisibility(item.dayMaxTempWords)
                nightTextTextView.setTextWithVisibility(item.nightText)
                nightSeaTextView.setTextWithVisibility(item.nightSea)
                nightPeipsiTextView.setTextWithVisibility(item.nightPeipsi)

                placesAndWindsButton.isVisible = item.isPlacesAndWindsExist
            }
        }

        private fun bindFutureDateItem(item: DayForecastMainScreenListItem, binding: ItemFutureDayForecastBinding) {
            with(binding) {
                dateTextView.text = SimpleDateFormat(DATE_FORMAT, Locale.getDefault()).format(item.date)

                dayPhenomenonTextView.setTextWithVisibility(item.dayPhenomenon)
                dayTempTextView.setTextWithVisibility(item.dayTempRange)
                dayMinTempWordsTextView.setTextWithVisibility(item.dayMinTempWords)
                dayMaxTempWordsTextView.setTextWithVisibility(item.dayMaxTempWords)
                dayTextTextView.setTextWithVisibility(item.dayText)
                daySeaTextView.setTextWithVisibility(item.daySea)
                dayPeipsiTextView.setTextWithVisibility(item.dayPeipsi)

                nightPhenomenonTextView.setTextWithVisibility(item.nightPhenomenon)
                nightTempTextView.setTextWithVisibility(item.nightTempRange)
                nightMinTempWordsTextView.setTextWithVisibility(item.dayMinTempWords)
                nightMaxTempWordsTextView.setTextWithVisibility(item.dayMaxTempWords)
                nightTextTextView.setTextWithVisibility(item.nightText)
                nightSeaTextView.setTextWithVisibility(item.nightSea)
                nightPeipsiTextView.setTextWithVisibility(item.nightPeipsi)

                placesAndWindsButton.isVisible = item.isPlacesAndWindsExist
            }
        }

        private fun bindTitleDateItem(item: TitleMainScreenLisItem, binding: ItemTitleBinding) {
            binding.titleTextView.text = item.title
        }

        private fun TextView.setTextWithVisibility(text: String) {
            if (text.isNotBlank()) {
                this.text = text
            }
            isVisible = text.isNotBlank()
        }
    }

    companion object {
        private const val DATE_FORMAT = "dd.MM.yyyy"
    }
}
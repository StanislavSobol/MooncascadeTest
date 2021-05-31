package com.example.mooncascadetest.presentation.mainscreen.model

data class TitleMainScreenLisItem(val title: String) :
    MainScreenListItemDelegate {

    override val type: MainScreenListItemDelegateType
        get() = MainScreenListItemDelegateType.TITLE
}

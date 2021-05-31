package com.example.mooncascadetest.tools

import android.widget.TextView
import androidx.core.view.isVisible

fun TextView.setTextWithVisibility(text: String) {
    if (text.isNotBlank()) {
        this.text = text
    }
    isVisible = text.isNotBlank()
}
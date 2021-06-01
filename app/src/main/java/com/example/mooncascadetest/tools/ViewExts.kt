package com.example.mooncascadetest.tools

import android.graphics.Paint
import android.widget.TextView
import androidx.core.view.isVisible

fun TextView.setTextWithVisibility(text: String, underlined: Boolean = false) {
    if (text.isNotBlank()) {
        if (underlined) {
            paintFlags = paintFlags or Paint.UNDERLINE_TEXT_FLAG
        }
        this.text = text
    }
    isVisible = text.isNotBlank()
}
package com.example.cashapp.utils.display

import android.content.Context
import android.widget.Toast

object Toaster {
    fun show(context: Context, text: CharSequence) {
        Toast.makeText(context, text, Toast.LENGTH_SHORT).show()
    }
}

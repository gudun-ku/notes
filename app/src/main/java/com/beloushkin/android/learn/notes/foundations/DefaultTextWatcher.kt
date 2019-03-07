package com.beloushkin.android.learn.notes.foundations

import android.text.TextWatcher

abstract class DefaultTextWatcher : TextWatcher {

    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
        // no-operation
    }

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
        // no-operation
    }
}
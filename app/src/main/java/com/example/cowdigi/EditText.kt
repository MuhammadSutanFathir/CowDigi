package com.example.cowdigi
import android.content.Context
import android.graphics.Canvas
import android.text.Editable
import android.text.TextWatcher
import android.util.AttributeSet
import android.view.View
import androidx.appcompat.widget.AppCompatEditText

class EditText @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : AppCompatEditText(context, attrs) {
    init {
        // Add a text watcher for validation
        addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                // Do nothing
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (s != null && s.isNotEmpty() && !s.toString().matches(Regex("^[0-9.]+$"))) {
                    setError("Masukkan angka", null)
                } else {
                    error = null // Remove error if input is valid or empty
                }
            }

            override fun afterTextChanged(s: Editable?) {
                // Do nothing
            }
        })
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        // Set hint for the EditText
        textAlignment = View.TEXT_ALIGNMENT_VIEW_START
    }
}

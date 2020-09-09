package ru.mikaeliv.canvas

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.inputmethod.EditorInfo
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnApply.setOnClickListener { updateUI() }

        etProgress.setOnEditorActionListener { v, actionId, event ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                updateUI()
            }
            false
        }
    }

    private fun updateUI() {
        progress.setProgress(etProgress.text.toString().toFloat())
        tvProgressCount.text = etProgress.text.toString()
    }
}
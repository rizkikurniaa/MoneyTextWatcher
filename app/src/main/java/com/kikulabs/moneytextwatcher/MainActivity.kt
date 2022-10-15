package com.kikulabs.moneytextwatcher

import android.os.Bundle
import android.text.TextWatcher
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity() {
    val randNumb = Random()
    var moneyTextWatcher: TextWatcher? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        random.setOnClickListener {
            val locales = Locale.getAvailableLocales()
            val locale = locales[randNumb.nextInt(locales.size)]
            update(locale, randNumb.nextInt(1000000))
        }

        val myIndonesianLocale = Locale("in", "ID")

        // Rp15.000 default
        update(myIndonesianLocale, 15000)
    }

    private fun update(locale: Locale, amount: Int) {
        if (moneyTextWatcher != null) {
            edittext.removeTextChangedListener(moneyTextWatcher)
        }

        moneyTextWatcher = MoneyTextWatcher(edittext, locale)
        edittext.addTextChangedListener(moneyTextWatcher)
        edittext.setText(amount.toString())
        edittext.setSelection(edittext.text.toString().length)
    }
}
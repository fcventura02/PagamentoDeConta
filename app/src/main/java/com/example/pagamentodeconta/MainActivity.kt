package com.example.pagamentodeconta

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.SeekBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.math.round

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var valueRate = 10
        btm_calculate.setOnClickListener {
            val amount = bill_amount.text.toString()
            val paid = amount_paid.text.toString()
            if (amount.isNotEmpty() && paid.isNotEmpty())
                calcular(valueRate, amount.toFloat(), paid.toFloat())
        }
        btm_clean.setOnClickListener {
            clean()
        }
        rate.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar, progress: Int, formUser: Boolean) {
                valueRate = progress
                val text = getString(R.string.rate_value_string, valueRate)
                tv_rate_value.text = text
                val amount = bill_amount.text.toString()

                if (amount.isNotEmpty()) {
                    calculaTotal(amount.toFloat(), valueRate)
                }

            }

            override fun onStartTrackingTouch(p0: SeekBar?) {
            }

            override fun onStopTrackingTouch(p0: SeekBar?) {
            }
        })
    }


    @SuppressLint("SetTextI18n")
    fun calcular(rate: Int, amount: Float, paid: Float) {
        tv_change
        if (bill_amount.length() == 0 || amount_paid.length() == 0)
            return Toast.makeText(
                this@MainActivity,
                "Insira valores nos campos", Toast.LENGTH_SHORT
            ).show()
        //calcula a porcentagem e o total da operação
        val result = calculaTotal(amount, rate)

        val change = paid - result
        var message = "Voltar Troco"

        if (change < 0)
            message = "Falta Receber"


        tv_change.text = message
        text_change.text = "R$ ${round(change * 100) / 100}"


    }

    @SuppressLint("SetTextI18n")
    private fun calculaTotal(amount: Float, rate: Int): Float {
        val rateValue = amount * rate / 100
        val result = amount + rateValue

        text_rate.text = "R$ ${
            round(rateValue * 100) / 100
        }"
        text_result.text = "R$ ${
            round(result * 100) / 100
        }"

        return result
    }

    @SuppressLint("SetTextI18n")
    fun clean() {
        bill_amount.setText("")
        amount_paid.setText("")
        tv_change.text = "Troco"
        text_rate.text = "R$ 0.00"
        text_change.text = "R$ 0.00"
        text_result.text = "R$ 0.00"
    }
}


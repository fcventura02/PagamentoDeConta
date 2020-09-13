package com.example.pagamentodeconta

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.SeekBar
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.math.round

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var valueRate = 10
        btm_calculate.setOnClickListener {
            calcular(valueRate)
        }
        btm_clean.setOnClickListener {
            clean()
        }
        rate.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener{
            @SuppressLint("SetTextI18n")
            override fun onProgressChanged(seekBar: SeekBar, progress: Int, formUser: Boolean) {
                valueRate = progress
                tv_rate_value.text = "Valor da taxa é de: $valueRate%"
            }

            override fun onStartTrackingTouch(p0: SeekBar?) {
            }

            override fun onStopTrackingTouch(p0: SeekBar?) {
            }
        })
    }



    @SuppressLint("SetTextI18n")
    fun calcular(rate:Int) {
        tv_change
        if (bill_amount.length() == 0 || amount_paid.length() == 0)
            return Toast.makeText(
                this@MainActivity,
                "Insira valores nos campos", Toast.LENGTH_SHORT
            ).show()

        val amount = bill_amount.text.toString().toFloat()
        val paid = amount_paid.text.toString().toFloat()

        if (amount <= 0 || paid <= 0)
            return Toast.makeText(
                this@MainActivity,
                "Insira valores maiores que 0 nos campos", Toast.LENGTH_SHORT
            ).show()

        val rateValue = amount * rate / 100
        val result = amount + rateValue
        val change = paid - result
        var message = "Voltar Troco"

        if (change < 0)
            message = "Falta Receber"

        text_rate.text = "R$ ${round(rateValue*100)/100}"
        text_result.text = "R$ $result"
        tv_change.text = message
        text_change.text = "R$ ${round(change*100)/100}"


    }

    @SuppressLint("SetTextI18n")
    fun clean() {
        bill_amount.setText("")
        amount_paid.setText("")
        tv_change.text = "Troco"
        text_rate.text = "R$ "
        text_change.text = "R$ "
        text_result.text = "R$ "
    }
}


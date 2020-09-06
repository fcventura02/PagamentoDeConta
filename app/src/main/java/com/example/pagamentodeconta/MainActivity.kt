package com.example.pagamentodeconta

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btm_calculate.setOnClickListener {
            calcular()
        };
        btm_clean.setOnClickListener {
            clean()
        }
    }

    fun calcular() {
        tv_change
        if (bill_amount.length() === 0 || amount_paid.length() === 0)
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

        var rate = amount * 0.15
        var result = amount + rate
        var change = paid - result
        var message: String = "Voltar Troco"
        if (change < 0)
            message = "Falta Receber"
        text_rate.setText("R$ " + rate)
        text_result.setText("R$ " + result)
        tv_change.setText(message)
        text_change.setText("R$ " + change)


    }

    fun clean() {
        bill_amount.setText("")
        amount_paid.setText("")
        tv_change.setText("Troco")
        text_rate.setText("R$ ")
        text_change.setText("R$ ")
        text_result.setText("R$ ")
    }
}


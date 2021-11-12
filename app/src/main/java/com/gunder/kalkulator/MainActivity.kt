package com.gunder.kalkulator

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.gunder.kalkulator.databinding.ActivityMainBinding
import net.objecthunter.exp4j.ExpressionBuilder

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.apply {
            btn00.setOnClickListener { appendOnClick(true, "00") }
            btn0.setOnClickListener { appendOnClick(true, "0") }
            btn1.setOnClickListener { appendOnClick(true, "1") }
            btn2.setOnClickListener { appendOnClick(true, "2") }
            btn3.setOnClickListener { appendOnClick(true, "3") }
            btn4.setOnClickListener { appendOnClick(true, "4") }
            btn5.setOnClickListener { appendOnClick(true, "5") }
            btn6.setOnClickListener { appendOnClick(true, "6") }
            btn7.setOnClickListener { appendOnClick(true, "7") }
            btn8.setOnClickListener { appendOnClick(true, "8") }
            btn9.setOnClickListener { appendOnClick(true, "9") }
            btnDots.setOnClickListener { appendOnClick(true, ".") }
            //        membuat listener dari operator dalam kalkulator
            btnPlus.setOnClickListener { appendOnClick(false, "+") }
            btnMinus.setOnClickListener { appendOnClick(false, "-") }
            btnMultiply.setOnClickListener { appendOnClick(false, "*") }
            btnDivide.setOnClickListener { appendOnClick(false, "/") }
            btnleftB.setOnClickListener { appendOnClick(false, "(") }
            btnRightB.setOnClickListener { appendOnClick(false, ")") }

            btnClear.setOnClickListener {
                clear()
            }
            btnEquals.setOnClickListener {
                calculate()
            }
        }


    }

    private fun appendOnClick(clear: Boolean, string: String) {
        if (clear) {
            binding.tvOutput.text = ""
            binding.tvInput.append(string)
        } else {
            binding.tvInput.append(binding.tvOutput.text)
            binding.tvInput.append(string)
            binding.tvOutput.text = ""
        }
    }

    private fun clear() {
        binding.tvInput.text = ""
        binding.tvOutput.text = ""
    }

    private fun calculate() {
        try {
            val input = ExpressionBuilder(binding.tvInput.text.toString()).build()
            val outPut = input.evaluate()
            val longOutput = outPut.toLong()
            if (outPut == longOutput.toDouble()) {
                binding.tvOutput.text = longOutput.toString()
            } else {
                binding.tvOutput.text = outPut.toString()
            }
        } catch (e: Exception) {
            Toast.makeText(this@MainActivity, e.message, Toast.LENGTH_LONG).show()
        }
    }
}
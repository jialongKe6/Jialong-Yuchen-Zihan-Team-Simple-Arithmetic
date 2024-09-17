package com.example.simplearithmetic

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.RadioGroup
import android.widget.TextView
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.simplearithmetic.ui.theme.SimpleArithmeticTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val operand1EditText = findViewById<EditText>(R.id.operand1)
        val operand2EditText = findViewById<EditText>(R.id.operand2)
        val operationGroup = findViewById<RadioGroup>(R.id.operationGroup)
        val calculateButton = findViewById<Button>(R.id.calculateButton)
        val resultText = findViewById<TextView>(R.id.resultText)

        calculateButton.setOnClickListener {
            val operand1 = operand1EditText.text.toString().toDoubleOrNull()
            val operand2 = operand2EditText.text.toString().toDoubleOrNull()

            if (operand1 == null || operand2 == null) {
                resultText.text = "Please enter valid numbers."
                return@setOnClickListener
            }

            val selectedOperationId = operationGroup.checkedRadioButtonId
            val result = performOperation(operand1, operand2, selectedOperationId)

            resultText.text = result ?: "Please select a valid operation."
        }
    }

    private fun performOperation(operand1: Double, operand2: Double, operationId: Int): String? {
        return try {
            when (operationId) {
                R.id.radio_add -> "Result: ${operand1 + operand2}"
                R.id.radio_subtract -> "Result: ${operand1 - operand2}"
                R.id.radio_multiply -> "Result: ${operand1 * operand2}"
                R.id.radio_divide -> handleDivision(operand1, operand2)
                R.id.radio_modulus -> handleModulus(operand1, operand2)
                else -> null
            }
        } catch (e: ArithmeticException) {
            e.message
        }
    }

    private fun handleDivision(operand1: Double, operand2: Double): String {
        return if (operand2 == 0.0) {
            "Divide by Zero not allowed."
        } else {
            "Result: ${operand1 / operand2}"
        }
    }

    private fun handleModulus(operand1: Double, operand2: Double): String {
        return if (operand2 == 0.0) {
            "Divide by Zero not allowed."
        } else {
            "Result: ${operand1 % operand2}"
        }
    }
}
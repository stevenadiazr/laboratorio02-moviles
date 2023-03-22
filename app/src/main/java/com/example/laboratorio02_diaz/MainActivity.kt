package com.example.laboratorio02_diaz

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat

class MainActivity : AppCompatActivity() {

    private lateinit var weitghEditText: EditText
    private lateinit var heightEditText: EditText
    private lateinit var actionCalculate: Button
    private lateinit var bmiTextView: TextView
    private lateinit var resultTextView: TextView
    private lateinit var infoTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bind()
        setListener()

    }

    private fun bind(){
        weitghEditText = findViewById(R.id.weight_edit_text)
        heightEditText = findViewById(R.id.height_edit_text)
        actionCalculate = findViewById(R.id.action_calculate)
        bmiTextView = findViewById(R.id.bmi_text_view)
        resultTextView = findViewById(R.id.result_text_view)
        infoTextView = findViewById(R.id.info_text_view)

        setListener()
    }

    private fun setListener(){
        actionCalculate.setOnClickListener{
            val weight = weitghEditText.text.toString()
            val height  = heightEditText.text.toString()

            if (!validateInput(weight, height)){
                clearTextView()
                return@setOnClickListener
            }

            val bmi = calculateBmi(weight.toFloat(), height.toFloat())
            val bmiTwoDigits = String.format("%.2f", bmi).toFloat()

            clearFocus()
            displayResult(bmiTwoDigits)
        }
    }

    private fun validateInput(weight: String?, height: String): Boolean {
        when{
            weight.isNullOrEmpty() -> {
                Toast.makeText( this, "weight is empty!!", Toast.LENGTH_SHORT).show()
                return false
            }
        }
        when{
            height.isNullOrEmpty() -> {
                Toast.makeText( this, "Height is empty!!", Toast.LENGTH_SHORT).show()
                    return false
            }
        }
        return true
    }

    private fun calculateBmi(weight: Float, height: Float): Float{
        return weight/((height/100)*(height/100))
    }

    private fun clearFocus(){
        weitghEditText.clearFocus()
        heightEditText.clearFocus()
    }

    private fun displayResult(bmi: Float){
        bmiTextView.text = bmi.toString()
        infoTextView.text = "(Normal Range is 18.5 - 24.9)"

        var informationResult = ""
        var color = 0

        when{
            bmi < 18.5 ->{
                informationResult = "Underweight"
                color = R.color.under_weight
            }
            bmi in 18.5..24.99 ->{
                informationResult = "Healthy"
                color = R.color.normal_weight
            }
            bmi in 25.00..29.99 ->{
                informationResult = "Overweight"
                color = R.color.over_weight
            }bmi > 29.99 ->{
            informationResult = "Obese"
            color = R.color.obese
            }
        }

        resultTextView.text = informationResult
        resultTextView.setTextColor(ContextCompat.getColor(this, color))

    }

    private fun clearTextView(){
        bmiTextView.text = ""
        resultTextView.text = ""
        infoTextView.text = ""
    }
}
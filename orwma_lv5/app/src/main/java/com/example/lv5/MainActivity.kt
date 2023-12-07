package com.example.lv5

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val textView = findViewById<TextView>(R.id.textView)
        val textView2 = findViewById<TextView>(R.id.textView2)
        val editText = findViewById<EditText>(R.id.editText)
        val editText2 = findViewById<EditText>(R.id.editText2)
        val button = findViewById<Button>(R.id.button)
        button.setOnClickListener {
            textView.text = editText.editableText
            textView2.text = editText2.editableText
        }

        val editText3=findViewById<EditText>(R.id.editText3)
        val editText4=findViewById<EditText>(R.id.editText4)
        val button2=findViewById<Button>(R.id.button2)

        button2.setOnClickListener {
            val bmi=calculateBMI(editText3.text.toString().toFloat(), editText4.text.toString().toFloat())
            Toast.makeText(this, bmi ,Toast.LENGTH_LONG).show()
        }
    }
    fun calculateBMI(kg: Float, m2: Float): String {
        val bmi: Float = kg / m2
        return bmi.toString()
    }

}
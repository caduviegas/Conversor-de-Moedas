package br.com.innaval.conversordemoedas

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val etValor =findViewById<EditText>(R.id.etValor)
        val tvResultado = findViewById<TextView>(R.id.tvResultado)
        val btConverter = findViewById<Button>(R.id.btConverter)
        btConverter.setOnClickListener {

        }
    }
}
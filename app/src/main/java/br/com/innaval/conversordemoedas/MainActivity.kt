package br.com.innaval.conversordemoedas

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import java.math.RoundingMode
import java.text.DecimalFormat

class MainActivity : AppCompatActivity() {


    lateinit var etValor: EditText
    lateinit var tvResultado:TextView
    lateinit var btConverter: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        etValor = findViewById(R.id.etValor)
        tvResultado = findViewById(R.id.tvResultado)
        btConverter = findViewById(R.id.btConverter)
        btConverter.setOnClickListener {
            baixarDTODaInternet()
        }
    }

    private fun baixarDTODaInternet(){


    }

    private fun exibirResultadoCalculado(respostaDTO:RespostaDTO){
        val textoDigitado = etValor.text.toString()
        val reais = textoDigitado.toDoubleOrNull()
        if(reais!=null){
            val df = DecimalFormat("#.##")
            df.roundingMode = RoundingMode.CEILING
            val constanteDolar = respostaDTO.rates.USD
            val dolar = reais * constanteDolar
            val resultado =  df.format(dolar)
            val resultadoFinal = "Valor em Dolar: $resultado USD$"
            tvResultado.text = resultadoFinal
        } else{
            exibirErroDigitacao()
        }
    }

    private fun exibirErroDigitacao(){
        tvResultado.text = " Digite um Numero válido"
    }

    private fun exibirErroConexao(){
        tvResultado.text = " Erro ao Conectar com o Servidor"

    }
}
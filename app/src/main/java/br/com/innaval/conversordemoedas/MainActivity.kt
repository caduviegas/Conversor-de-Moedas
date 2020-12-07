package br.com.innaval.conversordemoedas

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import org.json.JSONObject
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
        val requisicoes = Volley.newRequestQueue(this)
        val url = "https://api.exchangeratesapi.io/latest?base=BRL"
        val metodo = Request.Method.GET
        val body = null
        val respostaSucesso =  Response.Listener<JSONObject> { json ->
            if(json!=null){
                val gson = Gson()
                val respostaDTO = gson.fromJson<RespostaDTO>(json.toString(), RespostaDTO::class.java)
                exibirResultadoCalculado(respostaDTO)
            }else{
                exibirErroConexao()
            }

        }
        val respostaErro =  Response.ErrorListener {
            exibirErroConexao()

        }

        val requisicaoJson = JsonObjectRequest(
                metodo,
                url,
                body,
                respostaSucesso,
                respostaErro
        )
        requisicoes.add(requisicaoJson)

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
package br.com.innaval.conversordemoedas

data class RespostaDTO (
        val rates:CotacoesDTO,
        val base:String,
        val date:String
        )
package br.com.innaval.conversordemoedas

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class RespostaDTO (
        @SerializedName("rates")val rates:CotacoesDTO,
        @SerializedName ("base")val base:String,
        @SerializedName("date")val date:String
         ): Parcelable
package com.example.nbuservice.rate.bean

import com.github.kittinunf.fuel.core.ResponseDeserializable
import com.google.gson.Gson

data class NbuCurrency (
        val cc: String,
        val rate: Double
        ) {

    class Deserializer: ResponseDeserializable<Array<NbuCurrency>> {
        override fun deserialize(content: String): Array<NbuCurrency>? = Gson().fromJson(content, Array<NbuCurrency>::class.java)
    }

}

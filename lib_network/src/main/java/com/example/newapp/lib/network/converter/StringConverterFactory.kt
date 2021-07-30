package com.example.newapp.lib.network.converter

import okhttp3.ResponseBody
import retrofit2.Converter
import retrofit2.Retrofit
import java.lang.reflect.Type

class StringConverterFactory : Converter.Factory() {

    override fun responseBodyConverter(
        type: Type,
        annotations: Array<out Annotation>,
        retrofit: Retrofit
    ) = object : Converter<ResponseBody, Any?> {
        val nextResponseBodyConverter =
            retrofit.nextResponseBodyConverter<Any?>(this@StringConverterFactory, type, annotations)

        override fun convert(value: ResponseBody): Any? {
            if (type === String::class.java) {
                return value.string()
            }
            return nextResponseBodyConverter.convert(value)
        }
    }
}

package com.cryptofuture.prediction.datasource

import android.content.Context
import com.cryptofuture.prediction.R
import java.io.*
import javax.inject.Inject


class PredictionJsonDataSource @Inject constructor(
    private val context: Context
) {

    fun getPredictionsData(): String {
        val inputStream: InputStream =
            context.resources.openRawResource(R.raw.rewards)
        val writer: Writer = StringWriter()
        val buffer = CharArray(1024)
        inputStream.use { inputStream ->
            val reader: Reader = BufferedReader(InputStreamReader(inputStream, "UTF-8"))
            var n: Int
            while (reader.read(buffer).also { n = it } != -1) {
                writer.write(buffer, 0, n)
            }
        }
        return writer.toString()
    }
}

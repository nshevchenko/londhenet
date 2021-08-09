package com.cryptofuture.map.repository

import android.content.Context
import com.cryptofuture.map.R
import java.io.*
import javax.inject.Inject


class MapJsonDataSource @Inject constructor(
    private val context: Context
) {

    fun getPins(): String {
        val inputStream: InputStream =
            context.resources.openRawResource(R.raw.london_table_app_feature1)
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

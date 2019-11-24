package com.testcase.catsapp.data.local

import android.net.Uri
import android.os.Environment
import com.testcase.catsapp.domain.model.Picture
import com.testcase.catsapp.utils.whileReadBytes
import io.reactivex.BackpressureStrategy
import io.reactivex.Flowable
import okhttp3.*
import java.io.*

class LocalManagerImpl(
    private val okHttpClient: OkHttpClient
): LocalManager {
    override fun savePicture(picture: Picture): Flowable<Int> {
        return Flowable.create<Int>({ emitter ->
            val request = Request.Builder().url(picture.url).build()
            val response = okHttpClient.newCall(request).execute()
            if (!response.isSuccessful) {
                emitter.onError(IOException("Failed download picture $picture with exception $response"))
                return@create
            }

            val uri = Uri.parse(picture.url)
            val pictureName = uri.lastPathSegment
            val file = File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS), pictureName)

            val byteStream = response.body()!!.byteStream()
            val reader = BufferedInputStream(byteStream)
            val fileOutputStream = FileOutputStream(file)
            val data = ByteArray(256)
            val fileLength = response.body()!!.contentLength()
            var total = 0

            data.whileReadBytes(reader) { count ->
                total += count
                emitter.onNext(getPercent(total, fileLength.toInt()))
                fileOutputStream.write(data, 0, count)
            }

            reader.close()
            fileOutputStream.flush()
            fileOutputStream.close()

            emitter.onComplete()
        }, BackpressureStrategy.BUFFER)
    }

    private fun getPercent(total: Int, fileLength: Int): Int {
        return (total.toFloat() / fileLength * 100).toInt()
    }
}
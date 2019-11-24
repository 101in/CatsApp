package com.testcase.catsapp.utils

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.provider.Settings
import java.io.InputStream

inline fun ByteArray.whileReadBytes(input: InputStream, block: ByteArray.(Int) -> Unit) {
    while (true) {
        val count = input.read(this)
        if (count < 0) break
        this.block(count)
    }
}

fun Context.startSettingsActivity() {
    val packageSchema = "package"
    val intent = Intent(
        Settings.ACTION_APPLICATION_DETAILS_SETTINGS,
        Uri.fromParts(
            packageSchema,
            packageName,
            null
        )
    )
    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
    startActivity(intent)
}
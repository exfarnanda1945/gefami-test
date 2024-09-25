package com.exfarnanda1945.mainquestion

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import java.security.MessageDigest
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@Composable
fun HashDateScreen(modifier: Modifier = Modifier) {
    val date = getDate()
    val str = date+"mikopriaifabula"

    Box(modifier = modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Column(
            modifier = Modifier.fillMaxWidth(),
        ) {
            Text("String: $str")
            Text("Hash sha256: ${encrypt(str)}")
        }
    }
}

@SuppressLint("NewApi")
fun getDate(): String {
    val today: LocalDate = LocalDate.now()
    val formatter = DateTimeFormatter.ofPattern("ddMMyyyy")
    val formattedDate = today.format(formatter)
   return formattedDate
}

fun encrypt(input: String): String {
    val bytes = MessageDigest.getInstance("SHA-256").digest(input.toByteArray())
    return bytes.joinToString("") {
        "%02x".format(it)
    }
}

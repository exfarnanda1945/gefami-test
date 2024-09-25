package com.exfarnanda1945.mainquestion

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun ArrayObjectScreen(modifier: Modifier = Modifier) {
    var listPerson by remember {
        mutableStateOf(
            (1..6).map { Person(
                name = "Person $it",
                id = it,
                age = 20 + it
            ) }
        )
    }
    Box(modifier = modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(listPerson.toString(), textAlign = TextAlign.Center)
            Spacer(modifier = Modifier.height(10.dp))

            ElevatedButton(onClick = {
                val random = (1..6).random()
                listPerson = listPerson.map{person ->
                   if( person.id == random)Person(name = "Data berubah $random", age = 30+random, id = random) else person
                }.toMutableList()
            }) {
                Text("Ubah List")
            }
        }
    }
}


data class Person(
    val name: String,
    val id: Int,
    val age: Int
)

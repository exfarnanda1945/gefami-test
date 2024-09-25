package com.exfarnanda1945.mainquestion

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

val Context.dataStore by preferencesDataStore(name = "isLogged")
val key = booleanPreferencesKey("status")

@Composable
fun LoginScreen(modifier: Modifier) {
    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    val isLoggedIn by getFromDs(context).collectAsState(false)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center
    ) {
        OutlinedTextField(
            value = username,
            onValueChange = { username = it },
            label = { Text("Username") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Password") },
            visualTransformation = PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(8.dp))

        Button(
            onClick = {
                if(username.isEmpty() || password.isEmpty()){
                    Toast.makeText(context,"Email dan password tidak boleh kosong",Toast.LENGTH_SHORT).show()
                }else{
                    coroutineScope.launch {
                        saveToDs(context, !isLoggedIn)
                    }
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Login")
        }
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = if (isLoggedIn) "Logged in as $username" else "Not logged in",
            style = MaterialTheme.typography.bodyMedium
        )
    }
}

suspend fun saveToDs(context: Context, value: Boolean) {
    context.dataStore.edit { preferences ->
        preferences[key] = value

    }
}

fun getFromDs(context: Context): Flow<Boolean> {
    return context.dataStore.data.map { value: Preferences ->
        value[key] ?: false
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    LoginScreen(modifier = Modifier)
}
// presentation/QuoteScreen.kt (Debería funcionar bien con el Theme.kt actualizado)
package com.example.inspquotes.presentation

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview // Para Previews
import androidx.compose.ui.unit.dp
import androidx.wear.compose.material.* // Importante: usar componentes de Wear
import com.example.inspquotes.presentation.theme.InspQuotesTheme
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import okhttp3.Request
import org.json.JSONArray
import java.io.IOException

// Tu función fetchQuote (asumiendo que está aquí o accesible)
suspend fun fetchQuote(): String = withContext(Dispatchers.IO) {
    val client = OkHttpClient()
    val request = Request.Builder()
        .url("https://zenquotes.io/api/random")
        .build()
    try {
        val response = client.newCall(request).execute()
        if (response.isSuccessful) {
            response.body?.let { body ->
                val jsonArray = JSONArray(body.string())
                if (jsonArray.length() > 0) {
                    val quoteObject = jsonArray.getJSONObject(0)
                    val q = quoteObject.getString("q")
                    val a = quoteObject.getString("a")
                    return@withContext "\"$q\"\n— $a"
                }
            }
        }
        return@withContext "No se pudo obtener la cita."
    } catch (e: Exception) {
        e.printStackTrace()
        return@withContext "Error al cargar cita."
    }
}


@Composable
fun QuoteScreen() {
    val coroutineScope = rememberCoroutineScope()
    var quoteText by remember { mutableStateOf("Pulsa el botón") }
    var isLoading by remember { mutableStateOf(false) }

    Scaffold(
        timeText = { TimeText(modifier = Modifier.padding(top = 4.dp)) }, // Añadí un poco de padding
        vignette = { Vignette(vignettePosition = VignettePosition.TopAndBottom) }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(start = 16.dp, end = 16.dp, bottom = 16.dp, top = 28.dp), // Ajustado el padding para no superponerse con TimeText
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Button(
                onClick = {
                    if (!isLoading) {
                        isLoading = true
                        coroutineScope.launch {
                            quoteText = fetchQuote()
                            isLoading = false
                        }
                    }
                },
                enabled = !isLoading,
                modifier = Modifier.fillMaxWidth(0.8f),
                colors = ButtonDefaults.buttonColors( // Ejemplo de cómo podrías personalizar un botón si quisieras
                    backgroundColor = MaterialTheme.colors.primary,
                    contentColor = MaterialTheme.colors.onPrimary
                )
            ) {
                Text(if (isLoading) "Cargando..." else "Motívame")
            }

            Spacer(modifier = Modifier.height(12.dp))

            Column(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth()
                    .verticalScroll(rememberScrollState())
            ) {
                Text(
                    text = quoteText,
                    modifier = Modifier
                        .padding(top = 8.dp, bottom = 8.dp)
                        .fillMaxWidth(),
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.body1, // Usará el color onSurface/onBackground del tema
                    color = MaterialTheme.colors.onBackground // Puedes ser explícito si es necesario
                )
            }
        }
    }
}

// --- Previews para probar los temas ---
@Preview(device = Devices.WEAR_OS_SMALL_ROUND, showSystemUi = true, name = "Light Theme Preview")
@Composable
fun QuoteScreenPreviewLight() {
    InspQuotesTheme(darkTheme = false) { // Forzar tema claro
        QuoteScreen()
    }
}

@Preview(device = Devices.WEAR_OS_SMALL_ROUND, showSystemUi = true, name = "Dark Theme Preview")
@Composable
fun QuoteScreenPreviewDark() {
    InspQuotesTheme(darkTheme = true) { // Forzar tema oscuro
        QuoteScreen()
    }
}
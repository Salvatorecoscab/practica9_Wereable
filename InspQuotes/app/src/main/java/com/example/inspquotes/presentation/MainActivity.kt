// presentation/MainActivity.kt - (Sin cambios, está bien)
package com.example.inspquotes.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.inspquotes.presentation.theme.InspQuotesTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            InspQuotesTheme { // InspQuotesTheme ahora maneja el tema claro/oscuro
                QuoteScreen()
            }
        }
    }
}
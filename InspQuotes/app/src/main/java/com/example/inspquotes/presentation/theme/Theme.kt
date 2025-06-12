package com.example.inspquotes.presentation.theme

import androidx.compose.foundation.isSystemInDarkTheme // Para detectar el tema del sistema
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color // Para definir colores
import androidx.wear.compose.material.Colors // Objeto de colores de Wear OS
import androidx.wear.compose.material.MaterialTheme // Tema de Wear OS
// Si quieres personalizar la tipografía, también importarías:
// import androidx.wear.compose.material.Typography

// Define tus colores. Puedes crear un archivo Color.kt si prefieres,
// o mantenerlos aquí para simplicidad en un proyecto pequeño de Wear OS.

// Colores para el tema CLARO
val WearAppLightPrimary = Color(0xFF006590) // Un azul
val WearAppLightOnPrimary = Color.White
val WearAppLightSecondary = Color(0xFF496170)
val WearAppLightOnSecondary = Color.White
val WearAppLightError = Color(0xFFB00020)
val WearAppLightOnError = Color.White
val WearAppLightBackground = Color(0xFFFDFDFD) // Fondo casi blanco
val WearAppLightOnBackground = Color(0xFF191C1E) // Texto oscuro sobre fondo claro
val WearAppLightSurface = Color(0xFFF0F2F5) // Superficie ligeramente diferente al fondo (para Cards, etc.)
val WearAppLightOnSurface = Color(0xFF191C1E) // Texto oscuro sobre superficie clara
val WearAppLightOnSurfaceVariant = Color(0xFF40484D) // Para texto menos prominente

// Colores para el tema OSCURO
val WearAppDarkPrimary = Color(0xFF7ACEFF) // Un azul claro para el tema oscuro
val WearAppDarkOnPrimary = Color(0xFF00344C)
val WearAppDarkSecondary = Color(0xFFB1CBDD)
val WearAppDarkOnSecondary = Color(0xFF1B3342)
val WearAppDarkError = Color(0xFFFFB4AB)
val WearAppDarkOnError = Color(0xFF690005)
val WearAppDarkBackground = Color(0xFF191C1E) // Fondo oscuro
val WearAppDarkOnBackground = Color(0xFFE1E2E5) // Texto claro sobre fondo oscuro
val WearAppDarkSurface = Color(0xFF24282C) // Superficie ligeramente diferente al fondo oscuro
val WearAppDarkOnSurface = Color(0xFFE1E2E5) // Texto claro sobre superficie oscura
val WearAppDarkOnSurfaceVariant = Color(0xFFC0C8CD) // Para texto menos prominente

private val LightColorPalette = Colors(
    primary = WearAppLightPrimary,
    primaryVariant = WearAppLightPrimary, // Puedes tener una variante si quieres
    secondary = WearAppLightSecondary,
    secondaryVariant = WearAppLightSecondary, // Puedes tener una variante si quieres
    error = WearAppLightError,
    background = WearAppLightBackground,
    surface = WearAppLightSurface,
    onPrimary = WearAppLightOnPrimary,
    onSecondary = WearAppLightOnSecondary,
    onError = WearAppLightOnError,
    onBackground = WearAppLightOnBackground,
    onSurface = WearAppLightOnSurface,
    onSurfaceVariant = WearAppLightOnSurfaceVariant
)

private val DarkColorPalette = Colors(
    primary = WearAppDarkPrimary,
    primaryVariant = WearAppDarkPrimary,
    secondary = WearAppDarkSecondary,
    secondaryVariant = WearAppDarkSecondary,
    error = WearAppDarkError,
    background = WearAppDarkBackground,
    surface = WearAppDarkSurface,
    onPrimary = WearAppDarkOnPrimary,
    onSecondary = WearAppDarkOnSecondary,
    onError = WearAppDarkOnError,
    onBackground = WearAppDarkOnBackground,
    onSurface = WearAppDarkOnSurface,
    onSurfaceVariant = WearAppDarkOnSurfaceVariant
)

@Composable
fun InspQuotesTheme(
    darkTheme: Boolean = isSystemInDarkTheme(), // Detecta si el sistema está en modo oscuro
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    // Aquí también podrías definir una 'Typography' personalizada para Wear OS si lo deseas.
    // val typography = Typography(...)

    MaterialTheme(
        colors = colors,
        // typography = typography, // Descomenta si defines tu propia tipografía
        content = content
    )
}
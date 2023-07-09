package com.example.atlasforms.ui.theme


import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val LightColorScheme = lightColorScheme(
    primary = Color(0xFF216c2e),
    onPrimary= Color(0xFFffffff),
    primaryContainer= Color(0xFFa7f5a7),
    onPrimaryContainer= Color(0xFF002106),
    secondary = Color(0xFFffffff),
    onSecondary= Color(0xFFffffff),
    secondaryContainer= Color(0xFFd5e8d0),
    onSecondaryContainer= Color(0xFF101f10),
    tertiary= Color(0xFF39656b),
    onTertiary= Color(0xFFffffff),
    tertiaryContainer= Color(0xFFbcebf2),
    onTertiaryContainer= Color(0xFF001f23),
    background= Color(0xFFfcfdf7),
    onBackground= Color(0xFF1a1c19),
    surface= Color(0xFFfcfdf7),
    onSurface= Color(0xFF1a1c19),
    surfaceVariant= Color(0xFFdee5d9),
    onSurfaceVariant= Color(0xFF424940),
    error= Color(0xFFba1a1a),
    onError= Color(0xFFffffff),
    errorContainer= Color(0xFFffdad6),
    onErrorContainer= Color(0xFF410002),
    outline= Color(0xFF72796f),
)


@Composable
fun AtlasFormsTheme(
    content: @Composable () -> Unit
) {
    val colorScheme =  LightColorScheme

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}
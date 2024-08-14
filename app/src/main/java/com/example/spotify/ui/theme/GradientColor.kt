package com.example.spotify.ui.theme

import androidx.compose.runtime.Composable
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import java.util.concurrent.Flow

val gradientColors = listOf(
    Color(0xFF313131),
    Color(0xFF2b2b2b),
    Color(0xFF212121),
    Color(0xFF1a1a1a),
    Color(0xFF121212),
    Color(0xFF0d0d0d),
)


@Composable
fun gradientBackground(isVertical:Boolean,color: List<Color>):Brush {
    val endOffset = if(isVertical){
        Offset(0f,Float.POSITIVE_INFINITY)
    } else {
        Offset(Float.POSITIVE_INFINITY,0f)
    }

    return Brush.linearGradient(
        colors = color,
        start = Offset.Zero,
        end = endOffset
    )
}
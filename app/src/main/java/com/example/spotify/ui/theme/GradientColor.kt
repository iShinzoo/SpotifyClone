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

val imageGrad1 = listOf(
    Color(0xff87a889),
    Color(0xffa3c8a7)
)

val green = listOf(
    Color(0xff14b36c),
    Color(0xff15c878)
)

val brown = listOf(
    Color(0xff9a6241),
    Color(0xffaa6947)
)

val pink = listOf(
    Color(0xffc25b7e),
    Color(0xffd6658d)
)

val blue = listOf(
    Color(0xff3e78bf),
    Color(0xff4484d1)
)

val red = listOf(
    Color(0xffba1628),
    Color(0xffca1a2a)
)

val gold = listOf(
    Color(0xffd1a451),
    Color(0xffdfae56),
    Color(0xffdead56)
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
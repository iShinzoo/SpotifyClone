package com.example.spotify.diffscreensize

import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

data class Dimens(
    val extraSmall: Dp = 0.dp,
    val small1: Dp = 0.dp,
    val small2: Dp = 0.dp,
    val small3: Dp = 0.dp,
    val medium1: Dp = 0.dp,
    val medium2: Dp = 0.dp,
    val medium3: Dp = 0.dp,
    val large: Dp = 0.dp,
    val buttonHeight: Dp = 50.dp,
    val logoSize: Dp = 42.dp,
    val isCompactMedian : Boolean = false,
    val isExpanded: Boolean = false,
    val isMedium: Boolean = false
)

val CompactSmallDimens = Dimens(
    small1 = 6.dp,
    small2 = 5.dp,
    small3 = 8.dp,
    medium1 = 15.dp,
    medium2 = 26.dp,
    medium3 = 30.dp,
    large = 45.dp,
    buttonHeight = 30.dp,
    logoSize = 36.dp,
    isCompactMedian = true,
    isExpanded = false,
    isMedium = false
)

val CompactMediumDimens = Dimens(
    small1 = 8.dp,
    small2 = 13.dp,
    small3 = 17.dp,
    medium1 = 22.dp,
    medium2 = 30.dp,
    medium3 = 35.dp,
    large = 65.dp,
    buttonHeight = 48.dp,
    logoSize = 48.dp,
    isCompactMedian = true,
    isExpanded = false,
    isMedium = false
)

val CompactDimens = Dimens(
    small1 = 10.dp,
    small2 = 15.dp,
    small3 = 20.dp,
    medium1 = 30.dp,
    medium2 = 36.dp,
    medium3 = 40.dp,
    large = 80.dp,
    buttonHeight = 50.dp,
    logoSize = 55.dp,
    isCompactMedian = false,
    isExpanded = false,
    isMedium = false
)

val MediumDimens = Dimens(
    small1 = 10.dp,
    small2 = 15.dp,
    small3 = 20.dp,
    medium1 = 30.dp,
    medium2 = 36.dp,
    medium3 = 40.dp,
    large = 110.dp,
    logoSize = 55.dp,
    buttonHeight = 50.dp,
    isCompactMedian = false,
    isExpanded = false,
    isMedium = true
)

val ExpandedDimens = Dimens(
    small1 = 15.dp,
    small2 = 20.dp,
    small3 = 25.dp,
    medium1 = 35.dp,
    medium2 = 30.dp,
    medium3 = 45.dp,
    large = 130.dp,
    logoSize = 72.dp,
    buttonHeight = 60.dp,
    isCompactMedian = false,
    isExpanded = true,
)
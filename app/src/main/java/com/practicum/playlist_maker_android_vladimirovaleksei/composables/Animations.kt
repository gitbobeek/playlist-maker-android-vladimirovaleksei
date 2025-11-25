package com.practicum.playlist_maker_android_vladimirovaleksei.composables

import androidx.compose.animation.core.tween
import androidx.compose.animation.slideIn
import androidx.compose.animation.slideOut
import androidx.compose.ui.unit.IntOffset

const val TRANSITION_TIME = 400

fun horizontalSlideEnterTransition() = slideIn(
    animationSpec = tween(TRANSITION_TIME),
    initialOffset = { fullSize -> IntOffset(fullSize.width, 0) }
)

fun horizontalSlideExitTransition() = slideOut(
    animationSpec = tween(TRANSITION_TIME),
    targetOffset = { fullSize -> IntOffset(-fullSize.width, 0) }
)

fun horizontalPopEnterTransition() = slideIn(
    animationSpec = tween(TRANSITION_TIME),
    initialOffset = { fullSize -> IntOffset(-fullSize.width, 0) }
)

fun horizontalPopExitTransition() = slideOut(
    animationSpec = tween(TRANSITION_TIME),
    targetOffset = { fullSize -> IntOffset(fullSize.width, 0) }
)

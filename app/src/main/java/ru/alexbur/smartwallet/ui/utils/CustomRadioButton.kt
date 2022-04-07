package ru.alexbur.smartwallet.ui.utils

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.selection.selectable
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.unit.dp
import ru.alexbur.smartwallet.ui.utils.theme.*

@Composable
fun CustomRadioButton(
    modifier: Modifier,
    isSelect: Boolean,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    onClick: () -> Unit
) {

    val dotRadius = animateDpAsState(
        targetValue = RadioButtonDotSize / 2,
        animationSpec = tween(durationMillis = RadioAnimationDuration)
    )
    val selectableModifier =
        Modifier.selectable(
            selected = isSelect,
            onClick = onClick,
            role = Role.RadioButton,
            interactionSource = interactionSource,
            indication = rememberRipple(
                bounded = false,
                radius = RadioButtonRippleRadius
            )
        )

    val firstGradientList = if (isSelect) listOf(
        SelectedRadioButtonThirdColor,
        CheckedTrackColor,
        SelectedRadioButtonColor
    ) else {
        listOf(
            UnSelectedRadioButtonThirdColor,
            UnSelectedRadioButtonSecondColor,
            UnSelectedRadioButtonColor,
        )
    }
    Canvas(
        modifier
            .then(selectableModifier)
            .wrapContentSize(Alignment.Center)
            .padding(RadioButtonPadding)
            .requiredSize(RadioButtonSize)
    ) {
        // Draw the radio button
        val strokeWidth = RadioStrokeWidth.toPx()
        drawCircle(

            brush = Brush.linearGradient(firstGradientList),
            radius = RadioRadius.toPx() - strokeWidth / 2,
            style = Stroke(strokeWidth)
        )
        drawCircle(
            brush = Brush.linearGradient(firstGradientList),
            dotRadius.value.toPx() - strokeWidth / 2,
            style = Fill
        )
    }
}

private const val RadioAnimationDuration = 100

private val RadioButtonRippleRadius = 24.dp
private val RadioButtonPadding = 2.dp
private val RadioButtonSize = 20.dp
private val RadioRadius = RadioButtonSize / 2
private val RadioButtonDotSize = 12.dp
private val RadioStrokeWidth = 2.dp
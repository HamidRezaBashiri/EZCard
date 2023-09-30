package com.hamidrezabashiri.ezcard.ui.common


import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import com.hamidrezabashiri.ezcard.R
import com.hamidrezabashiri.ezcard.ui.theme.Grey200

@Composable
fun EzCardThemeSwitch(
    checked: Boolean, onThemeChange: (Boolean) -> Unit
) {
    // Animate the thumb position based on the checked state
    val thumbPosition by animateFloatAsState(
        targetValue = if (checked) 1f else 0f, animationSpec = spring()
    )

    val interactionSource = remember { MutableInteractionSource() }
    val isPressed by interactionSource.collectIsPressedAsState()

    Row(
        modifier = Modifier
            .clickable(interactionSource = interactionSource,
                indication = null,
                onClick = {
                    onThemeChange.invoke(!checked)

                })
            .height(42.dp) // Set the switch height
            .width(72.dp) // Set the switch width
            .background(
                color = MaterialTheme.colorScheme.surface,
                shape = RoundedCornerShape(24.dp) // Adjust the overall switch's rounded corners
            ), verticalAlignment = Alignment.CenterVertically
    ) {
        val thumbColor = if (checked) MaterialTheme.colorScheme.primary else Grey200

        Box(contentAlignment = Alignment.CenterEnd,
            modifier = Modifier
                .width(72.dp)
                .height(42.dp) // Set the thumb size
                .background(
                    color = thumbColor,
                    shape = RoundedCornerShape(20.dp) // Adjust the thumb's rounded corners
                )
                .graphicsLayer {
                    translationX = thumbPosition * 32.dp.toPx()
                }) {
            Icon(
                imageVector = ImageVector.vectorResource(if (checked) R.drawable.icon_moon else R.drawable.icon_sun),
                contentDescription = "Switch",
                tint = Color.Unspecified,
                modifier = Modifier
                    .size(38.dp) // Increase the icon size
                    .padding(4.dp) // Increase the padding around the icon to make it larger
            )
        }
    }
}

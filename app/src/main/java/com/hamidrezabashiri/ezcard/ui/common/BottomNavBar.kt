package com.hamidrezabashiri.ezcard.ui.common

import android.annotation.SuppressLint
import androidx.annotation.FloatRange
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.AnimationSpec
import androidx.compose.animation.core.SpringSpec
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.TransformOrigin
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.layout.MeasureResult
import androidx.compose.ui.layout.MeasureScope
import androidx.compose.ui.layout.Placeable
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.google.android.material.animation.AnimationUtils.lerp
import com.hamidrezabashiri.ezcard.R
import com.hamidrezabashiri.ezcard.ui.theme.DarkBlue150
import com.hamidrezabashiri.ezcard.ui.theme.DarkBlue250


@Composable
fun BottomNavBar(
    navItems: List<NavigationItem> = listOf(
        NavigationItem.Home,
        NavigationItem.Wallet,
        NavigationItem.Setting
    ),
    currentRoute: String?,
    onNavItemClicked: (String) -> Unit,
) {


    val currentIndex = navItems.indexOfFirst { it.route == currentRoute }


    Surface(
        color = Color.Transparent, shape = RoundedCornerShape(16.dp), contentColor = Color.White,
        modifier = Modifier
            .clip(RoundedCornerShape(topStart = 32.dp, topEnd = 32.dp))
            .background(
                Brush.linearGradient(
                    colors = listOf(DarkBlue150, DarkBlue250),
                    start = Offset(0f, 0f),
                    end = Offset.Infinite,
                )
            )
    ) {
        val springSpec = SpringSpec<Float>(
            // Determined experimentally
            stiffness = 800f, dampingRatio = 0.8f
        )

        EzCardBottomNavLayout(
            selectedIndex = currentIndex,
            itemCount = navItems.size,
            animSpec = springSpec,
            indicator = { EzCardBottomNavIndicator() }) {

            navItems.forEach {
                val selected = it.route == currentRoute
                val tint by animateColorAsState(
                    if (selected) {
                        Color.White
                    } else {
                        Color.Gray
                    }, label = ""
                )

                EzCardBottomNavigationItem(
                    icon = {
                        Icon(
                            imageVector = ImageVector.vectorResource(it.iconResId),
                            tint = tint,
                            contentDescription = stringResource(id = it.titleResId)
                        )
                    },
                    text = {

                    },
                    selected = selected,
                    onSelected = { onNavItemClicked.invoke(it.route) },
                    animSpec = springSpec,

                )


            }

        }


    }


}

@SuppressLint("RestrictedApi")
@Composable
private fun EzCardBottomNavLayout(
    selectedIndex: Int,
    itemCount: Int,
    animSpec: AnimationSpec<Float>,
    indicator: @Composable BoxScope.() -> Unit,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit,
) {
    // Track how "selected" each item is [0, 1]
    val selectionFractions = remember(itemCount) {
        List(itemCount) { i ->
            androidx.compose.animation.core.Animatable(if (i == selectedIndex) 1f else 0f)
        }
    }
    selectionFractions.forEachIndexed { index, selectionFraction ->
        val target = if (index == selectedIndex) 1f else 0f
        LaunchedEffect(target, animSpec) {
            selectionFraction.animateTo(target, animSpec)
        }
    }

    // Animate the position of the indicator
    val indicatorIndex = remember { androidx.compose.animation.core.Animatable(0f) }
    val targetIndicatorIndex = selectedIndex.toFloat()
    LaunchedEffect(targetIndicatorIndex) {
        indicatorIndex.animateTo(targetIndicatorIndex, animSpec)
    }

    Layout(
        modifier = modifier
            .height(BottomNavHeight),
        content = {
            content()
            Box(Modifier.layoutId("indicator"), content = indicator)
        }) { measurables, constraints ->
        check(itemCount == (measurables.size - 1)) // account for indicator

        val totalWidth = constraints.maxWidth
        val unselectedWidth = totalWidth / (itemCount)
//        val selectedWidth = 2 * unselectedWidth
        // Divide the width into n+1 slots and give the selected item 2 slots
        val indicatorMeasurable = measurables.first { it.layoutId == "indicator" }

        val itemPlaceables =
            measurables.filterNot { it == indicatorMeasurable }.mapIndexed { index, measurable ->
                // Animate item's width based upon the selection amount
                val width = lerp(unselectedWidth, unselectedWidth, selectionFractions[index].value)
                measurable.measure(
                    constraints.copy(
                        minWidth = width, maxWidth = width
                    )
                )
            }
        val indicatorPlaceable = indicatorMeasurable.measure(
            constraints.copy(
                minWidth = unselectedWidth, maxWidth = unselectedWidth
            )
        )

        layout(
            width = constraints.maxWidth,
            height = itemPlaceables.maxByOrNull { it.height }?.height ?: 0
        ) {
            val indicatorLeft = indicatorIndex.value * unselectedWidth
            indicatorPlaceable.placeRelative(x = indicatorLeft.toInt(), y = 0)
            var x = 0
            itemPlaceables.forEach { placeable ->
                placeable.placeRelative(x = x, y = 0)
                x += placeable.width
            }
        }
    }
}

@Composable
fun EzCardBottomNavigationItem(
    icon: @Composable BoxScope.() -> Unit,
    text: @Composable BoxScope.() -> Unit,
    selected: Boolean,
    onSelected: () -> Unit,
    animSpec: AnimationSpec<Float>,
    modifier: Modifier = Modifier,
) {
    // Animate the icon/text positions within the item based on selection
    val animationProgress by animateFloatAsState(if (selected) 1f else 0f, animSpec, label = "")
    AiotelBottomNavItemLayout(
        icon = icon,
        text = text,
        animationProgress = animationProgress,
        modifier = modifier
            .selectable(selected = selected, onClick = onSelected)
            .wrapContentSize()
    )
}

@SuppressLint("RestrictedApi")
@Composable
private fun AiotelBottomNavItemLayout(
    icon: @Composable BoxScope.() -> Unit,
    text: @Composable BoxScope.() -> Unit,
    @FloatRange(from = 0.0, to = 1.0) animationProgress: Float,
    modifier: Modifier = Modifier,
) {
    Layout(modifier = modifier, content = {
        Box(
            modifier = Modifier
                .layoutId("icon")
                .padding(horizontal = TextIconSpacing),
            content = icon
        )
        val scale = lerp(0.6f, 1f, animationProgress)
        Box(
            modifier = Modifier
                .layoutId("text")
                .padding(horizontal = TextIconSpacing)
                .graphicsLayer {
                    alpha = animationProgress
                    scaleX = scale
                    scaleY = scale
                    transformOrigin = BottomNavLabelTransformOrigin
                }, content = text
        )
    }) { measurables, constraints ->
        val iconPlaceable = measurables.first { it.layoutId == "icon" }.measure(constraints)
        val textPlaceable = measurables.first { it.layoutId == "text" }.measure(constraints)

        placeTextAndIcon(
            textPlaceable,
            iconPlaceable,
            constraints.maxWidth,
            constraints.maxHeight,
            animationProgress
        )
    }
}

private fun MeasureScope.placeTextAndIcon(
    textPlaceable: Placeable,
    iconPlaceable: Placeable,
    width: Int,
    height: Int,
    @FloatRange(from = 0.0, to = 1.0) animationProgress: Float,
): MeasureResult {
    val iconY = (height - iconPlaceable.height) / 2
    val textY = (height - textPlaceable.height) / 2

    val textWidth = textPlaceable.width * animationProgress
    val iconX = (width - textWidth - iconPlaceable.width) / 2
    val textX = iconX + iconPlaceable.width

    return layout(width, height) {
        iconPlaceable.placeRelative(iconX.toInt(), iconY)
        if (animationProgress != 0f) {
            textPlaceable.placeRelative(textX.toInt(), textY)
        }
    }
}

@Composable
private fun EzCardBottomNavIndicator(
    strokeWidth: Dp = 2.dp,
    color: Color = Color.White,
    shape: Shape = BottomNavIndicatorShape,
) {
    Box {
        Icon(
            imageVector = ImageVector.vectorResource(R.drawable.selected_bottom_bar_ellipse),
            contentDescription = "",
            modifier = Modifier
                .fillMaxSize()
//            .then(BottomNavigationItemPadding)
//            .border(strokeWidth, color, shape)
        )
        Spacer(
            modifier = Modifier
                .padding(bottom = 2.dp)
                .align(Alignment.BottomCenter)
                .height(6.dp)
                .fillMaxWidth(0.3f)
                .background(color = Color.White, shape = RoundedCornerShape(4.dp))
        )
    }
}

private val TextIconSpacing = 0.dp
private val BottomNavHeight = 60.dp
private val BottomNavLabelTransformOrigin = TransformOrigin(0f, 0.5f)
private val BottomNavIndicatorShape = RoundedCornerShape(percent = 50)
private val BottomNavigationItemPadding = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
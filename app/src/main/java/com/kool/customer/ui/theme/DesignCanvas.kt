package com.kool.customer.ui.theme

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.absoluteOffset
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.BiasAlignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.TransformOrigin
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import kotlin.math.min

/**
 * Renders a Figma frame at its own design size (Figma px == dp) and scales the
 * whole frame uniformly to fit the device, preserving every measurement of the
 * source design instead of re-flowing it.
 *
 * Children are positioned with [at], which is direction-agnostic: the Figma
 * canvas is measured from the physical top-left. Layout direction is RTL inside
 * the canvas so Arabic text shapes and orders correctly.
 */
@Composable
fun DesignCanvas(
    designWidth: Dp,
    designHeight: Dp,
    background: Color,
    modifier: Modifier = Modifier,
    content: @Composable BoxScope.() -> Unit,
) {
    BoxWithConstraints(
        modifier = modifier
            .fillMaxSize()
            .background(background)
            .clipToBounds(),
        contentAlignment = Alignment.Center,
    ) {
        val scale = min(
            maxWidth.value / designWidth.value,
            maxHeight.value / designHeight.value,
        )
        Box(
            modifier = Modifier
                .size(designWidth, designHeight)
                .graphicsLayer {
                    scaleX = scale
                    scaleY = scale
                    transformOrigin = TransformOrigin(0.5f, 0.5f)
                },
            contentAlignment = BiasAlignment(-1f, -1f),
        ) {
            content()
        }
    }
}

/** Places a child at exact design coordinates, measured from the physical top-left. */
fun Modifier.at(x: Float, y: Float, w: Float, h: Float): Modifier =
    this.absoluteOffset(x.dp, y.dp).size(w.dp, h.dp)

/** Static artwork cropped from the Figma file. */
@Composable
fun BoxScope.Art(
    @DrawableRes res: Int,
    x: Float,
    y: Float,
    w: Float,
    h: Float,
    alpha: Float = 1f,
    cornerRadius: Float = 0f,
) {
    var modifier = Modifier.at(x, y, w, h)
    if (cornerRadius > 0f) modifier = modifier.clip(RoundedCornerShape(cornerRadius.dp))
    Image(
        painter = painterResource(res),
        contentDescription = null,
        alpha = alpha,
        contentScale = ContentScale.Fit,
        modifier = modifier,
    )
}

/** A text run placed in its Figma text box. */
@Composable
fun BoxScope.Label(
    text: String,
    x: Float,
    y: Float,
    w: Float,
    h: Float,
    size: Float,
    color: Color,
    weight: FontWeight = FontWeight.Normal,
    align: TextAlign = TextAlign.Center,
    lineHeight: Float = size * 1.30f,
    maxLines: Int = 1,
) {
    Box(Modifier.at(x, y, w, h), contentAlignment = Alignment.Center) {
        Text(
            text = text,
            color = color,
            fontSize = size.sp,
            fontWeight = weight,
            textAlign = align,
            lineHeight = lineHeight.sp,
            maxLines = maxLines,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier.fillMaxWidth(),
        )
    }
}

/** Live photo, sized by the caller (used inside scrolled rows). */
@Composable
fun Photo(
    model: Any?,
    modifier: Modifier,
    radius: Float = 0f,
    placeholder: Color = Color(0xFFEDEAE4),
) {
    Box(
        modifier
            .clip(RoundedCornerShape(radius.dp))
            .background(placeholder),
    ) {
        AsyncImage(
            model = model,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize(),
        )
    }
}

/** Live photo (restaurant / dish) rendered in its Figma slot. */
@Composable
fun BoxScope.RemoteArt(
    model: Any?,
    x: Float,
    y: Float,
    w: Float,
    h: Float,
    radius: Float = 0f,
    placeholder: Color = Color(0xFFEDEAE4),
) {
    Photo(model, Modifier.at(x, y, w, h), radius, placeholder)
}

/** A solid rounded rectangle at design coordinates. */
@Composable
fun BoxScope.Slab(
    x: Float,
    y: Float,
    w: Float,
    h: Float,
    color: Color,
    radius: Float = 0f,
) {
    Box(
        Modifier
            .at(x, y, w, h)
            .clip(RoundedCornerShape(radius.dp))
            .background(color),
    )
}

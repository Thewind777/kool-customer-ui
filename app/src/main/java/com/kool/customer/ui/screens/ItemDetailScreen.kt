package com.kool.customer.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.BiasAlignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kool.customer.R
import com.kool.customer.data.model.MenuItemData
import com.kool.customer.ui.theme.Art
import com.kool.customer.ui.theme.DesignCanvas
import com.kool.customer.ui.theme.KoolNotesBg
import com.kool.customer.ui.theme.KoolPink
import com.kool.customer.ui.theme.KoolSurfaceVariant
import com.kool.customer.ui.theme.KoolTextPrimary
import com.kool.customer.ui.theme.KoolTextSecondary
import com.kool.customer.ui.theme.Label
import com.kool.customer.ui.theme.Photo
import com.kool.customer.ui.theme.Slab
import com.kool.customer.ui.theme.at

// Screen 04 · Item detail — Figma frame 2:11 (291 × 672).
@Composable
fun ItemDetailScreen(
    onBack: () -> Unit = {},
    onAddToCart: () -> Unit = {},
    meal: MenuItemData? = null,
) {
    var quantity by remember { mutableIntStateOf(1) }
    var selectedExtras by remember { mutableStateOf(setOf("bacon")) }
    var notes by remember { mutableStateOf("") }

    val unitPrice = meal?.price?.toDoubleOrNull() ?: 35.0
    val total = unitPrice * quantity + selectedExtras.sumOf { if (it == "cheese") 3.0 else if (it == "jalapeno") 2.0 else 4.0 }
    val totalText = String.format(java.util.Locale.US, "%.0f", total)

    DesignCanvas(designWidth = 291.dp, designHeight = 672.dp, background = Color(0xFFFCFAF7)) {
        // ── Hero (live item photo, design artwork behind it) ──────
        Box(
            Modifier
                .at(0f, 0f, 291f, 272f)
                .background(Brush.verticalGradient(listOf(Color(0xFFF4507F), Color(0xFFE23A6B)))),
        )
        if (meal?.imageUrl != null) {
            Photo(meal.imageUrl, Modifier.at(0f, 0f, 291f, 272f), radius = 0f)
        } else {
            Art(R.drawable.item_hero, 0f, 0f, 291f, 272f)
        }
        Art(R.drawable.item_back, 12f, 39f, 33f, 34f)
        Box(Modifier.at(12f, 39f, 33f, 34f).clip(RoundedCornerShape(17.dp)).clickable { onBack() })
        Art(R.drawable.item_fav, 223f, 39f, 34f, 34f)

        // ── Detail surface ────────────────────────────────────────
        Slab(0f, 260f, 291f, 412f, Color.White, 23f)
        Label(
            text = meal?.name ?: "دبل كازان",
            x = 147f, y = 268f, w = 129f, h = 30f,
            size = 16f, color = KoolTextPrimary, weight = FontWeight.Bold, align = TextAlign.End,
        )
        Art(R.drawable.item_star, 153f, 303f, 17f, 17f)
        Label("4.8", 175f, 302f, 31f, 21f, 13f, KoolTextPrimary, FontWeight.Bold)
        Label("(320) تقييم", 211f, 303f, 65f, 20f, 9f, KoolTextSecondary, align = TextAlign.Start)
        Label(
            text = meal?.description ?: "شريحتان لحم بقري مشوي مع جبنة شيدر وصوص كازان الخاص",
            x = 18f, y = 327f, w = 258f, h = 40f,
            size = 9f, color = KoolTextSecondary, align = TextAlign.End, maxLines = 2,
        )

        // ── Price + stepper ───────────────────────────────────────
        Label(String.format(java.util.Locale.US, "%.0f", total), 52f, 365f, 38f, 30f, 22f, KoolTextPrimary, FontWeight.Bold)
        Label("ل.د", 15f, 369f, 34f, 27f, 12f, KoolTextPrimary, FontWeight.Bold)

        Slab(164f, 373f, 113f, 34f, KoolSurfaceVariant, 17f)
        Art(R.drawable.item_minus, 166f, 376f, 28f, 28f)
        Box(Modifier.at(166f, 376f, 28f, 28f).clip(RoundedCornerShape(14.dp)).clickable {
            if (quantity > 1) quantity -= 1
        })
        Text(
            text = quantity.toString(),
            color = KoolTextPrimary,
            fontSize = 13.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            modifier = Modifier.at(205f, 377f, 35f, 25f),
        )
        Art(R.drawable.item_plus, 244f, 374f, 32f, 32f)
        Box(Modifier.at(244f, 374f, 32f, 32f).clip(RoundedCornerShape(16.dp)).clickable { quantity += 1 })

        // ── Extras ────────────────────────────────────────────────
        Label("إضافات الطلب", 135f, 418f, 141f, 24f, 14f, KoolTextPrimary, FontWeight.Bold, TextAlign.End)

        ExtraCard(
            key = "cheese", photo = com.kool.customer.R.drawable.item_cheese, photoW = 44f, photoH = 37f,
            label = "جبنة إضافية", price = "+ 3 ل.د", x = 13f,
            selected = selectedExtras.contains("cheese"),
            onToggle = { selectedExtras = selectedExtras.toggle("cheese") },
        )
        ExtraCard(
            key = "jalapeno", photo = com.kool.customer.R.drawable.item_jalapeno, photoW = 48f, photoH = 39f,
            label = "هالبينو", price = "+ 2 ل.د", x = 105f,
            selected = selectedExtras.contains("jalapeno"),
            onToggle = { selectedExtras = selectedExtras.toggle("jalapeno") },
        )
        ExtraCard(
            key = "bacon", photo = com.kool.customer.R.drawable.item_bacon, photoW = 53f, photoH = 39f,
            label = "بيكون", price = "+ 4 ل.د", x = 197f,
            selected = selectedExtras.contains("bacon"),
            onToggle = { selectedExtras = selectedExtras.toggle("bacon") },
        )

        // ── Notes ─────────────────────────────────────────────────
        Slab(13f, 550f, 266f, 36f, KoolNotesBg, 10f)
        if (notes.isEmpty()) {
            Label("ملاحظات خاصة (اختياري)", 38f, 557f, 222f, 20f, 9f, KoolTextSecondary, align = TextAlign.End)
        } else {
            Label(notes, 38f, 557f, 210f, 20f, 9f, KoolTextPrimary, align = TextAlign.End)
        }
        Art(R.drawable.item_pencil, 261f, 561f, 13f, 13f)

        // ── Add to basket ─────────────────────────────────────────
        Box(
            Modifier
                .at(13f, 596f, 266f, 50f)
                .clip(RoundedCornerShape(25.dp))
                .background(Color(0xFFFC5A82))
                .clickable { onAddToCart() },
        )
        Label("$totalText ل.د", 24f, 608f, 58f, 27f, 14f, Color.White, FontWeight.Bold)
        Label("أضف إلى السلة", 104f, 607f, 124f, 27f, 15f, Color.White, FontWeight.Bold)
        Art(R.drawable.item_bag, 238f, 610f, 22f, 25f)
    }
}

private fun Set<String>.toggle(key: String): Set<String> =
    if (contains(key)) this - key else this + key

@Composable
private fun androidx.compose.foundation.layout.BoxScope.ExtraCard(
    key: String,
    photo: Int,
    photoW: Float,
    photoH: Float,
    label: String,
    price: String,
    x: Float,
    selected: Boolean,
    onToggle: () -> Unit,
) {
    Box(
        Modifier
            .at(x, 445f, 85f, 94f)
            .clip(RoundedCornerShape(10.dp))
            .background(Color.White)
            .border(1.dp, Color(0xFFF0EEEA), RoundedCornerShape(10.dp))
            .clickable { onToggle() },
        contentAlignment = BiasAlignment(-1f, -1f),
    ) {
        Art(photo, 17f, 11f, photoW, photoH)
        Art(if (selected) R.drawable.item_check_checked else R.drawable.item_checkbox, 64f, 9f, 12f, 12f)
        Label(label, 3f, 51f, 79f, 18f, 9f, KoolTextPrimary, FontWeight.Bold)
        Label(price, 3f, 70f, 79f, 18f, 9f, KoolTextPrimary)
    }
}

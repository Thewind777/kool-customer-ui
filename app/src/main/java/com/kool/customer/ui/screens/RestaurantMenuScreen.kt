package com.kool.customer.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.BiasAlignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.kool.customer.R
import com.kool.customer.data.model.MenuItemData
import com.kool.customer.ui.theme.Art
import com.kool.customer.ui.theme.DesignCanvas
import com.kool.customer.ui.theme.KoolBackground
import com.kool.customer.ui.theme.KoolInfoChipBg
import com.kool.customer.ui.theme.KoolPink
import com.kool.customer.ui.theme.KoolSurfaceVariant
import com.kool.customer.ui.theme.KoolTextPrimary
import com.kool.customer.ui.theme.KoolTextSecondary
import com.kool.customer.ui.theme.Label
import com.kool.customer.ui.theme.Photo
import com.kool.customer.ui.theme.Slab
import com.kool.customer.ui.theme.at
import com.kool.customer.ui.viewmodel.RestaurantViewModel

// Screen 03 · Restaurant menu — Figma frame 2:8 (294 × 672).
// Hero, logo, names and prices are live; the rest is the design verbatim.
@Composable
fun RestaurantMenuScreen(
    restaurantId: Int,
    onBack: () -> Unit = {},
    onMealClick: (MenuItemData) -> Unit = {},
    vm: RestaurantViewModel = viewModel(factory = RestaurantViewModel.Factory(restaurantId)),
) {
    val details by vm.details.collectAsStateWithLifecycle()
    val info = details?.companyInfo
    val meals = details?.menuItems ?: emptyList()

    DesignCanvas(designWidth = 294.dp, designHeight = 672.dp, background = KoolBackground) {
        // ── Hero ──────────────────────────────────────────────────
        Slab(0f, 0f, 294f, 202f, Color(0xFF2B2B2B))
        Photo(info?.imageUrl, Modifier.at(0f, 0f, 294f, 202f))
        Slab(9f, 39f, 32f, 32f, Color.Black.copy(alpha = 0.18f), 16f)
        Slab(214f, 39f, 32f, 32f, Color.Black.copy(alpha = 0.18f), 16f)
        Slab(250f, 39f, 32f, 32f, Color.Black.copy(alpha = 0.18f), 16f)
        Art(R.drawable.menu_ic_back, 13f, 43f, 24f, 27f)
        Box(Modifier.at(6f, 36f, 38f, 41f).clickable { onBack() })
        Art(R.drawable.menu_ic_fav, 218f, 43f, 25f, 25f)
        Art(R.drawable.menu_ic_share, 254f, 43f, 25f, 25f)

        // ── Info surface ──────────────────────────────────────────
        Slab(0f, 186f, 294f, 486f, Color.White, 23f)
        Photo(info?.logoUrl, Modifier.at(12f, 199f, 78f, 73f), radius = 15f)
        Label(
            text = info?.name ?: "Kazan",
            x = 100f, y = 208f, w = 165f, h = 28f,
            size = 16f, color = KoolTextPrimary, weight = FontWeight.Bold, align = TextAlign.End,
        )
        Label("برجر · ساندويشات · دجاج · سمك", 108f, 232f, 172f, 19f, 9f, KoolTextSecondary, align = TextAlign.End)
        Art(R.drawable.menu_star, 106f, 255f, 15f, 15f)
        Label(info?.rating ?: "4.8", 126f, 253f, 25f, 18f, 13f, KoolTextPrimary, FontWeight.Bold)
        Label("(1.2K) تقييم", 153f, 254f, 103f, 18f, 10f, KoolTextSecondary, align = TextAlign.Start)

        // ── Delivery chips ────────────────────────────────────────
        Slab(11f, 286f, 93f, 39f, KoolInfoChipBg, 10f)
        Art(R.drawable.menu_clock, 16f, 292f, 17f, 17f)
        Label("35 - 20 دقيقة", 36f, 290f, 66f, 17f, 9f, KoolTextPrimary, FontWeight.Bold)

        Slab(111f, 286f, 70f, 39f, KoolInfoChipBg, 10f)
        Art(R.drawable.menu_pin, 116f, 292f, 17f, 17f)
        Label("2.5 كم", 136f, 290f, 43f, 17f, 9f, KoolTextPrimary, FontWeight.Bold)

        Slab(191f, 286f, 91f, 39f, KoolInfoChipBg, 10f)
        Art(R.drawable.menu_scooter, 196f, 292f, 19f, 17f)
        Label("مجانا", 216f, 290f, 64f, 17f, 9f, KoolTextPrimary, FontWeight.Bold)
        Label("رسوم التوصيل", 206f, 305f, 73f, 14f, 8f, KoolTextSecondary)

        // ── Tabs ──────────────────────────────────────────────────
        Slab(10f, 330f, 274f, 29f, KoolSurfaceVariant, 14f)
        Label("القائمة", 15f, 332f, 78f, 23f, 10f, KoolTextPrimary, FontWeight.Bold)
        Label("التقييمات", 106f, 332f, 78f, 23f, 10f, KoolTextSecondary)
        Label("معلومات", 197f, 332f, 78f, 23f, 10f, KoolTextSecondary)
        Slab(20f, 358f, 67f, 2f, KoolPink, 1f)

        // ── Filter chips (last one is the selected state in the design) ──
        FilterChip("مشروبات", 10f, 50f)
        FilterChip("وجبات", 68f, 43f)
        FilterChip("سندويشات", 119f, 65f)
        FilterChip("برجر", 192f, 43f)
        FilterChip("الكل", 242f, 41f, active = true)

        // ── Section heading ───────────────────────────────────────
        Label("برجر", 220f, 402f, 62f, 23f, 14f, KoolTextPrimary, FontWeight.ExtraBold, TextAlign.End)

        // ── Meals (live) ──────────────────────────────────────────
        LazyColumn(
            modifier = Modifier.at(10f, 415f, 274f, 250f),
            verticalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            items(meals) { meal ->
                MealCard(meal) { onMealClick(meal) }
            }
        }
    }
}

@Composable
private fun BoxScope.FilterChip(
    label: String,
    x: Float,
    w: Float,
    active: Boolean = false,
) {
    Slab(x, 370f, w, 27f, if (active) KoolPink else KoolSurfaceVariant, 11f)
    Label(label, x, 373f, w, 20f, 9f, if (active) Color.White else KoolTextSecondary)
}

@Composable
private fun MealCard(meal: MenuItemData, onAdd: () -> Unit) {
    Box(
        Modifier
            .width(274.dp)
            .height(86.dp)
            .clip(RoundedCornerShape(12.dp))
            .background(Color.White),
        contentAlignment = BiasAlignment(-1f, -1f),
    ) {
        Photo(meal.imageUrl, Modifier.at(0f, 0f, 81f, 84f), radius = 10f)

        if (meal.mostOrdered == "1") {
            Slab(81f, 6f, 60f, 17f, Color(0xFFF84C8A), 7f)
            Label("الأكثر طلباً", 81f, 6f, 60f, 17f, 8f, Color.White)
        }

        Label(meal.name ?: "", 88f, 25f, 144f, 20f, 11f, KoolTextPrimary, FontWeight.Bold, align = TextAlign.End)
        Label(
            text = meal.description ?: "",
            x = 89f, y = 44f, w = 143f, h = 29f,
            size = 8f, color = KoolTextSecondary, align = TextAlign.End, maxLines = 2,
        )
        Label(meal.price ?: "--", 100f, 68f, 46f, 18f, 12f, KoolTextPrimary, FontWeight.Bold, align = TextAlign.End)
        Label("ل.د", 95f, 70f, 20f, 15f, 9f, KoolTextSecondary)
        Art(R.drawable.menu_plus, 244f, 58f, 25f, 25f)
        Box(Modifier.at(244f, 58f, 25f, 25f).clip(RoundedCornerShape(13.dp)).clickable { onAdd() })
    }
}

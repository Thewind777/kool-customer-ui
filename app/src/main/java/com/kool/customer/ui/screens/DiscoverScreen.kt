package com.kool.customer.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
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
import com.kool.customer.data.model.RestaurantData
import com.kool.customer.ui.theme.Art
import com.kool.customer.ui.theme.DesignCanvas
import com.kool.customer.ui.theme.KoolBackground
import com.kool.customer.ui.theme.KoolInfoChipBg
import com.kool.customer.ui.theme.KoolPink
import com.kool.customer.ui.theme.KoolSearchBg
import com.kool.customer.ui.theme.KoolTextPrimary
import com.kool.customer.ui.theme.KoolTextSecondary
import com.kool.customer.ui.theme.Label
import com.kool.customer.ui.theme.Photo
import com.kool.customer.ui.theme.Slab
import com.kool.customer.ui.theme.at
import com.kool.customer.ui.viewmodel.DiscoverViewModel

// Screen 02 · Discover — Figma frame 2:7 (295 × 672).
// Restaurant photos are live; every other pixel follows the design.
@Composable
fun DiscoverScreen(
    onRestaurantClick: (String) -> Unit = {},
    vm: DiscoverViewModel = viewModel(),
) {
    val restaurants by vm.restaurants.collectAsStateWithLifecycle()

    DesignCanvas(designWidth = 295.dp, designHeight = 672.dp, background = KoolBackground) {
        // ── Header ────────────────────────────────────────────────
        Art(R.drawable.discover_logo, 104f, 40f, 77f, 35f)
        Art(R.drawable.discover_pin, 11f, 44f, 16f, 22f)
        Label("طرابلس", 29f, 43f, 41f, 20f, 10f, KoolTextPrimary, FontWeight.Bold)
        Art(R.drawable.discover_chevron, 72f, 49f, 11f, 10f)
        Art(R.drawable.discover_bell, 257f, 42f, 23f, 25f)

        // ── Search field ──────────────────────────────────────────
        Slab(10f, 82f, 274f, 35f, KoolSearchBg, 12f)
        Art(R.drawable.discover_search, 24f, 90f, 18f, 18f)
        Label("ابحث عن مطعم أو أطباق ...", 66f, 89f, 178f, 21f, 9f, KoolTextSecondary)
        Art(R.drawable.discover_filter, 252f, 90f, 19f, 18f)

        // ── Category tiles ────────────────────────────────────────
        Art(R.drawable.discover_tile_burger, 10f, 128f, 48f, 47f, cornerRadius = 12f)
        Art(R.drawable.discover_tile_pizza, 66f, 128f, 48f, 47f, cornerRadius = 12f)
        Art(R.drawable.discover_tile_chicken, 122f, 128f, 48f, 47f, cornerRadius = 12f)
        Art(R.drawable.discover_tile_seafood, 178f, 128f, 48f, 47f, cornerRadius = 12f)
        Art(R.drawable.discover_tile_more, 234f, 128f, 48f, 47f, cornerRadius = 12f)
        Label("برجر", 7f, 176f, 54f, 17f, 9f, KoolTextPrimary)
        Label("بيتزا", 63f, 176f, 54f, 17f, 9f, KoolTextPrimary)
        Label("دجاج", 119f, 176f, 54f, 17f, 9f, KoolTextPrimary)
        Label("مأكولات بحرية", 175f, 176f, 54f, 17f, 9f, KoolTextPrimary)
        Label("المزيد", 231f, 176f, 54f, 17f, 9f, KoolTextPrimary)

        // ── Promo banner (artwork + copy are part of the design asset) ──
        Art(R.drawable.discover_banner, 10f, 205f, 272f, 135f, cornerRadius = 16f)
        repeat(5) { i ->
            Slab(
                128f + i * 9f,
                344f,
                5f,
                5f,
                if (i == 0) KoolPink else Color(0xFFD8D4CE),
                2.5f,
            )
        }

        // ── Nearby restaurants ────────────────────────────────────
        Label("أهم المطاعم بالقرب منك", 94f, 355f, 188f, 25f, 14f, KoolTextPrimary, FontWeight.ExtraBold, TextAlign.End)
        Label("عرض الكل", 9f, 357f, 58f, 21f, 10f, KoolPink)
        LazyRow(
            modifier = Modifier.at(10f, 387f, 276f, 133f),
            horizontalArrangement = Arrangement.spacedBy(7.dp),
        ) {
            items(restaurants) { restaurant ->
                RestaurantCard(restaurant) { onRestaurantClick(restaurant.id ?: "") }
            }
        }

        // ── Top rated ─────────────────────────────────────────────
        Label("الأكثر تقييماً", 164f, 527f, 118f, 26f, 14f, KoolTextPrimary, FontWeight.ExtraBold, TextAlign.End)
        Label("عرض الكل", 9f, 532f, 58f, 20f, 10f, KoolPink)
        LazyRow(
            modifier = Modifier.at(10f, 557f, 276f, 71f),
            horizontalArrangement = Arrangement.spacedBy(7.dp),
        ) {
            items(restaurants.take(12)) { restaurant ->
                Photo(
                    model = restaurant.imageUrl,
                    modifier = Modifier.width(86.dp).height(71.dp),
                    radius = 9f,
                )
            }
        }

        // ── Bottom navigation ─────────────────────────────────────
        Slab(0f, 608f, 295f, 64f, Color.White)
        Slab(0f, 608f, 295f, 0.5f, Color(0xFFEFECE6))
        Art(R.drawable.discover_nav_home, 19f, 615f, 27f, 21f)
        Art(R.drawable.discover_nav_discover, 77f, 615f, 24f, 21f)
        Art(R.drawable.discover_nav_orders, 135f, 615f, 21f, 21f)
        Art(R.drawable.discover_nav_fav, 193f, 615f, 22f, 21f)
        Art(R.drawable.discover_nav_account, 251f, 615f, 20f, 21f)
        Label("الرئيسية", 9f, 637f, 53f, 17f, 8f, KoolPink)
        Label("اكتشف", 67f, 637f, 53f, 17f, 8f, KoolTextSecondary)
        Label("الطلبات", 125f, 637f, 53f, 17f, 8f, KoolTextSecondary)
        Label("المفضلة", 183f, 637f, 53f, 17f, 8f, KoolTextSecondary)
        Label("حسابي", 241f, 637f, 53f, 17f, 8f, KoolTextSecondary)
    }
}

@Composable
private fun RestaurantCard(restaurant: RestaurantData, onClick: () -> Unit) {
    Box(
        Modifier
            .width(86.dp)
            .height(133.dp)
            .clip(RoundedCornerShape(8.dp))
            .background(Color.White)
            .clickable { onClick() },
        contentAlignment = BiasAlignment(-1f, -1f),
    ) {
        Photo(restaurant.imageUrl, Modifier.at(0f, 0f, 86f, 58f), radius = 8f)
        Label(restaurant.name ?: "", 4f, 62f, 82f, 14f, 8f, KoolTextPrimary, FontWeight.Bold)
        Art(R.drawable.discover_star, 5f, 81f, 10f, 10f)
        Label(restaurant.rating ?: "--", 17f, 79f, 66f, 14f, 8f, KoolTextSecondary)
        restaurant.deliveryTime?.let {
            Label(it, 3f, 96f, 80f, 14f, 8f, KoolTextSecondary)
        }
        restaurant.deliveryFee?.let {
            Label(it, 4f, 112f, 78f, 15f, 9f, KoolTextPrimary, FontWeight.Bold)
        }
    }
}

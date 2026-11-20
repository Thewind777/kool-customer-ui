package com.kool.customer.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.kool.customer.data.model.RestaurantData
import com.kool.customer.ui.theme.*
import com.kool.customer.ui.viewmodel.DiscoverViewModel

data class CategoryItem(val label: String, val isActive: Boolean = false)

// Screen 02 · Discover
@Composable
fun DiscoverScreen(
    onRestaurantClick: (String) -> Unit = {},
    vm: DiscoverViewModel = viewModel(),
) {
    val apiRestaurants by vm.restaurants.collectAsStateWithLifecycle()

    CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Rtl) {
        val categories = listOf(
            CategoryItem("برجر", isActive = true),
            CategoryItem("بيتزا"),
            CategoryItem("دجاج"),
            CategoryItem("مأكولات بحرية"),
            CategoryItem("المزيد"),
        )

        var selectedTab by remember { mutableIntStateOf(0) }
        val navItems = listOf("الرئيسية", "اكتشف", "الطلبات", "المفضلة", "حسابي")
        val navIcons = listOf(
            Icons.Default.Home,
            Icons.Default.Search,
            Icons.Default.ShoppingBag,
            Icons.Default.FavoriteBorder,
            Icons.Default.Person,
        )

        Scaffold(
            containerColor = KoolBackground,
            bottomBar = {
                NavigationBar(
                    containerColor = KoolBottomNavBg,
                    modifier = Modifier.height(64.dp),
                ) {
                    navItems.forEachIndexed { i, label ->
                        NavigationBarItem(
                            selected = selectedTab == i,
                            onClick = { selectedTab = i },
                            icon = {
                                Icon(
                                    imageVector = navIcons[i],
                                    contentDescription = label,
                                    modifier = Modifier.size(21.dp),
                                )
                            },
                            label = {
                                Text(label, fontSize = 8.sp)
                            },
                            colors = NavigationBarItemDefaults.colors(
                                selectedIconColor = KoolBottomNavActive,
                                selectedTextColor = KoolBottomNavActive,
                                unselectedIconColor = KoolBottomNavInactive,
                                unselectedTextColor = KoolBottomNavInactive,
                                indicatorColor = Color.Transparent,
                            ),
                        )
                    }
                }
            },
        ) { innerPadding ->
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding),
                contentPadding = PaddingValues(bottom = 16.dp),
            ) {
                // Top bar
                item {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp, vertical = 12.dp),
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        Icon(Icons.Default.LocationOn, null, tint = KoolPink, modifier = Modifier.size(16.dp))
                        Spacer(Modifier.width(2.dp))
                        Text("طرابلس", fontSize = 11.sp, fontWeight = FontWeight.Bold, color = KoolTextPrimary)
                        Icon(Icons.Default.KeyboardArrowDown, null, tint = KoolTextPrimary, modifier = Modifier.size(11.dp))
                        Spacer(Modifier.weight(1f))
                        Text("KOOL", fontSize = 18.sp, fontWeight = FontWeight.ExtraBold, color = KoolPink)
                        Spacer(Modifier.weight(1f))
                        Icon(Icons.Default.Notifications, "إشعارات", tint = KoolTextPrimary, modifier = Modifier.size(23.dp))
                    }
                }

                // Search bar
                item {
                    Row(
                        modifier = Modifier
                            .padding(horizontal = 16.dp)
                            .fillMaxWidth()
                            .height(35.dp)
                            .clip(RoundedCornerShape(12.dp))
                            .background(KoolSearchBg),
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        Spacer(Modifier.width(10.dp))
                        Icon(Icons.Default.Search, null, tint = KoolTextSecondary, modifier = Modifier.size(18.dp))
                        Spacer(Modifier.width(8.dp))
                        Text("ابحث عن مطعم أو أطباق ...", color = KoolTextSecondary, fontSize = 10.sp, modifier = Modifier.weight(1f))
                        Icon(Icons.Default.Tune, "فلتر", tint = KoolTextSecondary, modifier = Modifier.size(18.dp))
                        Spacer(Modifier.width(10.dp))
                    }
                    Spacer(Modifier.height(16.dp))
                }

                // Category chips
                item {
                    LazyRow(
                        contentPadding = PaddingValues(horizontal = 16.dp),
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                    ) {
                        itemsIndexed(categories) { _, cat ->
                            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                Box(
                                    modifier = Modifier
                                        .size(48.dp, 47.dp)
                                        .clip(RoundedCornerShape(13.dp))
                                        .background(
                                            if (cat.isActive)
                                                Brush.horizontalGradient(listOf(KoolCategoryGradientStart, KoolCategoryGradientEnd))
                                            else Brush.linearGradient(listOf(Color.White, Color.White))
                                        ),
                                    contentAlignment = Alignment.Center,
                                ) {
                                    Box(
                                        modifier = Modifier
                                            .size(29.dp)
                                            .clip(CircleShape)
                                            .background(
                                                if (cat.isActive) Color.White.copy(alpha = 0.3f)
                                                else KoolPink.copy(alpha = 0.1f)
                                            )
                                    )
                                }
                                Spacer(Modifier.height(4.dp))
                                Text(cat.label, fontSize = 8.sp, fontWeight = FontWeight.Bold, color = KoolTextPrimary, textAlign = TextAlign.Center)
                            }
                        }
                    }
                    Spacer(Modifier.height(16.dp))
                }

                // Promo banner
                item {
                    Box(
                        modifier = Modifier
                            .padding(horizontal = 16.dp)
                            .fillMaxWidth()
                            .height(135.dp)
                            .clip(RoundedCornerShape(16.dp))
                            .background(Brush.horizontalGradient(listOf(Color(0xFF2D1B6B), Color(0xFF6B2D6B)))),
                        contentAlignment = Alignment.CenterEnd,
                    ) {
                        Column(modifier = Modifier.padding(end = 16.dp), horizontalAlignment = Alignment.End) {
                            Text("بيتزا", fontSize = 22.sp, fontWeight = FontWeight.ExtraBold, color = Color.White)
                            Text("كما تحبها", fontSize = 14.sp, color = Color.White)
                            Spacer(Modifier.height(8.dp))
                            Box(
                                modifier = Modifier.clip(RoundedCornerShape(12.dp)).background(KoolPink).padding(horizontal = 12.dp, vertical = 4.dp),
                            ) {
                                Text("اطلب الآن ←", fontSize = 10.sp, color = Color.White)
                            }
                        }
                    }
                    Spacer(Modifier.height(16.dp))
                }

                // Nearby restaurants section header
                item {
                    Row(
                        modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        Text("عرض الكل ←", fontSize = 10.sp, color = KoolPinkAccent)
                        Text("أهم المطاعم بالقرب منك", fontSize = 14.sp, fontWeight = FontWeight.ExtraBold, color = KoolTextPrimary)
                    }
                    Spacer(Modifier.height(10.dp))
                }

                item {
                    LazyRow(
                        contentPadding = PaddingValues(horizontal = 16.dp),
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                    ) {
                        items(apiRestaurants) { restaurant ->
                            RestaurantCard(
                                restaurant = restaurant,
                                onClick = { onRestaurantClick(restaurant.id ?: "") },
                            )
                        }
                        // Show placeholder cards while loading
                        if (apiRestaurants.isEmpty()) {
                            items(3) {
                                RestaurantCard(restaurant = null, onClick = {})
                            }
                        }
                    }
                    Spacer(Modifier.height(16.dp))
                }

                // Top rated section header
                item {
                    Row(
                        modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        Text("عرض الكل", fontSize = 10.sp, color = KoolPinkAccent)
                        Text("الأكثر تقييماً", fontSize = 14.sp, fontWeight = FontWeight.ExtraBold, color = KoolTextPrimary)
                    }
                    Spacer(Modifier.height(10.dp))
                }

                // Top rated meal images — using real restaurant photos as preview
                item {
                    LazyRow(
                        contentPadding = PaddingValues(horizontal = 16.dp),
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                    ) {
                        val topRated = apiRestaurants.take(3)
                        items(topRated) { r ->
                            AsyncImage(
                                model = r.imageUrl,
                                contentDescription = r.name,
                                contentScale = ContentScale.Crop,
                                modifier = Modifier
                                    .size(86.dp, 71.dp)
                                    .clip(RoundedCornerShape(9.dp))
                                    .background(KoolSurfaceVariant),
                            )
                        }
                        if (topRated.isEmpty()) {
                            val fallbackColors = listOf(
                                listOf(Color(0xFFB05E3A), Color(0xFF7A3A1A)),
                                listOf(Color(0xFF3A6BB0), Color(0xFF1A4A7A)),
                                listOf(Color(0xFF6BB03A), Color(0xFF3A7A1A)),
                            )
                            items(3) { i ->
                                Box(
                                    modifier = Modifier
                                        .size(86.dp, 71.dp)
                                        .clip(RoundedCornerShape(9.dp))
                                        .background(Brush.verticalGradient(fallbackColors[i])),
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun RestaurantCard(
    restaurant: RestaurantData?,
    onClick: () -> Unit,
) {
    Card(
        modifier = Modifier.width(86.dp).height(133.dp).clickable { onClick() },
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
    ) {
        Column {
            if (restaurant?.imageUrl != null) {
                AsyncImage(
                    model = restaurant.imageUrl,
                    contentDescription = restaurant.name,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(58.dp)
                        .clip(RoundedCornerShape(topStart = 8.dp, topEnd = 8.dp)),
                )
            } else {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(58.dp)
                        .clip(RoundedCornerShape(topStart = 8.dp, topEnd = 8.dp))
                        .background(Color(0xFFE0D8D0)),
                    contentAlignment = Alignment.Center,
                ) {
                    Text(
                        restaurant?.name?.take(1) ?: "·",
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        color = KoolTextPrimary,
                    )
                }
            }
            Column(modifier = Modifier.padding(horizontal = 4.dp, vertical = 2.dp)) {
                Text(restaurant?.name ?: "...", fontSize = 8.sp, fontWeight = FontWeight.Bold, color = KoolTextPrimary, maxLines = 1)
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(Icons.Default.Star, null, tint = KoolRatingStarColor, modifier = Modifier.size(10.dp))
                    Spacer(Modifier.width(2.dp))
                    Text("${restaurant?.rating ?: "--"} (${restaurant?.reviewCount ?: "--"})", fontSize = 8.sp, color = KoolTextSecondary)
                }
                Text(restaurant?.deliveryTime ?: "...", fontSize = 8.sp, color = KoolTextSecondary, textAlign = TextAlign.Center, modifier = Modifier.fillMaxWidth())
                Text(
                    restaurant?.deliveryFee ?: "--",
                    fontSize = 10.sp,
                    fontWeight = FontWeight.Bold,
                    color = if (restaurant?.deliveryFee == "مجانا") KoolDeliveryFreeColor else KoolTextPrimary,
                )
            }
        }
    }
}

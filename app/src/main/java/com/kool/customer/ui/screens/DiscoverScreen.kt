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
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kool.customer.ui.theme.*

data class RestaurantItem(
    val id: String,
    val name: String,
    val rating: String,
    val reviews: String,
    val deliveryTime: String,
    val deliveryFee: String,
    // TODO: replace imageUrl with real restaurant photo from API
    val imageUrl: String = "",
)

data class CategoryItem(val label: String, val isActive: Boolean = false)

// Screen 02 · Discover
// Figma: light background, search, category chips, promo banner, restaurant + meal rows, bottom nav
@Composable
fun DiscoverScreen(
    onRestaurantClick: (String) -> Unit = {},
) {
    CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Rtl) {
        val categories = listOf(
            CategoryItem("برجر", isActive = true),
            CategoryItem("بيتزا"),
            CategoryItem("دجاج"),
            CategoryItem("مأكولات بحرية"),
            CategoryItem("المزيد"),
        )

        val nearbyRestaurants = listOf(
            RestaurantItem("kazan", "Kazan", "4.8", "1.2K", "20 - 35 دقيقة", "مجانا"),
            RestaurantItem("marios", "Mario's", "4.6", "842", "25 - 40 دقيقة", "5 ل.د"),
            RestaurantItem("shawarma", "Shawarma House", "4.5", "678", "20 - 30 دقيقة", "مجانا"),
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
                        // Location
                        Icon(
                            Icons.Default.LocationOn,
                            contentDescription = null,
                            tint = KoolPink,
                            modifier = Modifier.size(16.dp),
                        )
                        Spacer(Modifier.width(2.dp))
                        Text(
                            "طرابلس",
                            fontSize = 11.sp,
                            fontWeight = FontWeight.Bold,
                            color = KoolTextPrimary,
                        )
                        Icon(
                            Icons.Default.KeyboardArrowDown,
                            contentDescription = null,
                            tint = KoolTextPrimary,
                            modifier = Modifier.size(11.dp),
                        )
                        Spacer(Modifier.weight(1f))
                        // Kool logo text placeholder
                        Text(
                            "KOOL",
                            fontSize = 18.sp,
                            fontWeight = FontWeight.ExtraBold,
                            color = KoolPink,
                        )
                        Spacer(Modifier.weight(1f))
                        Icon(
                            Icons.Default.Notifications,
                            contentDescription = "إشعارات",
                            tint = KoolTextPrimary,
                            modifier = Modifier.size(23.dp),
                        )
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
                        Icon(
                            Icons.Default.Search,
                            contentDescription = null,
                            tint = KoolTextSecondary,
                            modifier = Modifier.size(18.dp),
                        )
                        Spacer(Modifier.width(8.dp))
                        Text(
                            "ابحث عن مطعم أو أطباق ...",
                            color = KoolTextSecondary,
                            fontSize = 10.sp,
                            modifier = Modifier.weight(1f),
                        )
                        Icon(
                            Icons.Default.Tune,
                            contentDescription = "فلتر",
                            tint = KoolTextSecondary,
                            modifier = Modifier.size(18.dp),
                        )
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
                                                Brush.horizontalGradient(
                                                    listOf(KoolCategoryGradientStart, KoolCategoryGradientEnd)
                                                )
                                            else Brush.linearGradient(listOf(Color.White, Color.White))
                                        ),
                                    contentAlignment = Alignment.Center,
                                ) {
                                    // TODO: replace with real category icon asset
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
                                Text(
                                    cat.label,
                                    fontSize = 8.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = KoolTextPrimary,
                                    textAlign = TextAlign.Center,
                                )
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
                            // TODO: replace with real promo image from API
                            .background(
                                Brush.horizontalGradient(listOf(Color(0xFF2D1B6B), Color(0xFF6B2D6B)))
                            ),
                        contentAlignment = Alignment.CenterEnd,
                    ) {
                        Column(
                            modifier = Modifier.padding(end = 16.dp),
                            horizontalAlignment = Alignment.End,
                        ) {
                            Text("بيتزا", fontSize = 22.sp, fontWeight = FontWeight.ExtraBold, color = Color.White)
                            Text("كما تحبها", fontSize = 14.sp, color = Color.White)
                            Spacer(Modifier.height(8.dp))
                            Box(
                                modifier = Modifier
                                    .clip(RoundedCornerShape(12.dp))
                                    .background(KoolPink)
                                    .padding(horizontal = 12.dp, vertical = 4.dp),
                            ) {
                                Text("اطلب الآن ←", fontSize = 10.sp, color = Color.White)
                            }
                        }
                    }
                    Spacer(Modifier.height(16.dp))
                }

                // Nearby restaurants section
                item {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        Text(
                            "عرض الكل ←",
                            fontSize = 10.sp,
                            color = KoolPinkAccent,
                        )
                        Text(
                            "أهم المطاعم بالقرب منك",
                            fontSize = 14.sp,
                            fontWeight = FontWeight.ExtraBold,
                            color = KoolTextPrimary,
                        )
                    }
                    Spacer(Modifier.height(10.dp))
                }

                item {
                    LazyRow(
                        contentPadding = PaddingValues(horizontal = 16.dp),
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                    ) {
                        items(nearbyRestaurants) { restaurant ->
                            RestaurantCard(
                                restaurant = restaurant,
                                onClick = { onRestaurantClick(restaurant.id) },
                            )
                        }
                    }
                    Spacer(Modifier.height(16.dp))
                }

                // Top rated section header
                item {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        Text("عرض الكل", fontSize = 10.sp, color = KoolPinkAccent)
                        Text(
                            "الأكثر تقييماً",
                            fontSize = 14.sp,
                            fontWeight = FontWeight.ExtraBold,
                            color = KoolTextPrimary,
                        )
                    }
                    Spacer(Modifier.height(10.dp))
                }

                // Top rated meal images
                item {
                    LazyRow(
                        contentPadding = PaddingValues(horizontal = 16.dp),
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                    ) {
                        items(3) { i ->
                            val colors = listOf(
                                listOf(Color(0xFFB05E3A), Color(0xFF7A3A1A)),
                                listOf(Color(0xFF3A6BB0), Color(0xFF1A4A7A)),
                                listOf(Color(0xFF6BB03A), Color(0xFF3A7A1A)),
                            )
                            // TODO: replace with real top-rated meal photo from API
                            Box(
                                modifier = Modifier
                                    .size(86.dp, 71.dp)
                                    .clip(RoundedCornerShape(9.dp))
                                    .background(Brush.verticalGradient(colors[i])),
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun RestaurantCard(
    restaurant: RestaurantItem,
    onClick: () -> Unit,
) {
    Card(
        modifier = Modifier
            .width(86.dp)
            .height(133.dp)
            .clickable { onClick() },
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
    ) {
        Column {
            // TODO: replace with AsyncImage loading restaurant logo from API
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(58.dp)
                    .clip(RoundedCornerShape(topStart = 8.dp, topEnd = 8.dp))
                    .background(Color(0xFFE0D8D0)),
                contentAlignment = Alignment.Center,
            ) {
                Text(
                    restaurant.name.take(1),
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = KoolTextPrimary,
                )
            }
            Column(modifier = Modifier.padding(horizontal = 4.dp, vertical = 2.dp)) {
                Text(
                    restaurant.name,
                    fontSize = 8.sp,
                    fontWeight = FontWeight.Bold,
                    color = KoolTextPrimary,
                    maxLines = 1,
                )
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        Icons.Default.Star,
                        contentDescription = null,
                        tint = KoolRatingStarColor,
                        modifier = Modifier.size(10.dp),
                    )
                    Spacer(Modifier.width(2.dp))
                    Text(
                        "${restaurant.rating} (${restaurant.reviews})",
                        fontSize = 8.sp,
                        color = KoolTextSecondary,
                    )
                }
                Text(restaurant.deliveryTime, fontSize = 8.sp, color = KoolTextSecondary, textAlign = TextAlign.Center, modifier = Modifier.fillMaxWidth())
                Text(
                    restaurant.deliveryFee,
                    fontSize = 10.sp,
                    fontWeight = FontWeight.Bold,
                    color = if (restaurant.deliveryFee == "مجانا") KoolDeliveryFreeColor else KoolTextPrimary,
                )
            }
        }
    }
}

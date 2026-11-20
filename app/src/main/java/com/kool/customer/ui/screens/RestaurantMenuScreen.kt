package com.kool.customer.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
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
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.kool.customer.data.model.MenuItemData
import com.kool.customer.ui.theme.*
import com.kool.customer.ui.viewmodel.RestaurantViewModel

// Screen 03 · Restaurant Menu
@Composable
fun RestaurantMenuScreen(
    restaurantId: Int = 1,
    onBack: () -> Unit = {},
    onMealClick: (String) -> Unit = {},
    vm: RestaurantViewModel = viewModel(factory = RestaurantViewModel.Factory(restaurantId)),
) {
    val details by vm.details.collectAsStateWithLifecycle()
    val info = details?.companyInfo
    val menuItems = details?.menuItems ?: emptyList()

    val filterChips = listOf("مشروبات", "وجبات", "سندويشات", "برجر", "الكل")
    var selectedFilter by remember { mutableIntStateOf(4) }
    var selectedTab by remember { mutableIntStateOf(0) }
    val tabs = listOf("القائمة", "التقييمات", "معلومات")

    CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Rtl) {
        Box(modifier = Modifier.fillMaxSize().background(KoolBackground)) {
            LazyColumn(modifier = Modifier.fillMaxSize()) {
                // Hero photo
                item {
                    Box(modifier = Modifier.fillMaxWidth().height(202.dp)) {
                        if (info?.imageUrl != null) {
                            AsyncImage(
                                model = info.imageUrl,
                                contentDescription = info.name,
                                contentScale = ContentScale.Crop,
                                modifier = Modifier.fillMaxSize(),
                            )
                        } else {
                            Box(
                                modifier = Modifier.fillMaxSize().background(
                                    Brush.verticalGradient(listOf(Color(0xFF8B4513), Color(0xFF5C2D0A)))
                                ),
                            )
                        }
                        // Controls overlay
                        Row(
                            modifier = Modifier.fillMaxWidth().statusBarsPadding().padding(horizontal = 16.dp, vertical = 8.dp),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically,
                        ) {
                            Box(
                                modifier = Modifier.size(33.dp).clip(CircleShape).background(Color.White.copy(alpha = 0.9f)).clickable { onBack() },
                                contentAlignment = Alignment.Center,
                            ) {
                                Icon(Icons.Default.ArrowBack, "رجوع", tint = KoolTextPrimary, modifier = Modifier.size(18.dp))
                            }
                            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                                Box(modifier = Modifier.size(25.dp).clip(CircleShape).background(Color.White.copy(alpha = 0.9f)), contentAlignment = Alignment.Center) {
                                    Icon(Icons.Default.FavoriteBorder, null, tint = KoolTextPrimary, modifier = Modifier.size(16.dp))
                                }
                                Box(modifier = Modifier.size(25.dp).clip(CircleShape).background(Color.White.copy(alpha = 0.9f)), contentAlignment = Alignment.Center) {
                                    Icon(Icons.Default.Share, null, tint = KoolTextPrimary, modifier = Modifier.size(16.dp))
                                }
                            }
                        }
                    }
                }

                // Restaurant info card
                item {
                    Surface(
                        modifier = Modifier.fillMaxWidth().offset(y = (-16).dp),
                        shape = RoundedCornerShape(topStart = 23.dp, topEnd = 23.dp),
                        color = KoolSurface,
                    ) {
                        Column(modifier = Modifier.padding(horizontal = 12.dp)) {
                            Spacer(Modifier.height(12.dp))
                            Row(verticalAlignment = Alignment.Top) {
                                // Restaurant logo
                                if (info?.logoUrl != null) {
                                    AsyncImage(
                                        model = info.logoUrl,
                                        contentDescription = info.name,
                                        contentScale = ContentScale.Crop,
                                        modifier = Modifier.size(78.dp, 73.dp).clip(RoundedCornerShape(15.dp)),
                                    )
                                } else {
                                    Box(
                                        modifier = Modifier.size(78.dp, 73.dp).clip(RoundedCornerShape(15.dp)).background(Color(0xFF1A1A1A)),
                                        contentAlignment = Alignment.Center,
                                    ) {
                                        Text("K", fontSize = 32.sp, fontWeight = FontWeight.ExtraBold, color = KoolPink)
                                    }
                                }
                                Spacer(Modifier.width(12.dp))
                                Column {
                                    Text(info?.name ?: "Kazan", fontSize = 21.sp, fontWeight = FontWeight.Bold, color = KoolTextPrimary)
                                    Text("برجر · سندويشات · وجبات سريعة", fontSize = 9.sp, color = KoolTextSecondary)
                                    Spacer(Modifier.height(4.dp))
                                    Row(verticalAlignment = Alignment.CenterVertically) {
                                        Icon(Icons.Default.Star, null, tint = KoolRatingStarColor, modifier = Modifier.size(15.dp))
                                        Spacer(Modifier.width(4.dp))
                                        Text("4.8", fontSize = 13.sp, fontWeight = FontWeight.Bold, color = KoolTextPrimary)
                                        Spacer(Modifier.width(4.dp))
                                        Text("(1.2K) تقييم", fontSize = 10.sp, color = KoolTextSecondary)
                                    }
                                }
                            }
                            Spacer(Modifier.height(10.dp))

                            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                                DeliveryInfoChip(icon = { Icon(Icons.Default.AccessTime, null, Modifier.size(17.dp)) }, label = "20 - 35 دقيقة")
                                DeliveryInfoChip(icon = { Icon(Icons.Default.LocationOn, null, Modifier.size(17.dp)) }, label = "2.5 كم")
                                DeliveryInfoChip(icon = { Icon(Icons.Default.DeliveryDining, null, Modifier.size(19.dp)) }, label = "مجانا", caption = "رسوم التوصيل")
                            }
                            Spacer(Modifier.height(12.dp))

                            // Tab row
                            Box(modifier = Modifier.fillMaxWidth().height(29.dp).clip(RoundedCornerShape(14.dp)).background(KoolSurfaceVariant)) {
                                Row(modifier = Modifier.fillMaxSize()) {
                                    tabs.forEachIndexed { i, tab ->
                                        Box(
                                            modifier = Modifier.weight(1f).fillMaxHeight().clickable { selectedTab = i },
                                            contentAlignment = Alignment.Center,
                                        ) {
                                            Text(tab, fontSize = 10.sp, fontWeight = if (selectedTab == i) FontWeight.Bold else FontWeight.Normal, color = if (selectedTab == i) KoolTextPrimary else KoolTextSecondary)
                                        }
                                    }
                                }
                            }
                            Spacer(Modifier.height(4.dp))
                            Box(modifier = Modifier.fillMaxWidth(1f / 3f).height(2.dp).padding(horizontal = 8.dp).clip(RoundedCornerShape(1.dp)).background(KoolTabIndicator))
                            Spacer(Modifier.height(10.dp))

                            // Filter chips
                            LazyRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                                items(filterChips.size) { i ->
                                    Box(
                                        modifier = Modifier.height(27.dp).clip(RoundedCornerShape(11.dp)).background(if (selectedFilter == i) KoolAddButton else KoolChipBg).clickable { selectedFilter = i }.padding(horizontal = 10.dp),
                                        contentAlignment = Alignment.Center,
                                    ) {
                                        Text(filterChips[i], fontSize = 9.sp, color = if (selectedFilter == i) Color.White else KoolTextSecondary)
                                    }
                                }
                            }
                            Spacer(Modifier.height(10.dp))

                            Text("برجر", fontSize = 14.sp, fontWeight = FontWeight.ExtraBold, color = KoolTextPrimary, modifier = Modifier.fillMaxWidth(), textAlign = TextAlign.End)
                            Spacer(Modifier.height(8.dp))
                        }
                    }
                }

                // Menu items from API (or static fallback)
                if (menuItems.isNotEmpty()) {
                    items(menuItems) { item ->
                        MealCard(
                            item = item,
                            onAdd = { onMealClick(item.id ?: "") },
                            modifier = Modifier.padding(horizontal = 12.dp).padding(bottom = 8.dp),
                        )
                    }
                } else {
                    // Static fallback while loading
                    items(3) { i ->
                        val names = listOf("كلاسيك برجر", "دبل كازان", "سبايسي كازان")
                        val descs = listOf("لحم بقري 20%، جبنة شيدر، خس طماطم، صوص خاص", "شريحتان لحم، جبنة مضاعفة وصوص كازان الخاص", "لحم بقري حار، هالبينو، خس طماطم، صوص حار خاص")
                        val prices = listOf("28", "35", "32")
                        MealCard(
                            item = MenuItemData(id = "$i", name = names[i], description = descs[i], price = prices[i], mostOrdered = if (i == 0) "1" else "0"),
                            onAdd = { onMealClick("$i") },
                            modifier = Modifier.padding(horizontal = 12.dp).padding(bottom = 8.dp),
                        )
                    }
                }

                item { Spacer(Modifier.height(24.dp)) }
            }
        }
    }
}

@Composable
private fun DeliveryInfoChip(icon: @Composable () -> Unit, label: String, caption: String? = null) {
    Box(
        modifier = Modifier.height(39.dp).clip(RoundedCornerShape(10.dp)).background(KoolInfoChipBg).padding(horizontal = 6.dp),
        contentAlignment = Alignment.Center,
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Ltr) { icon() }
            Spacer(Modifier.width(4.dp))
            Column {
                Text(label, fontSize = 9.sp, fontWeight = FontWeight.Bold, color = Color(0xFF344751))
                if (caption != null) Text(caption, fontSize = 8.sp, color = KoolTextSecondary)
            }
        }
    }
}

@Composable
private fun MealCard(item: MenuItemData, onAdd: () -> Unit, modifier: Modifier = Modifier) {
    Box(
        modifier = modifier.fillMaxWidth().heightIn(min = 77.dp).clip(RoundedCornerShape(12.dp)).background(Color.White),
    ) {
        Row(modifier = Modifier.padding(0.dp)) {
            if (item.imageUrl != null) {
                AsyncImage(
                    model = item.imageUrl,
                    contentDescription = item.name,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(81.dp, if (item.mostOrdered == "1") 84.dp else 77.dp)
                        .clip(RoundedCornerShape(10.dp)),
                )
            } else {
                Box(
                    modifier = Modifier
                        .size(81.dp, if (item.mostOrdered == "1") 84.dp else 77.dp)
                        .clip(RoundedCornerShape(10.dp))
                        .background(Brush.verticalGradient(listOf(Color(0xFFB05E3A), Color(0xFF7A3A1A)))),
                )
            }
            Column(modifier = Modifier.weight(1f).padding(start = 8.dp, top = 6.dp, end = 8.dp, bottom = 6.dp)) {
                if (item.mostOrdered == "1") {
                    Box(
                        modifier = Modifier.height(17.dp).clip(RoundedCornerShape(7.dp)).background(KoolMostOrderedBadge).padding(horizontal = 6.dp),
                        contentAlignment = Alignment.Center,
                    ) {
                        Text("الأكثر طلباً", fontSize = 8.sp, color = Color.White)
                    }
                    Spacer(Modifier.height(2.dp))
                }
                Text(item.name ?: "", fontSize = 11.sp, fontWeight = FontWeight.Bold, color = Color(0xFF192A30))
                Text(item.description ?: "", fontSize = 8.sp, color = KoolTextTertiary, maxLines = 2, overflow = TextOverflow.Ellipsis)
                Spacer(Modifier.height(4.dp))
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text("ل.د", fontSize = 10.sp, color = KoolTextPrimary)
                        Spacer(Modifier.width(2.dp))
                        Text(item.price ?: "--", fontSize = 12.sp, fontWeight = FontWeight.Bold, color = KoolTextPrimary)
                    }
                    Box(
                        modifier = Modifier.size(25.dp).clip(RoundedCornerShape(13.dp)).background(KoolAddButton).clickable { onAdd() },
                        contentAlignment = Alignment.Center,
                    ) {
                        Text("+", fontSize = 18.sp, color = Color.White, fontWeight = FontWeight.Normal, lineHeight = 20.sp)
                    }
                }
            }
        }
    }
}

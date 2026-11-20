package com.kool.customer.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
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

data class ExtraItem(
    val id: String,
    val name: String,
    val price: String,
    // TODO: replace imageUrl with real extra item photo from API
    val imageUrl: String = "",
)

// Screen 04 · Item Detail (Burger details)
// Figma: hero photo, meal info, quantity stepper, extras grid, notes field, add-to-basket CTA
@Composable
fun ItemDetailScreen(
    onBack: () -> Unit = {},
    onAddToCart: () -> Unit = {},
) {
    CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Rtl) {
        var quantity by remember { mutableIntStateOf(1) }
        var selectedExtras by remember { mutableStateOf(setOf("bacon")) }
        var notes by remember { mutableStateOf("") }

        val extras = listOf(
            ExtraItem("cheese", "جبنة إضافية", "3 ل.د +"),
            ExtraItem("jalapeño", "هالبينو", "2 ل.د +"),
            ExtraItem("bacon", "بيكون", "4 ل.د +"),
        )

        val basePrice = 35

        Box(modifier = Modifier.fillMaxSize().background(KoolBackground)) {
            Column(modifier = Modifier.fillMaxSize().verticalScroll(rememberScrollState())) {
                // Hero photo
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(272.dp),
                ) {
                    // TODO: replace with real meal hero photo from restaurant API
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(
                                Brush.verticalGradient(
                                    listOf(Color(0xFFE8A87C), Color(0xFFD4583A), Color(0xFF8B2500))
                                )
                            ),
                    )
                    // Controls
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .statusBarsPadding()
                            .padding(horizontal = 12.dp, vertical = 8.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        Box(
                            modifier = Modifier
                                .size(33.dp, 34.dp)
                                .clip(RoundedCornerShape(10.dp))
                                .background(Color.White.copy(alpha = 0.9f))
                                .clickable { onBack() },
                            contentAlignment = Alignment.Center,
                        ) {
                            Icon(Icons.Default.ArrowBack, null, tint = KoolTextPrimary, modifier = Modifier.size(18.dp))
                        }
                        Box(
                            modifier = Modifier
                                .size(34.dp)
                                .clip(RoundedCornerShape(10.dp))
                                .background(Color.White.copy(alpha = 0.9f)),
                            contentAlignment = Alignment.Center,
                        ) {
                            Icon(Icons.Default.FavoriteBorder, null, tint = KoolTextPrimary, modifier = Modifier.size(18.dp))
                        }
                    }
                }

                // Detail surface
                Surface(
                    modifier = Modifier
                        .fillMaxWidth()
                        .offset(y = (-16).dp),
                    shape = RoundedCornerShape(topStart = 23.dp, topEnd = 23.dp),
                    color = KoolSurface,
                ) {
                    Column(modifier = Modifier.padding(horizontal = 16.dp)) {
                        Spacer(Modifier.height(16.dp))

                        // Meal name
                        Text(
                            "دبل كازان",
                            fontSize = 21.sp,
                            fontWeight = FontWeight.ExtraBold,
                            color = KoolTextPrimary,
                            modifier = Modifier.fillMaxWidth(),
                            textAlign = TextAlign.End,
                        )
                        Spacer(Modifier.height(4.dp))

                        // Rating
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.End,
                            verticalAlignment = Alignment.CenterVertically,
                        ) {
                            Text("(320) تقييم", fontSize = 12.sp, color = Color(0xFF829099))
                            Spacer(Modifier.width(4.dp))
                            Text("4.8", fontSize = 14.sp, fontWeight = FontWeight.Bold, color = KoolTextPrimary)
                            Spacer(Modifier.width(4.dp))
                            Icon(Icons.Default.Star, null, tint = KoolRatingStarColor, modifier = Modifier.size(17.dp))
                        }
                        Spacer(Modifier.height(8.dp))

                        // Description
                        Text(
                            "شريحتان لحم بقري 20%، جبنة شيدر، خس، طماطم، مخلل، وصوص كازان الخاص.",
                            fontSize = 11.sp,
                            color = Color(0xFF7D8992),
                            modifier = Modifier.fillMaxWidth(),
                            textAlign = TextAlign.End,
                        )
                        Spacer(Modifier.height(12.dp))

                        // Price + quantity stepper
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically,
                        ) {
                            // Quantity stepper
                            Box(
                                modifier = Modifier
                                    .height(34.dp)
                                    .width(113.dp)
                                    .clip(RoundedCornerShape(17.dp))
                                    .background(Color(0xFFF5F3F0)),
                                contentAlignment = Alignment.Center,
                            ) {
                                Row(
                                    modifier = Modifier.fillMaxWidth().padding(horizontal = 2.dp),
                                    horizontalArrangement = Arrangement.SpaceBetween,
                                    verticalAlignment = Alignment.CenterVertically,
                                ) {
                                    // Increment (+)
                                    Box(
                                        modifier = Modifier
                                            .size(32.dp)
                                            .clip(RoundedCornerShape(16.dp))
                                            .background(KoolIncrementBg)
                                            .clickable { quantity++ },
                                        contentAlignment = Alignment.Center,
                                    ) {
                                        Text("+", fontSize = 20.sp, color = Color.White, fontWeight = FontWeight.Normal, textAlign = TextAlign.Center)
                                    }
                                    Text(
                                        "$quantity",
                                        fontSize = 12.sp,
                                        fontWeight = FontWeight.Bold,
                                        color = KoolTextPrimary,
                                        textAlign = TextAlign.Center,
                                        modifier = Modifier.width(35.dp),
                                    )
                                    // Decrement (-)
                                    Box(
                                        modifier = Modifier
                                            .size(28.dp)
                                            .clip(RoundedCornerShape(14.dp))
                                            .background(KoolDecrementBg)
                                            .clickable { if (quantity > 1) quantity-- },
                                        contentAlignment = Alignment.Center,
                                    ) {
                                        Text("−", fontSize = 17.sp, color = Color(0xFF3D4D55), textAlign = TextAlign.Center)
                                    }
                                }
                            }

                            // Price
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Text("$basePrice", fontSize = 24.sp, fontWeight = FontWeight.Bold, color = KoolTextPrimary)
                                Spacer(Modifier.width(4.dp))
                                Text("ل.د", fontSize = 18.sp, color = Color(0xFF829099))
                            }
                        }
                        Spacer(Modifier.height(16.dp))

                        // Extras heading
                        Text(
                            "اختر الإضافات",
                            fontSize = 13.sp,
                            fontWeight = FontWeight.ExtraBold,
                            color = KoolTextPrimary,
                            modifier = Modifier.fillMaxWidth(),
                            textAlign = TextAlign.End,
                        )
                        Spacer(Modifier.height(10.dp))

                        // Extras grid
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(8.dp),
                        ) {
                            extras.forEach { extra ->
                                val isSelected = extra.id in selectedExtras
                                ExtraCard(
                                    extra = extra,
                                    isSelected = isSelected,
                                    modifier = Modifier.weight(1f),
                                    onToggle = {
                                        selectedExtras = if (isSelected) selectedExtras - extra.id
                                        else selectedExtras + extra.id
                                    },
                                )
                            }
                        }
                        Spacer(Modifier.height(12.dp))

                        // Notes field
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(36.dp)
                                .clip(RoundedCornerShape(9.dp))
                                .background(KoolNotesBg),
                            contentAlignment = Alignment.CenterEnd,
                        ) {
                            Row(
                                modifier = Modifier.fillMaxWidth().padding(horizontal = 12.dp),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically,
                            ) {
                                Icon(Icons.Default.Edit, null, tint = KoolTextMuted, modifier = Modifier.size(13.dp))
                                Text("ملاحظات خاصة (اختياري)", fontSize = 10.sp, color = KoolTextMuted)
                            }
                        }
                        Spacer(Modifier.height(16.dp))

                        // Add to basket CTA
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(50.dp)
                                .clip(RoundedCornerShape(15.dp))
                                .background(
                                    Brush.horizontalGradient(listOf(KoolGradientStart, KoolGradientEnd))
                                )
                                .clickable { onAddToCart() },
                            contentAlignment = Alignment.Center,
                        ) {
                            Row(
                                modifier = Modifier.fillMaxWidth().padding(horizontal = 12.dp),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically,
                            ) {
                                Icon(Icons.Default.ShoppingBag, null, tint = Color.White, modifier = Modifier.size(22.dp))
                                Text("أضف إلى السلة", fontSize = 15.sp, color = Color.White)
                                Text("$basePrice ل.د", fontSize = 17.sp, color = Color.White)
                            }
                        }
                        Spacer(Modifier.height(32.dp))
                    }
                }
            }
        }
    }
}

@Composable
private fun ExtraCard(
    extra: ExtraItem,
    isSelected: Boolean,
    modifier: Modifier = Modifier,
    onToggle: () -> Unit,
) {
    Box(
        modifier = modifier
            .height(94.dp)
            .clip(RoundedCornerShape(13.dp))
            .border(0.7.dp, KoolCardBorder, RoundedCornerShape(13.dp))
            .background(Color(0xFFFDFCFA))
            .clickable { onToggle() },
    ) {
        Column(
            modifier = Modifier.fillMaxSize().padding(4.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ) {
            // Extra photo placeholder
            // TODO: replace with real extra item photo from API
            Box(
                modifier = Modifier
                    .size(44.dp, 37.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .background(Color(0xFFE8D8C0)),
            )
            Spacer(Modifier.height(4.dp))
            Text(extra.name, fontSize = 10.sp, fontWeight = FontWeight.Bold, color = Color(0xFF25383E), textAlign = TextAlign.Center)
            Text(extra.price, fontSize = 10.sp, fontWeight = FontWeight.Bold, color = Color(0xFF25383E), textAlign = TextAlign.Center)
        }
        // Checkbox
        Box(
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(4.dp)
                .size(12.dp)
                .clip(RoundedCornerShape(3.dp))
                .background(if (isSelected) KoolCheckboxActive else Color.White)
                .border(0.7.dp, if (isSelected) KoolCheckboxActive else KoolCheckboxBorder, RoundedCornerShape(3.dp)),
            contentAlignment = Alignment.Center,
        ) {
            if (isSelected) {
                Text("✓", fontSize = 8.sp, color = Color.White, textAlign = TextAlign.Center)
            }
        }
    }
}

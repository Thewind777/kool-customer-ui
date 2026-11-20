package com.kool.customer.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
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

// Screen 01 · Welcome
// Figma: dark gradient bg, Kool branding, Arabic headline, page dots, CTA + login link
@Composable
fun WelcomeScreen(
    onStart: () -> Unit,
    onLogin: () -> Unit,
) {
    CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Rtl) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Brush.verticalGradient(listOf(KoolDarkBg, KoolDarkSurface))
                )
        ) {
            // Skip button top-right
            Box(
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .padding(top = 48.dp, end = 16.dp)
                    .border(0.6.dp, Color(0xFF697277), RoundedCornerShape(15.dp))
                    .clip(RoundedCornerShape(15.dp))
                    .clickable { onStart() }
                    .padding(horizontal = 12.dp, vertical = 6.dp),
                contentAlignment = Alignment.Center,
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = "تخطي",
                        color = Color.White,
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Normal,
                    )
                    Spacer(Modifier.width(4.dp))
                    Icon(
                        imageVector = Icons.Default.ArrowForward,
                        contentDescription = null,
                        tint = Color.White,
                        modifier = Modifier.size(14.dp),
                    )
                }
            }

            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Spacer(Modifier.weight(1f))

                // Brand logo placeholder
                // TODO: replace with real Kool logo asset
                Box(
                    modifier = Modifier
                        .size(117.dp, 190.dp)
                        .clip(RoundedCornerShape(16.dp))
                        .background(KoolPink.copy(alpha = 0.3f)),
                    contentAlignment = Alignment.Center,
                ) {
                    Text("K", color = KoolPink, fontSize = 64.sp, fontWeight = FontWeight.ExtraBold)
                }

                Spacer(Modifier.height(24.dp))

                // Headline
                Text(
                    text = "أكل أحلى",
                    color = Color.White,
                    fontSize = 33.sp,
                    fontWeight = FontWeight.ExtraBold,
                    textAlign = TextAlign.Center,
                )
                Text(
                    text = "تجربة أجمل",
                    color = KoolPinkAccent,
                    fontSize = 32.sp,
                    fontWeight = FontWeight.ExtraBold,
                    textAlign = TextAlign.Center,
                )

                Spacer(Modifier.height(12.dp))

                Text(
                    text = "اطلب من أفضل المطاعم",
                    color = Color.White.copy(alpha = 0.8f),
                    fontSize = 12.sp,
                    textAlign = TextAlign.Center,
                )
                Text(
                    text = "وقيم تجربتك على كول",
                    color = Color.White.copy(alpha = 0.8f),
                    fontSize = 12.sp,
                    textAlign = TextAlign.Center,
                )

                Spacer(Modifier.weight(1f))

                // Page indicator dots
                Row(
                    horizontalArrangement = Arrangement.spacedBy(4.dp),
                    modifier = Modifier.padding(bottom = 24.dp),
                ) {
                    Box(
                        modifier = Modifier
                            .size(8.dp)
                            .clip(RoundedCornerShape(4.dp))
                            .background(KoolPageDotActive),
                    )
                    repeat(3) {
                        Box(
                            modifier = Modifier
                                .size(8.dp)
                                .clip(RoundedCornerShape(4.dp))
                                .background(KoolPageDotInactive),
                        )
                    }
                }

                // Start CTA button
                Box(
                    modifier = Modifier
                        .padding(horizontal = 22.dp)
                        .fillMaxWidth()
                        .height(45.dp)
                        .clip(RoundedCornerShape(24.dp))
                        .background(
                            Brush.horizontalGradient(
                                listOf(KoolOrangeGradientStart, KoolGradientStart, KoolOrangeGradientEnd)
                            )
                        )
                        .clickable { onStart() },
                    contentAlignment = Alignment.Center,
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Text(
                            text = "ابدأ الآن",
                            color = Color.White,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold,
                        )
                        Spacer(Modifier.width(8.dp))
                        Icon(
                            imageVector = Icons.Default.ArrowForward,
                            contentDescription = null,
                            tint = Color.White,
                            modifier = Modifier.size(19.dp),
                        )
                    }
                }

                Spacer(Modifier.height(16.dp))

                // Login link
                Text(
                    text = "تسجيل الدخول",
                    color = Color.White.copy(alpha = 0.8f),
                    fontSize = 11.sp,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.clickable { onLogin() },
                )

                Spacer(Modifier.height(32.dp))
            }
        }
    }
}

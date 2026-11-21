package com.kool.customer.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.kool.customer.R
import com.kool.customer.ui.theme.Art
import com.kool.customer.ui.theme.DesignCanvas
import com.kool.customer.ui.theme.KoolDarkBg
import com.kool.customer.ui.theme.KoolPageDotActive
import com.kool.customer.ui.theme.KoolPageDotInactive
import com.kool.customer.ui.theme.Label
import com.kool.customer.ui.theme.Slab
import com.kool.customer.ui.theme.at

// Screen 01 · Welcome — Figma frame 2:4 (292 × 672). Coordinates are the frame's own px.
@Composable
fun WelcomeScreen(
    onStart: () -> Unit,
    onLogin: () -> Unit,
) {
    DesignCanvas(designWidth = 292.dp, designHeight = 672.dp, background = KoolDarkBg) {
        Box(
            Modifier
                .fillMaxSize()
                .background(Brush.verticalGradient(listOf(Color(0xFF11191C), Color(0xFF14171A)))),
        )

        // Decorative artwork, exactly where the design places it
        Art(R.drawable.welcome_coral_left, 0f, 88f, 66f, 145f)
        Art(R.drawable.welcome_coral_right, 241f, 182f, 51f, 115f)
        Art(R.drawable.welcome_peppers, 0f, 254f, 69f, 135f)
        Art(R.drawable.welcome_garnish, 268f, 302f, 24f, 91f)
        Art(R.drawable.welcome_food_art, 0f, 386f, 292f, 184f)

        // Brand artwork (K mark + كول Koool + Rate. & Order.)
        Art(R.drawable.kool_brand_artwork, 86f, 59f, 117f, 190f)

        // Headline
        Label("أكل أحلى", 68f, 260f, 190f, 45f, 33f, Color.White, FontWeight.ExtraBold)
        Label("تجربة أجمل", 62f, 299f, 198f, 45f, 32f, Color(0xFFF44981), FontWeight.ExtraBold)
        Label("اطلب من أفضل المطاعم", 78f, 344f, 175f, 21f, 12f, Color.White.copy(alpha = 0.92f))
        Label("وقيم تجربتك على كول", 78f, 365f, 175f, 20f, 12f, Color.White.copy(alpha = 0.92f))

        // Skip pill
        Box(
            Modifier
                .at(215f, 38f, 65f, 30f)
                .clip(RoundedCornerShape(15.dp))
                .border(0.6.dp, Color(0xFF697277), RoundedCornerShape(15.dp))
                .clickable { onStart() },
        )
        Label("تخطي", 220f, 42f, 40f, 20f, 11f, Color.White)
        Art(R.drawable.welcome_skip_arrow, 258f, 46f, 15f, 14f)

        // Page indicator
        Slab(118f, 570f, 8f, 8f, KoolPageDotActive, 4f)
        Slab(130f, 570f, 8f, 8f, KoolPageDotInactive, 4f)
        Slab(142f, 570f, 8f, 8f, KoolPageDotInactive, 4f)
        Slab(154f, 570f, 8f, 8f, KoolPageDotInactive, 4f)

        // Start CTA
        Box(
            Modifier
                .at(22f, 592f, 245f, 45f)
                .clip(RoundedCornerShape(24.dp))
                .background(
                    Brush.horizontalGradient(
                        0f to Color(0xFFFF967A),
                        0.55f to Color(0xFFFA4388),
                        1f to Color(0xFFFF7C75),
                    ),
                )
                .clickable { onStart() },
        )
        Label("ابدأ الآن", 75f, 601f, 136f, 27f, 16f, Color.White, FontWeight.Bold)
        Art(R.drawable.welcome_cta_arrow, 231f, 604f, 19f, 19f)

        // Login link
        Box(Modifier.at(70f, 646f, 150f, 17f).clickable { onLogin() })
        Label("تسجيل الدخول", 70f, 646f, 150f, 17f, 11f, Color.White.copy(alpha = 0.88f))
    }
}

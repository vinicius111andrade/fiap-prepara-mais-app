package com.vdemelo.preparamais.ui.home

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Cloud
import androidx.compose.material.icons.filled.LocalFireDepartment
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Thermostat
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material.icons.filled.WaterDrop
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.vdemelo.preparamais.ui.nav.NavRoutes

// Color definitions
val OrangeGradientStart = Color(0xFFFFA726)
val OrangeGradientEnd = Color(0xFFFF7043)
val PlaceholderGray = Color(0xFFBDBDBD)

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    onBackClick: () -> Unit = { navController.navigateUp() },
    onSearchTextChange: (String) -> Unit = {}
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(Color.White),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        SearchHeader(
            onBackClick = onBackClick,
            onSearchTextChange = onSearchTextChange
        )

        val scrollState = rememberScrollState()
        Column(modifier = Modifier.verticalScroll(scrollState)) {
            NavTilesGrid(navController = navController)
        }
    }
}

@Composable
fun SearchHeader(
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit = {},
    onSearchTextChange: (String) -> Unit = {},
    searchPlaceholder: String = "Search...",
) {
    var searchText by remember { mutableStateOf("") }

    Column(
        modifier = modifier.fillMaxWidth()
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(OrangeGradientStart, OrangeGradientEnd),
                        startY = 0f,
                        endY = 300f
                    )
                )
        ) {
            Column {
                // Status bar padding
                Spacer(modifier = Modifier.height(40.dp))

                // Content
                Column(
                    modifier = Modifier.padding(16.dp)
                ) {
                    // Header with back button
                    Row(
                        verticalAlignment = Alignment.Top
                    ) {
                        IconButton(
                            onClick = onBackClick,
                            modifier = Modifier.offset(x = (-12).dp)
                        ) {
                            Icon(
                                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                contentDescription = "Back",
                                tint = Color.White
                            )
                        }

                        Column(
                            modifier = Modifier
                                .weight(1f)
                                .padding(top = 8.dp)
                        ) {
                            Text(
                                text = "Qual Cenário Você",
                                color = Color.White,
                                fontSize = 22.sp,
                                fontWeight = FontWeight.SemiBold,
                                lineHeight = 26.sp
                            )
                            Text(
                                text = "Está Enfrentando?",
                                color = Color.White,
                                fontSize = 22.sp,
                                fontWeight = FontWeight.SemiBold,
                                lineHeight = 26.sp
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(24.dp))

                    // Search field
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(28.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = Color.White
                        ),
                        elevation = CardDefaults.cardElevation(
                            defaultElevation = 2.dp
                        )
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 20.dp, vertical = 14.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                imageVector = Icons.Default.Search,
                                contentDescription = "Search",
                                tint = PlaceholderGray,
                                modifier = Modifier.size(22.dp)
                            )

                            Spacer(modifier = Modifier.width(12.dp))

                            BasicTextField(
                                value = searchText,
                                onValueChange = {
                                    searchText = it
                                    onSearchTextChange(it)
                                },
                                modifier = Modifier.weight(1f),
                                textStyle = TextStyle(
                                    fontSize = 16.sp,
                                    color = Color.Black,
                                    fontWeight = FontWeight.Normal
                                ),
                                singleLine = true,
                                cursorBrush = SolidColor(OrangeGradientEnd),
                                decorationBox = { innerTextField ->
                                    Box {
                                        if (searchText.isEmpty()) {
                                            Text(
                                                text = searchPlaceholder,
                                                color = PlaceholderGray,
                                                fontSize = 16.sp,
                                                fontWeight = FontWeight.Normal
                                            )
                                        }
                                        innerTextField()
                                    }
                                }
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))
            }
        }
    }
}

@Composable
fun NavTilesGrid(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            NavTile(
                title = "Enchente",
                icon = { WaterDropIcon(Modifier.size(48.dp)) },
                modifier = Modifier.weight(1f),
                onClick = { navController.navigate(NavRoutes.PERSONALIZATION) }
            )

            NavTile(
                title = "Inundação",
                icon = { WaterDropIcon(Modifier.size(48.dp)) },
                modifier = Modifier.weight(1f),
                onClick = { navController.navigate(NavRoutes.PERSONALIZATION) }
            )
        }

        Row(
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            NavTile(
                title = "Incêndio",
                subtitle = "Florestal",
                icon = {
                    Icon(
                        imageVector = Icons.Default.LocalFireDepartment,
                        contentDescription = "Forest Fire",
                        modifier = Modifier.size(48.dp),
                        tint = Color.White
                    )
                },
                modifier = Modifier.weight(1f),
                onClick = { navController.navigate(NavRoutes.PERSONALIZATION) }
            )

            NavTile(
                title = "Deslizamento",
                subtitle = "De Terra",
                icon = {
                    Icon(
                        imageVector = Icons.Default.Warning,
                        contentDescription = "Landslide",
                        modifier = Modifier.size(48.dp),
                        tint = Color.White
                    )
                },
                modifier = Modifier.weight(1f),
                onClick = { navController.navigate(NavRoutes.PERSONALIZATION) }
            )
        }

        Row(
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            NavTile(
                title = "Tempestade",
                subtitle = "Severa",
                icon = {
                    Icon(
                        imageVector = Icons.Default.Cloud,
                        contentDescription = "Severe Storm",
                        modifier = Modifier.size(48.dp),
                        tint = Color.White
                    )
                },
                modifier = Modifier.weight(1f),
                onClick = { navController.navigate(NavRoutes.PERSONALIZATION) }
            )

            NavTile(
                title = "Onda De",
                subtitle = "Calor",
                icon = {
                    ThermometerIcon(Modifier.size(48.dp))
                },
                modifier = Modifier.weight(1f),
                onClick = { navController.navigate(NavRoutes.PERSONALIZATION) }
            )
        }
    }
}

@Composable
fun NavTile(
    title: String,
    modifier: Modifier = Modifier,
    subtitle: String? = null,
    icon: @Composable () -> Unit,
    context: Context = LocalContext.current,
    onClick: () -> Unit = { Toast.makeText(context, title, Toast.LENGTH_SHORT).show() }
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = modifier
            .fillMaxWidth()
            .height(120.dp)
            .clip(RoundedCornerShape(20.dp))
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(OrangeGradientStart, OrangeGradientEnd)
                )
            )
            .clickable { onClick() },
    ) {
        icon()
        Spacer(modifier = Modifier.height(12.dp))
        Text(
            text = title,
            color = Color.White,
            fontSize = 16.sp,
            fontWeight = FontWeight.Medium,
            textAlign = TextAlign.Center
        )
        subtitle?.let {
            Text(
                text = it,
                color = Color.White.copy(alpha = 0.9f),
                fontSize = 14.sp,
                textAlign = TextAlign.Center
            )
        }
    }
}

@Composable
fun WaterDropIcon(modifier: Modifier = Modifier) {
    Icon(
        imageVector = Icons.Default.WaterDrop,
        contentDescription = null,
        modifier = modifier,
        tint = Color.White
    )
}

@Composable
fun ThermometerIcon(modifier: Modifier = Modifier) {
    Icon(
        imageVector = Icons.Default.Thermostat,
        contentDescription = null,
        modifier = modifier,
        tint = Color.White
    )
}

@Preview
@Composable
fun PreviewHomeScreen() {
    HomeScreen()
}

package com.example.scoreboardping

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.scoreboardping.ui.ScoreApp
import com.example.scoreboardping.ui.ScoreHomeScreen
import com.example.scoreboardping.ui.theme.MyAppTheme
import com.example.scoreboardping.ui.theme.ScoreBoardPingTheme
import com.example.scoreboardping.ui.utils.ScoreContentType
import com.example.scoreboardping.ui.utils.ScoreNavigationType

import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.*
import androidx.compose.material.icons.filled.*
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.scoreboardping.ui.MainScreen
import com.example.scoreboardping.ui.NavigationScreen
import com.example.scoreboardping.ui.NavigationViewModel

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyAppTheme {
                MainScreen()
//
//                var showApp by remember { mutableStateOf(false) }
//                val viewModel: NavigationViewModel = viewModel()
//                var showMainScreen by remember { mutableStateOf(false) }
//
//
//                if (!showMainScreen) {
//                    WelcomeScreen(onEnter = { showMainScreen = true })
//                } else {
//                    NavigationScreen(
//                        viewModel = viewModel,
//                        showRail = true,
//                        animateEntrance = true // 👈 flag pour déclencher l'animation
//                    )
//                }
                //LibraryScreen()
            }
//
//            ScoreBoardPingTheme {
//                val layoutDirection = LocalLayoutDirection.current
//                Surface(
//                    modifier = Modifier
//                        .padding(
//                            start = WindowInsets.safeDrawing.asPaddingValues()
//                                .calculateStartPadding(layoutDirection),
//                            end = WindowInsets.safeDrawing.asPaddingValues()
//                                .calculateEndPadding(layoutDirection)
//                        )
//                ) {
//                    val windowSize = calculateWindowSizeClass(this)
//
//                    ScoreApp(
//                        windowSize = windowSize.widthSizeClass,
//                    )
//                   // ScoreHomeScreen(navigationType = ScoreNavigationType.BOTTOM_NAVIGATION, contentType = ScoreContentType.LIST_ONLY, modifier = Modifier)
//
//
//                }
//            }
        }
    }
}
@Composable
fun WelcomeScreen(onEnter: () -> Unit) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {
            Button(onClick = onEnter) {
                Text("Accéder à l'application")
            }
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LibraryScreen() {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Title") },
                navigationIcon = {
                    IconButton(onClick = { /* Back */ }) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                },
                actions = {
                    IconButton(onClick = {}) {
                        Icon(Icons.Default.MoreVert, contentDescription = "More")
                    }
                }
            )
        },
        bottomBar = {
            NavigationBar {
                repeat(3) { index ->
                    NavigationBarItem(
                        selected = index == 0,
                        onClick = { },
                        icon = {
                            Icon(
                                if (index == 0) Icons.Default.Star else Icons.Default.StarBorder,
                                contentDescription = "Label"
                            )
                        },
                        label = { Text("Label") }
                    )
                }
            }
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
        ) {

            // Filter Chips
            LazyRow(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(6) { index ->
                    FilterChip(
                        selected = index == 1,
                        onClick = { },
                        label = { Text("Label") }
                    )
                }
            }

            // Sort & View Options
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(Icons.Default.SwapVert, contentDescription = null)
                Spacer(modifier = Modifier.width(8.dp))
                Text("Label", fontWeight = FontWeight.Medium)
                Spacer(modifier = Modifier.weight(1f))
                Icon(Icons.Default.ViewModule, contentDescription = "Grid")
            }

            // Grid of Cards
            LazyVerticalGrid(
                columns = GridCells.Fixed(3),
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(8.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(12) { index ->
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(120.dp),
                        elevation = CardDefaults.cardElevation(4.dp)
                    ) {
                        Column(
                            modifier = Modifier
                                .padding(8.dp)
                                .fillMaxSize(),
                            verticalArrangement = Arrangement.SpaceBetween,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Box(
                                modifier = Modifier
                                    .size(48.dp)
                                    .background(
                                        Color.LightGray,
                                        shape = MaterialTheme.shapes.medium
                                    )
                            )
                            Text("Title", style = MaterialTheme.typography.labelLarge)
                            Text(
                                "Updated ${listOf("today", "yesterday", "2 days ago")[index % 3]}",
                                style = MaterialTheme.typography.labelMedium
                            )
                        }
                    }
                }
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun LibraryScreenPreview() {
    MyAppTheme {
        LibraryScreen()
    }
}

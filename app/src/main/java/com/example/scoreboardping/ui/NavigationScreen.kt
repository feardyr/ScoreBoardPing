package com.example.scoreboardping.ui


import android.content.res.Configuration
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.*
import androidx.compose.animation.core.tween



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NavigationScreen(
    viewModel: NavigationViewModel,
    showRail: Boolean,
    animateEntrance: Boolean  // Nouveau paramètre pour l'animation d'entré

) {
    val selectedItem by viewModel.selectedIndex.collectAsState()
    val isExpandedScreen = LocalConfiguration.current.screenWidthDp >= 600

    val animated = remember(animateEntrance) { mutableStateOf(animateEntrance) }

    AnimatedVisibility(
        visible = animated.value, // Toujours visible une fois l'app affichée
        enter = fadeIn(animationSpec = tween(durationMillis = 1000)),
    ) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Title") },
                navigationIcon = {
                    IconButton(onClick = { /* back */ }) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                },
                actions = {
                    IconButton(onClick = { /* more */ }) {
                        Icon(Icons.Default.MoreVert, contentDescription = "More")
                    }
                }
            )
        },
        bottomBar = {
                NavigationBar {
                    val items = listOf("Mail", "Chat", "Spaces")
                    items.forEachIndexed { index, label ->
                        NavigationBarItem(
                            selected = selectedItem == index,
                            onClick = { viewModel.onItemSelected(index) },
                            icon = {
                                Icon(
                                    if (selectedItem == index) Icons.Default.Star else Icons.Default.StarBorder,
                                    contentDescription = label
                                )
                            },
                            label = { Text(label) }
                        )
                    }
                }

        }
    ) { innerPadding ->
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            AnimatedVisibility(
                visible = showRail,
                enter = slideInHorizontally(
                    initialOffsetX = { -it }, // glisse depuis la gauche
                    animationSpec = tween(1000)
                ) + fadeIn(animationSpec = tween(1000)),

            ) {
                NavigationRailLayout(
                    selectedItem = selectedItem,
                    onSelect = viewModel::onItemSelected
                )
            }
            Content(selectedItem)
        }
    }
    }
}

@Composable
fun NavigationRailLayout(selectedItem: Int, onSelect: (Int) -> Unit) {
    Row {
        NavigationRail(
            modifier = Modifier
                .fillMaxHeight()
                .background(Color(0xFFF6EFF8)),
            header = {
                IconButton(onClick = {}) {
                    Icon(Icons.Default.Menu, contentDescription = "Menu")
                }
                Spacer(Modifier.height(16.dp))
                FloatingActionButton(
                    onClick = {},
                    containerColor = Color(0xFFFFDDE5),
                    contentColor = Color.Black,
                    shape = CircleShape,
                    elevation = FloatingActionButtonDefaults.elevation(0.dp)
                ) {
                    Icon(Icons.Default.Edit, contentDescription = "Compose")
                }
            }
        ) {
            val items = listOf("Mail", "Chat", "Spaces", "Meet")
            val icons = listOf(
                Icons.Default.Email,
                Icons.Default.Chat,
                Icons.Default.Group,
                Icons.Default.VideoCall
            )

            items.forEachIndexed { index, label ->
                val animatedColor by animateColorAsState(
                    targetValue = if (selectedItem == index) Color(0xFFEDE6FF) else Color.Transparent,
                    animationSpec = tween(300)
                )
                NavigationRailItem(
                    selected = selectedItem == index,
                    onClick = { onSelect(index) },
                    icon = { Icon(icons[index], contentDescription = label) },
                    label = { Text(label) },
                    alwaysShowLabel = true,
                    colors = NavigationRailItemDefaults.colors(
                        indicatorColor = animatedColor
                    )
                )
            }
        }

        Content(selectedItem)
    }
}

@Composable
fun DrawerContent(selectedItem: Int, onSelect: (Int) -> Unit) {
    val items = listOf("Mail", "Chat", "Spaces", "Meet")
    val icons = listOf(
        Icons.Default.Email,
        Icons.Default.Chat,
        Icons.Default.Group,
        Icons.Default.VideoCall
    )

    Column(modifier = Modifier.fillMaxHeight().padding(16.dp)) {
        Text("Navigation", style = MaterialTheme.typography.titleMedium)
        Spacer(Modifier.height(8.dp))
        items.forEachIndexed { index, label ->
            NavigationDrawerItem(
                label = { Text(label) },
                selected = selectedItem == index,
                onClick = { onSelect(index) },
                icon = { Icon(icons[index], contentDescription = label) }
            )
        }
    }
}

@Composable
fun Content(selectedItem: Int) {
    val screens = listOf("Mail Screen", "Chat Screen", "Spaces Screen", "Meet Screen")
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Text(text = screens[selectedItem], style = MaterialTheme.typography.headlineMedium)
    }
}

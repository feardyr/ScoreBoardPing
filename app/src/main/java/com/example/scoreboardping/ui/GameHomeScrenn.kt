package com.example.scoreboardping.ui

import androidx.annotation.StringRes
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.Image
import androidx.compose.ui.res.painterResource
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bookmark
import androidx.compose.material.icons.filled.ChangeHistory
import androidx.compose.material.icons.filled.Chat
import androidx.compose.material.icons.filled.Circle
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Groups
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Menu // Importation de l'icône Menu de Material Icons
import androidx.compose.material.icons.filled.Square
import androidx.compose.material.icons.filled.Update
import androidx.compose.material.icons.filled.VideoCall
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api // Annotation pour TopAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer // Remplace Drawer
import androidx.compose.material3.NavigationBar // Remplace BottomNavigation
import androidx.compose.material3.NavigationBarItem // Remplace BottomNavigationItem
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberDrawerState // Remplace rememberScaffoldState().drawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope // Utilisation de rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.scoreboardping.R
import kotlinx.coroutines.launch // Importation pour scope.launch
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.DrawerState
import androidx.compose.material3.TextButton
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.compose.runtime.getValue
import com.example.scoreboardping.ui.GameHomeScreen

@OptIn(ExperimentalMaterial3Api::class) // Annotation pour l'utilisation de TopAppBar
@Composable
fun MainScreen() {
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed) // Utilisation de rememberDrawerState de Material 3
    val scope = rememberCoroutineScope()

    val navController = rememberNavController()
    ModalNavigationDrawer( // Utilisation de ModalNavigationDrawer de Material 3
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet { // Utilisation de ModalDrawerSheet de Material 3
                DrawerContent(
                    navController = navController,
                    drawerState = drawerState
                )
            }
        },
        content = {
            Scaffold( // Utilisation de Scaffold de Material 3
                topBar = {
                    TopAppBar( // Utilisation de TopAppBar de Material 3
                        title = { Text("Title") },
                        navigationIcon = {
                            IconButton(onClick = {
                                scope.launch { drawerState.apply { if (isClosed) open() else close() } }
                            }) {
                                Icon(Icons.Default.Menu, contentDescription = null)
                            }
                        },
                        actions = {
                            Image(
                                painter = painterResource(R.drawable.avatar),
                                contentDescription = "Avatar",
                                modifier = Modifier
                                    .size(36.dp)
                                    .clip(CircleShape)
                            )
                        }
                        // Dans Material 3 TopAppBar, la couleur d'arrière-plan est gérée différemment via les couleurs du thème
                        // ou des paramètres spécifiques si nécessaire. Pour un simple arrière-plan, vous pouvez utiliser containerColor.
                        // containerColor = Color(0xFFF8F2FC) // Exemple de couleur d'arrière-plan si nécessaire
                    )
                },
                bottomBar = {
                    BottomNavigationBar(navController = navController)
                },
                content = { padding ->
                    NavHost(
                        navController = navController,
                        startDestination = GameHomeScreen.Start.name,
                        modifier = Modifier
                            .padding(padding)
                            .fillMaxSize()
                    ){
                        composable(route = GameHomeScreen.Start.name) {
                            ListGamesMain()
                        }
                        composable(route = GameHomeScreen.ListGames.name) {
                            ListGamesHeader()
                        }
                        composable(route = GameHomeScreen.Menu.name) {
                            Menu()
                        }
                        composable(route = GameHomeScreen.ScorePing.name) {
                            MainPingScreen()
                        }
                    }
//                    MainCard(Modifier.padding(padding))7
                    //ListGames(Modifier.padding(padding))
                }
            )
        }
    )
}
enum class GameHomeScreen(@StringRes val title: Int) {
    Start(title = R.string.app_name),
    ListGames(title = R.string.listegame),
    Menu(title = R.string.menu),
    ScorePing(title =R.string.scoreping)

}


@Composable
fun Menu() {
    Text("fbryfrfgrfrgyrgf")
}

@Composable
fun DrawerContent(
    navController : NavHostController,
    drawerState: DrawerState
) {
    val scope = rememberCoroutineScope()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    Column(
        Modifier
            .fillMaxHeight()
            .background(Color(0xFFF8F2FC))
            .padding(16.dp)
    ) {
        IconWithLabelButton(Icons.Default.Edit, "Edit", Color(0xFFF8DDEB))
        Spacer(modifier = Modifier.height(16.dp))
        IconWithLabelButton(Icons.Default.Email, "Mail", onClick = {
            if (currentRoute != GameHomeScreen.Menu.name) {
                navController.navigate(GameHomeScreen.Menu.name) {
                    popUpTo(navController.graph.startDestinationId) {
                        saveState = true
                    }
                    launchSingleTop = true
                    restoreState = true
                }
            }
            scope.launch { drawerState.close() }
        } )
        IconWithLabelButton(Icons.Default.Chat, "Chat",onClick = {
            if (currentRoute != GameHomeScreen.Start.name) {
                navController.navigate(GameHomeScreen.Start.name) {
                    popUpTo(navController.graph.startDestinationId) {
                        saveState = true
                    }
                    launchSingleTop = true
                    restoreState = true
                }
            }
            scope.launch { drawerState.close() }
        })
        IconWithLabelButton(Icons.Default.Groups, "Spaces", onClick = {
            if (currentRoute != GameHomeScreen.ScorePing.name) {
                navController.navigate(GameHomeScreen.ScorePing.name) {
                    popUpTo(navController.graph.startDestinationId) {
                        saveState = true
                    }
                    launchSingleTop = true
                    restoreState = true
                }
            }
            scope.launch { drawerState.close() }

        })
        IconWithLabelButton(Icons.Default.VideoCall, "Meet")
    }
}


@Composable
fun IconWithLabel(icon: ImageVector, label: String, bgColor: Color = Color.Transparent) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .background(bgColor, shape = RoundedCornerShape(10.dp))
            .padding(10.dp)
    ) {
        Icon(icon, contentDescription = null, tint = Color.Black)
        Spacer(Modifier.width(12.dp))
        Text(label, fontWeight = FontWeight.Bold)
    }
}

@Composable
fun IconWithLabelButton(
    icon: ImageVector,
    label: String,
    bgColor: Color = Color.Transparent,
    contentColor: Color = Color.Black,
    onClick: () -> Unit = {}
) {
    Button(onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            containerColor = bgColor,
            contentColor = contentColor),
        modifier = Modifier
      .padding(horizontal = 1.dp, vertical = 8.dp)

        ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start,
            modifier = Modifier.padding(horizontal = 8.dp)
        ) {
            Icon(icon, contentDescription = null)
            Spacer(Modifier.width(12.dp))
            Text(label, fontWeight = FontWeight.Bold)
        }
    }
}


@Composable
fun ListGamesHeader(
    modifier: Modifier = Modifier,
    onNextButtonClicked: (Int) -> Unit = {}

) {
    LazyColumn(
        modifier = modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(10) {
            //MainCard()
            HeaderCard()
        }
    }
}

@Composable
fun ListGamesMain(
    modifier: Modifier = Modifier,
    onNextButtonClicked: (Int) -> Unit = {}

) {
    LazyColumn(
        modifier = modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(10) {
            MainCard()
        }
    }
}

@Composable
fun HeaderCard(modifier: Modifier = Modifier) {
    Card(
        shape = RoundedCornerShape(8.dp),
        modifier = modifier
            .fillMaxWidth()
            .height(80.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFF8F2FC))
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxSize()
        ) {
            // Avatar
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .padding(12.dp)
                    .size(40.dp)
                    .clip(CircleShape)
                    .background(Color(0xFFE6E0F8))
            ) {
                Text("A", fontWeight = FontWeight.Bold, color = Color(0xFF381E72))
            }

            // Texts
            Column(
                verticalArrangement = Arrangement.Center,
                modifier = Modifier
                    .weight(1f)
            ) {
                Text("Header", fontWeight = FontWeight.Bold)
                Text("Subhead", color = Color.Gray, fontSize = 12.sp)
            }

            // Shapes
            Box(
                modifier = Modifier
                    .fillMaxHeight()
                    .width(80.dp)
                    .background(Color(0xFFDADCE0)), // Light grey area
                contentAlignment = Alignment.Center
            ) {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(4.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        Icons.Default.ChangeHistory,
                        contentDescription = "Triangle",
                        tint = Color.Gray,
                        modifier = Modifier.size(20.dp)
                    )
                    Icon(
                        Icons.Default.Square,
                        contentDescription = "Square",
                        tint = Color.Gray,
                        modifier = Modifier.size(20.dp)
                    )
                    Icon(
                        Icons.Default.Circle,
                        contentDescription = "Circle",
                        tint = Color.Gray,
                        modifier = Modifier.size(20.dp)
                    )
                }
            }
        }
    }
}


@Composable
fun MainCard(
    modifier: Modifier = Modifier,
    onNextButtonClicked: (Int) -> Unit = {}
)
{
    Card(
        shape = RoundedCornerShape(20.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Column(Modifier.padding(16.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                CircleAvatar("A")
                Spacer(Modifier.width(12.dp))
                Column {
                    Text("Header", fontWeight = FontWeight.Bold)
                    Text("Subhead", color = Color.Gray)
                }
            }
            Spacer(Modifier.height(12.dp))
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(120.dp)
                    .background(Color.LightGray)
            ) {
                // Fake image shapes
                Icon(Icons.Default.ChangeHistory, contentDescription = null, modifier = Modifier.align(Alignment.TopCenter))
                Icon(Icons.Default.Square, contentDescription = null, modifier = Modifier.align(Alignment.CenterStart))
                Icon(Icons.Default.Circle, contentDescription = null, modifier = Modifier.align(Alignment.CenterEnd))
            }
            Spacer(Modifier.height(12.dp))
            Text("Title", fontWeight = FontWeight.Bold)
            Text("Subtitle", color = Color.Gray)
            Text("Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor")
            Spacer(Modifier.height(8.dp))
            Row {
                OutlinedButton(onClick = {}) {
                    Text("Enabled")
                }
                Spacer(Modifier.width(8.dp))
                Button(onClick = {}, colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF6654A6))) {
                    Text("Enabled")
                }
            }
        }
    }
}



@Composable
fun CircleAvatar(letter: String) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .size(40.dp)
            .clip(CircleShape)
            .background(Color(0xFFE0F8E7))
    ) {
        Text(letter, color = Color.Black, fontWeight = FontWeight.Bold)
    }
}

//@Composable
//fun BottomNavigationBar(navController: NavHostController = rememberNavController()) {
//
//    NavigationBar(containerColor = Color(0xFFF8F2FC)) { // Utilisation de NavigationBar et containerColor pour Material 3
//        NavigationBarItem( // Utilisation de NavigationBarItem pour Material 3
//            icon = { Icon(Icons.Default.LocationOn, contentDescription = null) },
//            selected = true,
//            onClick = { },
//            label = { Text("Explore") }
//        )
//        NavigationBarItem( // Utilisation de NavigationBarItem pour Material 3
//            icon = { Icon(Icons.Default.Bookmark, contentDescription = null) },
//            selected = false,
//            onClick = { navController.navigate(GameHomeScreen.Start.name) },
//            label = { Text("Saved") }
//        )
//        NavigationBarItem( // Utilisation de NavigationBarItem pour Material 3
//            icon = { Icon(Icons.Default.Update, contentDescription = null) },
//            selected = false,
//            onClick = { navController.navigate(GameHomeScreen.ListGames.name)},
//            label = { Text("Updates") }
//        )
//    }
//}

@Composable
fun BottomNavigationBar(navController: NavHostController) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    NavigationBar(containerColor = Color(0xFFF8F2FC)) {
        NavigationBarItem(
            icon = { Icon(Icons.Default.LocationOn, contentDescription = null) },
            selected = currentRoute == GameHomeScreen.ScorePing.name,
            onClick = {
                if (currentRoute != GameHomeScreen.ScorePing.name) {
                    navController.navigate(GameHomeScreen.ScorePing.name) {
                        popUpTo(navController.graph.startDestinationId) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            },
            label = { Text("Explore") }
        )

        NavigationBarItem(
            icon = { Icon(Icons.Default.Bookmark, contentDescription = null) },
            selected = currentRoute == GameHomeScreen.Start.name,
            onClick = {
                if (currentRoute != GameHomeScreen.Start.name) {
                    navController.navigate(GameHomeScreen.Start.name) {
                        popUpTo(navController.graph.startDestinationId) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            },
            label = { Text("Saved") }
        )

        NavigationBarItem(
            icon = { Icon(Icons.Default.Update, contentDescription = null) },
            selected = currentRoute == GameHomeScreen.ListGames.name,
            onClick = {
                if (currentRoute != GameHomeScreen.ListGames.name) {
                    navController.navigate(GameHomeScreen.ListGames.name) {
                        popUpTo(navController.graph.startDestinationId) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            },
            label = { Text("Updates") }
        )
    }
}

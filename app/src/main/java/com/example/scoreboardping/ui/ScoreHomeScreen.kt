package com.example.scoreboardping.ui

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Send
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import com.example.scoreboardping.ui.utils.ScoreContentType
import com.example.scoreboardping.ui.utils.ScoreNavigationType

@Composable
fun ScoreHomeScreen(
    navigationType: ScoreNavigationType,
    contentType: ScoreContentType,
    modifier: Modifier = Modifier
){
    val navigationItemContentList = listOf(
        NavigationItemContent(
            icon = Icons.Default.Home,
            Nom = "Boulay",
            Prenom = "Nathan",
            point = 1100
        ),
        NavigationItemContent(
            icon = Icons.Default.DateRange,
            Nom = "Boulay",
            Prenom = "Yannick",
            point = 850
        ),
    )
}


private data class NavigationItemContent(
    val icon: ImageVector,
    val Nom: String,
    val Prenom: String,
    val point: Int,
)
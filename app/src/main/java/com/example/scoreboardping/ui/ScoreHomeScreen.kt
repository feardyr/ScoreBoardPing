package com.example.scoreboardping.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.R
import androidx.compose.foundation.layout.Row
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.scoreboardping.ui.theme.ScoreBoardPingTheme
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
    Column {
        Text(
            text = stringResource(id = com.example.scoreboardping.R.string.Nom, navigationItemContentList[0].Nom),
            )
        HomeLayout()
    }
}



@Preview(showBackground = true)
@Composable
fun HomeLayout(
    modifier: Modifier= Modifier
){
    Card (
        modifier = modifier,
        elevation = CardDefaults.cardElevation(defaultElevation = 5.dp)
    ){
        Column {
                Text(
                    text = "COUCOU"
                )
                Text(
                    text = "COUCOU2"
                )
            Row {
                Text(
                    text = "COUCOU3"
                )
                Text(
                    text = "COUCOU4"
                )
            }

        }
        Column {
            Text(
                text = "COUCOU5"
            )
            Text(
                text = "COUCOU6"
            )
        }
    }
}

private data class NavigationItemContent(
    val icon: ImageVector,
    val Nom: String,
    val Prenom: String,
    val point: Int,
)

@Preview(showBackground = true)
@Composable
fun ScoreHomeScreenPreview() {
    ScoreBoardPingTheme {
        ScoreHomeScreen(navigationType = ScoreNavigationType.BOTTOM_NAVIGATION, contentType = ScoreContentType.LIST_AND_DETAIL, modifier = Modifier)
    }
}
package com.example.scoreboardping.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.scoreboardping.ui.utils.ScoreContentType
import com.example.scoreboardping.ui.utils.ScoreNavigationType

@Composable
fun ScoreApp(
    modifier: Modifier = Modifier,
    windowSize: WindowWidthSizeClass,
){
    val navigationType: ScoreNavigationType
    val contentType: ScoreContentType
    //val viewModel = ScoreViewModel = viewModel()
   // val replyUiState = viewModel.uiState.collectAsState().value

    when (windowSize) {
        WindowWidthSizeClass.Compact -> {
            navigationType = ScoreNavigationType.BOTTOM_NAVIGATION
            contentType = ScoreContentType.LIST_ONLY
        }
        WindowWidthSizeClass.Medium -> {
            navigationType = ScoreNavigationType.NAVIGATION_RAIL
            contentType = ScoreContentType.LIST_ONLY
        }
        WindowWidthSizeClass.Expanded -> {
            navigationType = ScoreNavigationType.PERMANENT_NAVIGATION_DRAWER
            contentType = ScoreContentType.LIST_AND_DETAIL
        }
        else -> {
            navigationType = ScoreNavigationType.BOTTOM_NAVIGATION
            contentType = ScoreContentType.LIST_ONLY
        }
    }
    Column(
        modifier= Modifier
            .padding(40.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center

    ){
        Text(text = "ScoreBoardPing")

    }

    ScoreHomeScreen(
        navigationType = ScoreNavigationType.BOTTOM_NAVIGATION, contentType = ScoreContentType.LIST_ONLY, modifier = Modifier
    )

}
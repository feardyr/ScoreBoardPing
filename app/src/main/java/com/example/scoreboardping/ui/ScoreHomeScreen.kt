package com.example.scoreboardping.ui

import androidx.compose.material.icons.Icons
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
    replyUiState: ScoreUiState,
    modifier: Modifier = Modifier
){
    val navigationItemContentList = listOf(
        NavigationItemContent(
            icon = Icons.Default.Inbox,
            text = stringResource(id = R.string.tab_inbox)
        ),
        NavigationItemContent(
            icon = Icons.Default.Send,
            text = stringResource(id = R.string.tab_sent)
        ),
        NavigationItemContent(
            icon = Icons.Default.Drafts,
            text = stringResource(id = R.string.tab_drafts)
        ),
        NavigationItemContent(
            mailboxType = MailboxType.Spam,
            icon = Icons.Default.Report,
            text = stringResource(id = R.string.tab_spam)
        )
    )
}


private data class NavigationItemContent(
    val icon: ImageVector,
    val Nom: String,
    val Prenom: String,
    val point: Int,
)
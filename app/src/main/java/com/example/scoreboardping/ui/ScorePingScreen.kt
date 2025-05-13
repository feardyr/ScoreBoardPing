package com.example.scoreboardping.ui

import android.R.attr.text
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.TextFieldValue

class ScorePingScreen {

}


@Composable
fun MainPingScreen(){
    var text1 by remember { mutableStateOf("") }
    var text2 by remember { mutableStateOf("") }
    var text3 by remember { mutableStateOf("") }
    var text4 by remember { mutableStateOf("") }
    val options = listOf("BO 1", "BO 3", "BO 5","BO 7")
    var selectedOption by remember { mutableStateOf(options[0]) }
    var isSwitchOn by remember { mutableStateOf(false) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState()), // 👉 Scroll activé
            verticalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Match en double ", modifier = Modifier.weight(1f))
                Switch(
                    checked = isSwitchOn,
                    onCheckedChange = { isSwitchOn = it }
                )
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                // Premier champ
                OutlinedTextField(
                    value = text1,
                    onValueChange = { text1 = it },
                    label = { Text("Joueur 1") },
                    supportingText = { Text("Ecrire le nom du jouer") },
                    trailingIcon = {
                        IconButton(onClick = { text1 = "" }) {
                            Icon(Icons.Default.Close, contentDescription = "Clear")
                        }
                    },
                    modifier = Modifier.weight(1f),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedContainerColor = Color(0xFFE7E0EC),
                        unfocusedContainerColor = Color(0xFFE7E0EC),
                        focusedBorderColor = MaterialTheme.colorScheme.outline,
                        unfocusedBorderColor = MaterialTheme.colorScheme.outline,
                    ),
                    shape = RoundedCornerShape(8.dp)
                )
                // Deuxième champ
                OutlinedTextField(
                    value = text2,
                    onValueChange = { text2 = it },
                    label = { Text("Joueur 2") },
                    supportingText = { Text("Ecrire le nom du jouer") },
                    trailingIcon = {
                        IconButton(onClick = { text2 = "" }) {
                            Icon(Icons.Default.Close, contentDescription = "Clear")
                        }
                    },
                    modifier = Modifier.weight(1f),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedContainerColor = Color(0xFFE7E0EC),
                        unfocusedContainerColor = Color(0xFFE7E0EC),
                        focusedBorderColor = MaterialTheme.colorScheme.outline,
                        unfocusedBorderColor = MaterialTheme.colorScheme.outline,
                    ),
                    shape = RoundedCornerShape(8.dp)
                )
            }
            if (isSwitchOn) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    OutlinedTextField(
                        value = text3,
                        onValueChange = { text3 = it },
                        label = { Text("Joueur 3") },
                        supportingText = { Text("Écrire le nom du joueur") },
                        trailingIcon = {
                            IconButton(onClick = { text3 = "" }) {
                                Icon(Icons.Default.Close, contentDescription = "Clear")
                            }
                        },
                        modifier = Modifier.weight(1f),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedContainerColor = Color(0xFFE7E0EC),
                            unfocusedContainerColor = Color(0xFFE7E0EC),
                            focusedBorderColor = MaterialTheme.colorScheme.outline,
                            unfocusedBorderColor = MaterialTheme.colorScheme.outline,
                        ),
                        shape = RoundedCornerShape(8.dp)
                    )

                    OutlinedTextField(
                        value = text4,
                        onValueChange = { text4 = it },
                        label = { Text("Joueur 4") },
                        supportingText = { Text("Écrire le nom du joueur") },
                        trailingIcon = {
                            IconButton(onClick = { text4 = "" }) {
                                Icon(Icons.Default.Close, contentDescription = "Clear")
                            }
                        },
                        modifier = Modifier.weight(1f),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedContainerColor = Color(0xFFE7E0EC),
                            unfocusedContainerColor = Color(0xFFE7E0EC),
                            focusedBorderColor = MaterialTheme.colorScheme.outline,
                            unfocusedBorderColor = MaterialTheme.colorScheme.outline,
                        ),
                        shape = RoundedCornerShape(8.dp)
                    )
                }
            }

            if (isSwitchOn) {
                // Carte Équipe 1
                EquipeView(
                    joueur1 = text1,
                    joueur2 = text2,
                    equipeNom = "Equipe 1",
                    couleur = Color(0xFF1E88E5)
                )

                // Carte Équipe 2
                EquipeView(
                    joueur1 = text3,
                    joueur2 = text4,
                    equipeNom = "Equipe 2",
                    couleur = Color(0xFF388E3C)
                )
            }



            Column(
                modifier = Modifier.padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Text("Choisissez le nombre de manche :", style = MaterialTheme.typography.titleMedium)

                options.forEach { option ->
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.fillMaxWidth()
                            .clickable { selectedOption = option } // 👈 clique sur toute la ligne
                            .padding(vertical = 4.dp)
                    ) {
                        RadioButton(
                            selected = (option == selectedOption),
                            onClick = null
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(option)
                    }
                }



                Spacer(modifier = Modifier.height(16.dp))
                Text("Option sélectionnée : $selectedOption")
            }

        }



        // FAB en bas à droite
        ExtendedFloatingActionButton(
            onClick = { /* TODO */ },
            icon = { Icon(Icons.Default.Star, contentDescription = "Star") },
            text = { Text("Label") },
            containerColor = Color(0xFF6750A4),
            contentColor = Color.White,
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(16.dp)
        )
    }


}

@Composable
fun EquipeView(
    joueur1: String,
    joueur2: String,
    equipeNom: String,
    couleur: Color
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        colors = CardDefaults.cardColors(containerColor = couleur),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(
                text = equipeNom,
                style = MaterialTheme.typography.titleMedium,
                color = Color.White
            )
            Text("• $joueur1", style = MaterialTheme.typography.bodyLarge, color = Color.White)
            Text("• $joueur2", style = MaterialTheme.typography.bodyLarge, color = Color.White)
        }
    }
}
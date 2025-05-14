package com.example.scoreboardping.ui

//import EquipeDragAndDropScreen
import android.R.attr.text
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.gestures.detectDragGesturesAfterLongPress
import androidx.compose.foundation.gestures.draggable
import androidx.compose.foundation.gestures.rememberDraggableState
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.IntOffset
import kotlinx.coroutines.delay
import kotlin.math.roundToInt

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
    val joueursActuels = listOf(text1, text2, text3, text4).filter { it.isNotBlank() }

    var equipeAState by remember { mutableStateOf<List<String>>(emptyList()) }
    var equipeBState by remember { mutableStateOf<List<String>>(emptyList()) }

    LaunchedEffect(joueursActuels, isSwitchOn) {
        if (isSwitchOn) {
            val currentPlayersInTeamsSet = (equipeAState + equipeBState).toSet()
            val joueursActuelsSet = joueursActuels.toSet()

            // Condition simplifiée : mettre à jour si l'ensemble des joueurs dans les équipes
            // ne correspond pas à l'ensemble des joueurs actifs actuels.
            // Cela gère l'ajout/suppression/modification de joueurs et l'initialisation.
            if (currentPlayersInTeamsSet != joueursActuelsSet) {
                Log.d("EquipeUpdate", "Changement joueurs/équipes. Actuels: $joueursActuelsSet, DansEquipes: $currentPlayersInTeamsSet")
                if (joueursActuels.isNotEmpty()) {
                    val midPoint = (joueursActuels.size + 1) / 2
                    equipeAState = joueursActuels.take(midPoint)
                    equipeBState = joueursActuels.drop(midPoint)
                } else {
                    equipeAState = emptyList()
                    equipeBState = emptyList()
                }
                Log.d("EquipeUpdate", "Equipes après mise à jour -> A: $equipeAState, B: $equipeBState")
            }
        } else {
            // Si switch OFF, vider les équipes si elles ne le sont pas déjà.
            if (equipeAState.isNotEmpty() || equipeBState.isNotEmpty()) {
                Log.d("EquipeUpdate", "Switch OFF, vidage équipes.")
                equipeAState = emptyList()
                equipeBState = emptyList()
            }
        }
    }

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

//            if (isSwitchOn) {
//                // Carte Équipe 1
//                EquipeView(
//                    joueur1 = text1,
//                    joueur2 = text2,
//                    equipeNom = "Equipe 1",
//                    couleur = Color(0xFF1E88E5)
//                )
//
//                // Carte Équipe 2
//                EquipeView(
//                    joueur1 = text3,
//                    joueur2 = text4,
//                    equipeNom = "Equipe 2",
//                    couleur = Color(0xFF388E3C)
//                )
//            }



            if (isSwitchOn) {
                // ... (Champs Joueur 3 et 4) ...

                // APPEL MODIFIÉ à EquipeDragAndDropScreen
                if (joueursActuels.isNotEmpty()) { // Ou une condition plus stricte si nécessaire
                    Text("Organisez vos équipes :", style = MaterialTheme.typography.titleMedium)
                    EquipeDragAndDropScreen(
                        equipeA = equipeAState, // Passe l'état
                        equipeB = equipeBState, // Passe l'état
                        onEquipeAChange = { newEquipeA -> equipeAState = newEquipeA }, // Callback
                        onEquipeBChange = { newEquipeB -> equipeBState = newEquipeB }  // Callback
                    )
                }
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
@Composable
fun EquipeDragAndDropScreen(
    equipeA: List<String>, // Reçoit directement l'état
    equipeB: List<String>, // Reçoit directement l'état
    onEquipeAChange: (List<String>) -> Unit, // Callback pour mettre à jour
    onEquipeBChange: (List<String>) -> Unit,  // Callback pour mettre à jour
//    joueursInitiaux: List<String>
) {

//    var equipeA by remember { mutableStateOf(joueursInitiaux.take(2)) }
//    var equipeB by remember { mutableStateOf(joueursInitiaux.drop(2)) }
    var joueurEnCours by remember { mutableStateOf<String?>(null) }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 8.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Box(modifier = Modifier.weight(1f)) {
            DropEquipeCard(
                nom = "Équipe A",
                joueurs = equipeA,
                couleur = Color(0xFF1E88E5),
                onDrop = {
                    Log.d("DragDrop", "EquipeDragAndDropScreen - onDrop sur Équipe A. joueurEnCours: $joueurEnCours")
                    joueurEnCours?.let { joueur ->
                        if (equipeB.contains(joueur)) {
                            Log.d("DragDrop", "Déplacement de $joueur de B vers A")
                            onEquipeBChange(equipeB - joueur) // Modifie via callback
                            onEquipeAChange(equipeA + joueur) // M
//                            equipeB = equipeB - joueur
//                            equipeA = equipeA + joueur
                            joueurEnCours = null
                        }else{
                            Log.d("DragDrop", "$joueur n'est pas dans equipeB ou drag sur sa propre équipe.")
                        }
                    }
                },
                onDragStart = {
                    Log.d("DragDrop", "EquipeDragAndDropScreen - onDragStart: Définition de joueurEnCours = $it")
                    joueurEnCours = it }
            )
        }

        Box(modifier = Modifier.weight(1f)) {
            DropEquipeCard(
                nom = "Équipe B",
                joueurs = equipeB,
                couleur = Color(0xFF388E3C),
                onDrop = {
                    Log.d("DragDrop", "EquipeDragAndDropScreen - onDrop sur Équipe B. joueurEnCours: $joueurEnCours")
                    Log.d("DragDrop", "État actuel avant vérification -> EquipeA: $equipeA, EquipeB: $equipeB") // <-- AJOUTER CECI
                    joueurEnCours?.let { joueur ->
                        if (equipeA.contains(joueur)) {
                            Log.d("DragDrop", "Déplacement de $joueur de A vers B")
                            onEquipeAChange(equipeA - joueur) // Modifie via callback
                            onEquipeBChange(equipeB + joueur) //
//                            equipeA = equipeA - joueur
//                            equipeB = equipeB + joueur
                            joueurEnCours = null
                        }else{
                            Log.d("DragDrop", "$joueur n'est pas dans equipeA ou drag sur sa propre équipe.")

                        }
                    }
                },
                onDragStart = {
                    Log.d("DragDrop", "EquipeDragAndDropScreen - onDragStart: Définition de joueurEnCours = $it")
                    joueurEnCours = it }
            )
        }
    }
}

@Composable
fun DropEquipeCard(
    nom: String,
    joueurs: List<String>,
    couleur: Color,
    onDrop: (String) -> Unit,
    onDragStart: (String) -> Unit
) {
    val dropState = remember { mutableStateOf(false) }

    Card(
        modifier = Modifier
            .fillMaxHeight()
            .padding(4.dp)
            .background(
                if (dropState.value) couleur.copy(alpha = 0.1f) else Color.Transparent
            ),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFF5F5F5)),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(nom, style = MaterialTheme.typography.titleMedium, color = couleur)
            joueurs.forEach { joueur ->
                DraggableJoueurChip(
                    nom = joueur,
                    couleur = couleur,
                    onDragStart = { onDragStart(joueur) },
                    onDropped = { onDrop(joueur) }
                )
            }
        }
    }
}


@Composable
fun DraggableJoueurChip(
    nom: String,
    couleur: Color,
    onDragStart: () -> Unit,
    onDropped: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .shadow(2.dp, RoundedCornerShape(8.dp))
            .background(Color.White, RoundedCornerShape(8.dp))
            .pointerInput(nom) {
                detectDragGesturesAfterLongPress(
                    onDragStart = { onDragStart() },
                    onDragEnd = { onDropped() },
                    onDragCancel = {},
                    onDrag = {change, dragAmount ->
                        change.consume()
                    }
                )
            }
            .padding(8.dp)
    ) {
        Text(text = nom, color = couleur)
    }
}



@Composable
fun EquipeDragCard(
    equipeNom: String,
    joueurs: List<String>,
    couleur: Color,
    onJoueurRetire: (String) -> Unit
) {
    Card(
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
            joueurs.forEach { joueur ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { onJoueurRetire(joueur) },
                    colors = CardDefaults.cardColors(containerColor = Color.White),
                    shape = RoundedCornerShape(8.dp),
                    elevation = CardDefaults.cardElevation(2.dp)
                ) {
                    Text(
                        text = joueur,
                        modifier = Modifier.padding(8.dp),
                        color = couleur
                    )
                }
            }
        }
    }
}


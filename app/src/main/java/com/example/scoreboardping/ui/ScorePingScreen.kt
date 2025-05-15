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
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.boundsInWindow
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.zIndex
import kotlinx.coroutines.delay
import kotlin.collections.plusAssign
import kotlin.math.roundToInt
import androidx.compose.foundation.background
// import androidx.compose.foundation.clickable // Uncomment if needed elsewhere
import androidx.compose.foundation.gestures.detectDragGesturesAfterLongPress
import androidx.compose.foundation.layout.*
// import androidx.compose.foundation.rememberScrollState // Uncomment if MainPingScreen is scrollable
// import androidx.compose.foundation.verticalScroll // Uncomment if MainPingScreen is scrollable
// import androidx.compose.material.icons.Icons // Uncomment for MainPingScreen if needed
// import androidx.compose.material.icons.filled.Close // Uncomment for MainPingScreen if needed
// import androidx.compose.material.icons.filled.Star // Uncomment for MainPingScreen if needed
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.setValue
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.boundsInWindow
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
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
                        modifier = Modifier
                            .fillMaxWidth()
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
    equipeA: List<String>,
    equipeB: List<String>,
    onEquipeAChange: (List<String>) -> Unit,
    onEquipeBChange: (List<String>) -> Unit
) {
    // État pour suivre le joueur actuellement glissé
    var joueurEnCours by remember { mutableStateOf<String?>(null) }
    // État pour suivre la position globale du pointeur (doigt/souris) pendant le drag
    var positionCurseurPendantDrag by remember { mutableStateOf(Offset.Zero) }

    // États pour stocker les limites des zones de drop (Équipe A et Équipe B)
    var boundsEquipeA by remember { mutableStateOf<Rect?>(null) }
    var boundsEquipeB by remember { mutableStateOf<Rect?>(null) }

    // Fonction pour réinitialiser l'état du drag and drop
    val resetDragState = {
        Log.d("DragDropCore", "Resetting drag state. Joueur était: $joueurEnCours")
        joueurEnCours = null
        positionCurseurPendantDrag = Offset.Zero // Réinitialise la position aussi
    }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 8.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // Zone pour l'Équipe A
        Box(
            modifier = Modifier
                .weight(1f)
                .onGloballyPositioned { coordinates ->
                    boundsEquipeA = coordinates.boundsInWindow() // Met à jour les limites de la zone A
                }
        ) {
            DropEquipeCard(
                nom = "Équipe A",
                joueurs = equipeA,
                couleur = Color(0xFF1E88E5), // Bleu
                // Ce callback est déclenché lorsque le drag d'un chip se termine
                onDropSurCetteCarte = {
                    Log.d("DragDropCore", "Drop détecté sur carte A. Joueur: $joueurEnCours, Pos: $positionCurseurPendantDrag")
                    joueurEnCours?.let { joueur ->
                        // Vérifie si le joueur vient de l'équipe B ET si le drop est dans les limites de A
                        if (equipeB.contains(joueur) && boundsEquipeA?.contains(positionCurseurPendantDrag) == true) {
                            Log.i("DragDropTransfer", "$joueur transféré de B vers A")
                            onEquipeBChange(equipeB - joueur) // Retire de B
                            onEquipeAChange(equipeA + joueur)  // Ajoute à A
                        } else if (equipeA.contains(joueur) && boundsEquipeA?.contains(positionCurseurPendantDrag) == true) {
                            Log.d("DragDropCore", "$joueur relâché sur sa propre équipe (A). Pas de transfert.")
                        } else {
                            Log.w("DragDropCore", "Drop sur A non valide ou hors limites pour $joueur. Origine B: ${equipeB.contains(joueur)}")
                        }
                    }
                    resetDragState() // Toujours réinitialiser après une tentative de drop
                },
                // Callback pour quand un drag commence depuis un chip de cette carte
                onDragStartDepuisCetteCarte = { joueur, _ -> // L'offset local initial n'est pas crucial pour la logique de EquipeDragAndDropScreen
                    if (joueurEnCours == null) { // Permet de démarrer un drag seulement si aucun autre n'est en cours
                        joueurEnCours = joueur
                        Log.d("DragDropCore", "Drag commencé depuis A: $joueur")
                    }
                },
                // Callback pour suivre le mouvement du joueur pendant le drag
                onJoueurEnDrag = { _, _, currentGlobalPointerPosition -> // dragAmount n'est pas utilisé ici
                    if (joueurEnCours != null) { // Met à jour la position globale seulement si un drag est actif
                        positionCurseurPendantDrag = currentGlobalPointerPosition
                    }
                }
            )
        }

        // Zone pour l'Équipe B
        Box(
            modifier = Modifier
                .weight(1f)
                .onGloballyPositioned { coordinates ->
                    boundsEquipeB = coordinates.boundsInWindow() // Met à jour les limites de la zone B
                }
        ) {
            DropEquipeCard(
                nom = "Équipe B",
                joueurs = equipeB,
                couleur = Color(0xFF388E3C), // Vert
                onDropSurCetteCarte = {
                    Log.d("DragDropCore", "Drop détecté sur carte B. Joueur: $joueurEnCours, Pos: $positionCurseurPendantDrag")
                    joueurEnCours?.let { joueur ->
                        if (equipeA.contains(joueur) && boundsEquipeB?.contains(positionCurseurPendantDrag) == true) {
                            Log.i("DragDropTransfer", "$joueur transféré de A vers B")
                            onEquipeAChange(equipeA - joueur) // Retire de A
                            onEquipeBChange(equipeB + joueur)  // Ajoute à B
                        } else if (equipeB.contains(joueur) && boundsEquipeB?.contains(positionCurseurPendantDrag) == true) {
                            Log.d("DragDropCore", "$joueur relâché sur sa propre équipe (B). Pas de transfert.")
                        } else {
                            Log.w("DragDropCore", "Drop sur B non valide ou hors limites pour $joueur. Origine A: ${equipeA.contains(joueur)}")
                        }
                    }
                    resetDragState()
                },
                onDragStartDepuisCetteCarte = { joueur, _ ->
                    if (joueurEnCours == null) {
                        joueurEnCours = joueur
                        Log.d("DragDropCore", "Drag commencé depuis B: $joueur")
                    }
                },
                onJoueurEnDrag = { _, _, currentGlobalPointerPosition ->
                    if (joueurEnCours != null) {
                        positionCurseurPendantDrag = currentGlobalPointerPosition
                    }
                }
            )
        }
    }


    @Composable
    fun DropEquipeCard(
        nom: String,
        joueurs: List<String>,
        couleur: Color,
        onDropOnThisCard: () -> Unit, // Appelé par DraggableJoueurChip via onDroppedInternal quand un drag se termine
        onDragStartFromThisCard: (joueur: String, initialLocalOffset: Offset) -> Unit,
        onJoueurDrag: (joueur: String, dragAmount: Offset, currentGlobalPointerPosition: Offset) -> Unit
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .defaultMinSize(minHeight = 150.dp) // Assure une zone de drop même vide
                .padding(4.dp),
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(containerColor = Color(0xFFF5F5F5)), // Couleur de fond neutre pour la carte
            elevation = CardDefaults.cardElevation(4.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp), // Espace entre les chips de joueur
                horizontalAlignment = Alignment.CenterHorizontally // Centrer le titre de l'équipe
            ) {
                Text(nom, style = MaterialTheme.typography.titleMedium, color = couleur)
                if (joueurs.isEmpty()) {
                    Text(
                        "Glissez un joueur ici",
                        style = MaterialTheme.typography.bodySmall,
                        color = Color.Gray,
                        modifier = Modifier.padding(vertical = 24.dp) // Un peu d'espace vertical si vide
                    )
                } else {
                    joueurs.forEach { joueurItem -> // Renommé pour clarté
                        DraggableJoueurChip(
                            nom = joueurItem,
                            couleur = couleur, // La couleur est héritée de la carte d'équipe
                            onDragStartInternal = { localOffset ->
                                // Notifie le parent (EquipeDragAndDropScreen) que le drag de CE joueur a commencé
                                onDragStartFromThisCard(joueurItem, localOffset)
                            },
                            onDragInternal = { dragAmount, currentGlobalPointerPosition ->
                                // Notifie le parent du mouvement du joueur pendant le drag
                                onJoueurDrag(joueurItem, dragAmount, currentGlobalPointerPosition)
                            },
                            onDroppedInternal = {
                                // Notifie le parent que le drag de CE joueur est terminé.
                                // Le parent (EquipeDragAndDropScreen) décidera quoi faire en fonction
                                // de `joueurEnCours` et `positionCurseurPendantDrag`.
                                onDropOnThisCard()
                            },
                            modifier = Modifier.fillMaxWidth() // Chaque chip prend la largeur de la colonne
                        )
                    }
                }
            }
        }
    }
    @Composable
    fun DraggableJoueurChip(
        nom: String,
        couleur: Color,
        onDragStartInternal: (localOffset: Offset) -> Unit, // Offset local au chip
        onDragInternal: (dragAmount: Offset, currentGlobalPointerPosition: Offset) -> Unit,
        onDroppedInternal: () -> Unit,
        modifier: Modifier = Modifier
    ) {
        var isDragging by remember { mutableStateOf(false) }
        var chipVisualOffset by remember { mutableStateOf(Offset.Zero) } // Décalage visuel local du chip

        // Pour obtenir la position globale, on a besoin de la position du chip lui-même dans la fenêtre
        var chipPositionInParent by remember { mutableStateOf(Offset.Zero) }
        var chipGlobalPosition by remember { mutableStateOf(Offset.Zero) }


        Box(
            modifier = modifier // Applique les modificateurs externes (comme fillMaxWidth)
                .onGloballyPositioned { coordinates ->
                    // Position du chip par rapport à son parent direct
                    chipPositionInParent = coordinates.positionInParent()
                    // Position du chip dans la fenêtre (globale)
                    chipGlobalPosition = coordinates.positionInWindow()
                }
                .offset { // Applique le décalage visuel calculé
                    IntOffset(chipVisualOffset.x.roundToInt(), chipVisualOffset.y.roundToInt())
                }
                .zIndex(if (isDragging) 1f else 0f)
                .shadow(
                    elevation = if (isDragging) 8.dp else 2.dp,
                    shape = RoundedCornerShape(8.dp)
                )
                .background(
                    // Le fond du chip lui-même.
                    // On utilisera la couleur de l'équipe, mais avec un alpha si en drag.
                    color = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = if (isDragging) 0.7f else 1f),
                    shape = RoundedCornerShape(8.dp)
                )
                .pointerInput(Unit) { // Unit car l'identité est gérée par la clé du composable parent
                    detectDragGesturesAfterLongPress(
                        onDragStart = { offsetLocalToChip ->
                            Log.d("ChipDrag", "$nom - onDragStart. Local Offset: $offsetLocalToChip")
                            isDragging = true
                            chipVisualOffset = Offset.Zero // Réinitialise le décalage visuel
                            onDragStartInternal(offsetLocalToChip)
                        },
                        onDrag = { change, dragAmount ->
                            change.consume()
                            chipVisualOffset += dragAmount

                            // `change.position` est la position du pointeur *relative au chip*.
                            // Pour obtenir la position globale du pointeur:
                            // position globale du chip + position du pointeur dans le chip.
                            val currentGlobalPointerPosition = chipGlobalPosition + change.position
                            // Log.d("ChipDrag", "$nom - onDrag. Global Pointer: $currentGlobalPointerPosition, Chip Global: $chipGlobalPosition, Change Pos: ${change.position}")

                            onDragInternal(dragAmount, currentGlobalPointerPosition)
                        },
                        onDragEnd = {
                            Log.d("ChipDrag", "$nom - onDragEnd.")
                            isDragging = false
                            chipVisualOffset = Offset.Zero // Réinitialise pour la prochaine fois ou si recomposé ailleurs
                            onDroppedInternal()
                        },
                        onDragCancel = {
                            Log.d("ChipDrag", "$nom - onDragCancel.")
                            isDragging = false
                            chipVisualOffset = Offset.Zero
                            onDroppedInternal() // Traiter l'annulation comme un drop pour la réinitialisation de l'état parent
                        }
                    )
                }
                .padding(horizontal = 12.dp, vertical = 8.dp) // Padding interne pour le Text
        ) {
            Text(
                text = nom,
                // La couleur du texte est la couleur de l'équipe
                color = if (isDragging) couleur.copy(alpha = 0.7f) else couleur,
                style = MaterialTheme.typography.bodyMedium
            )
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
}


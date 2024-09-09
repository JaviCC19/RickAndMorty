package com.uvg.javier.Screen2

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.uvg.javier.dataCharacters.Character
import com.uvg.javier.dataCharacters.CharacterDb
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults


@Composable
fun CharacterRoute(
    onCharacterClick: (Int) -> Unit,
    modifier: Modifier = Modifier
){
    val charactersState by remember { mutableStateOf(CharacterDb().getAllCharacters()) }

    CharacterListScreen(onCharacterClick = onCharacterClick, characters = charactersState, modifier = modifier)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CharacterListScreen(
    modifier: Modifier = Modifier,
    characters: List<Character>,
    onCharacterClick: (Int) -> Unit) {
    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Characters") },
                    colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary // Usa el color primario del tema
                    )

            )
        }
    ) { paddingValues ->
        val characterDb = remember { CharacterDb() }
        val characters = characterDb.getAllCharacters()

        LazyColumn(contentPadding = paddingValues) {
            items(characters.size) { character ->
                CharacterListItem(character = characters[character], onClick = onCharacterClick)
                }
            }
        }
    }


@Composable
fun CharacterListItem(character: Character, onClick: (Int) -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick(character.id) }
            .padding(8.dp)
    ) {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(character.image)
                .crossfade(true)
                .build(),
            contentDescription = null,
            modifier = Modifier
                .background(color = MaterialTheme.colorScheme.secondary, shape = CircleShape)
                .size(64.dp)
                .clip(CircleShape)
        )
        Spacer(modifier = Modifier.width(8.dp))
        Column {
            Text(text = character.name, style = MaterialTheme.typography.bodyLarge)
            Text(text = character.species, style = MaterialTheme.typography.bodySmall)
        }
    }
}
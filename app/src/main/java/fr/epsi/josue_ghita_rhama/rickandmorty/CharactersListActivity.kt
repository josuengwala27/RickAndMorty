package fr.epsi.josue_ghita_rhama.rickandmorty

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import fr.epsi.josue_ghita_rhama.rickandmorty.ui.theme.RickAndMortyTheme


import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import coil.compose.rememberImagePainter

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.ui.graphics.Color

class CharactersListActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RickAndMortyTheme {
                //Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                Surface (modifier = Modifier.fillMaxSize()) {
                    CharacterListScreen()
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CharacterListScreen() {
    val characters = listOf(
        Pair("Rick Sanchez", "https://rickandmortyapi.com/api/character/avatar/1.jpeg"),
        Pair("Morty Smith", "https://rickandmortyapi.com/api/character/avatar/2.jpeg"),
        Pair("Summer Smith", "https://rickandmortyapi.com/api/character/avatar/3.jpeg"),
        Pair("Beth Smith", "https://rickandmortyapi.com/api/character/avatar/4.jpeg"),
        Pair("Jerry Smith", "https://rickandmortyapi.com/api/character/avatar/5.jpeg")
    )

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color(0xFFF8F9FA)
    ) {
        Column {
            TopAppBar(
                title = { Text(text = "Rick and Morty") },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFF6200EE),
                    titleContentColor = Color.White
                )
            )

            LazyColumn(
                modifier = Modifier.fillMaxSize()
            ) {
                items(characters) { character ->
                    CharacterListItem(characterName = character.first, characterImageUrl = character.second)
                }
            }
        }
    }
}

@Composable
fun CharacterListItem(characterName: String, characterImageUrl: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Image(
            painter = rememberAsyncImagePainter(characterImageUrl),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(72.dp)
                .clip(CircleShape)
        )

        Spacer(modifier = Modifier.width(16.dp))

        Text(
            text = characterName,
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.align(Alignment.CenterVertically)
        )
    }
}


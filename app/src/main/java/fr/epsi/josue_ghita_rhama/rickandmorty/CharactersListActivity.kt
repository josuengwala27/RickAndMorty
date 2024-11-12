package fr.epsi.josue_ghita_rhama.rickandmorty

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresExtension
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import fr.epsi.josue_ghita_rhama.rickandmorty.ui.theme.RickAndMortyTheme
import fr.epsi.josue_ghita_rhama.rickandmorty.viewmodel.CharactersListViewModel
import androidx.lifecycle.viewmodel.compose.viewModel

class CharactersListActivity : ComponentActivity() {
    @RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RickAndMortyTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    val viewModel: CharactersListViewModel = viewModel()
                    val characters by viewModel.characters.collectAsState()
                    val errorMessage by viewModel.errorMessage.collectAsState()

                    CharacterListScreen(characters = characters, errorMessage = errorMessage)
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CharacterListScreen(characters: List<Character>, errorMessage: String?) {
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

            if (errorMessage != null) {
                Text(
                    text = errorMessage,
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier.padding(16.dp),
                    color = Color.Red
                )
            } else if (characters.isNotEmpty()) {
                LazyColumn(
                    modifier = Modifier.fillMaxSize()
                ) {
                    items(characters) { character ->
                        CharacterListItem(character = character)
                    }
                }
            } else {
                CircularProgressIndicator(modifier = Modifier.padding(16.dp).align(Alignment.CenterHorizontally))
            }
        }
    }
}

@Composable
fun CharacterListItem(character: Character) {
    val context = LocalContext.current
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .clickable {
                val intent = Intent(context, CharacterDetailsActivity::class.java).apply {
                    putExtra("name", character.name)
                    putExtra("status", character.status)
                    putExtra("species", character.species)
                    putExtra("type", character.type)
                    putExtra("gender", character.gender)
                    putExtra("origin", character.origin.name)
                    putExtra("location", character.location.name)
                    putExtra("imageUrl", character.imageUrl)
                }
                context.startActivity(intent)
            }
    ) {
        Image(
            painter = rememberAsyncImagePainter(character.imageUrl),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(72.dp)
                .clip(CircleShape)
        )

        Spacer(modifier = Modifier.width(16.dp))

        Text(
            text = character.name,
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.align(Alignment.CenterVertically)
        )
    }
}

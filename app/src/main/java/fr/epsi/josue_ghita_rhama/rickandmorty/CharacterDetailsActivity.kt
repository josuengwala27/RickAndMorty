package fr.epsi.josue_ghita_rhama.rickandmorty

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter

class CharacterDetailsActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val name = intent.getStringExtra("name") ?: "Unknown"
            val status = intent.getStringExtra("status") ?: "Unknown"
            val species = intent.getStringExtra("species") ?: "Unknown"
            val type = intent.getStringExtra("type") ?: "Unknown"
            val gender = intent.getStringExtra("gender") ?: "Unknown"
            val origin = intent.getStringExtra("origin") ?: "Unknown"
            val location = intent.getStringExtra("location") ?: "Unknown"
            val imageUrl = intent.getStringExtra("imageUrl") ?: ""

            CharacterDetailsScreen(
                name = name,
                status = status,
                species = species,
                type = type,
                gender = gender,
                origin = origin,
                location = location,
                imageUrl = imageUrl
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CharacterDetailsScreen(
    name: String,
    status: String,
    species: String,
    type: String,
    gender: String,
    origin: String,
    location: String,
    imageUrl: String
) {
    val context = LocalContext.current

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = name) },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFF6200EE),
                    titleContentColor = Color.White
                ),
                navigationIcon = {
                    IconButton(onClick = { (context as? ComponentActivity)?.finish() }) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back", tint = Color.White)
                    }
                }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = rememberAsyncImagePainter(imageUrl),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(128.dp)
                    .clip(CircleShape)
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(text = "Status: $status", style = MaterialTheme.typography.bodyLarge)
            Text(text = "Species: $species", style = MaterialTheme.typography.bodyLarge)
            Text(text = "Type: $type", style = MaterialTheme.typography.bodyLarge)
            Text(text = "Gender: $gender", style = MaterialTheme.typography.bodyLarge)
            Text(text = "Origin: $origin", style = MaterialTheme.typography.bodyLarge)
            Text(text = "Location: $location", style = MaterialTheme.typography.bodyLarge)
        }
    }
}

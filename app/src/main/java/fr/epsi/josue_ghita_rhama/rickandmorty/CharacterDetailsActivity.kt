package fr.epsi.josue_ghita_rhama.rickandmorty

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter

class CharacterDetailsActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val name = intent.getStringExtra("name") ?: " "
            val status = intent.getStringExtra("status") ?: " "
            val species = intent.getStringExtra("species") ?: " "
            val type = intent.getStringExtra("type") ?: " "
            val gender = intent.getStringExtra("gender") ?: " "
            val origin = intent.getStringExtra("origin") ?: " "
            val location = intent.getStringExtra("location") ?: ""
            val imageUrl = intent.getStringExtra("imageUrl") ?: ""

            CharacterDetailsScreen(
                name = name,
                status = status,
                species = species,
                type = type,
                gender = gender,
                origin = origin,
                location = location,
                imageUrl = imageUrl,
                onBackPressed = { finish() }
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
    imageUrl: String,
    onBackPressed: () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = name) },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFF1B5E20),
                    titleContentColor = Color.White
                ),
                navigationIcon = {
                    IconButton(onClick = onBackPressed) {
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

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = rememberAsyncImagePainter(imageUrl),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(128.dp)
                        .clip(CircleShape)
                        .border(2.dp, Color.LightGray, CircleShape)
                )
            }
            Spacer(modifier = Modifier.height(16.dp))

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color(0xFFE8F5E9), shape = RoundedCornerShape(8.dp))
                    .padding(16.dp)
            ) {
                CharacterInfoRow(label = "Status", value = status)
                CharacterInfoRow(label = "Species", value = species)
                CharacterInfoRow(label = "Type", value = type.ifBlank { " " })
                CharacterInfoRow(label = "Gender", value = gender)
                CharacterInfoRow(label = "Origin", value = origin)
                CharacterInfoRow(label = "Location", value = location)
            }
        }
    }
}

@Composable
fun CharacterInfoRow(label: String, value: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        horizontalArrangement = Arrangement.Start
    ) {
        Text(text = "$label:", style = MaterialTheme.typography.bodyLarge, color = Color.Gray)
        Spacer(modifier = Modifier.width(8.dp))
        Text(text = value, style = MaterialTheme.typography.bodyLarge)
    }
}

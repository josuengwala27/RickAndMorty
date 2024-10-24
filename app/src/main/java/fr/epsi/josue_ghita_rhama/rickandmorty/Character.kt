package fr.epsi.josue_ghita_rhama.rickandmorty

import com.google.gson.annotations.SerializedName

data class Character(
    val id: Int,
    val name: String,
    val status: String,
    val species: String,
    val type: String,
    val gender: String,
    val origin: Origin,
    val location: Location,
    @SerializedName("image") val imageUrl: String
)

data class Origin(
    val name: String,
    val url: String
)

data class Location(
    val name: String,
    val url: String
)

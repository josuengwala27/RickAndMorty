package fr.epsi.josue_ghita_rhama.rickandmorty.viewmodel

import android.net.http.HttpException
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresExtension
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import fr.epsi.josue_ghita_rhama.rickandmorty.network.RickAndMortyApiService
import fr.epsi.josue_ghita_rhama.rickandmorty.Character
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.io.IOException

@RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
class CharactersListViewModel : ViewModel() {

    private val _characters = MutableStateFlow<List<Character>>(emptyList())
    val characters: StateFlow<List<Character>> = _characters

    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> = _errorMessage

    private var currentPage = 1
    private var isLoading = false
    private var isLastPage = false

    init {
        fetchCharacters()
    }

    @RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
    private fun fetchCharacters(page: Int = currentPage) {
        if (isLoading || isLastPage) return

        isLoading = true
        viewModelScope.launch {
            try {
                val service = RickAndMortyApiService.create()
                val response = service.getCharacters(page)
                Log.d("API Response", "Personnages récupérés : ${response.results}")

                _characters.value = _characters.value + response.results
                _errorMessage.value = null

                if (response.info.next == null) {
                    isLastPage = true
                } else {
                    currentPage++
                }
            } catch (e: HttpException) {
                Log.e("API Error", "Erreur HTTP", e)
                _errorMessage.value = "Erreur lors de la récupération des personnages : ${e.message}"
            } catch (e: IOException) {
                Log.e("API Error", "Erreur réseau : ${e.message}", e)
                _errorMessage.value = "Pas de connexion Internet."
            } catch (e: Exception) {
                Log.e("API Error", "Erreur inconnue : ${e.message}", e)
                _errorMessage.value = "Erreur inconnue : ${e.message}"
            } finally {
                isLoading = false
            }
        }
    }

    fun loadNextPage() {
        fetchCharacters()
    }
}

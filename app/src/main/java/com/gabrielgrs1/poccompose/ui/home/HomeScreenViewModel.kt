package com.gabrielgrs1.poccompose.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gabrielgrs1.poccompose.dao.ProductDao
import com.gabrielgrs1.poccompose.model.Product
import com.gabrielgrs1.poccompose.sampledata.sampleCandies
import com.gabrielgrs1.poccompose.sampledata.sampleDrinks
import com.gabrielgrs1.poccompose.sampledata.sampleProducts
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class HomeScreenViewModel : ViewModel() {

    private val dao = ProductDao()

    val uiState get() = _uiState.asStateFlow()
    private val _uiState: MutableStateFlow<HomeUiState> = MutableStateFlow(
        HomeUiState()
    )

    init {
        _uiState.update { currentState ->
            currentState.copy(
                onSearchChange = { searchText ->
                    _uiState.value = _uiState.value.copy(
                        searchText = searchText,
                        searchedProducts = searchedProducts(searchText)
                    )
                })
        }
        viewModelScope.launch {
            dao.products().collect { products ->
                _uiState.value = _uiState.value.copy(
                    sections = mapOf(
                        "Todos produtos" to products,
                        "Promoções" to sampleDrinks + sampleCandies,
                        "Doces" to sampleCandies,
                        "Bebidas" to sampleDrinks,
                    ), searchedProducts = searchedProducts(_uiState.value.searchText)
                )
            }
        }
    }

    private fun containsInNameOrDescription(text: String) = { product: Product ->
        product.name.contains(
            text, ignoreCase = true
        ) || product.description?.contains(
            text, ignoreCase = true
        ) ?: false
    }

    private fun searchedProducts(text: String) = if (text.isNotBlank()) {
        sampleProducts.filter(containsInNameOrDescription(text)) + dao.products().value.filter(
            containsInNameOrDescription(text)
        )
    } else emptyList()
}
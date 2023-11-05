package com.gabrielgrs1.poccompose.ui.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.gabrielgrs1.poccompose.dao.ProductDao
import com.gabrielgrs1.poccompose.model.Product
import com.gabrielgrs1.poccompose.sampledata.sampleCandies
import com.gabrielgrs1.poccompose.sampledata.sampleDrinks
import com.gabrielgrs1.poccompose.sampledata.sampleProducts

class HomeScreenViewModel : ViewModel() {

    private val dao = ProductDao()

    var uiState: HomeUiState by mutableStateOf(
        HomeUiState(
            onSearchChange = { searchText ->
                uiState = uiState.copy(
                    searchText = searchText,
                    searchedProducts = searchedProducts(searchText)
                )
            },
            sections = mapOf(
                "Todos produtos" to dao.products(),
                "Promoções" to sampleDrinks + sampleCandies,
                "Doces" to sampleCandies,
                "Bebidas" to sampleDrinks,
            )
        )
    )
        private set

    private fun containsInNameOrDescription(text: String) = { product: Product ->
        product.name.contains(
            text, ignoreCase = true
        ) || product.description?.contains(
            text, ignoreCase = true
        ) ?: false
    }

    private fun searchedProducts(text: String) =
        if (text.isNotBlank()) {
            sampleProducts.filter(containsInNameOrDescription(text)) +
                    dao.products().filter(containsInNameOrDescription(text))
        } else emptyList()

//    remember(products, text) {
//        HomeUiState(
//            sections = sections,
//            searchedProducts = searchedProducts,
//            searchText = text,
//            onSearchChange = {
//                text = it
//            }
//        )
//    }
}
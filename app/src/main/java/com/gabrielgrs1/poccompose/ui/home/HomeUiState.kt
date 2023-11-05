package com.gabrielgrs1.poccompose.ui.home

import com.gabrielgrs1.poccompose.model.Product

data class HomeUiState(
    val sections: Map<String, List<Product>> = emptyMap(),
    val searchedProducts: List<Product> = emptyList(),
    val searchText: String = "",
    val onSearchChange: (String) -> Unit = {}
) {
    fun isShowSections() = searchText.isBlank()
}
package com.gabrielgrs1.poccompose.dao

import androidx.compose.runtime.mutableStateListOf
import com.gabrielgrs1.poccompose.model.Product
import com.gabrielgrs1.poccompose.sampledata.sampleProducts
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.toList

class ProductDao {

    companion object {
        private val products = MutableStateFlow<List<Product>>(emptyList())
    }

    fun products() = products.asStateFlow()
    fun save(product: Product) {
        products.value = products.value + product
    }
}
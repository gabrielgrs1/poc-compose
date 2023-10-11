package com.gabrielgrs1.poccompose.dao

import androidx.compose.runtime.mutableStateListOf
import com.gabrielgrs1.poccompose.model.Product
import com.gabrielgrs1.poccompose.sampledata.sampleProducts

class ProductDao {

    companion object {
        private val products = mutableStateListOf<Product>()
    }

    fun products() = products.toList()
    fun save(product: Product) {
        products.add(product)
    }
}
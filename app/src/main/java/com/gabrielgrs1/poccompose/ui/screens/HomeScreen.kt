package com.gabrielgrs1.poccompose.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.gabrielgrs1.poccompose.model.Product
import com.gabrielgrs1.poccompose.sampledata.sampleProducts
import com.gabrielgrs1.poccompose.sampledata.sampleSections
import com.gabrielgrs1.poccompose.ui.components.CardProductItem
import com.gabrielgrs1.poccompose.ui.components.ProductSection
import com.gabrielgrs1.poccompose.ui.components.SearchTextField
import com.gabrielgrs1.poccompose.ui.theme.POCComposeTheme

@Composable
fun HomeScreen(
    sections: Map<String, List<Product>>, searchText: String = ""
) {
    Column {
        var text by remember { mutableStateOf(searchText) }

        SearchTextField(
            searchText = text,
            onSearchTextChange = {
                text = it
            }
        )
        val searchProducts = remember(text) {
            if (text.isNotBlank()) {
                sampleProducts.filter { product ->
                    product.name.contains(
                        text,
                        ignoreCase = true
                    ) || product.description?.contains(
                        text,
                        ignoreCase = true
                    ) ?: false
                }
            } else emptyList()
        }


        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            contentPadding = PaddingValues(bottom = 16.dp)
        ) {
            if (text.isBlank()) {
                for (section in sections) {
                    val title = section.key
                    val products = section.value
                    item {
                        ProductSection(
                            title = title, productList = products
                        )
                    }
                }
            } else {
                items(searchProducts) { p ->
                    CardProductItem(
                        product = p, modifier = Modifier.padding(vertical = 16.dp)
                    )
                }
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun HomeScreenPreview() {
    POCComposeTheme {
        Surface { HomeScreen(sampleSections) }
    }
}

@Preview(showSystemUi = true)
@Composable
fun HomeScreenWithSearchTextPreview() {
    POCComposeTheme {
        Surface { HomeScreen(sampleSections, searchText = "a") }
    }
}
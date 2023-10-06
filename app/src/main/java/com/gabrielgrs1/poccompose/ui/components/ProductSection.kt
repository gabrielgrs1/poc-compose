package com.gabrielgrs1.poccompose.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.gabrielgrs1.poccompose.R
import com.gabrielgrs1.poccompose.model.Product
import com.gabrielgrs1.poccompose.sampledata.sampleProducts
import com.gabrielgrs1.poccompose.ui.theme.POCComposeTheme


@Composable
fun ProductSection(title: String, productList: List<Product>, modifier: Modifier = Modifier) {
    Column(modifier = modifier) {
        Text(
            modifier = Modifier.padding(start = 16.dp, end = 16.dp),
            text = title,
            style = TextStyle(
                fontSize = 20.sp,
                fontFamily = FontFamily(Font(R.font.inter_regular)),
                fontWeight = FontWeight(400),
            )
        )
        LazyRow(
            modifier = Modifier
                .padding(top = 8.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            contentPadding = PaddingValues(horizontal = 16.dp)
        ) {
            items(productList) { product ->
                ProductItem(product = product)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun ProductSectionPreview() {
    POCComposeTheme {
        Surface {
            ProductSection(title = "Promoções", productList = sampleProducts)
        }
    }
}

package com.gabrielgrs1.poccompose.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.datasource.LoremIpsum
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.gabrielgrs1.poccompose.R
import com.gabrielgrs1.poccompose.extensions.toBrazilianCurrency
import com.gabrielgrs1.poccompose.model.Product
import com.gabrielgrs1.poccompose.ui.theme.POCComposeTheme
import java.math.BigDecimal

@Composable

fun ProductItem(product: Product, modifier: Modifier = Modifier) {
    val imageSize = 100.dp
    Surface(
        modifier = modifier,
        shape = RoundedCornerShape(15.dp),
        shadowElevation = 8.dp,
    ) {
        Column(
            modifier = Modifier
                .background(color = Color(0xFFFFFFFF))
                .width(200.dp)
                .heightIn(250.dp, 300.dp)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp)
                    .background(
                        brush = Brush.horizontalGradient(
                            colors = listOf(
                                MaterialTheme.colorScheme.primary,
                                MaterialTheme.colorScheme.secondary
                            )
                        )
                    )
            ) {
                AsyncImage(
                    model = product.imageUrl,
                    contentDescription = "Imagem do produto",
                    modifier = Modifier
                        .size(imageSize)
                        .offset(y = imageSize / 2)
                        .clip(shape = CircleShape)
                        .align(alignment = Alignment.BottomCenter),
                    contentScale = ContentScale.Crop,
                    placeholder = painterResource(id = R.drawable.ic_launcher_background)
                )
            }
            Spacer(modifier = Modifier.height(imageSize / 2))
            Column(modifier = Modifier.padding(16.dp)) {
                Text(
                    text = product.name, style = TextStyle(
                        fontSize = 18.sp,
                        fontFamily = FontFamily(Font(R.font.inter_bold)),
                        fontWeight = FontWeight(700),
                        color = Color(0xFF000000)
                    ), maxLines = 2, overflow = TextOverflow.Ellipsis
                )
                Text(
                    text = product.price.toBrazilianCurrency(), style = TextStyle(
                        fontSize = 14.sp,
                        fontFamily = FontFamily(Font(R.font.inter_regular)),
                        fontWeight = FontWeight(400),
                        color = Color(0xFF000000),
                    ), modifier = Modifier.padding(top = 8.dp)
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun ProductItemPreview() {
    POCComposeTheme {
        Surface {
            ProductItem(
                Product(
                    name = LoremIpsum(50).values.first(), price = BigDecimal("14.99")
                )
            )
        }
    }
}
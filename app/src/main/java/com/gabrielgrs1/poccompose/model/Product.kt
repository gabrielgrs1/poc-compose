package com.gabrielgrs1.poccompose.model

import android.os.Parcelable
import java.math.BigDecimal

data class Product(
    val name: String,
    val price: BigDecimal,
    val imageUrl: String? = null,
    val description: String? = null
)

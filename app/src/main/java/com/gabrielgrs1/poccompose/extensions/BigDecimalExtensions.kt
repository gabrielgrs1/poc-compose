package com.gabrielgrs1.poccompose.extensions

import java.math.BigDecimal
import java.text.NumberFormat
import java.util.Locale

fun BigDecimal.toBrazilianCurrency(): String =
    NumberFormat.getCurrencyInstance(Locale("pt", "br")).format(this)
package com.gabrielgrs1.poccompose.ui.activities

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.gabrielgrs1.poccompose.dao.ProductDao
import com.gabrielgrs1.poccompose.ui.productform.ProductFormScreen
import com.gabrielgrs1.poccompose.ui.theme.POCComposeTheme

class ProductFormActivity : ComponentActivity() {

    private val dao = ProductDao()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            POCComposeTheme {
                ProductFormScreen(onSaveClick = { product ->
                    dao.save(product)
                    finish()
                })
            }
        }
    }
}


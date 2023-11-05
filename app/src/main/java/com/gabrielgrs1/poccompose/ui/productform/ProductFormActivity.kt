package com.gabrielgrs1.poccompose.ui.productform

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material3.Surface
import com.gabrielgrs1.poccompose.dao.ProductDao
import com.gabrielgrs1.poccompose.ui.theme.POCComposeTheme

class ProductFormActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            POCComposeTheme {
                Surface {
                    val viewModel by viewModels<ProductFormScreenViewModel>()
                    ProductFormScreen(
                        viewModel = viewModel,
                        onSaveClick = {
                            finish()
                        }
                    )
                }
            }
        }
    }
}


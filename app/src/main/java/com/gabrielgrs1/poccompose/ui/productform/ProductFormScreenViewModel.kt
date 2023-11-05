package com.gabrielgrs1.poccompose.ui.productform

import androidx.lifecycle.ViewModel
import com.gabrielgrs1.poccompose.dao.ProductDao
import com.gabrielgrs1.poccompose.model.Product
import com.gabrielgrs1.poccompose.sampledata.sampleProducts
import java.math.BigDecimal
import java.text.DecimalFormat
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class ProductFormScreenViewModel : ViewModel() {

    private val dao = ProductDao()

    val uiState get() = _uiState.asStateFlow()
    private val _uiState: MutableStateFlow<ProductFormUiState> = MutableStateFlow(
        ProductFormUiState()
    )

    private val formatter = DecimalFormat("#,##")

    init {
        _uiState.update { currentState ->
            currentState.copy(
                onNameChange = { name ->
                    _uiState.value = _uiState.value.copy(
                        name = name
                    )
                },
                onUrlChange = { imageUrl ->
                    _uiState.value = _uiState.value.copy(
                        imageUrl = imageUrl
                    )
                },
                onDescriptionChange = { description ->
                    _uiState.value = _uiState.value.copy(
                        description = description
                    )
                },
                onPriceChange = {
                    val price = try {
                        formatter.format(BigDecimal(it))
                    } catch (e: IllegalArgumentException) {
                        if (it.isEmpty()) {
                            it
                        } else {
                            null
                        }
                    }
                    _uiState.value = _uiState.value.copy(
                        price = price.orEmpty()
                    )
                },
            )
        }
    }

    fun save() {
       _uiState.value.run {
           val convertedPrice = try {
               BigDecimal(price)
           } catch (e: NumberFormatException) {
               BigDecimal.ZERO
           }

           val product = Product(
               name = name,
               price = convertedPrice,
               imageUrl = imageUrl,
               description = description
           )

           dao.save(product)
       }
    }
}
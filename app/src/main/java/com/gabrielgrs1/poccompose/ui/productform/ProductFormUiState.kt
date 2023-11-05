package com.gabrielgrs1.poccompose.ui.productform

data class ProductFormUiState(
    val name: String = "",
    val imageUrl: String = "",
    val price: String = "",
    val description: String = "",
    val onUrlChange: (String) -> Unit = {},
    val onNameChange: (String) -> Unit = {},
    val onDescriptionChange: (String) -> Unit = {},
    val onPriceChange: (String) -> Unit = {},
) {
    val isShowPreview: Boolean get() = imageUrl.isNotBlank()

}
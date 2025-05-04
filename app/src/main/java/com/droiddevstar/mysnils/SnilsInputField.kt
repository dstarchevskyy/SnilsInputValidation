package com.droiddevstar.mysnils

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp

@Composable
fun SnilsInputField(
    state: State<SnilsUiState>,
    onValueChange: (TextFieldValue) -> Unit,
    modifier: Modifier = Modifier.padding(
        horizontal = 0.dp,
        vertical = 450.dp
    ),
    label: @Composable (() -> Unit)? = null
) {
    TextField(
        value = state.value.textFieldValue,
        onValueChange = { newValue: TextFieldValue ->
            onValueChange(newValue)
        },
        modifier = modifier,
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Number
        ),
        visualTransformation = MyVisualTransformation,
        label = label
    )
}
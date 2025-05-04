package com.droiddevstar.mysnils

import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.input.TextFieldValue
import com.droiddevstar.mysnils.snils.validator.EmptyStringValidationError
import com.droiddevstar.mysnils.snils.validator.ValidationResult

data class SnilsUiState(
    val textFieldValue: TextFieldValue = TextFieldValue(
        text = "",
        selection = TextRange.Zero
    ),
    val validationResult: ValidationResult = ValidationResult.Error(
        data = "",
        errors = listOf(
            EmptyStringValidationError
        )
    )
)
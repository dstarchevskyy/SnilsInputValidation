package com.droiddevstar.mysnils

import androidx.annotation.ColorRes
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.droiddevstar.mysnils.snils.validator.ValidationError
import com.droiddevstar.mysnils.snils.validator.ValidationResult

@Composable
fun ValidationResultText(uiState: State<SnilsUiState>) = with(uiState.value)  {
    @StringRes
    val stringRes: Int? = if (validationResult.isValid()) {
        R.string.valid_input
    } else {
        val errors: List<ValidationError>? = (validationResult as? ValidationResult.Error)?.errors
        errors?.firstOrNull()?.getErrorMessageStringResId()
    }

    val resolvedErrorMessage: String = stringResource(id = stringRes ?: return)

    @ColorRes
    val textColor: Int = if (validationResult.isValid()) {
        R.color.valid_green
    } else {
        R.color.invalid_red
    }

    Text(
        text = resolvedErrorMessage,
        color = colorResource(textColor),
        modifier = Modifier.padding(
            paddingValues = PaddingValues(
                horizontal = 30.dp,
                vertical = 16.dp
            )
        )
    )
}
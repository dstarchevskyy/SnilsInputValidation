package com.droiddevstar.mysnils

import androidx.compose.ui.text.input.TextFieldValue
import androidx.lifecycle.ViewModel
import com.droiddevstar.mysnils.snils.filter.ComplexFilter
import com.droiddevstar.mysnils.snils.filter.FilterMaxLength
import com.droiddevstar.mysnils.snils.filter.FilterOnlyDigits
import com.droiddevstar.mysnils.snils.filter.filter
import com.droiddevstar.mysnils.snils.validator.ComplexValidator
import com.droiddevstar.mysnils.snils.validator.EmptyStringValidator
import com.droiddevstar.mysnils.snils.validator.ExactLengthValidator
import com.droiddevstar.mysnils.snils.validator.OnlyDigitsValidator
import com.droiddevstar.mysnils.snils.validator.SnilsCheckSumValidator
import com.droiddevstar.mysnils.snils.validator.validator
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class MainViewModel : ViewModel() {
    private val _uiState: MutableStateFlow<SnilsUiState> = MutableStateFlow(SnilsUiState())
    val uiState: StateFlow<SnilsUiState> = _uiState.asStateFlow()

    private val snilsFilter: ComplexFilter = filter {
        +FilterOnlyDigits
        +FilterMaxLength(11)
    }

    private val snilsValidator: ComplexValidator = validator {
        +EmptyStringValidator
        +ExactLengthValidator(11)
        +OnlyDigitsValidator
        +SnilsCheckSumValidator
    }

    fun onValueChange(textFieldValue: TextFieldValue)  {
        val filteredText: String = snilsFilter.filter(textFieldValue.text)
        val validationResult = snilsValidator.validate(filteredText)

        _uiState.value = SnilsUiState(
            textFieldValue = textFieldValue,
            validationResult = validationResult,
        )
    }
}

// test 3
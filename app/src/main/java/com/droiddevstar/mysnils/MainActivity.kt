package com.droiddevstar.mysnils

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.droiddevstar.mysnils.ui.theme.MySnilsTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()
        setContent {
            MySnilsTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }

    @Composable
    fun Greeting(
        modifier: Modifier = Modifier,
        viewModel: MainViewModel = MainViewModel()
    ) {
        val uiState: State<SnilsUiState> = viewModel.uiState.collectAsState()

        Column {
            SnilsInputField(
                state = uiState,
                onValueChange = { textFieldValue: TextFieldValue ->
                    if (textFieldValue.text.length <= 11) {
                        viewModel.onValueChange(textFieldValue)
                    }
                },
                modifier = modifier.padding(30.dp),
                label = {
                    Text("SNILS")
                }
            )

            ValidationResultText(
                uiState = uiState
            )
        }
    }

    @Preview(showBackground = true)
    @Composable
    fun GreetingPreview() {
        MySnilsTheme {
            Greeting()
        }
    }
}



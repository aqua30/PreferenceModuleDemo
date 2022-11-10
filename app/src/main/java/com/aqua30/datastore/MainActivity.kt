package com.aqua30.datastore

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.graphics.Color.Companion.Blue
import androidx.compose.ui.graphics.Color.Companion.Green
import androidx.compose.ui.graphics.Color.Companion.Yellow
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.lifecycleScope
import com.aqua30.datastore.ui.theme.DataStoreModuleSampleTheme
import com.aqua30.local_preference.UserPreference
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@OptIn(ExperimentalLifecycleComposeApi::class)
@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DataStoreModuleSampleTheme {
                val userName: String by viewModel.userName.collectAsStateWithLifecycle()
                Log.e("Tag", "username: $userName")
                HomeScreen(
                    Modifier.fillMaxSize().background(Color.Cyan),
                    userName
                ) { name ->
                    lifecycleScope.launch {
                        viewModel.saveUserName(name)
                    }
                }
            }
        }
    }
}

@Composable
fun HomeScreen(
    modifier: Modifier,
    userName: String,
    onSubmit: (String) -> Unit
) {
    val editText = remember {
        mutableStateOf("")
    }

    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            "Saved text from DataStore",
            modifier = Modifier.padding(start = 16.dp),
            fontSize = 16.sp,
            color = Black
        )
        Text(
            userName,
            modifier = Modifier.padding(16.dp),
            fontSize = 20.sp,
        )
        Spacer(modifier = Modifier.height(50.dp))
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth().padding(8.dp),
            value = editText.value,
            onValueChange = {
                editText.value = it
                onSubmit(editText.value)
            },
            label = {
                Text("Enter some text to save", color = Black)
            },
            textStyle = TextStyle(
                color = Color.Black,
                fontSize = 26.sp,
                textAlign = TextAlign.Start,
            ),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = Green,
                unfocusedBorderColor = Blue
            )
        )

    }
}
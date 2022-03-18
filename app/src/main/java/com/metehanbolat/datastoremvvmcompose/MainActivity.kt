package com.metehanbolat.datastoremvvmcompose

import android.app.Application
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.metehanbolat.datastoremvvmcompose.repository.DataStorePreferenceRepository
import com.metehanbolat.datastoremvvmcompose.ui.theme.DataStoreMVVMComposeTheme
import com.metehanbolat.datastoremvvmcompose.ui.theme.Purple500
import com.metehanbolat.datastoremvvmcompose.viewmodel.DataStoreViewModel
import com.metehanbolat.datastoremvvmcompose.viewmodel.DataStoreViewModelFactory
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DataStoreMVVMComposeTheme {
                DataStoreInput(application)
            }
        }
    }
}

@Composable
fun DataStoreInput(
    application: Application,
    viewModel: DataStoreViewModel = androidx.lifecycle.viewmodel.compose.viewModel(
        factory = DataStoreViewModelFactory(DataStorePreferenceRepository(), application)
    )
) {
    val context = LocalContext.current
    val textState = remember { mutableStateOf(TextFieldValue()) }
    DataStorePreferenceRepository.getInstance()
    val getUserName = viewModel.userName.observeAsState().value
    val scope = rememberCoroutineScope()

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(Purple500)
                .height(50.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "DataStore Preference",
                color = Color.White,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
        }
        Column(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(30.dp))

            OutlinedTextField(
                value = textState.value,
                onValueChange = { textState.value = it },
                label = { Text(text = "Enter User Name", fontSize = 15.sp) },
                modifier = Modifier.fillMaxWidth(0.7f),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Text
                )
            )

            Spacer(modifier = Modifier.height(20.dp))

            Button(
                onClick = {
                    scope.launch {
                        viewModel.saveUserName(textState.value.text, context)
                    }
                },
                shape = RoundedCornerShape(8.dp),
                elevation = ButtonDefaults.elevation(
                    defaultElevation = 6.dp,
                    pressedElevation = 8.dp,
                    disabledElevation = 0.dp
                ),
                modifier = Modifier.padding(5.dp)
            ) {
                Text(
                    text = "Submit",
                    modifier = Modifier.padding(6.dp)
                )
            }

            Spacer(modifier = Modifier.height(30.dp))

            Text(
                text = getUserName!!,
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                fontSize = 20.sp
            )
        }
    }
}

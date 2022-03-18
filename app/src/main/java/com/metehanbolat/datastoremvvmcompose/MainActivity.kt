package com.metehanbolat.datastoremvvmcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.metehanbolat.datastoremvvmcompose.ui.theme.DataStoreMVVMComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DataStoreMVVMComposeTheme {

            }
        }
    }
}
package com.example.askly_prototype

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.askly_prototype.ui.theme.AsklyprototypeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AsklyprototypeTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        version = "0.1",
                        date = "3/7/25",
                        modifier = Modifier.padding(innerPadding)
                        //Esto es un comentario de prueba
                    )
                }
            }
        }
    }
}

@Composable
fun Greeting(version: String, date: String, modifier: Modifier = Modifier) {
    Text(
        text = "Askly first test build. Version $version! Dated $date",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    AsklyprototypeTheme {
        Greeting("0.1","3/7/25")
    }
}
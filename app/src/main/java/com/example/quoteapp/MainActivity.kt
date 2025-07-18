package com.example.quoteapp

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.quoteapp.ui.theme.QuoteAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            QuoteAppTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    QuoteScreen()
                }
            }
        }
    }
}

@Composable
fun QuoteScreen() {
    val context = LocalContext.current
    val quotes = listOf(
        "Believe in yourself.",
        "Push yourself, because no one else will.",
        "Dream it. Wish it. Do it.",
        "Don’t watch the clock; do what it does. Keep going.",
        "Do it now. Sometimes ‘later’ becomes ‘never’."
    )

    var currentQuote by remember { mutableStateOf(quotes.random()) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = currentQuote,
            style = MaterialTheme.typography.headlineSmall,
            modifier = Modifier.padding(20.dp)
        )

        Button(onClick = { currentQuote = quotes.random() }) {
            Text("New Quote")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = {
            val shareIntent = Intent().apply {
                action = Intent.ACTION_SEND
                putExtra(Intent.EXTRA_TEXT, currentQuote)
                type = "text/plain"
            }
            context.startActivity(Intent.createChooser(shareIntent, "Share Quote via"))
        }) {
            Text("Share")
        }
    }
}

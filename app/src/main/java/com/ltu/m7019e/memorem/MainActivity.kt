package com.ltu.m7019e.memorem

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.ltu.m7019e.memorem.ui.theme.MemoremTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MemoremTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MemoremApp()
                }
            }
        }
    }
}

@Preview(showBackground = true,
    showSystemUi = true)
@Composable
fun MemoremPreview() {
    MemoremTheme(darkTheme = false) {
        MemoremApp()
    }
}

@Preview(showBackground = true,
    showSystemUi = true)
@Composable
fun MemoremPreviewDark() {
    MemoremTheme(darkTheme = true) {
        MemoremApp()
    }
}
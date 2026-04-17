package com.ldb.mobileualachallenge.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.ldb.mobileualachallenge.presentation.navigation.AppNavGraph
import com.ldb.mobileualachallenge.presentation.theme.ChallengeTheme

// TODO annotate hilt
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ChallengeTheme {
                AppNavGraph()
            }
        }
    }
}
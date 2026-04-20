package com.ldb.mobileualachallenge.main.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.ldb.mobileualachallenge.main.presentation.navigation.AppNavGraph
import com.ldb.mobileualachallenge.core.presentation.theme.ChallengeTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
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
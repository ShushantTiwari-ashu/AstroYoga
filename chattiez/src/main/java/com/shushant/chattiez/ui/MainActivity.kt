package com.shushant.chattiez.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.CompositionLocalProvider
import androidx.core.view.WindowCompat
import com.shushant.common.compose.utils.LocalOnFinishDispatcher
import com.shushant.common.compose.theme.AppTheme
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : ComponentActivity() {
    private val mainViewModel: MainViewModel by viewModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        setContent {
            mainViewModel.setTheme(isSystemInDarkTheme())
            CompositionLocalProvider(LocalOnFinishDispatcher provides { finish() }) {
                AppTheme { MainScreen(mainViewModel = mainViewModel) }
            }
        }
    }
}
package com.example

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.ui.components.TokGoBottomBar
import com.example.ui.screens.*
import com.example.ui.theme.TokGoTheme
import com.example.viewmodel.TokGoViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val viewModel: TokGoViewModel = viewModel()
            TokGoTheme(darkTheme = viewModel.isDarkMode) {
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    bottomBar = {
                        TokGoBottomBar(
                            currentTab = viewModel.currentTab,
                            onTabSelected = { tab -> viewModel.currentTab = tab }
                        )
                    }
                ) { innerPadding ->
                    Box(modifier = Modifier.padding(innerPadding)) {
                        when (viewModel.currentTab) {
                            "splash" -> SplashScreen(onNavigateToHome = { viewModel.currentTab = "home" })
                            "home" -> HomeFeedScreen(viewModel)
                            "discover" -> DiscoverScreen(viewModel)
                            "create" -> CreateVideoScreen(viewModel)
                            "live" -> LiveScreen(viewModel)
                            "profile" -> ProfileScreen(viewModel)
                            "tasks" -> TasksChallengesScreen(viewModel, initialTab = "tasks")
                            "challenges" -> TasksChallengesScreen(viewModel, initialTab = "challenges")
                            "sponsor_dashboard" -> SponsorDashboardScreen(viewModel)
                            "wallet" -> WalletScreen(viewModel)
                            "admin_control" -> AdminControlRoomScreen(viewModel)
                            "moderation" -> AdminControlRoomScreen(viewModel) // or dedicated moderation
                            "messages" -> MessagesNotificationsScreen(viewModel, initialTab = "messages")
                            "notifications" -> MessagesNotificationsScreen(viewModel, initialTab = "notifications")
                            "settings" -> SettingsScreen(viewModel)
                            else -> HomeFeedScreen(viewModel)
                        }
                    }
                }
            }
        }
    }
}

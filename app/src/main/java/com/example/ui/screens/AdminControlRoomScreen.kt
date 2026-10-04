package com.example.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Dashboard
import androidx.compose.material.icons.filled.MonetizationOn
import androidx.compose.material.icons.filled.People
import androidx.compose.material.icons.filled.Security
import androidx.compose.material.icons.filled.Videocam
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ui.theme.TokGoCyan
import com.example.ui.theme.TokGoPink
import com.example.viewmodel.TokGoViewModel

@Composable
fun AdminControlRoomScreen(viewModel: TokGoViewModel) {
    var adminTab by remember { mutableStateOf("Overview") }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .statusBarsPadding()
        ) {
            // Admin Top Bar
            Surface(
                color = MaterialTheme.colorScheme.surfaceVariant,
                tonalElevation = 4.dp
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                        IconButton(onClick = { viewModel.currentTab = "profile" }) {
                            Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Exit Admin")
                        }
                        Text(text = "TokGo Control Room (Admin)", style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Black)
                    }
                    Text(text = "Secure Ledger Active", color = TokGoCyan, style = MaterialTheme.typography.bodySmall, fontWeight = FontWeight.Bold)
                }
            }

            Row(modifier = Modifier.fillMaxSize()) {
                // Sidebar
                Column(
                    modifier = Modifier
                        .width(220.dp)
                        .fillMaxHeight()
                        .background(MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f))
                        .padding(12.dp),
                    verticalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    val tabs = listOf("Overview", "Users", "Creators", "Sponsors", "Advertisements", "Payments", "Withdrawals", "Moderation", "Revenue", "Settings")
                    tabs.forEach { tab ->
                        val isSelected = adminTab == tab
                        Surface(
                            color = if (isSelected) MaterialTheme.colorScheme.primary else Color.Transparent,
                            shape = RoundedCornerShape(8.dp),
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable { adminTab = tab }
                        ) {
                            Text(
                                text = tab,
                                color = if (isSelected) MaterialTheme.colorScheme.onPrimary else MaterialTheme.colorScheme.onSurface,
                                modifier = Modifier.padding(horizontal = 12.dp, vertical = 10.dp),
                                fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal,
                                fontSize = 14.sp
                            )
                        }
                    }
                }

                // Main Content Area
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxHeight()
                        .padding(16.dp)
                ) {
                    when (adminTab) {
                        "Overview" -> {
                            LazyColumn(verticalArrangement = Arrangement.spacedBy(16.dp)) {
                                item {
                                    Text(text = "Platform Real-Time Statistics", style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold)
                                }
                                item {
                                    val statCards = listOf(
                                        Triple("Total Users", "1,245,800", Icons.Default.People),
                                        Triple("Active Users Today", "342,100", Icons.Default.People),
                                        Triple("Videos Uploaded", "89,420", Icons.Default.Videocam),
                                        Triple("LIVE Streams Now", "1,420", Icons.Default.Videocam),
                                        Triple("Advertising Revenue", "142,500,000 UGX", Icons.Default.MonetizationOn),
                                        Triple("Creator Earnings", "84,200,000 UGX", Icons.Default.MonetizationOn),
                                        Triple("Platform Revenue (30%)", "36,150,000 UGX", Icons.Default.Dashboard),
                                        Triple("Pending Withdrawals", "12,400,000 UGX", Icons.Default.Security)
                                    )
                                    LazyVerticalGrid(
                                        columns = GridCells.Fixed(2),
                                        horizontalArrangement = Arrangement.spacedBy(12.dp),
                                        verticalArrangement = Arrangement.spacedBy(12.dp),
                                        modifier = Modifier.height(340.dp)
                                    ) {
                                        items(statCards) { stat ->
                                            Card(shape = RoundedCornerShape(12.dp)) {
                                                Column(modifier = Modifier.padding(14.dp)) {
                                                    Icon(imageVector = stat.third, contentDescription = stat.first, tint = TokGoPink, modifier = Modifier.size(24.dp))
                                                    Spacer(modifier = Modifier.height(8.dp))
                                                    Text(text = stat.first, style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
                                                    Text(text = stat.second, style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                        "Revenue" -> {
                            Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
                                Text(text = "Platform Revenue & Payout Rules", style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold)
                                Card(shape = RoundedCornerShape(12.dp)) {
                                    Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
                                        Text(text = "Platform Revenue Share: ${viewModel.platformRevenuePercent}%", fontWeight = FontWeight.Bold, fontSize = 16.sp)
                                        Text(text = "The developer/platform receives 30% (configurable) of eligible revenue, with the remaining distributed according to creator, sponsor, task, advertising, and tax rules.", style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
                                        Slider(
                                            value = viewModel.platformRevenuePercent.toFloat(),
                                            onValueChange = { viewModel.platformRevenuePercent = it.toInt() },
                                            valueRange = 10f..50f,
                                            steps = 8
                                        )
                                        Button(onClick = {}) {
                                            Text("Save Revenue Rules")
                                        }
                                    }
                                }
                            }
                        }
                        "Moderation" -> {
                            Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                                Text(text = "Moderation & Reported Items", style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold)
                                viewModel.reports.forEach { report ->
                                    Card(shape = RoundedCornerShape(8.dp)) {
                                        Row(
                                            modifier = Modifier
                                                .fillMaxWidth()
                                                .padding(12.dp),
                                            horizontalArrangement = Arrangement.SpaceBetween,
                                            verticalAlignment = Alignment.CenterVertically
                                        ) {
                                            Column {
                                                Text(text = "${report.itemType}: ${report.itemName}", fontWeight = FontWeight.Bold)
                                                Text(text = "Reason: ${report.reason} • By: ${report.reporter}", style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
                                            }
                                            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                                                Button(onClick = { viewModel.updateModerationStatus(report.id, "Approved") }) {
                                                    Text("Approve")
                                                }
                                                OutlinedButton(onClick = { viewModel.updateModerationStatus(report.id, "Removed") }) {
                                                    Text("Remove")
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                        else -> {
                            Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
                                Text(text = "$adminTab Management", style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold)
                                Text(text = "Manage all $adminTab settings, transactions, and audit logs securely.", style = MaterialTheme.typography.bodyMedium, color = MaterialTheme.colorScheme.onSurfaceVariant)
                            }
                        }
                    }
                }
            }
        }
    }
}

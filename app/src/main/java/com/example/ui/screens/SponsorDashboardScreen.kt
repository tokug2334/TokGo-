package com.example.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.model.SubscriptionPlan
import com.example.ui.theme.TokGoPink
import com.example.viewmodel.TokGoViewModel

@Composable
fun SponsorDashboardScreen(viewModel: TokGoViewModel) {
    var selectedPlanForCheckout by remember { mutableStateOf<SubscriptionPlan?>(null) }
    var selectedPaymentMethod by remember { mutableStateOf("MTN Mobile Money") }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .statusBarsPadding()
                .padding(bottom = 80.dp),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Header
            item {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    IconButton(onClick = { viewModel.currentTab = "profile" }) {
                        Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                    Text(text = "Sponsor / Business Hub", style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold)
                    Spacer(modifier = Modifier.width(48.dp))
                }
            }

            // Active Subscription Status
            item {
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(12.dp),
                    colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(text = "Subscription Status", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
                            Surface(
                                color = if (viewModel.activeSponsorSubscription != null) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.error,
                                shape = RoundedCornerShape(4.dp)
                            ) {
                                Text(
                                    text = if (viewModel.activeSponsorSubscription != null) "ACTIVE" else "NO ACTIVE PLAN",
                                    color = MaterialTheme.colorScheme.onPrimary,
                                    fontSize = 11.sp,
                                    fontWeight = FontWeight.Bold,
                                    modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                                )
                            }
                        }
                        Spacer(modifier = Modifier.height(8.dp))
                        if (viewModel.activeSponsorSubscription != null) {
                            Text(text = "Plan: ${viewModel.activeSponsorSubscription?.name}", style = MaterialTheme.typography.bodyMedium)
                            Text(text = "Expires: ${viewModel.sponsorExpiryDate}", style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
                            Text(text = "Automatic Renewal: ${if (viewModel.sponsorAutoRenew) "Enabled" else "Disabled"}", style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
                        } else {
                            Text(text = "Subscribe below to start creating advertisements, tasks, and sponsored challenges on TokGo.", style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
                        }
                    }
                }
            }

            // Plans Comparison
            item {
                Text(text = "Advertising Subscription Plans", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
                Spacer(modifier = Modifier.height(4.dp))
                Text(text = "All payments securely processed via MTN Mobile Money, Airtel, Visa/Mastercard, or Bank Account.", style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
            }

            items(viewModel.subscriptionPlans) { plan ->
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(12.dp)
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(text = plan.name, style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
                            Text(
                                text = "${plan.price} ${plan.currency}",
                                style = MaterialTheme.typography.titleMedium,
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.primary
                            )
                        }
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(text = plan.description, style = MaterialTheme.typography.bodyMedium, color = MaterialTheme.colorScheme.onSurfaceVariant)
                        Spacer(modifier = Modifier.height(8.dp))
                        Divider()
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(text = "• Allowed Ads: ${plan.adsAllowed}", style = MaterialTheme.typography.bodySmall)
                        Text(text = "• Allowed Campaigns: ${plan.campaignsAllowed}", style = MaterialTheme.typography.bodySmall)
                        Text(text = "• Sponsored Challenges: ${plan.challengesAllowed}", style = MaterialTheme.typography.bodySmall)
                        Text(text = "• Sponsored Tasks: ${plan.tasksAllowed}", style = MaterialTheme.typography.bodySmall)
                        Spacer(modifier = Modifier.height(16.dp))
                        Button(
                            onClick = { selectedPlanForCheckout = plan },
                            modifier = Modifier.fillMaxWidth(),
                            colors = ButtonDefaults.buttonColors(containerColor = TokGoPink)
                        ) {
                            Text("Subscribe to ${plan.name}", fontWeight = FontWeight.Bold)
                        }
                    }
                }
            }

            if (selectedPlanForCheckout != null) {
                item {
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(12.dp),
                        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primaryContainer)
                    ) {
                        Column(modifier = Modifier.padding(16.dp), verticalArrangement = Arrangement.spacedBy(12.dp)) {
                            Text(text = "Checkout — ${selectedPlanForCheckout?.name}", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
                            Text(text = "Price: ${selectedPlanForCheckout?.price} ${selectedPlanForCheckout?.currency}", style = MaterialTheme.typography.bodyMedium)

                            Text(text = "Select Secure Payment Method:", style = MaterialTheme.typography.bodySmall, fontWeight = FontWeight.SemiBold)
                            val methods = listOf("MTN Mobile Money", "Airtel Money", "Visa / Mastercard", "Bank Account")
                            methods.forEach { method ->
                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .clickable { selectedPaymentMethod = method }
                                        .padding(vertical = 4.dp),
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.SpaceBetween
                                ) {
                                    Text(text = method, style = MaterialTheme.typography.bodyMedium)
                                    if (selectedPaymentMethod == method) {
                                        Icon(imageVector = Icons.Default.CheckCircle, contentDescription = "Selected", tint = MaterialTheme.colorScheme.primary)
                                    }
                                }
                            }

                            Button(
                                onClick = {
                                    selectedPlanForCheckout?.let { viewModel.subscribeSponsor(it) }
                                    selectedPlanForCheckout = null
                                },
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                Text("Authorize & Pay Securely")
                            }
                        }
                    }
                }
            }
        }
    }
}

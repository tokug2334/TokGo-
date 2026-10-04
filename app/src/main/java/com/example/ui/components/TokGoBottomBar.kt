package com.example.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Explore
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.LiveTv
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ui.theme.TokGoCyan
import com.example.ui.theme.TokGoPink

@Composable
fun TokGoBottomBar(
    currentTab: String,
    onTabSelected: (String) -> Unit
) {
    if (currentTab == "splash" || currentTab == "admin_control") return

    Surface(
        color = MaterialTheme.colorScheme.surface.copy(alpha = 0.955f),
        tonalElevation = 8.dp,
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .navigationBarsPadding()
                .height(64.dp),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically
        ) {
            NavItem(
                icon = Icons.Default.Home,
                label = "Home",
                isSelected = currentTab == "home",
                onClick = { onTabSelected("home") }
            )
            NavItem(
                icon = Icons.Default.Explore,
                label = "Discover",
                isSelected = currentTab == "discover",
                onClick = { onTabSelected("discover") }
            )

            // Center + Create Button
            Box(
                modifier = Modifier
                    .size(48.dp)
                    .clip(CircleShape)
                    .background(
                        Brush.linearGradient(
                            colors = listOf(TokGoPink, TokGoCyan)
                        )
                    )
                    .clickable { onTabSelected("create") },
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Create Video",
                    tint = Color.White,
                    modifier = Modifier.size(28.dp)
                )
            }

            NavItem(
                icon = Icons.Default.LiveTv,
                label = "LIVE",
                isSelected = currentTab == "live",
                onClick = { onTabSelected("live") }
            )
            NavItem(
                icon = Icons.Default.Person,
                label = "Profile",
                isSelected = currentTab == "profile",
                onClick = { onTabSelected("profile") }
            )
        }
    }
}

@Composable
fun NavItem(
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    label: String,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    val color = if (isSelected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurface.copy(alpha = 0.6f)
    Column(
        modifier = Modifier
            .clickable(onClick = onClick)
            .padding(horizontal = 8.dp, vertical = 4.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            imageVector = icon,
            contentDescription = label,
            tint = color,
            modifier = Modifier.size(24.dp)
        )
        Spacer(modifier = Modifier.height(2.dp))
        Text(
            text = label,
            style = MaterialTheme.typography.labelSmall,
            color = color,
            fontSize = 10.sp
        )
    }
}

package com.example.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LiveTv
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ui.theme.TokGoPink
import com.example.viewmodel.TokGoViewModel

@Composable
fun LiveScreen(viewModel: TokGoViewModel) {
    var isBroadcasting by remember { mutableStateOf(false) }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        if (isBroadcasting) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Black),
                contentAlignment = Alignment.Center
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Surface(
                        color = TokGoPink,
                        shape = RoundedCornerShape(16.dp)
                    ) {
                        Text(
                            text = "🔴 LIVE BROADCASTING",
                            color = Color.White,
                            modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
                            fontWeight = FontWeight.Bold
                        )
                    }
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(text = "1,420 Viewers watching", color = Color.White, fontSize = 14.sp)
                    Spacer(modifier = Modifier.height(32.dp))
                    Button(onClick = { isBroadcasting = false }, colors = ButtonDefaults.buttonColors(containerColor = Color.Red)) {
                        Text("End LIVE")
                    }
                }
            }
        } else {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .statusBarsPadding()
                    .padding(bottom = 80.dp)
                    .padding(16.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                        Icon(imageVector = Icons.Default.LiveTv, contentDescription = "LIVE", tint = TokGoPink)
                        Text(text = "TokGo LIVE", style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold)
                    }
                    Button(
                        onClick = { isBroadcasting = true },
                        colors = ButtonDefaults.buttonColors(containerColor = TokGoPink)
                    ) {
                        Text("Go LIVE", color = Color.White, fontWeight = FontWeight.Bold)
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))
                Text(text = "Popular Livestreams Right Now", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.SemiBold)
                Spacer(modifier = Modifier.height(12.dp))

                val liveStreams = listOf(
                    Triple("creator_jane", "Building an Android App live!", "12.4K"),
                    Triple("music_live", "Late night acoustic session 🎸", "8.9K"),
                    Triple("gaming_pro", "TokGo Gaming Championship Finals 🏆", "45.1K"),
                    Triple("fitness_coach", "Morning Energy Workout Routine 💪", "5.6K")
                )

                LazyVerticalGrid(
                    columns = GridCells.Fixed(2),
                    horizontalArrangement = Arrangement.spacedBy(12.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    items(liveStreams) { stream ->
                        Card(
                            modifier = Modifier
                                .height(220.dp)
                                .clickable { isBroadcasting = true },
                            shape = RoundedCornerShape(12.dp)
                        ) {
                            Box(modifier = Modifier.fillMaxSize()) {
                                Box(
                                    modifier = Modifier
                                        .fillMaxSize()
                                        .background(Color.DarkGray)
                                )
                                Surface(
                                    color = TokGoPink,
                                    shape = RoundedCornerShape(4.dp),
                                    modifier = Modifier.padding(8.dp)
                                ) {
                                    Text(
                                        text = "LIVE • ${stream.third}",
                                        color = Color.White,
                                        fontSize = 10.sp,
                                        fontWeight = FontWeight.Bold,
                                        modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                                    )
                                }
                                Column(
                                    modifier = Modifier
                                        .align(Alignment.BottomStart)
                                        .padding(12.dp)
                                ) {
                                    Text(text = "@${stream.first}", color = Color.White, fontWeight = FontWeight.Bold, fontSize = 13.sp)
                                    Text(text = stream.second, color = Color.White.copy(alpha = 0.9f), fontSize = 11.sp, maxLines = 2)
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

package com.example.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.model.VideoItem
import com.example.ui.theme.TokGoPink
import com.example.viewmodel.TokGoViewModel

@Composable
fun CreateVideoScreen(viewModel: TokGoViewModel) {
    var step by remember { mutableStateOf("record") } // record, edit, post
    var caption by remember { mutableStateOf("") }
    var allowComments by remember { mutableStateOf(true) }
    var allowSharing by remember { mutableStateOf(true) }
    var allowDuet by remember { mutableStateOf(true) }
    var isSponsored by remember { mutableStateOf(false) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
    ) {
        when (step) {
            "record" -> {
                // Camera Preview & Record Controls
                Box(modifier = Modifier.fillMaxSize()) {
                    // Simulated Camera Viewfinder
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(Color(0xFF11141C)),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(text = "Camera Viewfinder Preview", color = Color.White.copy(alpha = 0.5f), fontSize = 16.sp)
                    }

                    // Top controls
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .statusBarsPadding()
                            .padding(16.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        IconButton(onClick = { viewModel.currentTab = "home" }) {
                            Icon(imageVector = Icons.Default.Close, contentDescription = "Close", tint = Color.White)
                        }
                        Surface(
                            color = Color.Black.copy(alpha = 0.6f),
                            shape = RoundedCornerShape(16.dp)
                        ) {
                            Row(
                                modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy(4.dp)
                            ) {
                                Icon(imageVector = Icons.Default.MusicNote, contentDescription = "Sound", tint = Color.White, modifier = Modifier.size(16.dp))
                                Text(text = "Add Sound", color = Color.White, fontSize = 13.sp)
                            }
                        }
                        IconButton(onClick = { /* flip */ }) {
                            Icon(imageVector = Icons.Default.FlipCameraIos, contentDescription = "Flip", tint = Color.White)
                        }
                    }

                    // Right side tools (Speed, Filters, Timer, Flash)
                    Column(
                        modifier = Modifier
                            .align(Alignment.CenterEnd)
                            .padding(end = 16.dp),
                        verticalArrangement = Arrangement.spacedBy(20.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        ToolIcon(icon = Icons.Default.FlashOn, label = "Flash")
                        ToolIcon(icon = Icons.Default.Speed, label = "Speed")
                        ToolIcon(icon = Icons.Default.FilterVintage, label = "Filters")
                        ToolIcon(icon = Icons.Default.Timer, label = "Timer")
                        ToolIcon(icon = Icons.Default.AutoFixHigh, label = "Beauty")
                    }

                    // Bottom Record Button
                    Box(
                        modifier = Modifier
                            .align(Alignment.BottomCenter)
                            .padding(bottom = 48.dp)
                            .fillMaxWidth(),
                        contentAlignment = Alignment.Center
                    ) {
                        Row(
                            horizontalArrangement = Arrangement.spacedBy(48.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                IconButton(onClick = { step = "edit" }) {
                                    Icon(imageVector = Icons.Default.Upload, contentDescription = "Upload", tint = Color.White)
                                }
                                Text("Upload", color = Color.White, fontSize = 11.sp)
                            }

                            // Big Record Button
                            Box(
                                modifier = Modifier
                                    .size(80.dp)
                                    .clip(CircleShape)
                                    .background(Color.White)
                                    .padding(4.dp)
                                    .clickable { step = "edit" },
                                contentAlignment = Alignment.Center
                            ) {
                                Box(
                                    modifier = Modifier
                                        .size(68.dp)
                                        .clip(CircleShape)
                                        .background(TokGoPink)
                                )
                            }

                            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                IconButton(onClick = { step = "edit" }) {
                                    Icon(imageVector = Icons.Default.Check, contentDescription = "Done", tint = Color.White)
                                }
                                Text("Next", color = Color.White, fontSize = 11.sp)
                            }
                        }
                    }
                }
            }
            "edit" -> {
                // Editing Screen
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .statusBarsPadding()
                        .padding(16.dp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        IconButton(onClick = { step = "record" }) {
                            Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Back", tint = Color.White)
                        }
                        Text("Editing Studio", color = Color.White, fontWeight = FontWeight.Bold, fontSize = 18.sp)
                        TextButton(onClick = { step = "post" }) {
                            Text("Next", color = TokGoPink, fontWeight = FontWeight.Bold)
                        }
                    }

                    Spacer(modifier = Modifier.height(16.dp))
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f)
                            .background(Color(0xFF1F293D), RoundedCornerShape(12.dp)),
                        contentAlignment = Alignment.Center
                    ) {
                        Text("Video Preview & Timeline Editor", color = Color.White.copy(alpha = 0.7f))
                    }

                    Spacer(modifier = Modifier.height(16.dp))
                    // Editing tools row
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 8.dp),
                        horizontalArrangement = Arrangement.SpaceAround
                    ) {
                        EditToolButton(icon = Icons.Default.ContentCut, label = "Trim")
                        EditToolButton(icon = Icons.Default.TextFields, label = "Text")
                        EditToolButton(icon = Icons.Default.EmojiEmotions, label = "Stickers")
                        EditToolButton(icon = Icons.Default.MusicNote, label = "Audio")
                        EditToolButton(icon = Icons.Default.FilterList, label = "Effects")
                    }
                }
            }
            "post" -> {
                // Posting Screen
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .statusBarsPadding()
                        .padding(16.dp),
                    verticalArrangement = Arrangement.SpaceBetween
                ) {
                    Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            IconButton(onClick = { step = "edit" }) {
                                Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Back", tint = Color.White)
                            }
                            Text("Post to TokGo", color = Color.White, fontWeight = FontWeight.Bold, fontSize = 18.sp)
                            Spacer(modifier = Modifier.width(48.dp))
                        }

                        OutlinedTextField(
                            value = caption,
                            onValueChange = { caption = it },
                            placeholder = { Text("Write a caption and add #hashtags...") },
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(120.dp),
                            colors = OutlinedTextFieldDefaults.colors(
                                focusedTextColor = Color.White,
                                unfocusedTextColor = Color.White
                            )
                        )

                        // Privacy & toggles
                        Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                            PostToggleRow("Allow Comments", allowComments) { allowComments = it }
                            PostToggleRow("Allow Sharing", allowSharing) { allowSharing = it }
                            PostToggleRow("Allow Duet / Remix", allowDuet) { allowDuet = it }
                            PostToggleRow("Sponsored Content / Advertisement", isSponsored) { isSponsored = it }
                        }
                    }

                    Button(
                        onClick = {
                            val newVideo = VideoItem(
                                id = "v_${System.currentTimeMillis()}",
                                username = "current_user",
                                userAvatar = "https://images.unsplash.com/photo-1534528741775-53994a69daeb",
                                videoUrl = "",
                                caption = caption.ifEmpty { "New TokGo video! #TokGo #WeGo" },
                                hashtags = listOf("TokGo", "WeGo"),
                                soundName = "Original Sound",
                                likesCount = 1,
                                commentsCount = 0,
                                sharesCount = 0,
                                savesCount = 0,
                                isSponsored = isSponsored,
                                sponsorName = if (isSponsored) "Brand Partner" else null
                            )
                            viewModel.videos = listOf(newVideo) + viewModel.videos
                            viewModel.currentTab = "home"
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(56.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = TokGoPink)
                    ) {
                        Text("POST TOKGO", fontSize = 16.sp, fontWeight = FontWeight.Bold, color = Color.White)
                    }
                }
            }
        }
    }
}

@Composable
fun ToolIcon(icon: androidx.compose.ui.graphics.vector.ImageVector, label: String) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        IconButton(onClick = {}) {
            Icon(imageVector = icon, contentDescription = label, tint = Color.White, modifier = Modifier.size(28.dp))
        }
        Text(text = label, color = Color.White, fontSize = 10.sp)
    }
}

@Composable
fun EditToolButton(icon: androidx.compose.ui.graphics.vector.ImageVector, label: String) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Icon(imageVector = icon, contentDescription = label, tint = Color.White, modifier = Modifier.size(24.dp))
        Spacer(modifier = Modifier.height(4.dp))
        Text(text = label, color = Color.White, fontSize = 11.sp)
    }
}

@Composable
fun PostToggleRow(title: String, checked: Boolean, onCheckedChange: (Boolean) -> Unit) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = title, color = Color.White, fontSize = 14.sp)
        Switch(checked = checked, onCheckedChange = onCheckedChange)
    }
}

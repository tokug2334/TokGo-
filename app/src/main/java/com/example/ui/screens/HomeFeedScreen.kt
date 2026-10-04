package com.example.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.model.VideoItem
import com.example.ui.theme.TokGoCyan
import com.example.ui.theme.TokGoPink
import com.example.viewmodel.TokGoViewModel

@Composable
fun HomeFeedScreen(viewModel: TokGoViewModel) {
    var feedType by remember { mutableStateOf("For You") }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
    ) {
        // Vertical Video Feed
        LazyColumn(
            modifier = Modifier.fillMaxSize()
        ) {
            itemsIndexed(viewModel.videos) { index, video ->
                VideoCardItem(
                    video = video,
                    onLike = { viewModel.likeVideo(video.id) },
                    onSave = { viewModel.saveVideo(video.id) },
                    onOpenComments = { /* open comments */ },
                    onOpenProfile = { viewModel.currentTab = "profile" }
                )
            }
        }

        // Top Navigation & Header
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .statusBarsPadding()
                .padding(horizontal = 16.dp, vertical = 8.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "LIVE",
                    color = if (feedType == "LIVE") Color.White else Color.White.copy(alpha = 0.6f),
                    fontWeight = if (feedType == "LIVE") FontWeight.Bold else FontWeight.Normal,
                    fontSize = 15.sp,
                    modifier = Modifier.clickable { viewModel.currentTab = "live" }
                )
                Text(
                    text = "Following",
                    color = if (feedType == "Following") Color.White else Color.White.copy(alpha = 0.6f),
                    fontWeight = if (feedType == "Following") FontWeight.Bold else FontWeight.Normal,
                    fontSize = 15.sp,
                    modifier = Modifier.clickable { feedType = "Following" }
                )
                Text(
                    text = "For You",
                    color = if (feedType == "For You") TokGoCyan else Color.White.copy(alpha = 0.6f),
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                    modifier = Modifier.clickable { feedType = "For You" }
                )
            }

            Row(
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(onClick = { viewModel.currentTab = "discover" }) {
                    Icon(imageVector = Icons.Default.Search, contentDescription = "Search", tint = Color.White)
                }
            }
        }
    }
}

@Composable
fun VideoCardItem(
    video: VideoItem,
    onLike: () -> Unit,
    onSave: () -> Unit,
    onOpenComments: () -> Unit,
    onOpenProfile: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(720.dp) // standard full height video item simulation
            .background(Color.DarkGray)
    ) {
        // Video placeholder background / image
        AsyncImage(
            model = video.userAvatar,
            contentDescription = video.caption,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )

        // Gradient scrim for readability
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Brush.verticalGradient(
                        colors = listOf(Color.Transparent, Color.Black.copy(alpha = 0.8f)),
                        startY = 600f
                    )
                )
        )

        // Right side vertical action bar
        Column(
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(end = 16.dp, bottom = 80.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // Profile avatar + follow
            Box(
                modifier = Modifier
                    .size(48.dp)
                    .clip(CircleShape)
                    .clickable(onClick = onOpenProfile)
            ) {
                AsyncImage(
                    model = video.userAvatar,
                    contentDescription = video.username,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )
            }

            // Like
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                IconButton(onClick = onLike) {
                    Icon(
                        imageVector = if (video.isLiked) Icons.Default.Favorite else Icons.Outlined.FavoriteBorder,
                        contentDescription = "Like",
                        tint = if (video.isLiked) TokGoPink else Color.White,
                        modifier = Modifier.size(32.dp)
                    )
                }
                Text(text = "${video.likesCount}", color = Color.White, fontSize = 12.sp, fontWeight = FontWeight.Bold)
            }

            // Comment
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                IconButton(onClick = onOpenComments) {
                    Icon(
                        imageVector = Icons.Outlined.Comment,
                        contentDescription = "Comment",
                        tint = Color.White,
                        modifier = Modifier.size(32.dp)
                    )
                }
                Text(text = "${video.commentsCount}", color = Color.White, fontSize = 12.sp, fontWeight = FontWeight.Bold)
            }

            // Save
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                IconButton(onClick = onSave) {
                    Icon(
                        imageVector = if (video.isSaved) Icons.Default.Bookmark else Icons.Outlined.BookmarkBorder,
                        contentDescription = "Save",
                        tint = if (video.isSaved) TokGoCyan else Color.White,
                        modifier = Modifier.size(32.dp)
                    )
                }
                Text(text = "${video.savesCount}", color = Color.White, fontSize = 12.sp, fontWeight = FontWeight.Bold)
            }

            // Share
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                IconButton(onClick = { /* Share */ }) {
                    Icon(
                        imageVector = Icons.Outlined.Share,
                        contentDescription = "Share",
                        tint = Color.White,
                        modifier = Modifier.size(32.dp)
                    )
                }
                Text(text = "${video.sharesCount}", color = Color.White, fontSize = 12.sp, fontWeight = FontWeight.Bold)
            }
        }

        // Bottom caption and sponsor info
        Column(
            modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(start = 16.dp, end = 80.dp, bottom = 80.dp)
        ) {
            if (video.isSponsored) {
                Surface(
                    color = TokGoPink.copy(alpha = 0.9f),
                    shape = RoundedCornerShape(4.dp),
                    modifier = Modifier.padding(bottom = 8.dp)
                ) {
                    Text(
                        text = "Sponsored • ${video.sponsorName ?: "Advertisement"}",
                        color = Color.White,
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                    )
                }
            }

            Text(
                text = "@${video.username}",
                color = Color.White,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = video.caption,
                color = Color.White.copy(alpha = 0.95f),
                fontSize = 14.sp
            )
            Spacer(modifier = Modifier.height(8.dp))
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Icon(imageVector = Icons.Default.MusicNote, contentDescription = "Sound", tint = Color.White, modifier = Modifier.size(16.dp))
                Text(
                    text = video.soundName,
                    color = Color.White.copy(alpha = 0.85f),
                    fontSize = 13.sp
                )
            }
        }
    }
}

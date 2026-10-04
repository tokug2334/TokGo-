package com.example.model

data class VideoItem(
    val id: String,
    val username: String,
    val userAvatar: String,
    val videoUrl: String,
    val caption: String,
    val hashtags: List<String>,
    val soundName: String,
    val likesCount: Int,
    val commentsCount: Int,
    val sharesCount: Int,
    val savesCount: Int,
    val isSponsored: Boolean = false,
    val sponsorName: String? = null,
    val isFollowing: Boolean = false,
    val isLiked: Boolean = false,
    val isSaved: Boolean = false
)

data class TaskItem(
    val id: String,
    val sponsorName: String,
    val sponsorLogo: String,
    val taskName: String,
    val reward: String,
    val deadline: String,
    val participantsCount: Int,
    val description: String,
    val isCompleted: Boolean = false
)

data class ChallengeItem(
    val id: String,
    val sponsor: String,
    val challengeTitle: String,
    val hashtag: String,
    val prize: String,
    val participantsCount: Int,
    val timeRemaining: String,
    val isJoined: Boolean = false
)

data class SubscriptionPlan(
    val id: String,
    val name: String,
    val price: Long,
    val currency: String,
    val durationMonths: Int,
    val description: String,
    val adsAllowed: Int,
    val campaignsAllowed: Int,
    val challengesAllowed: Int,
    val tasksAllowed: Int
)

data class CreatorWallet(
    val availableBalance: Double,
    val pendingBalance: Double,
    val totalEarnings: Double,
    val nextPayoutDate: String,
    val adsEarnings: Double,
    val sponsoredEarnings: Double,
    val tasksEarnings: Double,
    val challengesEarnings: Double,
    val liveEarnings: Double,
    val otherEarnings: Double
)

data class TransactionRecord(
    val id: String,
    val type: String, // Payout, Subscription, Ads, etc.
    val amount: String,
    val date: String,
    val status: String, // Completed, Pending, Failed
    val reference: String
)

data class NotificationItem(
    val id: String,
    val category: String, // Likes, Comments, Followers, Tasks, Sponsors, Earnings, Security
    val title: String,
    val message: String,
    val time: String,
    val isUnread: Boolean = true
)

data class MessageItem(
    val id: String,
    val senderName: String,
    val senderAvatar: String,
    val lastMessage: String,
    val time: String,
    val unreadCount: Int = 0
)

data class ModerationReport(
    val id: String,
    val itemType: String, // Video, Comment, Account, LIVE, Advertisement, Task, Challenge
    val itemName: String,
    val reporter: String,
    val reason: String,
    val status: String // Pending, Approved, Removed, Warned, Banned
)

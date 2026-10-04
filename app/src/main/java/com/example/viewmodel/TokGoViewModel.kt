package com.example.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.model.*

class TokGoViewModel : ViewModel() {

    // App Navigation state
    var currentTab by mutableStateOf("splash") // splash, home, discover, create, live, profile, tasks, challenges, sponsor_dashboard, wallet, admin_control, moderation, messages, notifications, settings
    var isDarkMode by mutableStateOf(true)

    // Admin Settings
    var platformRevenuePercent by mutableStateOf(30)
    var minPayoutAmount by mutableStateOf(10000.0)
    var payoutsPaused by mutableStateOf(false)
    var currencySymbol by mutableStateOf("UGX")
    var gracePeriodAllowed by mutableStateOf(true)

    // Plans
    var subscriptionPlans by mutableStateOf(listOf(
        SubscriptionPlan("plan_1", "Plan 1 — 6 Months Standard", 50000L, "UGX", 6, "Standard business advertising plan for growing brands.", 5, 2, 1, 1),
        SubscriptionPlan("plan_2", "Plan 2 — 6 Months Pro", 80000L, "UGX", 6, "Advanced advertising package with priority placement.", 15, 5, 3, 3),
        SubscriptionPlan("plan_3", "Plan 3 — Yearly Enterprise", 800000L, "UGX", 12, "Ultimate yearly enterprise package with maximum reach.", 50, 20, 10, 10)
    ))

    var activeSponsorSubscription by mutableStateOf<SubscriptionPlan?>(null)
    var sponsorExpiryDate by mutableStateOf("2027-03-14")
    var sponsorAutoRenew by mutableStateOf(true)

    // Videos Feed
    var videos by mutableStateOf(listOf(
        VideoItem("v1", "alex_creator", "https://images.unsplash.com/photo-1534528741775-53994a69daeb", "", "Exploring the vibrant streets of Kampala! 🌆✨ #TokGo #Travel #Africa", listOf("TokGo", "Travel", "Africa"), "Original Sound - Alex", 12450, 842, 320, 1500, false, null),
        VideoItem("v2", "peak_energy", "https://images.unsplash.com/photo-1507003211169-0a1dd7228f2d", "", "Power your day with Peak Energy drink! Best refreshment ever. ⚡🥤", listOf("TokGo", "Sponsored", "Energy"), "Peak Energy Beat", 48900, 3120, 5400, 12000, true, "Peak Energy Corp"),
        VideoItem("v3", "dance_queen", "https://images.unsplash.com/photo-1494790108377-be9c29b29330", "", "New dance challenge! Show me your moves 💃🔥 #DanceChallenge #TokGo", listOf("DanceChallenge", "TokGo", "Viral"), "Trending Afrobeat Mix", 89200, 4510, 8900, 23000, false, null),
        VideoItem("v4", "tech_guru", "https://images.unsplash.com/photo-1500648767791-00dcc994a43e", "", "Unboxing the latest smartphone tech. Incredible camera quality! 📱💡", listOf("Tech", "Gadgets", "TokGo"), "Tech Synth Groove", 34100, 1290, 1400, 4500, true, "Gadget Hub Ltd")
    ))

    var currentVideoIndex by mutableStateOf(0)

    // Tasks
    var tasks by mutableStateOf(listOf(
        TaskItem("t1", "Peak Energy Corp", "https://images.unsplash.com/photo-1507003211169-0a1dd7228f2d", "Create a video using Peak Energy", "5,000 UGX", "3 Days left", 12450, "Record a 30-second creative video featuring the Peak Energy can."),
        TaskItem("t2", "Gadget Hub Ltd", "https://images.unsplash.com/photo-1500648767791-00dcc994a43e", "Review our wireless earbuds", "12,000 UGX", "5 Days left", 8230, "Showcase the sound clarity and battery life of our new earbuds."),
        TaskItem("t3", "Savannah Fashion", "https://images.unsplash.com/photo-1534528741775-53994a69daeb", "Style your weekend outfit", "8,000 UGX", "1 Day left", 15400, "Highlight your favorite streetwear piece from our new catalog.")
    ))

    // Challenges
    var challenges by mutableStateOf(listOf(
        ChallengeItem("c1", "Peak Energy Corp", "Summer Energy Rush", "#PeakEnergyRush", "2,500,000 UGX", 34200, "2 Days", false),
        ChallengeItem("c2", "Savannah Fashion", "Afrobeat Dance Wave", "#AfrobeatWave", "5,000,000 UGX", 68100, "5 Days", true),
        ChallengeItem("c3", "Gadget Hub Ltd", "Tech Innovation Showcase", "#TechTokGo", "1,800,000 UGX", 19500, "12 Hours", false)
    ))

    // Wallet
    var wallet by mutableStateOf(CreatorWallet(
        availableBalance = 345200.0,
        pendingBalance = 120500.0,
        totalEarnings = 1850000.0,
        nextPayoutDate = "October 14, 2026",
        adsEarnings = 680000.0,
        sponsoredEarnings = 540000.0,
        tasksEarnings = 280000.0,
        challengesEarnings = 200000.0,
        liveEarnings = 150000.0,
        otherEarnings = 0.0
    ))

    var transactions by mutableStateOf(listOf(
        TransactionRecord("tx1", "Payout (6-Month Cycle)", "450,000 UGX", "Apr 14, 2026", "Completed", "REF-984210"),
        TransactionRecord("tx2", "Sponsored Task Reward", "12,000 UGX", "Sep 10, 2026", "Completed", "REF-883921"),
        TransactionRecord("tx3", "LIVE Gifts Earning", "45,000 UGX", "Sep 08, 2026", "Completed", "REF-772910")
    ))

    // Notifications
    var notifications by mutableStateOf(listOf(
        NotificationItem("n1", "Earnings", "Payout Verification", "Your 6-month earnings cycle is verified. Next payout in 30 days.", "2h ago", true),
        NotificationItem("n2", "Tasks", "Task Approved", "Your submission for Peak Energy task was approved (+5,000 UGX).", "5h ago", true),
        NotificationItem("n3", "Likes", "New Like", "alex_creator and 34 others liked your video.", "1d ago", false)
    ))

    // Messages
    var messages by mutableStateOf(listOf(
        MessageItem("m1", "Peak Energy Corp", "https://images.unsplash.com/photo-1507003211169-0a1dd7228f2d", "Hello! We loved your recent video submission.", "10:42 AM", 2),
        MessageItem("m2", "Savannah Fashion", "https://images.unsplash.com/photo-1534528741775-53994a69daeb", "Your challenge entry is currently trending!", "Yesterday", 0),
        MessageItem("m3", "TokGo Official", "https://images.unsplash.com/photo-1494790108377-be9c29b29330", "Welcome to TokGo Creator Center! Review guidelines.", "Sep 12", 0)
    ))

    // Moderation Reports
    var reports by mutableStateOf(listOf(
        ModerationReport("r1", "Video", "Video #v3 (Dance Challenge)", "user_safeguard", "Copyright sound dispute", "Pending"),
        ModerationReport("r2", "Advertisement", "Ad Banner #a2", "system_bot", "Misleading discount claim", "Pending"),
        ModerationReport("r3", "Comment", "Spam comment on video v1", "user_clean", "Spam / Scam link", "Resolved")
    ))

    // Actions
    fun likeVideo(videoId: String) {
        videos = videos.map {
            if (it.id == videoId) {
                val newLiked = !it.isLiked
                it.copy(
                    isLiked = newLiked,
                    likesCount = if (newLiked) it.likesCount + 1 else it.likesCount - 1
                )
            } else it
        }
    }

    fun saveVideo(videoId: String) {
        videos = videos.map {
            if (it.id == videoId) {
                val newSaved = !it.isSaved
                it.copy(
                    isSaved = newSaved,
                    savesCount = if (newSaved) it.savesCount + 1 else it.savesCount - 1
                )
            } else it
        }
    }

    fun subscribeSponsor(plan: SubscriptionPlan) {
        activeSponsorSubscription = plan
        sponsorExpiryDate = if (plan.durationMonths == 12) "September 2027" else "March 2027"
    }

    fun withdrawFunds(amount: Double) {
        if (amount <= wallet.availableBalance) {
            wallet = wallet.copy(availableBalance = wallet.availableBalance - amount)
            transactions = listOf(
                TransactionRecord(
                    id = "tx_${System.currentTimeMillis()}",
                    type = "Creator Payout",
                    amount = "${amount} ${currencySymbol}",
                    date = "Today",
                    status = "Completed",
                    reference = "REF-${System.currentTimeMillis().toString().takeLast(6)}"
                )
            ) + transactions
        }
    }

    fun updateAdminPlanPrice(planId: String, newPrice: Long) {
        subscriptionPlans = subscriptionPlans.map {
            if (it.id == planId) it.copy(price = newPrice) else it
        }
    }

    fun updateModerationStatus(reportId: String, newStatus: String) {
        reports = reports.map {
            if (it.id == reportId) it.copy(status = newStatus) else it
        }
    }
}

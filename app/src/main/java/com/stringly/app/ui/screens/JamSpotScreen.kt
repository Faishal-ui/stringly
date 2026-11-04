package com.stringly.app.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
import com.stringly.app.ui.theme.*

data class JamPost(
    val author: String,
    val title: String,
    val description: String,
    val likes: Int,
    val replies: Int,
    val tag: String
)

@Composable
fun JamSpotScreen() {
    val posts = listOf(
        JamPost("Mateo R.", "Neo-Soul Double Stops in E", "Clean tone + slight chorus magic with thumb-over bass chords.", 342, 48, "Tabs Included"),
        JamPost("Sarah K.", "How do you build speed on Economy Picking?", "Stuck at 130 BPM on the 3-notes-per-string major scales.", 189, 32, "Technique"),
        JamPost("David G.", "Custom Pedal Chain for 80s Shoegaze", "Fuzz into reverse delay before pre-amp drive gives infinite textures.", 512, 74, "Gear Rig"),
        JamPost("Elena V.", "Acoustic Fingerstyle Perussive Slap", "Combining thumb slap with index finger upward strum on Cadd9.", 275, 19, "Acoustic")
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(horizontal = 20.dp, vertical = 16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // Header
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                Text(
                    text = "COMMUNITY HUB",
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Bold,
                    color = PrimaryRed,
                    letterSpacing = 1.5.sp
                )
                Text(
                    text = "Jam Spot Forum",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = OnSurfaceLight
                )
            }
            Button(
                onClick = {},
                shape = RoundedCornerShape(20.dp),
                colors = ButtonDefaults.buttonColors(containerColor = PrimaryRed),
                contentPadding = PaddingValues(horizontal = 14.dp, vertical = 6.dp)
            ) {
                Icon(imageVector = Icons.Default.Add, contentDescription = null, modifier = Modifier.size(16.dp))
                Spacer(modifier = Modifier.width(4.dp))
                Text(text = "Post Lick", fontSize = 12.sp, fontWeight = FontWeight.Bold)
            }
        }

        // Community Posts Feed
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(12.dp),
            modifier = Modifier.fillMaxSize()
        ) {
            items(posts) { post ->
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(20.dp),
                    colors = CardDefaults.cardColors(containerColor = Color.White),
                    elevation = CardDefaults.cardElevation(defaultElevation = 1.5.dp)
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy(8.dp)
                            ) {
                                Box(
                                    modifier = Modifier
                                        .size(32.dp)
                                        .clip(CircleShape)
                                        .background(PrimaryRed),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Text(
                                        text = post.author.first().toString(),
                                        fontWeight = FontWeight.Bold,
                                        color = Color.White,
                                        fontSize = 14.sp
                                    )
                                }
                                Text(
                                    text = post.author,
                                    fontWeight = FontWeight.SemiBold,
                                    fontSize = 14.sp,
                                    color = OnSurfaceLight
                                )
                            }
                            Surface(
                                shape = RoundedCornerShape(8.dp),
                                color = Color(0xFFF3F3F5)
                            ) {
                                Text(
                                    text = post.tag,
                                    fontSize = 10.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = GraphiteMute,
                                    modifier = Modifier.padding(horizontal = 8.dp, vertical = 2.dp)
                                )
                            }
                        }

                        Spacer(modifier = Modifier.height(10.dp))

                        Text(
                            text = post.title,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold,
                            color = OnSurfaceLight
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = post.description,
                            fontSize = 13.sp,
                            color = GraphiteMute,
                            lineHeight = 18.sp
                        )

                        Spacer(modifier = Modifier.height(12.dp))

                        // Interaction Row
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                                Row(
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.spacedBy(4.dp)
                                ) {
                                    Icon(
                                        imageVector = Icons.Default.Favorite,
                                        contentDescription = null,
                                        tint = PrimaryRed,
                                        modifier = Modifier.size(16.dp)
                                    )
                                    Text(
                                        text = "${post.likes}",
                                        fontSize = 12.sp,
                                        color = GraphiteMute,
                                        fontWeight = FontWeight.Medium
                                    )
                                }
                                Row(
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.spacedBy(4.dp)
                                ) {
                                    Icon(
                                        imageVector = Icons.Default.ChatBubbleOutline,
                                        contentDescription = null,
                                        tint = GraphiteMute,
                                        modifier = Modifier.size(16.dp)
                                    )
                                    Text(
                                        text = "${post.replies} replies",
                                        fontSize = 12.sp,
                                        color = GraphiteMute,
                                        fontWeight = FontWeight.Medium
                                    )
                                }
                            }
                            Icon(
                                imageVector = Icons.Default.Share,
                                contentDescription = null,
                                tint = GraphiteMute,
                                modifier = Modifier.size(18.dp)
                            )
                        }
                    }
                }
            }
        }
    }
}

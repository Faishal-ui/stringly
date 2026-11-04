package com.stringly.app.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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

data class SongTab(
    val title: String,
    val artist: String,
    val difficulty: String,
    val key: String,
    val bpm: Int,
    val tuning: String
)

@Composable
fun TabsScreen() {
    var searchQuery by remember { mutableStateOf("") }

    val songs = listOf(
        SongTab("Wish You Were Here", "Pink Floyd", "MED", "G Major", 60, "Standard"),
        SongTab("Neon", "John Mayer", "HARD", "Drop C", 102, "Drop C"),
        SongTab("Little Wing", "Jimi Hendrix", "HARD", "Eb Standard", 71, "Eb Standard"),
        SongTab("Blackbird", "The Beatles", "EASY", "G Major", 94, "Standard"),
        SongTab("Tears in Heaven", "Eric Clapton", "MED", "A Major", 78, "Standard")
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
                    text = "SONG TABS",
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Bold,
                    color = PrimaryRed,
                    letterSpacing = 1.5.sp
                )
                Text(
                    text = "Interactive Player",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = OnSurfaceLight
                )
            }
            Surface(
                shape = RoundedCornerShape(12.dp),
                color = Color.White,
                shadowElevation = 1.dp
            ) {
                Row(
                    modifier = Modifier.padding(horizontal = 10.dp, vertical = 6.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.FilterList,
                        contentDescription = null,
                        tint = PrimaryRed,
                        modifier = Modifier.size(16.dp)
                    )
                    Text(
                        text = "Filter",
                        fontWeight = FontWeight.Bold,
                        fontSize = 12.sp,
                        color = OnSurfaceLight
                    )
                }
            }
        }

        // Search Bar
        OutlinedTextField(
            value = searchQuery,
            onValueChange = { searchQuery = it },
            placeholder = { Text(text = "Search song, artist, or chords...", fontSize = 13.sp) },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = null,
                    tint = GraphiteMute
                )
            },
            shape = RoundedCornerShape(16.dp),
            modifier = Modifier.fillMaxWidth(),
            colors = OutlinedTextFieldDefaults.colors(
                focusedContainerColor = Color.White,
                unfocusedContainerColor = Color.White,
                focusedBorderColor = PrimaryRed,
                unfocusedBorderColor = Color.Transparent
            ),
            singleLine = true
        )

        // Live Tab Player Sheet
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(22.dp),
            colors = CardDefaults.cardColors(containerColor = CharcoalHardware),
            elevation = CardDefaults.cardElevation(defaultElevation = 3.dp)
        ) {
            Column(modifier = Modifier.padding(18.dp)) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column {
                        Text(
                            text = "NOW PRACTICING",
                            fontSize = 10.sp,
                            fontWeight = FontWeight.Bold,
                            color = ElectricOrange,
                            letterSpacing = 1.2.sp
                        )
                        Text(
                            text = "Wish You Were Here",
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.White
                        )
                    }
                    IconButton(
                        onClick = {},
                        modifier = Modifier
                            .size(36.dp)
                            .clip(CircleShape)
                            .background(ElectricOrange)
                    ) {
                        Icon(
                            imageVector = Icons.Default.PlayArrow,
                            contentDescription = "Play",
                            tint = Color.White,
                            modifier = Modifier.size(20.dp)
                        )
                    }
                }

                Spacer(modifier = Modifier.height(14.dp))

                // Interactive Tab Line Display
                Surface(
                    shape = RoundedCornerShape(10.dp),
                    color = Color(0xFF262A35),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(
                        modifier = Modifier.padding(12.dp),
                        verticalArrangement = Arrangement.spacedBy(2.dp)
                    ) {
                        Text(
                            text = "e|-------------------------3-3--|",
                            fontSize = 12.sp,
                            fontFamily = androidx.compose.ui.text.font.FontFamily.Monospace,
                            color = Color.White
                        )
                        Text(
                            text = "B|-------------------------3-3--|",
                            fontSize = 12.sp,
                            fontFamily = androidx.compose.ui.text.font.FontFamily.Monospace,
                            color = Color.White
                        )
                        Text(
                            text = "G|--------0---2-0----------0-0--|",
                            fontSize = 12.sp,
                            fontFamily = androidx.compose.ui.text.font.FontFamily.Monospace,
                            color = ElectricOrange,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = "D|---0-h2---------2-0------0-0--|",
                            fontSize = 12.sp,
                            fontFamily = androidx.compose.ui.text.font.FontFamily.Monospace,
                            color = Color.White
                        )
                        Text(
                            text = "A|---------------------2---2-2--|",
                            fontSize = 12.sp,
                            fontFamily = androidx.compose.ui.text.font.FontFamily.Monospace,
                            color = Color.White
                        )
                        Text(
                            text = "E|-3-----------------------3-3--|",
                            fontSize = 12.sp,
                            fontFamily = androidx.compose.ui.text.font.FontFamily.Monospace,
                            color = Color.White
                        )
                    }
                }
            }
        }

        // Song Catalog List
        Text(
            text = "Trending Song Sheets",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = OnSurfaceLight
        )

        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(10.dp),
            modifier = Modifier.fillMaxSize()
        ) {
            items(songs) { song ->
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.cardColors(containerColor = Color.White),
                    elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(14.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column {
                            Text(
                                text = song.title,
                                fontSize = 15.sp,
                                fontWeight = FontWeight.Bold,
                                color = OnSurfaceLight
                            )
                            Text(
                                text = "${song.artist} • ${song.key} • ${song.bpm} BPM",
                                fontSize = 12.sp,
                                color = GraphiteMute
                            )
                        }
                        Surface(
                            shape = RoundedCornerShape(8.dp),
                            color = when (song.difficulty) {
                                "EASY" -> StageSuccess.copy(alpha = 0.15f)
                                "MED" -> AmberGold.copy(alpha = 0.15f)
                                else -> PrimaryRed.copy(alpha = 0.15f)
                            }
                        ) {
                            Text(
                                text = song.difficulty,
                                fontSize = 11.sp,
                                fontWeight = FontWeight.Bold,
                                color = when (song.difficulty) {
                                    "EASY" -> StageSuccess
                                    "MED" -> AmberGold
                                    else -> PrimaryRed
                                },
                                modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                            )
                        }
                    }
                }
            }
        }
    }
}

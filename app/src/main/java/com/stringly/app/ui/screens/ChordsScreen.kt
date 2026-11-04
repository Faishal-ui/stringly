package com.stringly.app.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
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

data class ChordItem(
    val name: String,
    val suffix: String,
    val formula: String,
    val dots: String,
    val fingering: String,
    val tag: String
)

@Composable
fun ChordsScreen() {
    val roots = listOf("C", "D", "E", "F", "G", "A", "B")
    var selectedRoot by remember { mutableStateOf("C") }

    val chordList = listOf(
        ChordItem("C", "Major", "1 - 3 - 5", "✕ ● ● ○ ● ○", "x-3-2-0-1-0", "Standard Open"),
        ChordItem("Cadd9", "Add 9", "1 - 3 - 5 - 9", "✕ ● ● ○ ● ○", "x-3-2-0-3-0", "Rock Ballad"),
        ChordItem("Cmaj7", "Major 7th", "1 - 3 - 5 - 7", "✕ ● ● ○ ○ ○", "x-3-2-0-0-0", "Lush Jazz"),
        ChordItem("C7", "Dominant 7", "1 - 3 - 5 - b7", "✕ ● ● ● ● ○", "x-3-2-3-1-0", "Blues Core"),
        ChordItem("Cm", "Minor", "1 - b3 - 5", "✕ ● ● ● ● ●", "x-3-5-5-4-3", "Barre Fret 3")
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(horizontal = 20.dp, vertical = 16.dp)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(18.dp)
    ) {
        // Header
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                Text(
                    text = "CHORD LIBRARY",
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Bold,
                    color = PrimaryRed,
                    letterSpacing = 1.5.sp
                )
                Text(
                    text = "3,200+ Voicings",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = OnSurfaceLight
                )
            }
            IconButton(
                onClick = {},
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape)
                    .background(Color.White)
            ) {
                Icon(imageVector = Icons.Default.Search, contentDescription = "Search", tint = OnSurfaceLight)
            }
        }

        // Root Note Selector Pills
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            items(roots) { root ->
                val isSelected = root == selectedRoot
                Surface(
                    shape = RoundedCornerShape(16.dp),
                    color = if (isSelected) PrimaryRed else Color.White,
                    shadowElevation = if (isSelected) 3.dp else 1.dp,
                    modifier = Modifier.clickable { selectedRoot = root }
                ) {
                    Text(
                        text = root,
                        modifier = Modifier.padding(horizontal = 20.dp, vertical = 10.dp),
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        color = if (isSelected) Color.White else OnSurfaceLight
                    )
                }
            }
        }

        // Featured Chord Breakdown
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(22.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White),
            elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
        ) {
            Column(modifier = Modifier.padding(20.dp)) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Text(
                                text = "${selectedRoot}add9",
                                fontSize = 28.sp,
                                fontWeight = FontWeight.ExtraBold,
                                color = OnSurfaceLight
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Surface(
                                shape = RoundedCornerShape(8.dp),
                                color = StageSuccess.copy(alpha = 0.15f)
                            ) {
                                Text(
                                    text = "Acoustic Staple",
                                    fontSize = 11.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = StageSuccess,
                                    modifier = Modifier.padding(horizontal = 8.dp, vertical = 2.dp)
                                )
                            }
                        }
                        Text(
                            text = "Bright, shimmering open voicing loved in rock ballads.",
                            fontSize = 13.sp,
                            color = GraphiteMute,
                            modifier = Modifier.padding(top = 4.dp)
                        )
                    }
                    IconButton(
                        onClick = {},
                        modifier = Modifier
                            .size(44.dp)
                            .clip(CircleShape)
                            .background(PrimaryRed)
                    ) {
                        Icon(
                            imageVector = Icons.Default.VolumeUp,
                            contentDescription = "Strum",
                            tint = Color.White
                        )
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Fretboard Visual Frame
                Surface(
                    shape = RoundedCornerShape(16.dp),
                    color = Color(0xFFF3F3F5),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(
                        modifier = Modifier.padding(16.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = "✕   ●   ●   ○   ●   ○",
                            fontSize = 14.sp,
                            fontWeight = FontWeight.ExtraBold,
                            color = PrimaryRed,
                            letterSpacing = 10.sp
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = "Fingering: x - 3 - 2 - 0 - 3 - 0",
                            fontSize = 13.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = OnSurfaceLight
                        )
                        Text(
                            text = "Tuning: E - A - D - G - B - E",
                            fontSize = 11.sp,
                            color = GraphiteMute
                        )
                    }
                }
            }
        }

        // Voicing Variations List
        Text(
            text = "Variations & Extensions",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = OnSurfaceLight
        )

        chordList.forEach { chord ->
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(18.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Text(
                                text = chord.name,
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Bold,
                                color = OnSurfaceLight
                            )
                            Spacer(modifier = Modifier.width(6.dp))
                            Text(
                                text = chord.suffix,
                                fontSize = 13.sp,
                                color = GraphiteMute
                            )
                        }
                        Text(
                            text = "${chord.fingering} • ${chord.tag}",
                            fontSize = 12.sp,
                            color = PrimaryRed,
                            fontWeight = FontWeight.Medium
                        )
                    }
                    IconButton(
                        onClick = {},
                        modifier = Modifier
                            .size(36.dp)
                            .clip(CircleShape)
                            .background(Color(0xFFF3F3F5))
                    ) {
                        Icon(
                            imageVector = Icons.Default.PlayArrow,
                            contentDescription = "Preview",
                            tint = PrimaryRed,
                            modifier = Modifier.size(20.dp)
                        )
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(24.dp))
    }
}

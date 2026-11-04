package com.stringly.app.ui.screens

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
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
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.stringly.app.ui.theme.*

@Composable
fun HomeScreen(
    onNavigateTo: (String) -> Unit
) {
    var bpm by remember { mutableIntStateOf(120) }
    var isMetronomePlaying by remember { mutableStateOf(false) }
    var currentBeat by remember { mutableIntStateOf(0) }

    LaunchedEffect(isMetronomePlaying, bpm) {
        if (isMetronomePlaying) {
            val interval = (60_000L / bpm)
            while (true) {
                kotlinx.coroutines.delay(interval)
                currentBeat = (currentBeat + 1) % 4
            }
        } else {
            currentBeat = 0
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 20.dp, vertical = 16.dp),
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        // Hero Greeting
        Column {
            Text(
                text = "WELCOME BACK",
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.primary,
                letterSpacing = 1.5.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(4.dp))
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = "Good evening, ",
                    style = MaterialTheme.typography.headlineLarge,
                    color = MaterialTheme.colorScheme.onSurface
                )
                Text(
                    text = "Leo",
                    style = MaterialTheme.typography.headlineLarge,
                    fontWeight = FontWeight.ExtraBold,
                    color = MaterialTheme.colorScheme.onSurface
                )
            }
            Text(
                text = "Ready for today's session",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }

        // Quick Tuner & Metronome Live Bar
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(14.dp)
        ) {
            // Live Tuner Mini Card
            Card(
                modifier = Modifier
                    .weight(1f)
                    .clickable { onNavigateTo("tuner") },
                shape = RoundedCornerShape(20.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
            ) {
                Column(modifier = Modifier.padding(14.dp)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "TUNER PULSE",
                            style = MaterialTheme.typography.labelSmall,
                            color = GraphiteMute,
                            fontSize = 10.sp,
                            fontWeight = FontWeight.Bold
                        )
                        Box(
                            modifier = Modifier
                                .size(8.dp)
                                .clip(CircleShape)
                                .background(StageSuccess)
                        )
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                    Row(verticalAlignment = Alignment.Bottom) {
                        Text(
                            text = "E",
                            fontSize = 28.sp,
                            fontWeight = FontWeight.Bold,
                            color = OnSurfaceLight
                        )
                        Text(
                            text = "2",
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold,
                            color = StageSuccess
                        )
                        Spacer(modifier = Modifier.width(6.dp))
                        Text(
                            text = "440.0 Hz",
                            style = MaterialTheme.typography.labelSmall,
                            color = GraphiteMute
                        )
                    }
                    Spacer(modifier = Modifier.height(10.dp))
                    Surface(
                        shape = RoundedCornerShape(12.dp),
                        color = StageSuccess.copy(alpha = 0.15f)
                    ) {
                        Row(
                            modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(4.dp)
                        ) {
                            Icon(
                                imageVector = Icons.Default.CheckCircle,
                                contentDescription = null,
                                tint = StageSuccess,
                                modifier = Modifier.size(12.dp)
                            )
                            Text(
                                text = "In Tune (±0¢)",
                                fontSize = 10.sp,
                                fontWeight = FontWeight.Bold,
                                color = StageSuccess
                            )
                        }
                    }
                }
            }

            // Live Metronome Mini Card
            Card(
                modifier = Modifier.weight(1f),
                shape = RoundedCornerShape(20.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
            ) {
                Column(modifier = Modifier.padding(14.dp)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "BPM ENGINE",
                            style = MaterialTheme.typography.labelSmall,
                            color = GraphiteMute,
                            fontSize = 10.sp,
                            fontWeight = FontWeight.Bold
                        )
                        // 4 Pips
                        Row(horizontalArrangement = Arrangement.spacedBy(4.dp)) {
                            repeat(4) { i ->
                                Box(
                                    modifier = Modifier
                                        .size(if (currentBeat == i && isMetronomePlaying) 8.dp else 6.dp)
                                        .clip(CircleShape)
                                        .background(
                                            if (currentBeat == i && isMetronomePlaying) PrimaryRed else Color(0xFFD9DADC)
                                        )
                                )
                            }
                        }
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Row(verticalAlignment = Alignment.Bottom) {
                            Text(
                                text = "$bpm",
                                fontSize = 28.sp,
                                fontWeight = FontWeight.Bold,
                                color = OnSurfaceLight
                            )
                            Spacer(modifier = Modifier.width(4.dp))
                            Text(
                                text = "4/4",
                                style = MaterialTheme.typography.labelSmall,
                                color = GraphiteMute
                            )
                        }
                        IconButton(
                            onClick = { isMetronomePlaying = !isMetronomePlaying },
                            modifier = Modifier
                                .size(32.dp)
                                .clip(CircleShape)
                                .background(PrimaryRed)
                        ) {
                            Icon(
                                imageVector = if (isMetronomePlaying) Icons.Default.Pause else Icons.Default.PlayArrow,
                                contentDescription = null,
                                tint = Color.White,
                                modifier = Modifier.size(18.dp)
                            )
                        }
                    }
                    Spacer(modifier = Modifier.height(10.dp))
                    Surface(
                        shape = RoundedCornerShape(12.dp),
                        color = Color(0xFFF3F3F5)
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 6.dp, vertical = 2.dp),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = "−",
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier
                                    .clickable { if (bpm > 40) bpm -= 2 }
                                    .padding(horizontal = 6.dp)
                            )
                            Text(
                                text = "Moderato",
                                fontSize = 11.sp,
                                fontWeight = FontWeight.SemiBold,
                                color = OnSurfaceLight
                            )
                            Text(
                                text = "+",
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier
                                    .clickable { if (bpm < 240) bpm += 2 }
                                    .padding(horizontal = 6.dp)
                            )
                        }
                    }
                }
            }
        }

        // Stage Rig Tools (6 Quick Navigation Modules)
        Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Stage Rig Tools",
                    style = MaterialTheme.typography.headlineSmall,
                    color = MaterialTheme.colorScheme.onSurface
                )
                Text(
                    text = "Reorder",
                    style = MaterialTheme.typography.labelSmall,
                    color = MaterialTheme.colorScheme.primary,
                    fontWeight = FontWeight.Bold
                )
            }

            val tools = listOf(
                Triple("Tuner", "Chromatic", "tuner"),
                Triple("Chords", "3,200+ Voicings", "chords"),
                Triple("Metronome", "Tap & Poly", "metronome"),
                Triple("Tabs", "Interactive", "tabs"),
                Triple("Jam Spot", "Community", "jam-spot"),
                Triple("Pedalboard", "DSP Virtual", "pedalboard")
            )

            val gradients = listOf(
                listOf(PrimaryRed, ElectricOrange),
                listOf(PrimaryDeep, AmberGold),
                listOf(Color(0xFF2C3E50), Color(0xFF4CA1AF)),
                listOf(PrimaryDeep, PrimaryRed),
                listOf(AmberGold, ElectricOrange),
                listOf(Color(0xFF9A2600), CharcoalHardware)
            )

            val icons = listOf(
                Icons.Default.GraphicEq,
                Icons.Default.GridOn,
                Icons.Default.Speed,
                Icons.Default.QueueMusic,
                Icons.Default.Groups,
                Icons.Default.Speaker
            )

            Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                for (row in 0 until 2) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        for (col in 0 until 3) {
                            val index = row * 3 + col
                            val tool = tools[index]
                            Card(
                                modifier = Modifier
                                    .weight(1f)
                                    .clickable { onNavigateTo(tool.third) },
                                shape = RoundedCornerShape(18.dp),
                                colors = CardDefaults.cardColors(containerColor = Color.White),
                                elevation = CardDefaults.cardElevation(defaultElevation = 1.dp)
                            ) {
                                Column(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(vertical = 14.dp, horizontal = 8.dp),
                                    horizontalAlignment = Alignment.CenterHorizontally
                                ) {
                                    Box(
                                        modifier = Modifier
                                            .size(46.dp)
                                            .clip(RoundedCornerShape(14.dp))
                                            .background(Brush.linearGradient(gradients[index])),
                                        contentAlignment = Alignment.Center
                                    ) {
                                        Icon(
                                            imageVector = icons[index],
                                            contentDescription = tool.first,
                                            tint = Color.White,
                                            modifier = Modifier.size(24.dp)
                                        )
                                    }
                                    Spacer(modifier = Modifier.height(8.dp))
                                    Text(
                                        text = tool.first,
                                        fontWeight = FontWeight.Bold,
                                        fontSize = 13.sp,
                                        color = OnSurfaceLight
                                    )
                                    Text(
                                        text = tool.second,
                                        fontSize = 10.sp,
                                        color = GraphiteMute
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }

        // Chord of the Day Card
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(22.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White),
            elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
        ) {
            Column(modifier = Modifier.padding(18.dp)) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.Top
                ) {
                    Column {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Text(
                                text = "Cadd9",
                                fontSize = 26.sp,
                                fontWeight = FontWeight.Bold,
                                color = OnSurfaceLight
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Surface(
                                shape = RoundedCornerShape(8.dp),
                                color = Color(0xFFF3F3F5)
                            ) {
                                Text(
                                    text = "Acoustic Staple",
                                    fontSize = 11.sp,
                                    fontWeight = FontWeight.SemiBold,
                                    color = GraphiteMute,
                                    modifier = Modifier.padding(horizontal = 8.dp, vertical = 2.dp)
                                )
                            }
                        }
                        Text(
                            text = "Bright, shimmering open voicing loved in rock ballads.",
                            style = MaterialTheme.typography.bodyMedium,
                            color = GraphiteMute,
                            modifier = Modifier.padding(top = 4.dp)
                        )
                    }
                    Button(
                        onClick = { /* Play Strum */ },
                        shape = RoundedCornerShape(20.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = PrimaryRed),
                        contentPadding = PaddingValues(horizontal = 14.dp, vertical = 6.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.VolumeUp,
                            contentDescription = null,
                            tint = Color.White,
                            modifier = Modifier.size(16.dp)
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(text = "Strum", fontSize = 12.sp, color = Color.White)
                    }
                }

                Spacer(modifier = Modifier.height(14.dp))

                // Fretboard mini preview box
                Surface(
                    shape = RoundedCornerShape(14.dp),
                    color = Color(0xFFF8F9FA),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(
                        modifier = Modifier.padding(12.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = "✕  ●  ●  ○  ●  ○",
                            fontSize = 13.sp,
                            fontWeight = FontWeight.Bold,
                            color = PrimaryRed,
                            letterSpacing = 8.sp
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = "Fingering: x-3-2-0-3-0",
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Medium,
                            color = OnSurfaceLight
                        )
                    }
                }
            }
        }

        // Featured Song of the Week
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(22.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White),
            elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
        ) {
            Column(modifier = Modifier.padding(18.dp)) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(6.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.Star,
                            contentDescription = null,
                            tint = PrimaryRed,
                            modifier = Modifier.size(20.dp)
                        )
                        Text(
                            text = "Song of the Week",
                            style = MaterialTheme.typography.headlineSmall,
                            fontSize = 18.sp,
                            color = OnSurfaceLight
                        )
                    }
                    Surface(
                        shape = RoundedCornerShape(8.dp),
                        color = AmberGold.copy(alpha = 0.15f)
                    ) {
                        Text(
                            text = "●●○ MED",
                            fontSize = 10.sp,
                            fontWeight = FontWeight.Bold,
                            color = AmberGold,
                            modifier = Modifier.padding(horizontal = 8.dp, vertical = 2.dp)
                        )
                    }
                }

                Spacer(modifier = Modifier.height(10.dp))

                Text(
                    text = "Wish You Were Here",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = OnSurfaceLight
                )
                Text(
                    text = "Pink Floyd • 1975 • Key of G • 60 BPM",
                    fontSize = 12.sp,
                    color = GraphiteMute
                )

                Spacer(modifier = Modifier.height(12.dp))

                Button(
                    onClick = { onNavigateTo("tabs") },
                    shape = RoundedCornerShape(24.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = PrimaryRed),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(text = "Start Tab Session", fontWeight = FontWeight.SemiBold)
                    Spacer(modifier = Modifier.width(6.dp))
                    Icon(
                        imageVector = Icons.Default.ArrowForward,
                        contentDescription = null,
                        modifier = Modifier.size(16.dp)
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(24.dp))
    }
}

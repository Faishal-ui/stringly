package com.stringly.app.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
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

data class PedalEffect(
    val name: String,
    val type: String,
    val color: Color,
    val knob1: String,
    val knob2: String,
    val knob3: String,
    var isEnabled: Boolean = true
)

@Composable
fun PedalboardScreen() {
    val pedals = remember {
        mutableStateListOf(
            PedalEffect("Tube Screamer", "Overdrive", Color(0xFF27AE60), "Drive", "Tone", "Level", true),
            PedalEffect("Big Muff Pi", "Fuzz", Color(0xFFE74C3C), "Sustain", "Tone", "Volume", false),
            PedalEffect("Small Clone", "Chorus", Color(0xFF2980B9), "Rate", "Depth", "Blend", true),
            PedalEffect("Flashback", "Delay", Color(0xFF8E44AD), "Time", "Feedback", "FX Level", true)
        )
    }

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
                    text = "VIRTUAL DSP RIG",
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Bold,
                    color = PrimaryRed,
                    letterSpacing = 1.5.sp
                )
                Text(
                    text = "Pedalboard Tones",
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
                Text(
                    text = "Preset: Indie Lead",
                    modifier = Modifier.padding(horizontal = 10.dp, vertical = 6.dp),
                    fontWeight = FontWeight.Bold,
                    fontSize = 12.sp,
                    color = PrimaryRed
                )
            }
        }

        // Signal Chain Banner
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(containerColor = CharcoalHardware)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 12.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "SIGNAL: Guitar In → 4 FX → Amp Out",
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Bold,
                    color = ElectricOrange,
                    letterSpacing = 1.sp
                )
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .size(8.dp)
                            .clip(CircleShape)
                            .background(StageSuccess)
                    )
                    Text(
                        text = "48kHz DSP",
                        fontSize = 10.sp,
                        color = Color.White,
                        fontWeight = FontWeight.SemiBold
                    )
                }
            }
        }

        // Stompbox Stack List
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(14.dp),
            modifier = Modifier.fillMaxSize()
        ) {
            itemsIndexed(pedals) { index, pedal ->
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(20.dp),
                    colors = CardDefaults.cardColors(containerColor = Color.White),
                    elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy(10.dp)
                            ) {
                                Box(
                                    modifier = Modifier
                                        .size(14.dp)
                                        .clip(CircleShape)
                                        .background(if (pedal.isEnabled) pedal.color else Color(0xFFD9DADC))
                                )
                                Column {
                                    Text(
                                        text = pedal.name,
                                        fontSize = 17.sp,
                                        fontWeight = FontWeight.Bold,
                                        color = OnSurfaceLight
                                    )
                                    Text(
                                        text = pedal.type,
                                        fontSize = 11.sp,
                                        color = GraphiteMute
                                    )
                                }
                            }
                            Switch(
                                checked = pedal.isEnabled,
                                onCheckedChange = { pedal.isEnabled = it },
                                colors = SwitchDefaults.colors(
                                    checkedThumbColor = Color.White,
                                    checkedTrackColor = pedal.color
                                )
                            )
                        }

                        Spacer(modifier = Modifier.height(14.dp))

                        // 3 Tactile Knobs Row
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceEvenly
                        ) {
                            listOf(pedal.knob1, pedal.knob2, pedal.knob3).forEach { knobName ->
                                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                    Box(
                                        modifier = Modifier
                                            .size(46.dp)
                                            .clip(CircleShape)
                                            .background(Color(0xFFF3F3F5))
                                            .padding(6.dp),
                                        contentAlignment = Alignment.TopCenter
                                    ) {
                                        Box(
                                            modifier = Modifier
                                                .size(6.dp)
                                                .clip(CircleShape)
                                                .background(if (pedal.isEnabled) pedal.color else GraphiteMute)
                                        )
                                    }
                                    Spacer(modifier = Modifier.height(4.dp))
                                    Text(
                                        text = knobName,
                                        fontSize = 11.sp,
                                        fontWeight = FontWeight.SemiBold,
                                        color = OnSurfaceLight
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

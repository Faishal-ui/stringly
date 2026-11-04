package com.stringly.app.ui.screens

import androidx.compose.animation.core.*
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
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.stringly.app.ui.theme.*

@Composable
fun MetronomeScreen() {
    var bpm by remember { mutableIntStateOf(120) }
    var isPlaying by remember { mutableStateOf(false) }
    var currentBeat by remember { mutableIntStateOf(0) }
    var timeSignature by remember { mutableIntStateOf(4) }
    var tempoName by remember { mutableStateOf("Moderato") }

    LaunchedEffect(bpm) {
        tempoName = when {
            bpm < 60 -> "Largo"
            bpm < 76 -> "Adagio"
            bpm < 108 -> "Andante"
            bpm < 120 -> "Moderato"
            bpm < 168 -> "Allegro"
            bpm < 200 -> "Presto"
            else -> "Prestissimo"
        }
    }

    LaunchedEffect(isPlaying, bpm, timeSignature) {
        if (isPlaying) {
            val interval = (60_000L / bpm)
            while (true) {
                kotlinx.coroutines.delay(interval)
                currentBeat = (currentBeat + 1) % timeSignature
            }
        } else {
            currentBeat = 0
        }
    }

    // Pendulum visual rotation
    val pendulumRotation = remember { Animatable(0f) }
    LaunchedEffect(isPlaying, bpm) {
        if (isPlaying) {
            val halfBeatMs = (30_000 / bpm).toInt()
            while (true) {
                pendulumRotation.animateTo(24f, animationSpec = tween(halfBeatMs, easing = FastOutSlowInEasing))
                pendulumRotation.animateTo(-24f, animationSpec = tween(halfBeatMs, easing = FastOutSlowInEasing))
            }
        } else {
            pendulumRotation.snapTo(0f)
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(horizontal = 24.dp, vertical = 20.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        // Header
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                Text(
                    text = "METRONOME",
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Bold,
                    color = PrimaryRed,
                    letterSpacing = 1.5.sp
                )
                Text(
                    text = "Precision Clock",
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold,
                    color = OnSurfaceLight
                )
            }
            Surface(
                shape = RoundedCornerShape(12.dp),
                color = Color.White,
                shadowElevation = 1.dp
            ) {
                Row(modifier = Modifier.padding(horizontal = 10.dp, vertical = 6.dp)) {
                    Text(
                        text = "$timeSignature/4",
                        fontWeight = FontWeight.Bold,
                        color = OnSurfaceLight,
                        fontSize = 14.sp
                    )
                }
            }
        }

        // Tactile Pendulum / Visualizer
        Box(
            modifier = Modifier
                .size(240.dp)
                .clip(CircleShape)
                .background(Color.White),
            contentAlignment = Alignment.Center
        ) {
            // Visual Tick Ring
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
                    .clip(CircleShape)
                    .background(Color(0xFFF3F3F5))
            )

            // Pendulum Arm
            Box(
                modifier = Modifier
                    .width(4.dp)
                    .height(140.dp)
                    .rotate(pendulumRotation.value)
                    .background(
                        Brush.verticalGradient(
                            listOf(PrimaryRed, ElectricOrange)
                        )
                    )
            )

            // Center Knob Readout
            Box(
                modifier = Modifier
                    .size(110.dp)
                    .clip(CircleShape)
                    .background(Color.White)
                    .padding(8.dp),
                contentAlignment = Alignment.Center
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(
                        text = "$bpm",
                        fontSize = 38.sp,
                        fontWeight = FontWeight.ExtraBold,
                        color = OnSurfaceLight
                    )
                    Text(
                        text = tempoName,
                        fontSize = 11.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = PrimaryRed
                    )
                }
            }
        }

        // Beat Indicator Pips
        Row(
            horizontalArrangement = Arrangement.spacedBy(10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            repeat(timeSignature) { idx ->
                val isActive = currentBeat == idx && isPlaying
                val isAccent = idx == 0
                Box(
                    modifier = Modifier
                        .size(if (isActive) 16.dp else 12.dp)
                        .clip(CircleShape)
                        .background(
                            when {
                                isActive && isAccent -> ElectricOrange
                                isActive -> PrimaryRed
                                else -> Color(0xFFD9DADC)
                            }
                        )
                )
            }
        }

        // Stepper Controls
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(
                onClick = { if (bpm > 30) bpm -= 5 },
                modifier = Modifier
                    .size(48.dp)
                    .clip(CircleShape)
                    .background(Color.White)
            ) {
                Text(text = "−5", fontWeight = FontWeight.Bold, color = OnSurfaceLight)
            }
            IconButton(
                onClick = { if (bpm > 30) bpm -= 1 },
                modifier = Modifier
                    .size(42.dp)
                    .clip(CircleShape)
                    .background(Color.White)
            ) {
                Text(text = "−1", fontWeight = FontWeight.Bold, color = OnSurfaceLight)
            }
            IconButton(
                onClick = { if (bpm < 280) bpm += 1 },
                modifier = Modifier
                    .size(42.dp)
                    .clip(CircleShape)
                    .background(Color.White)
            ) {
                Text(text = "+1", fontWeight = FontWeight.Bold, color = OnSurfaceLight)
            }
            IconButton(
                onClick = { if (bpm < 280) bpm += 5 },
                modifier = Modifier
                    .size(48.dp)
                    .clip(CircleShape)
                    .background(Color.White)
            ) {
                Text(text = "+5", fontWeight = FontWeight.Bold, color = OnSurfaceLight)
            }
        }

        // Large Play / Stop CTA Button
        Button(
            onClick = { isPlaying = !isPlaying },
            shape = RoundedCornerShape(28.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = if (isPlaying) CharcoalHardware else PrimaryRed
            ),
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp)
        ) {
            Icon(
                imageVector = if (isPlaying) Icons.Default.Pause else Icons.Default.PlayArrow,
                contentDescription = null,
                tint = Color.White,
                modifier = Modifier.size(28.dp)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = if (isPlaying) "STOP METRONOME" else "START BEAT",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
        }

        Spacer(modifier = Modifier.height(8.dp))
    }
}

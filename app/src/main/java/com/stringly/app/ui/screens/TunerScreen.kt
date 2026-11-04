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
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.stringly.app.ui.theme.*

data class GuitarString(
    val note: String,
    val octave: Int,
    val freq: Float
)

@Composable
fun TunerScreen() {
    val strings = listOf(
        GuitarString("E", 2, 82.41f),
        GuitarString("A", 2, 110.00f),
        GuitarString("D", 3, 146.83f),
        GuitarString("G", 3, 196.00f),
        GuitarString("B", 3, 246.94f),
        GuitarString("E", 4, 329.63f)
    )

    var selectedStringIndex by remember { mutableIntStateOf(0) }
    val currentString = strings[selectedStringIndex]

    // Cents offset for tuning needle (-50 to +50 cents)
    var centsOffset by remember { mutableFloatStateOf(0f) }
    val needleRotation = remember { Animatable(0f) }

    LaunchedEffect(centsOffset) {
        needleRotation.animateTo(centsOffset * 0.8f, animationSpec = tween(150))
    }

    val isInTune = centsOffset in -3f..3f

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
                    text = "CHROMATIC TUNER",
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Bold,
                    color = PrimaryRed,
                    letterSpacing = 1.5.sp
                )
                Text(
                    text = "Standard Tuning",
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
                Text(
                    text = "A4 = 440 Hz",
                    modifier = Modifier.padding(horizontal = 10.dp, vertical = 6.dp),
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 12.sp,
                    color = GraphiteMute
                )
            }
        }

        // Circular Gauge Meter
        Box(
            modifier = Modifier
                .size(260.dp)
                .clip(CircleShape)
                .background(Color.White),
            contentAlignment = Alignment.Center
        ) {
            // Gauge Dial Ring
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(20.dp)
                    .clip(CircleShape)
                    .background(Color(0xFFF3F3F5))
            )

            // Needle Pointer
            Box(
                modifier = Modifier
                    .width(4.dp)
                    .height(110.dp)
                    .rotate(needleRotation.value)
                    .background(if (isInTune) StageSuccess else PrimaryRed)
            )

            // Center Readout
            Box(
                modifier = Modifier
                    .size(130.dp)
                    .clip(CircleShape)
                    .background(Color.White),
                contentAlignment = Alignment.Center
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Row(verticalAlignment = Alignment.Bottom) {
                        Text(
                            text = currentString.note,
                            fontSize = 44.sp,
                            fontWeight = FontWeight.ExtraBold,
                            color = OnSurfaceLight
                        )
                        Text(
                            text = "${currentString.octave}",
                            fontSize = 22.sp,
                            fontWeight = FontWeight.Bold,
                            color = if (isInTune) StageSuccess else PrimaryRed
                        )
                    }
                    Text(
                        text = "${currentString.freq} Hz",
                        fontSize = 12.sp,
                        color = GraphiteMute
                    )
                }
            }
        }

        // Tuning Status Badge
        Surface(
            shape = RoundedCornerShape(16.dp),
            color = if (isInTune) StageSuccess.copy(alpha = 0.15f) else PrimaryRed.copy(alpha = 0.15f)
        ) {
            Row(
                modifier = Modifier.padding(horizontal = 14.dp, vertical = 8.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(6.dp)
            ) {
                Icon(
                    imageVector = if (isInTune) Icons.Default.CheckCircle else Icons.Default.Tune,
                    contentDescription = null,
                    tint = if (isInTune) StageSuccess else PrimaryRed,
                    modifier = Modifier.size(16.dp)
                )
                Text(
                    text = if (isInTune) "PERFECTLY IN TUNE (±0¢)" else "OFFSET: ${centsOffset.toInt()}¢",
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold,
                    color = if (isInTune) StageSuccess else PrimaryRed
                )
            }
        }

        // 6-String Quick Selector
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            strings.forEachIndexed { idx, s ->
                val isSelected = idx == selectedStringIndex
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.clickable {
                        selectedStringIndex = idx
                        centsOffset = (-15..15).random().toFloat()
                    }
                ) {
                    Box(
                        modifier = Modifier
                            .size(44.dp)
                            .clip(CircleShape)
                            .background(if (isSelected) PrimaryRed else Color.White),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = s.note,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold,
                            color = if (isSelected) Color.White else OnSurfaceLight
                        )
                    }
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = "${6 - idx}th",
                        fontSize = 10.sp,
                        color = GraphiteMute
                    )
                }
            }
        }

        // Auto / Mic Listen Button
        Button(
            onClick = {
                // Simulate microphone lock
                centsOffset = 0f
            },
            shape = RoundedCornerShape(26.dp),
            colors = ButtonDefaults.buttonColors(containerColor = PrimaryRed),
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
        ) {
            Icon(
                imageVector = Icons.Default.Mic,
                contentDescription = "Mic",
                modifier = Modifier.size(20.dp)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = "LISTEN THROUGH MIC",
                fontSize = 15.sp,
                fontWeight = FontWeight.Bold
            )
        }

        Spacer(modifier = Modifier.height(8.dp))
    }
}

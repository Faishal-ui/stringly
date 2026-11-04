package com.stringly.app.ui.theme

import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.ui.graphics.Color

val PrimaryRed = Color(0xFFC0392B)
val PrimaryDeep = Color(0xFF9E2016)
val ElectricOrange = Color(0xFFFF6B35)
val AmberGold = Color(0xFFFF8C00)
val StageSuccess = Color(0xFF27AE60)
val GraphiteMute = Color(0xFF6B6B6B)
val SurfaceLight = Color(0xFFF9F9FB)
val SurfaceCard = Color(0xFFFFFFFF)
val SurfaceDark = Color(0xFF14171D)
val OnSurfaceLight = Color(0xFF1A1C1D)
val CharcoalHardware = Color(0xFF1A1A1A)

val LightColorScheme = lightColorScheme(
    primary = PrimaryDeep,
    onPrimary = Color.White,
    primaryContainer = PrimaryRed,
    onPrimaryContainer = Color.White,
    secondary = ElectricOrange,
    background = SurfaceLight,
    surface = SurfaceLight,
    surfaceVariant = Color(0xFFEEEEF0),
    onSurface = OnSurfaceLight,
    onSurfaceVariant = GraphiteMute
)

val DarkColorScheme = darkColorScheme(
    primary = ElectricOrange,
    onPrimary = Color.Black,
    primaryContainer = PrimaryDeep,
    onPrimaryContainer = Color.White,
    secondary = AmberGold,
    background = SurfaceDark,
    surface = SurfaceDark,
    surfaceVariant = Color(0xFF20242D),
    onSurface = Color.White,
    onSurfaceVariant = Color(0xFFA0A5B1)
)

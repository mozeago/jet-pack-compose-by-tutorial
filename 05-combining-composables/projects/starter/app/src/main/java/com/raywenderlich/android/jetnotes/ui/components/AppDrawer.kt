package com.raywenderlich.android.jetnotes.ui.components

import android.graphics.drawable.Icon
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.MaterialTheme.shapes
import androidx.compose.material.SnackbarDefaults.backgroundColor
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.raywenderlich.android.jetnotes.routing.JetNotesRouter
import com.raywenderlich.android.jetnotes.routing.JetNotesRouter.currentScreen
import com.raywenderlich.android.jetnotes.routing.Screen
import com.raywenderlich.android.jetnotes.theme.JetNotesTheme
import com.raywenderlich.android.jetnotes.theme.JetNotesThemeSettings

@Composable
private fun AppDrawerHeader() {
    Row(modifier = Modifier.fillMaxWidth()) {
        Image(
            imageVector = Icons.Filled.Menu, contentDescription = "Drawer Header Icon",
            colorFilter = ColorFilter.tint(MaterialTheme.colors.onSurface),
            modifier = Modifier.padding(16.dp)
        )
        Text(text = "JetNotes", modifier = Modifier.align(alignment = Alignment.CenterVertically))
    }
}

@Preview(showBackground = true)
@Composable
fun AppDrawerPreview() {
    JetNotesTheme {
        AppDrawerHeader()
    }
}

@Composable
private fun ScreenNavigationButton(
    icon: ImageVector,
    label: String,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    val colors = MaterialTheme.colors
    //Define alpha for the image  for two different states of the button :selected/unselected.
    val imageAlpha = if (isSelected) {
        1f
    } else {
        0.6f
    }
    val textColor = if (isSelected) {
        colors.primary
    } else {
        colors.onSurface.copy(0.6f)
    }
    val backgroundColor = if (isSelected) {
        colors.primary.copy(0.12f)
    } else {
        colors.surface
    }
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 8.dp, end = 8.dp, top = 8.dp), color = backgroundColor,
        shape = shapes.small
    ) {
        Row(
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .clickable(onClick = onClick)
                .fillMaxWidth()
                .padding(4.dp)
        ) {
            Image(
                imageVector = icon,
                contentDescription = "Screen Navigation Button",
                colorFilter = ColorFilter.tint(textColor),
                alpha = imageAlpha
            )
            Spacer(modifier = Modifier.width(16.dp))
            Text(
                text = label,
                style = MaterialTheme.typography.body2,
                color = textColor,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ScreenNavigationButtonPreview() {
    JetNotesTheme {
        ScreenNavigationButton(
            icon = Icons.Filled.Home,
            label = "Notes",
            isSelected = true,
            onClick = {})
    }
}

@Composable
private fun LightDarkThemeItem() {
    Row(Modifier.padding(8.dp)) {
        Text(
            text = "Turn On dark theme",
            style = MaterialTheme.typography.body2,
            color = MaterialTheme.colors.onSurface.copy(alpha = 0.6f),
            modifier = Modifier
                .weight(1f)
                .padding(start = 8.dp, top = 8.dp, end = 8.dp, bottom = 8.dp)
                .align(alignment = Alignment.CenterVertically)
        )
        Switch(
            checked = JetNotesThemeSettings.isDarkThemeEnabled,
            onCheckedChange = {
                JetNotesThemeSettings.isDarkThemeEnabled = it
            },
            modifier = Modifier
                .padding(start = 8.dp, end = 8.dp)
                .align(alignment = Alignment.CenterVertically)
        )
    }
}

@Preview(showBackground = true)
@Composable

fun LightDarkThemeItemPreview() {
    JetNotesTheme {
        LightDarkThemeItem()
    }
}

@Composable
fun AppDrawer(
    currentScreen: Screen,
    closeDrawerAction: () -> Unit
) {
    Column(modifier = Modifier.fillMaxSize()) {
        AppDrawerHeader()
        Divider(color = MaterialTheme.colors.onSurface.copy(alpha = .2f))
        ScreenNavigationButton(
            icon = Icons.Filled.Home,
            label = "Notes",
            isSelected = currentScreen == Screen.Notes,
            onClick = {
                JetNotesRouter.navigateTo(Screen.Notes)
                closeDrawerAction()
            }
        )
        ScreenNavigationButton(
            icon = Icons.Filled.Delete,
            label = "Trash",
            isSelected = currentScreen == Screen.Trash,
            onClick = {
                JetNotesRouter.navigateTo(Screen.Trash)
                closeDrawerAction()
            }
        )
        LightDarkThemeItem()
    }
}

@Preview(showBackground = true)
@Composable
fun AppDrawerPreviewFun() {
    JetNotesTheme {
        AppDrawer(currentScreen = Screen.Notes) {

        }
    }
}

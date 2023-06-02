package dev.diegodc.core.ui

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun SimplifiedTopNavigation(
    modifier: Modifier = Modifier,
    title: String,
    @DrawableRes icon: Int = R.drawable.ic_carat_left,
    onIconClick: () -> Unit = {},
    backgroundColor: Color? = null,
    elevation: Dp = 24.dp
) {
    Surface(
        modifier = modifier
            .fillMaxWidth()
            .background(backgroundColor ?: MaterialTheme.colorScheme.primary),
        shadowElevation = elevation
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    top = 14.dp,
                    bottom = 14.dp,
                    start = 18.dp
                ),
            contentAlignment = Alignment.CenterStart
        ) {
            SimplifiedTopNavigationIcon(
                icon = icon,
                onIconClick = onIconClick
            )
            Text(
                modifier = Modifier.align(Alignment.Center),
                text = title,
                style = MaterialTheme.typography.titleMedium.copy(fontSize = 18.sp),
                color = Color.DarkGray
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun BoxScope.SimplifiedTopNavigationIcon(
    @DrawableRes icon: Int,
    onIconClick: () -> Unit
) {
    Surface(
        modifier = Modifier
            .size(34.dp)
            .align(Alignment.CenterStart)
            .testTag("button_back"),
        shadowElevation = 15.dp,
        shape = CircleShape,
        onClick = onIconClick,
    ) {
        Image(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp),
            painter = painterResource(id = icon),
            contentDescription = null,
            alignment = Alignment.Center
        )
    }
}
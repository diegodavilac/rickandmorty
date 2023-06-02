package dev.diegodc.core.common.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun LoadingToggle(
    modifier: Modifier = Modifier,
    isLoading: Boolean = false,
    loaderSize: Dp = 48.dp,
    stroke: Dp = 6.dp,
    padding: Dp = 0.dp,
    content: @Composable () -> Unit
){
    Box(modifier = modifier) {
        if (isLoading) {
            CircularProgressIndicator(
                modifier = Modifier.size(loaderSize).padding(horizontal = padding),
                color = MaterialTheme.colorScheme.primary,
                strokeWidth = stroke
            )
        } else {
            content()
        }
    }
}
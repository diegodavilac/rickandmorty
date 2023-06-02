package dev.diegodc.features.characters.characterDetail

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.SubcomposeAsyncImage
import dev.diegodc.core.ui.SimplifiedTopNavigation

@Composable
internal fun CharacterDetailScreen(
    onBackClick: () -> Unit,
    viewModel: CharacterDetailViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsState()

    CharacterDetailContent(
        onBackClick = onBackClick,
        state = state
    )
}

@Composable
internal fun CharacterDetailContent(
    modifier: Modifier = Modifier,
    state: CharacterDetailViewState,
    onBackClick: () -> Unit,
) {
    Column(modifier = modifier.fillMaxSize()) {
        SimplifiedTopNavigation(
            title = state.detail?.name ?: "",
            onIconClick = onBackClick
        )

        SubcomposeAsyncImage(
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(top = 24.dp),
            model = state.detail?.image,
            contentDescription = null,
            alignment = Alignment.TopCenter,
            loading = {
                CircularProgressIndicator(
                    modifier = Modifier
                        .size(90.dp)
                        .padding(10.dp),
                    color = MaterialTheme.colorScheme.primary,
                    strokeWidth = 6.dp
                )
            },
            success = {
                Image(
                    painter = it.painter,
                    contentDescription = null,
                    modifier = Modifier
                        .size(180.dp)
                        .border(
                            width = 4.dp,
                            color = MaterialTheme.colorScheme.primary,
                            shape = CircleShape
                        )
                        .padding(4.dp)
                        .clip(CircleShape)
                )
            }
        )
    }
}
package dev.diegodc.features.characters.root

import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyGridItemScope
import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import dev.diegodc.core.common.ui.LoadingToggle
import dev.diegodc.core.ui.SimplifiedTopNavigation
import dev.diegodc.data.repository.model.RickAndMortyCharacter

@Composable
internal fun CharactersScreen(
    modifier: Modifier = Modifier,
    viewModel: CharacterViewModel = hiltViewModel(),
    onCharacterClicked: (Int) -> Unit
) {
    val state by viewModel.state.collectAsState()
    val listState = rememberLazyGridState()

    listState.OnBottomReached {
        if (state.items.isNotEmpty()) {
            viewModel.loadMore()
        }
    }

    CharactersContent(
        modifier = modifier,
        state = state,
        onCharacterClicked = {
            onCharacterClicked(it.id)
        },
        gridState = listState,
    )
}

@Composable
internal fun CharactersContent(
    modifier: Modifier = Modifier,
    state: CharactersViewState,
    onCharacterClicked: (RickAndMortyCharacter) -> Unit,
    gridState: LazyGridState = rememberLazyGridState()
) {
    Box(modifier = modifier.fillMaxSize()) {
        LoadingToggle(
            modifier = Modifier.align(Alignment.Center),
            isLoading = state.isLoading
        ) {
            LazyVerticalGrid(
                state = gridState,
                columns = GridCells.Fixed(2),
                modifier = Modifier.padding(horizontal = 16.dp)
            ) {
                items(state.items.size) {
                    CharacterItem(
                        item = state.items[it],
                        onItemClick = {
                            onCharacterClicked(state.items[it])
                        }
                    )
                }
            }
        }

        androidx.compose.animation.AnimatedVisibility(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 24.dp),
            visible = state.isLoadingMore,
            enter = fadeIn() + slideInVertically(
                initialOffsetY = { it }
            ),
            exit = slideOutVertically(
                targetOffsetY = { it }
            ) + fadeOut(),
        ) {
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .background(
                        color = Color.White,
                        shape = RoundedCornerShape(100)
                    ),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator(
                    modifier = Modifier
                        .size(32.dp),
                    color = MaterialTheme.colorScheme.primary,
                    strokeWidth = 4.dp
                )
            }
        }
    }

}

@Composable
internal fun LazyGridItemScope.CharacterItem(
    item: RickAndMortyCharacter,
    onItemClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .clickable {
                onItemClick()
            }
            .padding(
                8.dp
            ),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        colors = CardDefaults.cardColors(
            contentColor = Color.White
        )
    ) {
        Column(modifier = Modifier.padding(8.dp)) {
            AsyncImage(
                model = item.image,
                contentDescription = "",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(80.dp),
                contentScale = ContentScale.Fit
            )

            Text(
                text = item.name, style = MaterialTheme.typography.bodyLarge.copy(
                    color = Color.Black,
                )
            )
        }
    }
}

@Composable
fun LazyGridState.OnBottomReached(
    buffer: Int = 0,
    onLoadMore: () -> Unit
) {
    require(buffer >= 0) { "buffer cannot be negative, but was $buffer" }

    val shouldLoadMore = remember {
        derivedStateOf {
            val lastVisibleItem = layoutInfo.visibleItemsInfo.lastOrNull()
                ?: return@derivedStateOf true

            // subtract buffer from the total items
            lastVisibleItem.index >= layoutInfo.totalItemsCount - 1 - buffer
        }
    }

    LaunchedEffect(shouldLoadMore) {
        snapshotFlow { shouldLoadMore.value }
            .collect { if (it) onLoadMore() }
    }
}
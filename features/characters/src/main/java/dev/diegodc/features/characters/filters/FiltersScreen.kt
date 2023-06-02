package dev.diegodc.features.characters.filters

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.capitalize
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.intl.Locale
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import dev.diegodc.features.characters.R
import dev.diegodc.core.ui.R as uiR

@Composable
internal fun FiltersScreen(
    onBackClick: () -> Unit,
    onFiltering: () -> Unit,
    viewModel: FiltersViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsState()

    FiltersContent(
        state = state,
        onBackClick = onBackClick,
        onClearFilters = viewModel::clearFilters,
        onFilter = {
            viewModel.sendFilters()
            onFiltering()
        },
        onNameChanged = viewModel::updateName,
        onStatusChanged = viewModel::updateStatus,
        onGenderChanged = viewModel::updateGender,
        onSpeciesChanged = viewModel::updateSpecies
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun FiltersContent(
    modifier: Modifier = Modifier,
    state: FiltersViewState,
    onBackClick: () -> Unit,
    onClearFilters: () -> Unit,
    onFilter: () -> Unit,
    onNameChanged: (String) -> Unit,
    onStatusChanged: (String) -> Unit,
    onGenderChanged: (String) -> Unit,
    onSpeciesChanged: (String) -> Unit
) {

    Column(
        modifier = modifier.padding(
            horizontal = 24.dp
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 24.dp, bottom = 29.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = stringResource(id = R.string.filters_title),
                fontSize = 26.sp,
                color = Color.Black
            )
            IconButton(
                modifier = Modifier
                    .size(32.dp),
                onClick = onBackClick,
                content = {
                    Image(
                        painter = painterResource(id = uiR.drawable.ic_close),
                        contentDescription = null,
                    )
                }
            )
        }

        LazyColumn(
        ) {
            item {
                Column(
                    horizontalAlignment = Alignment.Start,
                    modifier = Modifier.padding(
                        vertical = 8.dp
                    )
                ) {
                    Text(
                        text = "Name", style = MaterialTheme.typography.headlineSmall.copy(
                            color = Color.Black, fontWeight = FontWeight.Bold,
                        )
                    )
                    TextField(value = state.name?:"", onValueChange = onNameChanged)
                }
            }

            item {
                Column(
                    horizontalAlignment = Alignment.Start,
                    modifier = Modifier.padding(
                        vertical = 8.dp
                    )
                ) {
                    Text(
                        text = "Status", style = MaterialTheme.typography.headlineSmall.copy(
                            color = Color.Black, fontWeight = FontWeight.Bold,
                        )
                    )
                    ButtonList(
                        selectedValue = state.status?.name,
                        values = CharacterStatus.values().map { it.name },
                        onSelectedValue = onStatusChanged
                    )
                }
            }

            item {
                Column(
                    horizontalAlignment = Alignment.Start,
                    modifier = Modifier.padding(
                        vertical = 8.dp
                    )
                ) {
                    Text(
                        text = "Gender", style = MaterialTheme.typography.headlineSmall.copy(
                            color = Color.Black, fontWeight = FontWeight.Bold,
                        )
                    )
                    ButtonList(
                        selectedValue = state.gender?.name,
                        values = CharacterGender.values().map { it.name },
                        onSelectedValue = onGenderChanged
                    )
                }
            }

            item {
                Column(
                    horizontalAlignment = Alignment.Start,
                    modifier = Modifier.padding(
                        vertical = 8.dp
                    )
                ) {
                    Text(
                        text = "Species", style = MaterialTheme.typography.headlineSmall.copy(
                            color = Color.Black, fontWeight = FontWeight.Bold,
                        )
                    )
                    ButtonList(
                        selectedValue = state.species?.name,
                        values = CharacterSpecies.values().map { it.name },
                        onSelectedValue = onSpeciesChanged
                    )
                }
            }

            item {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 10.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {

                    Text(
                        modifier = Modifier
                            .clickable { onClearFilters() },
                        text = stringResource(id = R.string.filters_clear),
                        style = MaterialTheme.typography.bodyMedium,
                        textDecoration = TextDecoration.Underline,
                        color = MaterialTheme.colorScheme.primary
                    )


                    ElevatedButton(
                        modifier = modifier
                            .height(48.dp)
                            .padding(
                                vertical = 4.dp,
                            ),
                        onClick = {onFilter()}
                    ) {
                        Text(
                            text = stringResource(id = R.string.filters_show_results),
                            style = MaterialTheme.typography.labelLarge.copy(
                                color = Color.Black,
                                fontSize = 16.sp,
                            ),
                        )
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun ButtonList(
    selectedValue: String?,
    values: List<String>,
    onSelectedValue: (String) -> Unit
) {
    FlowRow(
        verticalArrangement = Arrangement.Center,
        horizontalArrangement = Arrangement.Center
    ) {
        values.forEach {
            val isSelected = selectedValue.equals(it, true)
            val buttonContainerColor =
                if (isSelected)
                    Color.DarkGray
                else
                    Color.LightGray
            Button(
                modifier = Modifier
                    .padding(end = 12.dp, top = 16.dp),
                shape = RoundedCornerShape(6.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = buttonContainerColor
                ),
                onClick = {
                    onSelectedValue(it)
                }
            ) {
                Text(
                    text = it.capitalize(locale = Locale.current),
                    color = Color.Black
                )
            }
        }
    }
}
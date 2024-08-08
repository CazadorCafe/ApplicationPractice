package org.jdc.template.ux.directory

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import org.jdc.template.R
import org.jdc.template.model.db.main.directoryitem.DirectoryItemEntityView
import org.jdc.template.ui.compose.appbar.AppBarMenu
import org.jdc.template.ui.compose.appbar.AppBarMenuItem
import org.jdc.template.ui.navigation.HandleNavigation
import org.jdc.template.ux.MainAppScaffoldWithNavBar

@Composable
fun DirectoryScreen(
    navController: NavController,
    viewModel: DirectoryViewModel = hiltViewModel(),
) {
    val uiState = viewModel.uiState


    val appBarMenuItems = listOf(
        // icons
        AppBarMenuItem.Icon(Icons.Outlined.Search, R.string.search) {},

        // overflow
        AppBarMenuItem.OverflowMenuItem(R.string.settings) { uiState.onSettingsClicked() }
    )

    MainAppScaffoldWithNavBar(
        title = stringResource(R.string.directory),
        navigationIconVisible = false,
        actions = { AppBarMenu(appBarMenuItems) },
        onNavigationClick = { navController.popBackStack() },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { uiState.onNewClicked() },
            ) {
                Icon(Icons.Filled.Add, contentDescription = stringResource(R.string.add))
            }
        },
    ) {
        DirectoryContent(
            uiState,
        )
    }

    HandleNavigation(viewModel, navController)
}

@Composable
private fun DirectoryContent(
    uiState: DirectoryUiState
) {
    val directoryList by uiState.directoryListFlow.collectAsStateWithLifecycle()
    val configuration = LocalConfiguration.current
    val screenWidthDp = configuration.screenWidthDp

    val isTablet = screenWidthDp >= 600

    if (isTablet) {
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            contentPadding = PaddingValues(8.dp),
            modifier = Modifier.fillMaxSize()
        ) {
            items(directoryList.size) { index ->
                GridItem(index, directoryList, uiState)
            }
        }
    } else {
        LazyColumn(modifier = Modifier.fillMaxWidth()) {
            items(directoryList) { individual ->
                ListItem(
                    headlineContent = {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            if (individual.profilePicture?.value?.isNotBlank() == true) {
                                Image(
                                    painter = rememberImagePainter(individual.profilePicture.value),
                                    contentDescription = null,
                                    modifier = Modifier
                                        .size(64.dp)
                                        .clip(CircleShape)
                                        .background(MaterialTheme.colorScheme.onSurface.copy(alpha = 0.2f))
                                )
                            } else {
                                Box(
                                    modifier = Modifier
                                        .size(64.dp)
                                        .clip(CircleShape)
                                        .background(MaterialTheme.colorScheme.onSurface.copy(alpha = 0.2f)),
                                    contentAlignment = Alignment.Center
                                )
                                {
                                    Text(
                                        text = getInitials(fullName = individual.getFullName()),
                                        style = TextStyle(
                                            fontSize = 24.sp,
                                            fontWeight = FontWeight.Bold,
                                            color = MaterialTheme.colorScheme.onSurface
                                        ),
                                        textAlign = TextAlign.Center
                                    )
                                }
                            }
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(text = individual.getFullName())
                        }
                    },
                    Modifier
                        .clickable { uiState.onIndividualClicked(individual.individualId) },
                )
            }
        }
    }
}

@Composable
fun GridItem(
    index: Int,
    directoryList: List<DirectoryItemEntityView>,
    uiState: DirectoryUiState,
) {
    val hasProfilePicture = directoryList[index].profilePicture?.value?.isNotBlank() == true
    Card(
        modifier = Modifier
            .padding(4.dp)
            .fillMaxWidth()
            .aspectRatio(1f)
            .background(MaterialTheme.colorScheme.surface)
            .clickable { uiState.onIndividualClicked(directoryList[index].individualId) },
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            if (hasProfilePicture) {
                Image(
                    painter = rememberImagePainter(directoryList[index].profilePicture?.value),
                    contentDescription = null,
                    modifier = Modifier
                        .size(64.dp)
                        .clip(CircleShape)
                        .background(MaterialTheme.colorScheme.onSurface.copy(alpha = 0.2f))
                        .align(Alignment.CenterHorizontally)
                )
            } else {
                Box(
                    modifier = Modifier
                        .size(64.dp)
                        .clip(CircleShape)
                        .background(MaterialTheme.colorScheme.onSurface.copy(alpha = 0.2f)),
                    contentAlignment = Alignment.Center
                )
                {
                    Text(
                        text = getInitials(fullName = directoryList[index].getFullName()),
                        style = TextStyle(
                            fontSize = 24.sp,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.onSurface
                        ),
                        textAlign = TextAlign.Center
                    )
                }
            }
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = directoryList[index].getFullName(),
                fontSize = 16.sp,
                textAlign = TextAlign.Center

            )
        }
    }
}

@Composable
fun getInitials(fullName: String): String {
    val names = fullName.split(" ")
    return if (names.size >= 2) {
        "${names[0].first()}${names[1].first()}".uppercase()
    } else {
        names.firstOrNull()?.take(2)?.uppercase() ?: ""
    }
}

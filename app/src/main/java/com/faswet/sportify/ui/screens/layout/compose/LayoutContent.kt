package com.faswet.sportify.ui.screens.layout.compose

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import coil3.request.crossfade
import com.faswet.sportify.R
import com.faswet.sportify.ui.base.compose.SportifyBottomNavBar
import com.faswet.sportify.ui.base.compose.SportifyDrawer
import com.faswet.sportify.ui.screens.layout.contract.LayoutContract
import com.faswet.sportify.ui.theme.dimens
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LayoutContent(
    modifier: Modifier = Modifier,
    state: LayoutContract.State,
    onEventSent: (event: LayoutContract.Event) -> Unit,
) {
    val pager: PagerState = rememberPagerState(
        initialPage = state.currentScreen,
        initialPageOffsetFraction = 0f,
        pageCount = { 4 }
    )
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    LaunchedEffect(state.currentScreen) {
        pager.scrollToPage(state.currentScreen)
    }
    LaunchedEffect(pager.currentPage) {
        onEventSent(LayoutContract.Event.OnScreenChanged(pager.currentPage))
    }
    BackHandler{
        if (drawerState.isOpen){
            scope.launch {
                drawerState.close()
            }
        }else if (pager.currentPage != 0){
            onEventSent(LayoutContract.Event.OnScreenChanged(0))
        }
    }

    ModalNavigationDrawer(
        drawerContent = {
            SportifyDrawer(state = state, onEventSent = onEventSent)
        },
        drawerState = drawerState,
        scrimColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.5f),
    ) {
        Scaffold(
            modifier = modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.primary),
            bottomBar = {
                SportifyBottomNavBar(state.currentScreen) {
                    onEventSent(LayoutContract.Event.OnBottomNavClicked(it))
                }
            },
            topBar = {
                TopAppBar(
                    title = {},
                    modifier
                        .fillMaxWidth()
                        .background(MaterialTheme.colorScheme.primary)
                        .padding(vertical = MaterialTheme.dimens.size8dp),
                    actions = {
                        IconButton(
                            onClick = {

                            },
                        ) {
                            Icon(painter = painterResource(R.drawable.ic_notification), contentDescription = null)
                        }
                    },
                    navigationIcon = {
                        IconButton(
                            onClick = {
                                scope.launch {
                                    drawerState.apply {
                                        if (isClosed) open() else close()
                                    }
                                }
                            }
                        ) {
                            if (state.userModel?.profilePicture?.profileId == 0) {
                                AsyncImage(
                                    model = ImageRequest.Builder(LocalContext.current)
                                        .data(state.userModel.profilePicture.profileUrl ?: "")
                                        .crossfade(true)
                                        .build(),
                                    placeholder = painterResource(R.drawable.ic_sportify_logo),
                                    error = painterResource(R.drawable.ic_sportify_logo),
                                    contentDescription = null,
                                    modifier = modifier
                                        .size(MaterialTheme.dimens.size56dp)
                                        .clip(CircleShape),
                                    contentScale = ContentScale.Fit
                                )
                            } else Image(
                                painter = painterResource(state.avatar),
                                contentDescription = null
                            )
                        }
                    },
                    windowInsets = WindowInsets(0)
                )
            }

        ) { padding ->
            Surface(modifier = modifier.padding(padding)) {
                HorizontalPager(pager, modifier = modifier.fillMaxSize()) {
                    when (it) {
                        0 -> HomeScreen()
                        1 -> FavoriteScreen()
                        2 -> BookingScreen()
                        3 -> EventsScreen()
                    }
                }
            }
        }
    }
}


@Composable
fun HomeScreen() {
    Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Text("Home Screen")
    }
}

@Composable
fun FavoriteScreen() {
    Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Text("Favorite Screen")
    }
}

@Composable
fun EventsScreen() {
    Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Text("Events Screen")
    }
}

@Composable
fun BookingScreen() {
    Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Text("Booking Screen")
    }
}
package com.faswet.sportify.ui.screens.layout.compose

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import com.faswet.sportify.R
import com.faswet.sportify.ui.base.compose.SportifyBottomNavBar
import com.faswet.sportify.ui.screens.layout.contract.LayoutContract
import com.faswet.sportify.ui.theme.dimens

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
    LaunchedEffect(state.currentScreen) {
        pager.scrollToPage(state.currentScreen)
    }
    LaunchedEffect(pager.currentPage) {
        onEventSent(LayoutContract.Event.OnScreenChanged(pager.currentPage))
    }
    Scaffold(
        modifier = modifier.fillMaxSize()
            .background(MaterialTheme.colorScheme.primary),
        bottomBar = {
            SportifyBottomNavBar(state.currentScreen) {
                onEventSent(LayoutContract.Event.OnBottomNavClicked(it))
            }
        },
        topBar ={
            TopAppBar(
                title = {},
                modifier.fillMaxWidth()
                    .background(MaterialTheme.colorScheme.primary)
                    .clip(RoundedCornerShape(MaterialTheme.dimens.size56dp)),
                actions = {},
                navigationIcon = {
                    IconButton(
                        onClick = {}
                    ) {
                        if(state.currentScreen == 0)Image(painter = painterResource(R.drawable.ic_sportify_logo), contentDescription = null)
                    }
                },
                windowInsets = TopAppBarDefaults.windowInsets
            )
        }

) { padding ->
        Surface(modifier = modifier.padding(padding)) {
            HorizontalPager(pager, modifier = modifier.fillMaxSize()) {
                when (it) {
                    0 -> HomeScreen()
                    1 -> SearchScreen()
                    2 -> ProfileScreen()
                    3 -> SettingsScreen()
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
fun ProfileScreen() {
    Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Text("Profile Screen")
    }
}

@Composable
fun SettingsScreen() {
    Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Text("Settings Screen")
    }
}

@Composable
fun SearchScreen() {
    Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Text("Search Screen")
    }
}
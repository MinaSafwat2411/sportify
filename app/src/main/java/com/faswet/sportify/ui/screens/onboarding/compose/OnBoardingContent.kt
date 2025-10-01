package com.faswet.sportify.ui.screens.onboarding.compose

import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.ime
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.faswet.sportify.R
import com.faswet.sportify.ui.base.compose.SportifyCustomToken
import com.faswet.sportify.ui.screens.onboarding.contract.OnBoardingContract
import com.faswet.sportify.ui.theme.dimens
import kotlin.math.pow

@Composable
fun OnBoardingContent(
    modifier: Modifier = Modifier,
    state: OnBoardingContract.State,
    onEventSent: (event: OnBoardingContract.Event) -> Unit,
) {
    val pager: PagerState = rememberPagerState(
        initialPage = state.currentScreen,
        initialPageOffsetFraction = 0f,
        pageCount = { 5 }
    )
    LaunchedEffect(state.currentScreen) {
        pager.animateScrollToPage(state.currentScreen,animationSpec = tween(
            durationMillis = 500,
            easing = { fraction -> 1 - (1 - fraction).pow(4) }
        ))
    }
    LaunchedEffect(pager.currentPage) {
        onEventSent(OnBoardingContract.Event.OnScreenChanged(pager.currentPage))
    }
    Scaffold(
        containerColor = MaterialTheme.colorScheme.background,
        contentWindowInsets = WindowInsets.ime, modifier = modifier
            .windowInsetsPadding(WindowInsets.safeDrawing)
            .fillMaxSize(),
        bottomBar = {
        }) { padding ->
        Surface(
            modifier = modifier
                .fillMaxSize()
                .padding(padding),
            color = MaterialTheme.colorScheme.background,
        ) {
            Column(
                modifier = Modifier.fillMaxSize()
                    .padding(MaterialTheme.dimens.size16dp),
                Arrangement.SpaceBetween,
                Alignment.CenterHorizontally
            ) {
                SportifyCustomToken(currentIndex = pager.currentPage, numberOfScreen = 5)
                HorizontalPager(pager, modifier = modifier.fillMaxWidth()) { it ->
                    when (it) {
                        0 -> OnBoardingItem(state = state, index = 0)
                        1 -> OnBoardingItem(state = state, index = 1)
                        2 -> OnBoardingItem(state = state, index = 2)
                        3 -> OnBoardingItem(state = state, index = 3)
                        4 -> OnBoardingItem(state = state, index = 4)
                    }
                }
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    TextButton(
                        onClick = {
                            onEventSent(OnBoardingContract.Event.OnNextClicked)
                        },
                        modifier = modifier
                            .background(color = MaterialTheme.colorScheme.onPrimary, shape = RoundedCornerShape(MaterialTheme.dimens.size25dp))
                            .padding(start = MaterialTheme.dimens.size16dp, end = MaterialTheme.dimens.size16dp,)
                    ) {
                        Text(
                            text = stringResource(id = if (pager.currentPage == 4) R.string.get_started else R.string.next),
                            style = MaterialTheme.typography.bodyMedium.copy(
                                MaterialTheme.colorScheme.primary
                            )
                        )
                    }
                    Spacer(modifier = Modifier.height(MaterialTheme.dimens.size4dp))
                    Text(
                        text = stringResource(id = R.string.skip),
                        style = MaterialTheme.typography.bodySmall,
                        modifier = modifier.clickable{
                            onEventSent(OnBoardingContract.Event.OnSkipClicked)
                        }
                    )
                }
            }

        }
    }
}

package com.faswet.sportify.ui.screens.onboarding.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.ime
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.width
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import com.faswet.sportify.R
import com.faswet.sportify.ui.base.compose.SportifyCustomToken
import com.faswet.sportify.ui.screens.onboarding.contract.OnBoardingContract
import com.faswet.sportify.ui.theme.Black
import com.faswet.sportify.ui.theme.White
import com.faswet.sportify.ui.theme.dimens

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
                modifier = Modifier.fillMaxSize(),
                Arrangement.SpaceBetween,
                Alignment.CenterHorizontally
            ) {
                SportifyCustomToken(currentIndex = pager.currentPage, numberOfScreen = 5)
                HorizontalPager(pager, modifier = modifier.fillMaxWidth()) {
                    when (it) {
                        0 -> OnBoardingItem(state = state, index = 0)
                        1 -> OnBoardingItem(state = state, index = 1)
                        2 -> OnBoardingItem(state = state, index = 2)
                        3 -> OnBoardingItem(state = state, index = 3)
                        4 -> OnBoardingItem(state = state, index = 4)
                    }
                }
                TextButton(
                    onClick = {
                    },
                    modifier = modifier
                        .width(MaterialTheme.dimens.size150dp)
                        .height(MaterialTheme.dimens.size50dp)
                        .background(color = MaterialTheme.colorScheme.onPrimary, shape = RoundedCornerShape(MaterialTheme.dimens.size25dp))
                        .padding(start = MaterialTheme.dimens.size16dp, end = MaterialTheme.dimens.size16dp,)
                ) {
                    Text(
                        text = stringResource(id = R.string.next),
                        style = TextStyle(
                            fontSize = MaterialTheme.dimens.size20sp,
                            fontFamily = FontFamily(Font(R.font.inter_semibold)),
                            fontWeight = FontWeight(500),
                            color = MaterialTheme.colorScheme.background,
                        )
                    )
                }
            }

        }
    }
}

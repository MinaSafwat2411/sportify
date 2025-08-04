package com.faswet.sportify.ui.screens.onboarding.compose

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import com.faswet.sportify.R
import com.faswet.sportify.ui.screens.onboarding.contract.OnBoardingContract
import com.faswet.sportify.ui.theme.dimens

@Composable
fun OnBoardingItem(
    modifier: Modifier = Modifier,
    state: OnBoardingContract.State,
    index: Int
) {
    Column(
        modifier = modifier.padding(horizontal = MaterialTheme.dimens.size20dp)
    ) {
        Image(
            painter = painterResource(state.imageList[index]),
            contentDescription = null,
            modifier = modifier.padding(
                horizontal = MaterialTheme.dimens.size70dp
            )
        )
        Spacer(modifier.height(MaterialTheme.dimens.size60dp))
        Text(
            text = stringResource(state.titleList[index]),
            style = TextStyle(
                fontSize = MaterialTheme.dimens.size24sp,
                fontFamily = FontFamily(Font(R.font.inter_bold)),
                fontWeight = FontWeight(700),
                color = MaterialTheme.colorScheme.onBackground,

                )
        )
        Spacer(modifier.height(MaterialTheme.dimens.size8dp))
        Text(
            text = stringResource(state.descriptionList[index]),
            style = TextStyle(
                fontSize = MaterialTheme.dimens.size16sp,
                fontFamily = FontFamily(Font(R.font.inter_regular)),
                fontWeight = FontWeight(300),
                color = MaterialTheme.colorScheme.onBackground,
            )
        )
    }
}
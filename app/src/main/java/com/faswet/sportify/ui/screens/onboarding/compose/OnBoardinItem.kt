package com.faswet.sportify.ui.screens.onboarding.compose

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
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
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(state.imageList[index]),
            contentDescription = null,
            modifier = modifier.height(MaterialTheme.dimens.size300dp)
                .width(MaterialTheme.dimens.size300dp)
        )
        Spacer(modifier.height(MaterialTheme.dimens.size60dp))
        Text(
            text = stringResource(state.titleList[index]),
            style = MaterialTheme.typography.titleLarge,
            textAlign = TextAlign.Start,
            modifier = modifier.fillMaxWidth()
        )
        Spacer(modifier.height(MaterialTheme.dimens.size8dp))
        Text(
            text = stringResource(state.descriptionList[index]),
            style = MaterialTheme.typography.bodySmall,
            textAlign = TextAlign.Start,
            modifier = modifier.fillMaxWidth()
        )
    }
}
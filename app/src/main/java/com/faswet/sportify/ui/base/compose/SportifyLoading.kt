package com.faswet.sportify.ui.base.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.faswet.sportify.ui.theme.Transparent
import com.faswet.sportify.ui.theme.dimens

@Composable
fun SportifyLoading(
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier.fillMaxSize()
            .background(
                color = Transparent
            ),
        contentAlignment = Alignment.Center,
    ){
        CircularProgressIndicator(
            color = MaterialTheme.colorScheme.onPrimary,
            strokeWidth = MaterialTheme.dimens.size1dp,
            modifier = modifier.size(MaterialTheme.dimens.size28dp)
        )
    }
}
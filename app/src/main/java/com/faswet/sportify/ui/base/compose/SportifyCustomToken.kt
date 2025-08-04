package com.faswet.sportify.ui.base.compose

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import com.faswet.sportify.R
import com.faswet.sportify.ui.theme.dimens

@Composable
fun SportifyCustomToken(
    modifier: Modifier = Modifier,
    currentIndex: Int,
    numberOfScreen: Int
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(
                vertical = MaterialTheme.dimens.size30dp,
                horizontal = MaterialTheme.dimens.size20dp
            ),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = modifier
        ) {
            LazyRow(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = modifier
                    .padding(
                        horizontal = MaterialTheme.dimens.size5dp,
                        vertical = MaterialTheme.dimens.size7dp
                    )
                    .fillMaxWidth(),
                userScrollEnabled = false
            ) {
                items(numberOfScreen-1) {index ->
                    Image(
                        painter = painterResource(if (currentIndex > index) R.drawable.ic_rectangle_active else R.drawable.ic_rectangle_not_active),
                        contentDescription = null,
                        modifier = Modifier
                            .height(MaterialTheme.dimens.size6dp)
                            .width(MaterialTheme.dimens.size90dp)
                    )
                }
            }
            LazyRow(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = modifier.fillMaxWidth(),
                userScrollEnabled = false
            ) {
                items(numberOfScreen) {index ->
                    Image(
                        painter = painterResource(if (currentIndex >= index) R.drawable.ic_dot_active else R.drawable.ic_dot_not_active),
                        contentDescription = null,
                        modifier = Modifier.size(MaterialTheme.dimens.size20dp)
                    )
                }
            }
        }
    }
}
package com.faswet.sportify.ui.base.compose

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import coil3.request.crossfade
import com.faswet.sportify.R
import com.faswet.sportify.ui.screens.layout.contract.LayoutContract
import com.faswet.sportify.ui.theme.dimens

@Composable
fun SportifyDrawer(
    modifier: Modifier = Modifier,
    state : LayoutContract.State,
    onEventSent: (event: LayoutContract.Event) -> Unit,
) {
    ModalDrawerSheet(
        drawerContainerColor = MaterialTheme.colorScheme.secondary,
        modifier = modifier.width(MaterialTheme.dimens.size300dp),
    ) {
        Spacer(modifier = modifier.height(MaterialTheme.dimens.size20dp))
        IconButton(
            onClick = {
            },
            modifier = modifier.size(MaterialTheme.dimens.size120dp)
                .align(Alignment.CenterHorizontally)
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
                        .size(MaterialTheme.dimens.size120dp)
                        .clip(CircleShape),
                    contentScale = ContentScale.Fit
                )
            } else Image(
                painter = painterResource(state.avatar),
                contentDescription = null,
                modifier = modifier
                    .size(MaterialTheme.dimens.size120dp)
                    .clip(CircleShape)
            )
        }
        Spacer(modifier = modifier.height(MaterialTheme.dimens.size16dp))
        NavigationDrawerItem(
            label = { Text(text = stringResource(R.string.settings), style = MaterialTheme.typography.titleMedium) },
            selected = false,
            onClick = { /*TODO*/ },
            icon = {
                Icon(
                    painter = painterResource(R.drawable.ic_settings),
                    contentDescription = null
                )
            },
        )
    }
}
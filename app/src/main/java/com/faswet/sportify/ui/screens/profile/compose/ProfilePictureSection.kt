package com.faswet.sportify.ui.screens.profile.compose

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
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
import com.faswet.sportify.ui.screens.profile.contract.ProfileContract
import com.faswet.sportify.ui.theme.dimens

@Composable
fun ProfilePictureSection(
    state: ProfileContract.State,
    onEventSent: (event: ProfileContract.Event) -> Unit,
    modifier: Modifier = Modifier,
) {
    Box(
        contentAlignment = Alignment.Center
    ) {
        Image(
            painterResource(R.drawable.ic_profile_section),
            contentDescription = null,
            modifier = modifier
                .height(MaterialTheme.dimens.size80dp)
                .fillMaxWidth()
                .padding(horizontal = MaterialTheme.dimens.size12dp),
            contentScale = ContentScale.FillHeight
        )
        Box(
            contentAlignment = Alignment.BottomEnd,
            modifier = modifier
        ) {
            IconButton(
                onClick = {
                },
                modifier = modifier
                    .size(MaterialTheme.dimens.size70dp)
                    .clip(CircleShape)
            ) {
                if (state.userModel.profilePicture?.profileId == 0) {
                    AsyncImage(
                        model = ImageRequest.Builder(LocalContext.current)
                            .data(state.userModel.profilePicture.profileUrl ?: "")
                            .crossfade(true)
                            .build(),
                        placeholder = painterResource(R.drawable.ic_sportify_logo),
                        error = painterResource(R.drawable.ic_sportify_logo),
                        contentDescription = null,
                        modifier = modifier
                            .size(MaterialTheme.dimens.size70dp)
                            .clip(CircleShape),
                        contentScale = ContentScale.Fit,
                    )
                } else Image(
                    painter = painterResource(state.avatar),
                    contentDescription = null,
                    modifier = modifier
                        .size(MaterialTheme.dimens.size70dp)
                        .clip(CircleShape)
                )
            }
            Card(
                modifier = modifier
                    .background(MaterialTheme.colorScheme.primary, CircleShape),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    contentColor = MaterialTheme.colorScheme.onPrimary
                ),
                shape = CircleShape,
            ) {
                Icon(
                    modifier = modifier
                        .padding(MaterialTheme.dimens.size4dp)
                        .size(MaterialTheme.dimens.size18dp),
                    painter = painterResource(R.drawable.ic_edit),
                    contentDescription = null
                )
            }

        }
    }
}
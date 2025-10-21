package com.faswet.sportify.ui.screens.profile.compose

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.faswet.sportify.R
import com.faswet.sportify.ui.screens.profile.contract.ProfileContract
import com.faswet.sportify.ui.theme.dimens

@Composable
fun LogoutSection(
    state: ProfileContract.State,
    onEventSent: (ProfileContract.Event) -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier.border(
            width = MaterialTheme.dimens.size1dp,
            color = MaterialTheme.colorScheme.error,
            shape = MaterialTheme.shapes.extraLarge
        ).clickable {
            onEventSent(ProfileContract.Event.OnLogoutClicked)
        },
        shape = MaterialTheme.shapes.medium,
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.background
        )
    ) {
        Row(
            modifier = modifier.padding(MaterialTheme.dimens.size8dp)
        ) {
            Icon(
                painter = painterResource(R.drawable.ic_logout),
                contentDescription = null,
                tint = MaterialTheme.colorScheme.error
            )
            Text(
                text = stringResource(R.string.logout),
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.error
            )
        }
    }
}
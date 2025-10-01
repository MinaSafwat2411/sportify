package com.faswet.sportify.ui.screens.profile.compose

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import com.faswet.sportify.R
import com.faswet.sportify.ui.screens.profile.contract.ProfileContract
import com.faswet.sportify.ui.theme.GrayColor
import com.faswet.sportify.ui.theme.Green
import com.faswet.sportify.ui.theme.dimens

@Composable
fun MemberShipSection(
    state: ProfileContract.State,
    onEventSent: (event: ProfileContract.Event) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column {
        Spacer(modifier = modifier.height(MaterialTheme.dimens.size79dp))
        Card(
            modifier = modifier
                .fillMaxWidth(),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.onPrimary
            )
        ) {
            Column(
                modifier = modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    state.userModel.name ?: "",
                    style = MaterialTheme.typography.titleMedium
                        .copy(
                            color = MaterialTheme.colorScheme.primary
                        )
                )
                Spacer(modifier = modifier.height(MaterialTheme.dimens.size4dp))
                Text(
                    state.userModel.email ?: "",
                    style = MaterialTheme.typography.titleSmall.copy(
                        color = GrayColor
                    )
                )
            }
            Column(
                verticalArrangement = Arrangement.spacedBy(MaterialTheme.dimens.size16dp),
                modifier = modifier.padding(MaterialTheme.dimens.size16dp)
            ) {
                Row(
                    modifier
                        .fillMaxWidth(),
                    Arrangement.SpaceBetween
                ) {
                    Text(
                        text = stringResource(R.string.membership),
                        style = MaterialTheme.typography.titleMedium.copy(
                            color = MaterialTheme.colorScheme.primary
                        )
                    )
                    Text(
                        text = state.memberShipPlan.planName ?: "",
                        style = MaterialTheme.typography.titleMedium.copy(
                            fontWeight = FontWeight.Bold,
                            color = if (state.userModel.membershipStatus?.membershipStatusValue == 1) Green else MaterialTheme.colorScheme.error
                        )
                    )
                }
                HorizontalDivider(
                    modifier = modifier.fillMaxWidth(),
                    color = MaterialTheme.colorScheme.primary
                )
                Row(
                    modifier
                        .fillMaxWidth(),
                    Arrangement.SpaceBetween
                ) {
                    Text(
                        text = stringResource(R.string.status),
                        style = MaterialTheme.typography.titleMedium.copy(
                            color = MaterialTheme.colorScheme.primary
                        )
                    )
                    Text(
                        text = state.userModel.membershipStatus?.membershipStatusName ?: "",
                        style = MaterialTheme.typography.titleMedium.copy(
                            fontWeight = FontWeight.Bold,
                            color = if (state.userModel.membershipStatus?.membershipStatusValue == 1) Green else MaterialTheme.colorScheme.error
                        )
                    )
                }
            }
        }
    }
}
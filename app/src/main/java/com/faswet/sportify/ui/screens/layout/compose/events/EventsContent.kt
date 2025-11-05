package com.faswet.sportify.ui.screens.layout.compose.events

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
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
import com.faswet.sportify.ui.screens.layout.contract.LayoutContract
import com.faswet.sportify.ui.theme.dimens

@Composable
fun EventsContent(
    state: LayoutContract.State,
    onEventSent: (event: LayoutContract.Event) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxSize()
            .padding(MaterialTheme.dimens.size8dp)
    ) {
        Card(
            modifier = modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.onBackground
            )
        ) {
            Row (
                modifier = modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ){
                Icon(painter = painterResource(R.drawable.ic_back), contentDescription = null, tint = MaterialTheme.colorScheme.primary)
                Text(text = stringResource(R.string.january), style = MaterialTheme.typography.titleLarge.copy(
                    color = MaterialTheme.colorScheme.primary
                ))
                Icon(painter = painterResource(R.drawable.ic_back), contentDescription = null, tint = MaterialTheme.colorScheme.primary)
            }
        }
    }

}
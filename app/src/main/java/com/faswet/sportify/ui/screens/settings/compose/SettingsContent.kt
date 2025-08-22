package com.faswet.sportify.ui.screens.settings.compose

import android.content.pm.PackageManager
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.faswet.sportify.R
import com.faswet.sportify.ui.main.contract.MainContract
import com.faswet.sportify.ui.theme.GrayColor
import com.faswet.sportify.ui.theme.dimens
import com.faswet.sportify.utils.activity.findActivity
import com.faswet.sportify.utils.extentions.restartApp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsContent(
    modifier: Modifier = Modifier,
    state: MainContract.State,
    onEventSent: (event: MainContract.Event) -> Unit,
) {
    val context = LocalContext.current
    val activity = context.findActivity()
    val packageInfo = if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.TIRAMISU) {
        context.packageManager.getPackageInfo(
            context.packageName,
            PackageManager.PackageInfoFlags.of(0)
        )
    } else {
        @Suppress("DEPRECATION")
        context.packageManager.getPackageInfo(context.packageName, 0)
    }
    val versionName = packageInfo.versionName
    Scaffold(
        containerColor = MaterialTheme.colorScheme.background,
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = stringResource(R.string.settings),
                        style = MaterialTheme.typography.titleLarge
                    )
                },
                modifier
                    .fillMaxWidth(),
                navigationIcon = {
                    IconButton(
                        onClick = {
                            onEventSent(MainContract.Event.OnBackClicked)
                        }
                    ) {
                        Icon(
                            painter = painterResource(R.drawable.ic_back),
                            contentDescription = null
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.background,
                    titleContentColor = MaterialTheme.colorScheme.onPrimary,
                    navigationIconContentColor = MaterialTheme.colorScheme.onPrimary,
                    actionIconContentColor = MaterialTheme.colorScheme.onPrimary
                ),
                windowInsets = WindowInsets(0)
            )
        },
    ) { paddingValues ->
        Surface(
            modifier = Modifier
                .fillMaxSize(),
            color = MaterialTheme.colorScheme.background,
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues),
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = MaterialTheme.dimens.size16dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = stringResource(R.string.switch_theme),
                        style = MaterialTheme.typography.titleMedium
                    )
                    Switch(
                        checked = state.isDarkTheme ?: false,
                        onCheckedChange = {
                            onEventSent(MainContract.Event.ToggleTheme(it))
                        },
                        colors = SwitchDefaults.colors(
                            checkedThumbColor = MaterialTheme.colorScheme.onPrimary,
                            checkedTrackColor = MaterialTheme.colorScheme.primary,
                            uncheckedThumbColor = MaterialTheme.colorScheme.primary,
                            uncheckedTrackColor = MaterialTheme.colorScheme.outline,
                            checkedBorderColor = MaterialTheme.colorScheme.onPrimary,
                            uncheckedBorderColor = MaterialTheme.colorScheme.onPrimary,
                            checkedIconColor = MaterialTheme.colorScheme.onPrimary,
                            uncheckedIconColor = MaterialTheme.colorScheme.onPrimary,
                        )
                    )
                }
                HorizontalDivider(modifier
                    .fillMaxWidth()
                    .padding(vertical = MaterialTheme.dimens.size16dp, horizontal = MaterialTheme.dimens.size18dp))
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = MaterialTheme.dimens.size16dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = stringResource(R.string.switch_language),
                        style = MaterialTheme.typography.titleMedium
                    )
                    Text(
                        text = state.currentLanguage.uppercase(),
                        style = MaterialTheme.typography.titleMedium,
                        modifier = modifier.clickable {
                            val newLang = if (state.currentLanguage == "en") "ar" else "en"
                            onEventSent(MainContract.Event.ChangeLanguage(newLang, context))
                            activity?.restartApp()
                        }
                    )
                }
                HorizontalDivider(modifier
                    .fillMaxWidth()
                    .padding(vertical = MaterialTheme.dimens.size16dp, horizontal = MaterialTheme.dimens.size18dp))
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = MaterialTheme.dimens.size16dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = stringResource(R.string.version),
                        style = MaterialTheme.typography.titleMedium
                    )
                    Text(
                        text = versionName.toString(),
                        style = MaterialTheme.typography.titleMedium
                            .copy(
                                color = GrayColor,
                            ),
                    )
                }
            }
        }
    }
}
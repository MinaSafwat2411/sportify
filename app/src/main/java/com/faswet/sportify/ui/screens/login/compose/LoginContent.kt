package com.faswet.sportify.ui.screens.login.compose

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.ime
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.windowInsetsBottomHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import com.faswet.sportify.R
import com.faswet.sportify.data.models.FieldType
import com.faswet.sportify.ui.base.compose.SportifyCustomButton
import com.faswet.sportify.ui.base.compose.SportifyTextField
import com.faswet.sportify.ui.base.compose.TopSnackBarContainer
import com.faswet.sportify.ui.screens.login.contract.LoginContract
import com.faswet.sportify.ui.theme.dimens
import kotlinx.coroutines.launch

@Composable
fun LoginContent(
    modifier: Modifier = Modifier,
    state: LoginContract.State,
    onEventSent: (event: LoginContract.Event) -> Unit,
) {
    val scrollState = rememberScrollState()
    val snackBarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()
    LaunchedEffect(key1 = state.errorMessage) {
        if (state.errorMessage?.isNotEmpty()==true) {
            scope.launch {
                snackBarHostState.showSnackbar(
                    message = state.errorMessage,
                    withDismissAction = true
                )
            }
        }
    }
    TopSnackBarContainer(snackBarHostState = snackBarHostState,
        modifier = modifier,
        snackBarColor = MaterialTheme.colorScheme.error,
        snackBarTextColor = MaterialTheme.colorScheme.onPrimary,
        snackBarActionTextColor = MaterialTheme.colorScheme.onPrimary,
        snackBarActionColor = MaterialTheme.colorScheme.onPrimary,
    ) {
        Scaffold(
            containerColor = MaterialTheme.colorScheme.background,
            contentWindowInsets = WindowInsets.ime,
        ) { paddingValues ->

            Surface(
                modifier = Modifier
                    .fillMaxSize(),
                color = MaterialTheme.colorScheme.background,
            ) {
                LazyColumn(
                    Modifier
                        .imePadding()
                        .fillMaxSize()
                        .padding(paddingValues)
                        .padding(horizontal = MaterialTheme.dimens.size16dp),
                    state = rememberLazyListState(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    item {
                        Column(
                            modifier = Modifier
                                .fillMaxSize(),
                            verticalArrangement = Arrangement.Bottom,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Image(
                                painter = painterResource(R.drawable.ic_sportify_logo),
                                contentDescription = null,
                                modifier = Modifier.size(MaterialTheme.dimens.size200dp),
                                alignment = Alignment.Center
                            )

                            SportifyTextField(
                                isError = state.emailError != null,
                                onErrorText = stringResource(state.emailError?: R.string.empty),
                                label = stringResource(R.string.email),
                                type = FieldType.Email,
                                value = state.email,
                                onValueChanged = {
                                    onEventSent(LoginContract.Event.OnEmailChanged(it))
                                },
                                keyboardOptions = KeyboardOptions.Default,
                                placeholder = "Enter your email",
                                labelTextAlign = TextAlign.Start
                            )
                            Spacer(modifier = Modifier.height(MaterialTheme.dimens.size16dp))
                            SportifyTextField(
                                isError = state.passwordError != null,
                                onErrorText = stringResource(state.passwordError?: R.string.empty),
                                label = stringResource(R.string.password),
                                type = if (state.isPasswordVisible) FieldType.Text else FieldType.Password,
                                value = state.password,
                                onValueChanged = {
                                    onEventSent(LoginContract.Event.OnPasswordChanged(it))
                                },
                                keyboardOptions = KeyboardOptions.Default,
                                placeholder = "Enter your password",
                                labelTextAlign = TextAlign.Start,
                                suffixIcon = {
                                    Image(painter = painterResource(if (state.isPasswordVisible) R.drawable.ic_visibility_off else R.drawable.ic_visibility),
                                        contentDescription = null,
                                        modifier = Modifier
                                            .size(MaterialTheme.dimens.size24dp)
                                            .clickable {
                                                onEventSent(LoginContract.Event.OnShowHidePasswordClicked)
                                            }
                                    )
                                }
                            )
                            Spacer(modifier = Modifier.height(MaterialTheme.dimens.size8dp))
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.End
                            ) {
                                Text(
                                    text = stringResource(R.string.forgot_password),
                                    style = MaterialTheme.typography.displayLarge.copy(
                                        color = MaterialTheme.colorScheme.onPrimary
                                    ),
                                    textAlign = TextAlign.End,
                                    modifier = Modifier
                                        .clickable {
                                            onEventSent(LoginContract.Event.OnForgotPasswordClicked)
                                        }
                                )
                            }
                            Spacer(modifier = Modifier.height(MaterialTheme.dimens.size8dp))
                        }
                    }
                    item {
                        SportifyCustomButton(
                            text = stringResource(R.string.login),
                            onClick = {
                                onEventSent(LoginContract.Event.OnLoginClicked)
                            },
                            textColor = MaterialTheme.colorScheme.primary,
                            backgroundColor = MaterialTheme.colorScheme.onPrimary
                        )
                        Text(
                            text = stringResource(R.string.don_t_have_an_account) + " "+ stringResource(R.string.sign_up),
                            style = MaterialTheme.typography.displayLarge.copy(
                                color = MaterialTheme.colorScheme.onPrimary
                            ),
                            textAlign = TextAlign.Center,
                            modifier = Modifier
                                .clickable {
                                    onEventSent(LoginContract.Event.OnForgotPasswordClicked)
                                }.padding(MaterialTheme.dimens.size8dp)
                        )

                    }
                    item {
                        Spacer(
                            Modifier.windowInsetsBottomHeight(
                                WindowInsets.systemBars
                            )
                        )
                    }
                }
            }
        }
    }
}
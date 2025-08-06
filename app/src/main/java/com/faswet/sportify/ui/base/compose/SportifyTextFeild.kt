package com.faswet.sportify.ui.base.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import com.faswet.sportify.R
import com.faswet.sportify.data.models.FieldType
import com.faswet.sportify.ui.theme.BrandBlue
import com.faswet.sportify.ui.theme.HintColor
import com.faswet.sportify.ui.theme.dimens
import com.faswet.sportify.utils.constants.Constants
import com.faswet.sportify.utils.extentions.alternate

@Composable
fun SportifyTextField(
    label: String,
    type: FieldType,
    value: String?,
    placeholder: String?,
    onValueChanged: (text: String) -> Unit,
    keyboardOptions: KeyboardOptions,
    isError: Boolean = false,
    onErrorText: String? = null,
    labelTextAlign: TextAlign = TextAlign.Center,
    trailingIcon: @Composable (() -> Unit)? = null,
    leadingIcon: @Composable (() -> Unit)? = null,
    suffixIcon: @Composable (() -> Unit)? = null,
    prefixIcon: @Composable (() -> Unit)? = null,
) {

    var passwordVisible by rememberSaveable { mutableStateOf(false) }

    var textFieldValue by rememberSaveable {
        mutableStateOf(
            value ?: Constants.General.EMPTY_TEXT
        )
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
    ) {
        Text(
            text = label,
            style = MaterialTheme.typography.titleSmall.copy(
                fontWeight = FontWeight.Bold,
                color = if (isError) MaterialTheme.colorScheme.error else MaterialTheme.colorScheme.onPrimary
            ),
            textAlign = labelTextAlign,
        )
        TextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = MaterialTheme.dimens.size8dp)
                .height(MaterialTheme.dimens.size56dp)
                .shadow(
                    elevation = MaterialTheme.dimens.size4dp,
                    shape = RoundedCornerShape(MaterialTheme.dimens.size8dp)
                )
                .background(
                    color = MaterialTheme.colorScheme.background,
                    shape = RoundedCornerShape(MaterialTheme.dimens.size8dp)
                )
                .border(
                    width = MaterialTheme.dimens.size1dp,
                    color = if (isError) MaterialTheme.colorScheme.error else BrandBlue,
                    shape = RoundedCornerShape(MaterialTheme.dimens.size8dp)
                ),
            value = textFieldValue,
            onValueChange = {
                textFieldValue = it
                onValueChanged(it)
            },
            placeholder = {
                Text(
                    text = placeholder ?: Constants.General.EMPTY_TEXT,
                    style = MaterialTheme.typography.bodyMedium.copy(
                        color = if (isError) MaterialTheme.colorScheme.error else MaterialTheme.colorScheme.onPrimary
                    )
                )
            },
            isError = isError,
            singleLine = true,
            keyboardOptions = keyboardOptions,
            colors = TextFieldDefaults.colors(
                focusedIndicatorColor = MaterialTheme.colorScheme.background,
                unfocusedIndicatorColor = MaterialTheme.colorScheme.background,
                disabledIndicatorColor = MaterialTheme.colorScheme.background,
                errorIndicatorColor = MaterialTheme.colorScheme.background,
                errorContainerColor = MaterialTheme.colorScheme.background,
                focusedContainerColor = MaterialTheme.colorScheme.background,
                unfocusedContainerColor = MaterialTheme.colorScheme.background,
                disabledContainerColor = MaterialTheme.colorScheme.background,
                errorTextColor = MaterialTheme.colorScheme.error,
                cursorColor = MaterialTheme.colorScheme.onPrimary,
                focusedTextColor = MaterialTheme.colorScheme.onPrimary,
                unfocusedTextColor = MaterialTheme.colorScheme.onPrimary,
                disabledTextColor = MaterialTheme.colorScheme.onPrimary,
                errorCursorColor = MaterialTheme.colorScheme.error,
                errorLabelColor = MaterialTheme.colorScheme.error,
            ),
            shape = RoundedCornerShape(MaterialTheme.dimens.size16dp),
            trailingIcon = trailingIcon,
            leadingIcon = leadingIcon,
            suffix = suffixIcon,
            prefix = prefixIcon,
            visualTransformation = if (type == FieldType.Password && !passwordVisible) PasswordVisualTransformation() else VisualTransformation.None,
        )
        if (isError){
            Spacer(modifier = Modifier.height(MaterialTheme.dimens.size4dp))
            Text(
                text = onErrorText?: Constants.General.EMPTY_TEXT,
                style = MaterialTheme.typography.displayLarge.copy(
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.error
                ),
                textAlign = labelTextAlign,
            )
        }
    }
}

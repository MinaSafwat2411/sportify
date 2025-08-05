package com.faswet.sportify.ui.base.compose

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.faswet.sportify.ui.theme.DarkGray
import com.faswet.sportify.ui.theme.GrayColor
import com.faswet.sportify.ui.theme.dimens

@Composable
fun SportifyCustomButton(
    modifier: Modifier = Modifier,
    text: String,
    onClick: () -> Unit,
    enabled: Boolean = true,
    textColor: Color,
    backgroundColor: Color,
) {
    TextButton(
        onClick = onClick,
        enabled = enabled,
        modifier = modifier.fillMaxWidth()
            .height(MaterialTheme.dimens.size56dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = backgroundColor,
            contentColor = textColor,
            disabledContainerColor = GrayColor,
            disabledContentColor = DarkGray
        ),
        shape = MaterialTheme.shapes.medium
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.displayMedium.copy(
                color = textColor
            )
        )
    }
}
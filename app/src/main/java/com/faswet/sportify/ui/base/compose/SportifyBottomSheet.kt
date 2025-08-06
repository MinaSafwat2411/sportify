package com.faswet.sportify.ui.base.compose

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColor
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.LocalIndication
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.faswet.sportify.R
import com.faswet.sportify.ui.theme.Transparent
import com.faswet.sportify.ui.theme.dimens

@Composable
fun SportifyBottomNavBar(
    selectedIndex: Int,
    onTabSelected: (Int) -> Unit
) {
    val tabs = listOf(R.string.home,R.string.favorite, R.string.booking, R.string.event)
    val icons = listOf(R.drawable.ic_home_unselected, R.drawable.ic_favorite_unselected, R.drawable.ic_booking_unselected, R.drawable.ic_event)
    val iconsSelected = listOf(R.drawable.ic_home_selected, R.drawable.ic_favorite_selected, R.drawable.ic_booking_selected, R.drawable.ic_event)
    Surface(
        color = MaterialTheme.colorScheme.onPrimary,
        modifier = Modifier
            .fillMaxWidth()
            .animateContentSize()
            .padding(MaterialTheme.dimens.size8dp)
            .background(MaterialTheme.colorScheme.primary, shape = RoundedCornerShape(MaterialTheme.dimens.size56dp))
            .clip(RoundedCornerShape(MaterialTheme.dimens.size56dp))
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(MaterialTheme.dimens.size8dp)
                .background(MaterialTheme.colorScheme.onPrimary, shape = RoundedCornerShape(MaterialTheme.dimens.size20dp))
                .shadow(MaterialTheme.dimens.size8dp, shape = RoundedCornerShape(MaterialTheme.dimens.size20dp))
                .clip(RoundedCornerShape(MaterialTheme.dimens.size56dp)),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically
        ) {
            tabs.forEachIndexed { index, label ->
                val selected = index == selectedIndex
                val transition = updateTransition(targetState = selected, label = "tab_animation")

                val backgroundColor by transition.animateColor(
                    label = "background_color",
                    transitionSpec = { tween(durationMillis = 900, easing = FastOutSlowInEasing) }
                ) { isSelected ->
                    if (isSelected) MaterialTheme.colorScheme.primary else Transparent
                }

                val contentColor by transition.animateColor(
                    label = "icon_text_color",
                    transitionSpec = { tween(durationMillis = 900) }
                ) { isSelected ->
                    if (isSelected) MaterialTheme.colorScheme.onPrimary else MaterialTheme.colorScheme.primary
                }

                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(MaterialTheme.dimens.size56dp))
                        .background(backgroundColor)
                        .border(
                            width = MaterialTheme.dimens.size1dp,
                            color = if (selected) MaterialTheme.colorScheme.primary else Transparent,
                            shape = RoundedCornerShape(MaterialTheme.dimens.size56dp)
                        )
                        .clickable(
                            onClick = { onTabSelected(index) },
                            interactionSource = remember { MutableInteractionSource() },
                            indication = LocalIndication.current
                        )
                        .padding(horizontal = MaterialTheme.dimens.size8dp, vertical = MaterialTheme.dimens.size8dp)
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.animateContentSize()
                            .padding(vertical = MaterialTheme.dimens.size8dp, horizontal = MaterialTheme.dimens.size12dp)
                    ) {
                        Icon(
                            painter = painterResource(id = if (selected) iconsSelected[index] else icons[index]),
                            contentDescription = null,
                            modifier = Modifier.size(MaterialTheme.dimens.size24dp),
                            tint = contentColor
                        )
                        AnimatedVisibility(visible = selected) {
                            Row {
                                Spacer(modifier = Modifier.width(MaterialTheme.dimens.size4dp))
                                Text(
                                    text = stringResource(id = label),
                                    color = contentColor,
                                    style = MaterialTheme.typography.labelMedium
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}
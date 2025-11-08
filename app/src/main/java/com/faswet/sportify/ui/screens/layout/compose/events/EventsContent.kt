package com.faswet.sportify.ui.screens.layout.compose.events

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.faswet.sportify.ui.screens.layout.contract.LayoutContract
import com.faswet.sportify.ui.theme.dimens
import java.text.SimpleDateFormat
import java.util.*

@Composable
fun EventsContent(
    state: LayoutContract.State,
    onEventSent: (LayoutContract.Event) -> Unit,
    modifier: Modifier = Modifier
) {
    // Calendar instance for current month
    val calendar = Calendar.getInstance().apply { time = state.selectedDate }

    // Start of month
    val monthStart = Calendar.getInstance().apply {
        time = state.selectedDate
        set(Calendar.DAY_OF_MONTH, 1)
    }

    // Day of week of first day (1=Sunday..7=Saturday)
    val firstDayOfWeek = monthStart.get(Calendar.DAY_OF_WEEK)

    // Number of days in this month
    val daysInMonth = monthStart.getActualMaximum(Calendar.DAY_OF_MONTH)

    // Month name
    val monthName = SimpleDateFormat("MMMM yyyy", Locale.getDefault()).format(calendar.time)

    // Generate list of all dates for display (include leading blanks)
    val totalCells = ((firstDayOfWeek - 1) + daysInMonth) // Total cells needed
    val dates = mutableListOf<Date?>()
    for (i in 1..totalCells) {
        if (i < firstDayOfWeek) {
            dates.add(null) // empty cell before first day
        } else {
            val day = i - firstDayOfWeek + 1
            val date = Calendar.getInstance().apply {
                time = calendar.time
                set(Calendar.DAY_OF_MONTH, day)
            }.time
            dates.add(date)
        }
    }
    Card(
        modifier = modifier
            .fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.onBackground
        ),
    ) {
        Column(modifier = modifier.padding(MaterialTheme.dimens.size8dp)) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = MaterialTheme.dimens.size8dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(onClick = { onEventSent(LayoutContract.Event.OnPreviousMonthClicked) }) {
                    Icon(
                        painter = androidx.compose.ui.res.painterResource(id = com.faswet.sportify.R.drawable.ic_backward),
                        contentDescription = "Previous Month"
                    )
                }
                Text(
                    text = monthName,
                    style = MaterialTheme.typography.titleLarge.copy(
                        color = MaterialTheme.colorScheme.primary
                    )
                )
                IconButton(onClick = { onEventSent(LayoutContract.Event.OnNextMonthClicked) }) {
                    Icon(
                        painter = androidx.compose.ui.res.painterResource(id = com.faswet.sportify.R.drawable.ic_forward),
                        contentDescription = "Next Month"
                    )
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            // Weekday names
            val dayNames = arrayOf("Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat")
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceAround) {
                dayNames.forEach { day ->
                    Text(
                        text = day,
                        style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Bold),
                        modifier = Modifier.weight(1f),
                        textAlign = androidx.compose.ui.text.style.TextAlign.Center
                    )
                }
            }

            Spacer(modifier = Modifier.height(4.dp))

            // Calendar grid
            val rows = dates.chunked(7) // split into weeks
            rows.forEach { week ->
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Start) {
                    week.forEach { date ->
                        if (date != null) {
                            val isSelected = date.isSameDay(state.selectedDate)
                            Box(
                                modifier = Modifier
                                    .width(MaterialTheme.dimens.size56dp)
                                    .height(MaterialTheme.dimens.size56dp)
                                    .padding(2.dp)
                                    .background(
                                        color = if (isSelected) MaterialTheme.colorScheme.primaryContainer else MaterialTheme.colorScheme.surface,
                                        shape = RoundedCornerShape(8.dp)
                                    )
                                    .clickable {
                                        onEventSent(LayoutContract.Event.OnDateChanged(date))
                                    },
                                contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    text = SimpleDateFormat("d", Locale.getDefault()).format(date),
                                    style = MaterialTheme.typography.bodyLarge.copy(
                                        fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal,
                                        color = if (isSelected) MaterialTheme.colorScheme.onPrimaryContainer else MaterialTheme.colorScheme.onSurface
                                    )
                                )
                            }
                        } else {
                            Spacer(modifier = Modifier.weight(1f).aspectRatio(1f))
                        }
                    }
                }
            }
        }
    }
}

// Helper extension
private fun Date.isSameDay(other: Date): Boolean {
    val cal1 = Calendar.getInstance().apply { time = this@isSameDay }
    val cal2 = Calendar.getInstance().apply { time = other }
    return cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR) &&
            cal1.get(Calendar.DAY_OF_YEAR) == cal2.get(Calendar.DAY_OF_YEAR)
}

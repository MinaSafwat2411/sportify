package com.faswet.sportify.utils.extentions


import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.res.Configuration
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Base64
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import com.faswet.sportify.ui.main.MainActivity
import com.faswet.sportify.ui.main.NavigationScreen
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.zip
import java.io.File
import java.math.BigDecimal
import java.math.RoundingMode
import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale



fun String.isLongEnough() = length >= 8
fun String.hasEnoughDigits() = count(Char::isDigit) > 0
fun String.isMixedCase() = any(Char::isLowerCase) && any(Char::isUpperCase)
fun String.hasSpecialChar() = any { it in "!@#$%^&*()-_=+" }


private val requirements = listOf(
    String::isLongEnough,
    String::hasEnoughDigits,
    String::hasSpecialChar,
    String::isMixedCase
)
val String.meetsRequirements get() = requirements.all { check -> check(this) }



@SuppressLint("SimpleDateFormat")
fun Context.createImageFile(): File {
    val timeStamp = SimpleDateFormat("yyyyMMdd_HHmmss").format(Date())
    val imageFileName = "JPEG_" + timeStamp + "_"
    val image = File.createTempFile(
        imageFileName, /* prefix */
        ".jpg", /* suffix */
        externalCacheDir      /* directory */
    )
    return image
}


fun <T1, T2, T3, R> Flow<T1>.zip(
    secondFlow: Flow<T2>,
    thirdFlow: Flow<T3>,
    transform: (T1, T2, T3) -> R
): Flow<R> {
    return this.zip(secondFlow) { flow1, flow2 -> Pair(flow1, flow2) }
        .zip(thirdFlow) { (flow1, flow2), flow3 ->
            transform(flow1, flow2, flow3)
        }
}


fun <T> NavController.navigateWithResult(key: String, value: T) {
    popBackStack()
    currentBackStackEntry
        ?.savedStateHandle
        ?.set(key, value)
}

fun <T> NavController.navigateToWithResult(deepLink: String, key: String, value: T) {
    navigate(deepLink) {
        popUpTo(NavigationScreen.Layout.route) {
            inclusive = true
        }
        launchSingleTop = true
    }
    currentBackStackEntry
        ?.savedStateHandle
        ?.set(key, value)


}

fun String?.convertToBitmap(): Bitmap? {
    return if (!isNullOrEmpty()) {
        val decodedString: ByteArray = Base64.decode(this, Base64.DEFAULT)
        BitmapFactory.decodeByteArray(decodedString, 0, decodedString.size)
    } else {
        null
    }
}
fun String.toAmPmFormat(): String {
    return try {
        val inputFormat = SimpleDateFormat("HH:mm", Locale.getDefault())
        val outputFormat = SimpleDateFormat("hh:mm a", Locale.getDefault())
        val date = inputFormat.parse(this)
        outputFormat.format(date?: Date()).uppercase(Locale.getDefault())
    } catch (e: Exception) {
        return  ""
    }
}

fun String.alternate(): String {
    return this.mapIndexed { index, char ->
        if (index % 2 == 0) char.uppercaseChar() else char.lowercaseChar()
    }.joinToString("")
}

fun Double.toTwoDecimalString(): String {
    val value = DecimalFormat("#.##")
    return value.format(this)
}

fun String.isValidDoubleFormat(): Boolean {
    // Ensure it's not empty
    if (this.isEmpty()) return false

    // Ensure proper number format using regex
    val doublePattern = Regex("^-?\\d*(\\.\\d+)?$")

    return this.matches(doublePattern)
}

@SuppressLint("DefaultLocale")
fun String.toThreeDecimalString(): String {
    return this.toDoubleOrNull()?.let { String.format("%.3f", it) } ?: this
}


fun String.formatDoubleWithCommasWhileTyping(): String {
    if (this.isEmpty() || this == "." || this == "-" || this == "-.") return this
    if (this.endsWith(",")) return this
    if (this.startsWith(",")) return this

    val cleanString = this.replace(",", "").trim()
    if (cleanString.matches(Regex("""-?\d+\."""))) return this
    if (cleanString.count { it == '.' } > 1) return this

    return try {
        val parts = cleanString.split(".")
        val integerPart = parts[0].toBigDecimalOrNull() ?: return this
        val formattedInt = DecimalFormat("#,###").format(integerPart)

        if (parts.size > 1) {
            val decimalPart = parts[1]
            val absValue = BigDecimal(cleanString).abs()
            val maxDecimals = if (absValue < BigDecimal.ONE) 3 else 2
            "$formattedInt.${decimalPart.take(maxDecimals)}"
        } else {
            formattedInt
        }
    } catch (e: Exception) {
        this
    }
}


fun String.formatDoubleWithCommasForDisplay(): String {

    val cleanValue = this.replace(",", "").trim()
    val doubleValue = cleanValue.toDoubleOrNull() ?: return this

    val symbols = DecimalFormatSymbols(Locale.US)
    val pattern = when {
        doubleValue == 0.0 -> "#,##0.00"
        kotlin.math.abs(doubleValue) >= 1.0 -> "#,##0.00"
        else -> "#,##0.000"
    }

    val decimalFormat = DecimalFormat(pattern, symbols)
    decimalFormat.roundingMode = RoundingMode.DOWN
    return decimalFormat.format(doubleValue)
}


fun String.isValidNumber(): Boolean {
    if (this.isBlank()) return true
    return this.matches(Regex("^\\d+(\\.\\d{0,})?\$"))
}

fun String.formatToTwoDecimals(): String{

    val parts = this.split(".")
    return if (parts.size == 2 && parts[1].length == 1) {
        "${parts[0]}.${parts[1]}0"
    } else {
        this
    }
}


fun String.convertToDouble(): Double {
    if (this.isEmpty()) {
        return 0.0
    }
    val sanitizedInput = this.replace(",", "").replace("-","")
    val finalInput = sanitizedInput.replace(Regex("\\.\\.+"), ".")
    return finalInput.toDoubleOrNull() ?: 0.0
}

fun String.toFormattedDouble(): Double {
    if (this.isEmpty()) {
        return 0.0
    }
    val sanitizedInput = this.replace(",", "")
    return sanitizedInput.toDouble()
}

fun String.hideIBAN(): String{
    if(this.length == 29){
        return this.replaceRange(10,19,"XXXXXXXXX")
    }
    return this
}

fun String.hideAccountNumber(): String{
    if(this.length > 4){
        val middle = this.length / 2

        return this.replaceRange(middle-1, middle+1, "XXX")
    }
    return this
}

fun String.toBase64Encoded(): String{
    return Base64.encodeToString(this.toByteArray(Charsets.UTF_8), Base64.NO_WRAP)
}

@SuppressLint("DefaultLocale")
fun String.toShortenedFormat(): String {
    return try {
        val number = this.toLong()
        when {
            number >= 1_000_000_000 -> String.format("%.1fB", number / 1_000_000_000.0)
            number >= 1_000_000 -> String.format("%.1fM", number / 1_000_000.0)
            number >= 1_000 -> String.format("%.1fK", number / 1_000.0)
            else -> number.toString()
        }
    } catch (e: NumberFormatException) {
        this
    }
}

fun <T> ((T) -> Unit).singleClickWrapper(delay: Long = 1000L): (T) -> Unit {
    var lastClickTime = 0L
    return { param: T ->
        val currentTime = System.currentTimeMillis()
        if (currentTime - lastClickTime > delay) {
            lastClickTime = currentTime
            this(param)
        }
    }
}

fun (() -> Unit).singleClickWrapperNonParams(delay: Long = 1000L): () -> Unit {
    var lastClickTime = 0L
    return {
        val currentTime = System.currentTimeMillis()
        if (currentTime - lastClickTime > delay) {
            lastClickTime = currentTime
            this()
        }
    }
}

@SuppressLint("DefaultLocale")
fun Long.toCompactFormat(): String {
    return when {
        this < 1_000 -> this.toString()

        this < 1_000_000 -> {
            val value = BigDecimal(this).divide(BigDecimal(1_000)).setScale(2, RoundingMode.DOWN)
            "${value.stripTrailingZeros().toPlainString()}k"
        }

        else -> {
            val value = BigDecimal(this).divide(BigDecimal(1_000_000)).setScale(3, RoundingMode.DOWN)
            "${value.stripTrailingZeros().toPlainString()}M"
        }
    }
}

fun String.normalizeDecimalInput(): String {
    val trimmed = this.trim()
    return if (trimmed.startsWith(".")) {
        "0$trimmed"
    } else {
        trimmed
    }
}

fun Context.updateLocale(locale: Locale): Context {
    Locale.setDefault(locale)
    val config = Configuration(resources.configuration)
    config.setLocale(locale)
    return createConfigurationContext(config)
}

fun Activity.restartApp() {
    val intent = Intent(this, MainActivity::class.java)
    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
    this.startActivity(intent)
    if (this is AppCompatActivity) {
        this.finish()
    }
}
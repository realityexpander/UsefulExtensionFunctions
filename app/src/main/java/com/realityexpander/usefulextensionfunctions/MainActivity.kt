package com.realityexpander.usefulextensionfunctions

import android.app.Fragment
import android.content.Context
import android.content.res.Resources
import android.os.Bundle
import android.util.Log
import android.util.TypedValue
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.AnimRes
import androidx.annotation.DimenRes
import androidx.annotation.DrawableRes
import androidx.annotation.FontRes
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.core.content.res.ResourcesCompat
import com.google.android.material.snackbar.Snackbar
import com.realityexpander.usefulextensionfunctions.ui.theme.UsefulExtensionFunctionsTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            UsefulExtensionFunctionsTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Greeting("Android")
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    UsefulExtensionFunctionsTheme {
        Greeting("Android")
    }
}

// Helpers from: https://github.com/cybercoder-naj

// Toast messages
fun Context.toast(msg: String, length: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(this, msg, length).show()
}
fun Fragment.toast(msg: String, length: Int = Toast.LENGTH_SHORT) {
    this.activity.toast(msg, length)
    // requireContext().toast(msg, length) // if you want to use requireContext()
}

// Snackbar messages
fun View.snackbar(msg: String, length: Int = Snackbar.LENGTH_SHORT) {
    Snackbar.make(this, msg, length).show()
}
fun Fragment.snackbar(msg: String, length: Int = Snackbar.LENGTH_SHORT) {
    view?.snackbar(msg, length)
}

// Log messages
fun Any.log(msg: String) {
    Log.d(this::class.java.simpleName, msg)
}


// Repeat Strings
fun String.repeat(times: Int): String {
    return buildString {
        repeat(times) {
            append(this@repeat)
        }
    }
}

// String substring Range
fun String.substring(range: IntRange): String {
    return substring(range.first, range.last + 1)
}
/*
 * val mainStr = "Interesting"
 * val substr = mainStr[2..8] // "teresti"
 */
operator fun String.get(range: IntRange) =
    substring(range.first, range.last + 1)


// Android resources
fun Context.drawable(@DrawableRes resId: Int) =
    ResourcesCompat.getDrawable(resources, resId, null)

fun Context.font(@FontRes resId: Int) =
    ResourcesCompat.getFont(this, resId)

fun Context.dimen(@DimenRes resId: Int) =
    resources.getDimension(resId)

fun Context.anim(@AnimRes resId: Int) =
    AnimationUtils.loadAnimation(this, resId)


// Complex units for dimensions
fun Context.dpToPx(dp: Float) =
    dp * resources.displayMetrics.density

val Float.dp
    get() = TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_DIP,
        this,
        Resources.getSystem().displayMetrics
    )

val Float.sp
    get() = TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_SP,
        this,
        Resources.getSystem().displayMetrics
    )
/*
 * use as 18.dp
 * or 22.5f.sp
 */
val Int.dp get() = toFloat().dp
val Int.sp get() = toFloat().sp




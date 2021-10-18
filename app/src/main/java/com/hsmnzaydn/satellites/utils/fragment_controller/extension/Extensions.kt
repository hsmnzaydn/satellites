package com.hsmnzaydn.satellites.utils.fragment_controller.extension

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.fragment.app.Fragment


/**
 * Created by Kemal Tunç on 2020-09-30
 */


fun Fragment.getFragTag(extra: String = ""): String {
    return this::class.java.simpleName + extra
}


fun Context.sendEmail(
    addresses: Array<String?>?,
    subject: String?
) {
    val intent = Intent(Intent.ACTION_SENDTO)
    intent.data = Uri.parse("mailto:") // only email apps should handle this
    intent.putExtra(Intent.EXTRA_EMAIL, addresses)
    intent.putExtra(Intent.EXTRA_SUBJECT, subject)
    if (intent.resolveActivity(packageManager) != null) {
        startActivity(intent)
    }
}

fun Context.makeCallWithNumber(
    telephone: String?){
    val callIntent = Intent(Intent.ACTION_DIAL)
    callIntent.data = Uri.parse("tel:$telephone")

    startActivity(callIntent)

}
package com.hsmnzaydn.satellites.utils

import android.app.Activity
import android.app.ProgressDialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.provider.Settings;
import android.view.inputmethod.InputMethodManager
import java.io.IOException
import java.io.InputStream
import java.nio.charset.Charset


class CoreCommonUtils {
    companion object {
        /**
         * Loading göstermek amacıyla kullanılır
         * @param context: Bu fonksiyonun çağırıldığı contexttir
         */
        fun showLoadingDialog(context: Context): ProgressDialog {
            var progressDialog: ProgressDialog = ProgressDialog(context)
            if (!(context as Activity).isFinishing) {
                progressDialog.show()
            }
            if (progressDialog.window != null) {
                progressDialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            }
          //  progressDialog.setContentView(R.layout.progress_dialog)
            progressDialog.isIndeterminate = true
            /*progressDialog.setCancelable(false)*/
            progressDialog.setCanceledOnTouchOutside(false)
            return progressDialog
        }

        fun loadJSONFromAsset(context: Context,filename:String): String? {
            var json: String? = null
            json = try {
                val input: InputStream = context.getAssets().open("${filename}.json")
                val size: Int = input.available()
                val buffer = ByteArray(size)
                input.read(buffer)
                input.close()
                var charset = Charset.forName("UTF-8")
                String(buffer, charset)
            } catch (ex: IOException) {
                ex.printStackTrace()
                return null
            }
            return json
        }

    }


}

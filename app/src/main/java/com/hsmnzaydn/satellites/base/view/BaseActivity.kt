package com.hsmnzaydn.satellites.base.view

import android.app.ProgressDialog
import android.content.Context
import android.content.ContextWrapper
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.os.PersistableBundle
import android.view.View
import android.view.WindowManager
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.google.gson.Gson
import com.hsmnzaydn.satellites.R
import com.hsmnzaydn.satellites.utils.CoreCommonUtils
import java.util.*


abstract class BaseActivity : AppCompatActivity() {
    var progressDialog: ProgressDialog? = null


    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
    }

    open fun showLoading() {
        progressDialog?.let {
            if (!it.isShowing) {
                it.show()
            }
        } ?: run {
            progressDialog = CoreCommonUtils.showLoadingDialog(this)
        }

    }

    open fun hideLoading() {
        progressDialog?.let {
            if (it.isShowing) {
                it.dismiss()
            }
        }

    }


    open fun showInformation(text: String) {
        Toast.makeText(this@BaseActivity, text, Toast.LENGTH_SHORT).show()
    }

    open fun showError(text: String) {
        Toast.makeText(this@BaseActivity, text, Toast.LENGTH_SHORT).show()
    }




}
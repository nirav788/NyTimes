package com.upwork.nytimes.Utills

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.net.ConnectivityManager
import android.util.Log
import android.view.Window
import android.view.WindowManager
import androidx.appcompat.widget.PopupMenu
import com.upwork.nytimes.R

internal var SpinKitProgressDialog: Dialog? = null

fun Context.isNetworkConnectionAvailable(): Boolean {
    val cm = this.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val activeNetwork = cm.activeNetworkInfo
    return (activeNetwork != null && activeNetwork.isConnected)
}

fun Context.checkInternetConnection(): Boolean {
    return if (isNetworkConnectionAvailable()) {
        Log.d("Network", "Connected")
        true
    } else {
        Log.d("Network", "Not Connected")
        CheckNetworkConnectionDialog()
        false
    }
}

fun Context.CheckNetworkConnectionDialog() {
    val builder = AlertDialog.Builder(this, R.style.DialogTheme)
    builder.setTitle(resources.getString(R.string.no_connection))
    builder.setMessage(resources.getString(R.string.turn_on_connection))
    builder.setNegativeButton(getString(R.string.dialog_ok)) { dialog, which -> dialog.dismiss() }
    val alertDialog = builder.create()
    alertDialog.show()
}


fun Context.showDialog() {
    // implementation 'com.github.ybq:Android-SpinKit:1.2.0'
    if (SpinKitProgressDialog != null) {
        if (SpinKitProgressDialog!!.isShowing) {
            return
        }
    }

    SpinKitProgressDialog = Dialog(this)
    SpinKitProgressDialog!!.requestWindowFeature(Window.FEATURE_NO_TITLE)
    SpinKitProgressDialog!!.window!!
        .setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN)
    SpinKitProgressDialog!!.setContentView(R.layout.progress)
    SpinKitProgressDialog!!.window!!
        .setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
    SpinKitProgressDialog!!.window!!.setDimAmount(0.3f)
    SpinKitProgressDialog!!.setCancelable(false)
    val lp = WindowManager.LayoutParams()
    lp.copyFrom(SpinKitProgressDialog!!.window!!.attributes)
    lp.width = WindowManager.LayoutParams.WRAP_CONTENT
    lp.height = WindowManager.LayoutParams.WRAP_CONTENT
    SpinKitProgressDialog!!.show()
    SpinKitProgressDialog!!.window!!.attributes = lp
}

fun Context.hideDialog() {
    if (SpinKitProgressDialog != null && SpinKitProgressDialog!!.isShowing) {
        SpinKitProgressDialog!!.dismiss()
        SpinKitProgressDialog = null
    }
}

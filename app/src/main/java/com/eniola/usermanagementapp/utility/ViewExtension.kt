package com.eniola.studyapp.utility

import android.content.Context
import android.view.View
import android.widget.Toast

/**
 * Function to hide view
 */
fun View.hide(onlyInvisible: Boolean = false) {
    this.visibility = if(onlyInvisible) View.VISIBLE else View.GONE
}

fun View.show(){
    this.visibility = View.VISIBLE
}

fun Context.toast(message: String?, length: Int = Toast.LENGTH_LONG ) {
    Toast.makeText(this, message, length).show()
}
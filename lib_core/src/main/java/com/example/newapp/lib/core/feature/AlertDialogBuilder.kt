package com.example.newapp.lib.core.feature

import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import com.example.newapp.lib.core.R

fun Fragment.buildAlertDialog(
    title: Int? = R.string.standard_error_title,
    message: Int?,
    cancelable: Boolean = false,
    positiveButtonText: Int,
    negativeButtonText: Int? = null,
    neutralButtonText: Int? = null,
    negativeAction: () -> Unit = {},
    positiveAction: () -> Unit = {},
    neutralAction: () -> Unit = {}
) = AlertDialog.Builder(requireContext()).apply {
    title?.let { setTitle(it) }
    neutralButtonText?.let {
        this.setNeutralButton(neutralButtonText) { _, _ -> neutralAction() }
    }
    negativeButtonText?.let {
        this.setNegativeButton(negativeButtonText) { _, _ -> negativeAction() }
    }
    message?.let { setMessage(message) }
    setCancelable(cancelable)
    setPositiveButton(positiveButtonText) { _, _ -> positiveAction() }
}

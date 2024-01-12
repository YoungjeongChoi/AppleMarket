package com.example.applemarket

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import kotlin.ClassCastException
import kotlin.system.exitProcess

open class DialogDeleteFragment : DialogFragment() {

    interface FragmentDataListener {

    }

    override fun onCreateDialog(savedInstanceState : Bundle?): Dialog {
        return activity?.let{
            val builder = AlertDialog.Builder(it)
            builder.setMessage(R.string.delete_message)
                .setPositiveButton(R.string.ok, { dialog, id ->  })
                .setNegativeButton(R.string.cancel, DialogInterface.OnClickListener { dialog, id -> dismiss() })
            builder.create()
        } ?: throw IllegalStateException("null")
    }

}


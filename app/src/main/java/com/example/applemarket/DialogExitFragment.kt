package com.example.applemarket

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import kotlin.ClassCastException
import kotlin.system.exitProcess

open class DialogExitFragment : DialogFragment() {

//    internal lateinit var listener: NoticeDialogListener

//    interface NoticeDialogListener {
//        fun onDialogPositiveClick(dialog: DialogFragment)
//        fun onDialogNegativeClick(dialog: DialogFragment)
//    }

//    override fun onAttach(context: Context) {
//        super.onAttach(context)
//
//        try {
//
//        } catch (e: ClassCastException) {
//            throw ClassCastException ((context.toString() + "must implement NoticeDialogListener?"))
//        }
//    }

    override fun onCreateDialog(savedInstanceState : Bundle?): Dialog {
        return activity?.let{
            val builder = AlertDialog.Builder(it)
            builder.setTitle(R.string.exit_title)
                .setMessage(R.string.exit_message)
                .setPositiveButton(R.string.ok, { dialog, id -> exitProcess(0) })
                .setNegativeButton(R.string.cancel, DialogInterface.OnClickListener { dialog, id -> dismiss() })
            builder.create()
        } ?: throw IllegalStateException("null")
    }

}


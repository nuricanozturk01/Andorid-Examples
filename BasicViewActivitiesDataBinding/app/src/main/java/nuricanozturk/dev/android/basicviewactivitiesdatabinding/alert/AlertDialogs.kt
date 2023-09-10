package nuricanozturk.dev.android.basicviewactivitiesdatabinding.alert

import android.content.Context
import android.content.DialogInterface
import androidx.appcompat.app.AlertDialog

public fun promptNotConfirmedDialog(context: Context, titleResId: String, message: String,
                                    neutralButtonText: String,
                                    callback: (DialogInterface, Int) -> Unit)
{
    AlertDialog.Builder(context)
        .setTitle(titleResId)
        .setMessage(message)
        .setNeutralButton(neutralButtonText) {d,w -> callback(d,w)}
        .create()
        .show()
}

fun promptDecision(context: Context, titleResId: String, messageResId: String,
                   positiveButtonResId: String, negativeButtonResId: String,
                   positiveButtonCallback: (DialogInterface, Int) -> Unit,
                   negativeButtonCallback: (DialogInterface, Int) -> Unit)
{
    AlertDialog.Builder(context)
        .setTitle(titleResId)
        .setMessage(messageResId)
        .setPositiveButton(positiveButtonResId) {d, w -> positiveButtonCallback(d, w)}
        .setNegativeButton(negativeButtonResId) {d, w -> negativeButtonCallback(d, w)}
        .create()
        .show()
}
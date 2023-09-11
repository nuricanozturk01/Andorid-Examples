package nuricanozturk.dev.android.basicviewactivitiesdatabinding.alert

import android.content.Context
import android.content.DialogInterface
import androidx.appcompat.app.AlertDialog

fun promptNotConfirmedDialog(context: Context, titleResId: Int, messageResId: Int,
                             neutralButtonResId: Int,
                             callback: (DialogInterface, Int) -> Unit)
{
    AlertDialog.Builder(context)
        .setTitle(titleResId)
        .setMessage(messageResId)
        .setNeutralButton(neutralButtonResId) {d, w -> callback(d, w)}
        .create()
        .show()
}

fun promptDecision(context: Context, titleResId: Int, messageResId: Int,
                   positiveButtonResId: Int, negativeButtonResId: Int,
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
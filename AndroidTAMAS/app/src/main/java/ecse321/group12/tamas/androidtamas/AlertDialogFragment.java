package ecse321.group12.tamas.androidtamas;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.os.Bundle;

public class AlertDialogFragment extends DialogFragment
{
     interface DeletionListener
    {
        void OnDeletionAction(int data);
    }
    private DeletionListener mlistener;

    public void setDeletionListener(DeletionListener listener)
    {
        mlistener = listener;
    }
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState)
    {
        Bundle alertInfo=getArguments();
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(alertInfo.getString("title",""));

        builder.setMessage(alertInfo.getString("Notification",""))
                .setPositiveButton(alertInfo.getString("Positive Confirmation",""), (Dialog, id) ->
                {
                    mlistener.OnDeletionAction(1);
                    dismiss();
                })
                .setNegativeButton(alertInfo.getString("Negative Confirmation",""), (dialog, id) ->
                {
                    mlistener.OnDeletionAction(0);
                    dismiss();
                });
        // Create the AlertDialog object and return it
        return builder.create();
    }
    @Override
    public void onAttach(Context context)
    {
        super.onAttach(context);
    }

}

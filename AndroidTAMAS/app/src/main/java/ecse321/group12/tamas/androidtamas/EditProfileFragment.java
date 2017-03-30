package ecse321.group12.tamas.androidtamas;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.os.Bundle;

public class EditProfileFragment extends DialogFragment
{
     interface ProfileDeletionListener
    {
        void OnDeletionAction(int data);
    }
    private ProfileDeletionListener mlistener;

    public void setDeletionListener(ProfileDeletionListener listener)
    {
        mlistener = listener;
    }
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState)
    {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(R.string.fragment_edit_profile_title);

        builder.setMessage(R.string.fragment_edit_profile_tv_deletion_confirmation)
                .setPositiveButton(R.string.fragment_edit_profile_delete, (Dialog, id) ->
                {
                    mlistener.OnDeletionAction(1);
                })
                .setNegativeButton(R.string.fragment_edit_profile_cancel, (dialog, id) ->
                {
                    mlistener.OnDeletionAction(0);
                });
        // Create the AlertDialog object and return it
        return builder.create();
    }
    @Override
    public void onAttach(Context context)
    {
        super.onAttach(context);
        // Verify that the host activity implements the callback interface
        try
        {
            // Instantiate the EditNameDialogListener so we can send events to the host
            EditProfileFragment.ProfileDeletionListener listener = (EditProfileFragment.ProfileDeletionListener) context;
        }
        catch (ClassCastException e) {
            // The activity doesn't implement the interface, throw exception
            throw new ClassCastException(context.toString()
                    + " must implement EditNameDialogListener");
        }
    }

}

package ecse321.group12.tamas.androidtamas;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;

import ecse321.group12.tamas.model.Applicant;
import ecse321.group12.tamas.model.Job;
import ecse321.group12.tamas.model.ResourceManager;
import ecse321.group12.tamas.model.TAjob;
import ecse321.group12.tamas.persistence.PersistenceXStream;


public class CurrentApplicationPendingFragment extends Fragment implements View.OnClickListener
{
    private int applicationNumber;
    private ResourceManager rm;
    private String fileName;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        applicationNumber=bundle.getInt("applicationIndex", -1);
        fileName = getContext().getApplicationContext().getFilesDir().getAbsolutePath() + "/tamas_data.xml";
        rm = PersistenceXStream.initializeModelManager(fileName);
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(getContext().getApplicationContext()).addApi(AppIndex.API).build();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.content_current_application_pending, container, false);
        Button edit = (Button) view.findViewById(R.id.fragment_current_applications_button_edit);
        edit.setOnClickListener(this);
        TextView tv = (TextView) view.findViewById(R.id.fragment_current_applications_tv_application_position_and_type);
        tv.setText(getApplicationInformation());
        return view;
    }
    @Override
    public void onClick(View v)
    {
            moveTo(EditApplicationActivity.class);
    }

    public void setArguments (Bundle bundle)
    {
        applicationNumber=bundle.getInt("ApplicationIndex",-1);
    }

    private void moveTo(Class target)
    {
        Intent i = new Intent(getActivity().getApplicationContext(), target);
        startActivity(i);
        getActivity().finish();
    }
    private String getApplicationInformation()
    {
        Applicant a= (Applicant) rm.getLoggedIn();
        Job J=a.getApplication(applicationNumber).getJob();
        String position;
        String positionType;
        if (J.getClass().equals(TAjob.class))
        {
            position="Teaching Assistant";
            if (((TAjob) J).getIsLab())
            {
                positionType="Laboratory";
            }
            else
            {
                positionType="Tutorial";
            }
        }
        else
        {
            position="Grader";
            positionType="";
        }
        return (a.getApplication(applicationNumber).getJob().getCourse().getName()+" "+position+" "+positionType);
    }
    public static CurrentApplicationPendingFragment newInstance(String text) {

        CurrentApplicationPendingFragment f = new CurrentApplicationPendingFragment();

        Bundle b = new Bundle();
        b.putString("text", text);
        f.setArguments(b);
        return f;
    }
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    public Action getIndexApiAction()
    {
        Thing object = new Thing.Builder()
                .setName("Login Page") // TODO: Define a title for the content shown.
                // TODO: Make sure this auto-generated URL is correct.
                .setUrl(Uri.parse("http://[ENTER-YOUR-URL-HERE]"))
                .build();
        return new Action.Builder(Action.TYPE_VIEW)
                .setObject(object)
                .setActionStatus(Action.STATUS_TYPE_COMPLETED)
                .build();
    }
    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        AppIndex.AppIndexApi.start(client, getIndexApiAction());
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        AppIndex.AppIndexApi.end(client, getIndexApiAction());
        client.disconnect();
    }


}

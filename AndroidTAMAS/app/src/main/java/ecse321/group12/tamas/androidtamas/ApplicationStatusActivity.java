package ecse321.group12.tamas.androidtamas;

import android.app.FragmentManager;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;

import ecse321.group12.tamas.controller.InvalidInputException;
import ecse321.group12.tamas.controller.TamasController;
import ecse321.group12.tamas.model.Applicant;
import ecse321.group12.tamas.model.Application;
import ecse321.group12.tamas.model.ResourceManager;
import ecse321.group12.tamas.model.TAjob;
import ecse321.group12.tamas.persistence.PersistenceXStream;

public class ApplicationStatusActivity extends AppCompatActivity
{
    private ResourceManager rm;
    private String fileName;
    int applicationNumber;
    boolean isAccepted;
    String error;

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_application_status);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener
                (
                        view -> Snackbar.make(view, "DONE?", Snackbar.LENGTH_LONG)
                                .setAction(
                                        "LOGOUT", v ->
                                        {
                                            TamasController tc = new TamasController(rm);
                                            tc.logOut();
                                            moveTo(LoginActivity.class,null);
                                        }
                                ).show()
                );

        fileName = getFilesDir().getAbsolutePath() + "/tamas_data.xml";
        rm = PersistenceXStream.initializeModelManager(fileName);

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();

        //check the is accepted status, then inflate the correct layout!
        //requires the application number to be passed from the previous activity

        Bundle info = getIntent().getExtras();
        applicationNumber=info.getInt("currentApplication",-1);
        isAccepted=info.getBoolean("isAccepted", false);

        LinearLayout parent= (LinearLayout) findViewById(R.id.application_status_sc_inflation_target);
        if (isAccepted)
        {
            View child = getLayoutInflater().inflate(R.layout.content_job_status_accepted, null);
            child.setId(applicationNumber+1000);
            parent.addView(child);

            RadioButton decline = (RadioButton) child.findViewById(R.id.job_status_radiobutton_decline);
            Button confirm = (Button) child.findViewById(R.id.job_status_accepted_button_confirm);
            confirm.setOnClickListener(v ->
            {
                AlertDialogFragment deletionWarning = new AlertDialogFragment();
                FragmentManager fm = getFragmentManager();
                deletionWarning.setArguments(bundleAlertData());
                deletionWarning.show(fm,"DeletionDialogFragment");
                deletionWarning.setDeletionListener(new AlertDialogFragment.DeletionListener()
                {//DO NOT TURN THIS STATEMENT INTO A LAMBDA!!! THE EXPLICIT OVERRIDE IS NEEDED!
                    @Override
                    public void OnDeletionAction(int data)
                    {
                        if (data == 1)
                        {
                            TamasController tc = new TamasController(rm);
                            try
                            {
                                if (!decline.isChecked())
                                {
                                    tc.assignApplicantToJob(((Applicant) rm.getLoggedIn()).getApplication(applicationNumber));
                                }
                                rm.removeApplication(((Applicant) rm.getLoggedIn()).getApplication(applicationNumber));
                                ((Applicant) rm.getLoggedIn()).getApplications().size();
                                PersistenceXStream.saveToXMLwithXStream(rm);
                                moveTo(CurrentApplicationActivity.class, null);
                            }
                            catch (InvalidInputException e)
                            {
                                error=e.getMessage();
                                Toast.makeText(getApplicationContext(),error,Toast.LENGTH_SHORT).show();
                            }

                        }
                    }
                });
            });
            TextView tv=(TextView) child.findViewById(R.id.job_status_accepted_textview_acceptance_course);
            tv.setText(((Applicant)rm.getLoggedIn()).getApplication(applicationNumber).getJob().getCourse().getName());

            tv=(TextView) child.findViewById(R.id.job_status_accepted_textview_acceptance_position);
            tv.setText(getPosition(applicationNumber));

            tv=(TextView) child.findViewById(R.id.job_status_accepted_textview_acceptance_hours);
            tv.setText(((Applicant)rm.getLoggedIn()).getApplication(applicationNumber).getJob().getMaxHours());

            tv=(TextView) child.findViewById(R.id.job_status_accepted_textview_acceptance_wage);
            tv.setText(Double.toString(((Applicant)rm.getLoggedIn()).getApplication(applicationNumber).getJob().getWage()));

            tv=(TextView) child.findViewById(R.id.job_status_accepted_textview_acceptance_letter);
            String mesg="Congratulations, you have been accepted! Do you wish to accept this offer?";
            tv.setText(mesg);
        }
        else
        {
            View child = getLayoutInflater().inflate(R.layout.content_job_status_rejected, null);
            child.setId(applicationNumber+1000);
            parent.addView(child);

            Button back = (Button) child.findViewById(R.id.job_status_rejected_button_confirm);
            back.setOnClickListener(v ->
            {
                AlertDialogFragment deletionWarning = new AlertDialogFragment();
                FragmentManager fm = getFragmentManager();
                deletionWarning.setArguments(bundleAlertData());
                deletionWarning.show(fm,"DeletionDialogFragment");
                deletionWarning.setDeletionListener
                    (new AlertDialogFragment.DeletionListener()
                {//DO NOT TURN THIS STATEMENT INTO A LAMBDA!!! THE EXPLICIT OVERRIDE IS NEEDED!
                    @Override
                    public void OnDeletionAction(int data)
                    {
                        if (data == 1)
                        {
                                ((Applicant) rm.getLoggedIn()).getApplication(applicationNumber).delete();
                                ((Applicant) rm.getLoggedIn()).getApplications().size();
                                PersistenceXStream.saveToXMLwithXStream(rm);
                                moveTo(CurrentApplicationActivity.class, null);
                        }
                    }
                });
            });
        }
    }
    private String getPosition(int position)
    {
       Application a = ((Applicant) rm.getLoggedIn()).getApplication(position);

        String positionName;
        String positionType;

        if (a.getJob().getClass().equals(TAjob.class))
        {
            positionName = "Teaching Assistant";

            TAjob j =(TAjob)a.getJob();
            boolean type =j.isIsLab();
            if(type==true)
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
            positionName = "Grader";
            positionType="";
        }
        return positionName+" "+positionType;
    }
    private Bundle bundleAlertData()
    {
        Intent i=new Intent(getApplicationContext(), ApplicationStatusActivity.class);

        i.putExtra("Title","WARNING");
        i.putExtra("Positive Confirmation","DELETE");
        i.putExtra("Negative Confirmation","CANCEL");
        i.putExtra("Notification","This action will delete offer and your application. Are you sure of your decision?");
        Bundle ret=i.getExtras();
        return ret;
    }
    private void moveTo(Class target, Intent i)
    {
        if (i==null)
        {
            i = new Intent(getApplicationContext(), target);
        }
        startActivity(i);
        finish();
    }
    @Override
    public void onBackPressed()
    {
        moveTo(HomeActivity.class, null);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_login, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
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
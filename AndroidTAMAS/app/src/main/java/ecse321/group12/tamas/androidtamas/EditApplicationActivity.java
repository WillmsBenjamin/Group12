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
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;

import ecse321.group12.tamas.controller.InvalidInputException;
import ecse321.group12.tamas.controller.TamasController;
import ecse321.group12.tamas.model.Applicant;
import ecse321.group12.tamas.model.ResourceManager;
import ecse321.group12.tamas.persistence.PersistenceXStream;

public class EditApplicationActivity extends AppCompatActivity {

    private ResourceManager rm;
    private String fileName;
    String error = null;
    int applicationNumber;

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_application);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        applicationNumber=getIntent().getExtras().getInt("currentApplication", -1);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener
                (
                        view -> Snackbar.make(view, "DONE?", Snackbar.LENGTH_LONG)
                                .setAction(
                                        "LOGOUT", v ->
                                        {
                                            TamasController tc = new TamasController(rm);
                                            tc.logOut();
                                            moveTo(LoginActivity.class, null);
                                        }
                                ).show()
                );
        fileName = getFilesDir().getAbsolutePath() + "/tamas_data.xml";
        rm = PersistenceXStream.initializeModelManager(fileName);
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
        Button delete = (Button) findViewById(R.id.edit_application_button_delete_application);
        delete.setOnClickListener(v ->
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
                        //create a controller method
                        ((Applicant) rm.getLoggedIn()).getApplication(applicationNumber).delete();
                        PersistenceXStream.saveToXMLwithXStream(rm);
                        moveTo(CurrentApplicationActivity.class, null);
                    }
                }
            });
        });
        TextView tv = (TextView) findViewById(R.id.edit_application_immutable_job_title);
        tv.setText( ((Applicant) rm.getLoggedIn()).getApplication(applicationNumber).getJob().getCourse().getName());

        refreshData();
    }
    public Bundle bundleAlertData()
    {
        Intent i=new Intent(getApplicationContext(), EditApplicationActivity.class);

        i.putExtra("Title","WARNING");
        i.putExtra("Positive Confirmation","DELETE");
        i.putExtra("Negative Confirmation","CANCEL");
        i.putExtra("Notification","This action will delete the selected application and all the associated data. Do you still wish to delete the application?");
        Bundle ret=i.getExtras();
        return ret;
    }
    public void modifyApplication(View v)
    {
        TamasController tc = new TamasController(rm);

        EditText et = (EditText) findViewById(R.id.edit_application_et_mutable_courseexperience);
        String experience=et.getText().toString();
        et = (EditText) findViewById(R.id.edit_application_et_immutable_coursegpa);
        String courseGPA=et.getText().toString();

        try
        {
            tc.modifyApplication(experience,courseGPA,(Applicant)rm.getLoggedIn(),((Applicant) rm.getLoggedIn()).getApplication(applicationNumber).getJob());
            Toast.makeText(getApplicationContext(),"Application Successfully Updated",Toast.LENGTH_SHORT).show();
            moveTo(CurrentApplicationActivity.class, null);
        }
        catch (InvalidInputException e)
        {
            error=e.getMessage();
            Toast.makeText(getApplicationContext(),error,Toast.LENGTH_SHORT).show();
        }


    }
    private void refreshData()
    {
        EditText et = (EditText) findViewById(R.id.edit_application_et_mutable_courseexperience);
        if(!et.getText().equals( ((Applicant) rm.getLoggedIn()).getApplication(applicationNumber).getExperience()) )
        {
          et.setText(((Applicant) rm.getLoggedIn()).getApplication(applicationNumber).getExperience());
        }
        et = (EditText) findViewById(R.id.edit_application_et_immutable_coursegpa);
        if(!et.getText().equals( ((Applicant) rm.getLoggedIn()).getApplication(applicationNumber).getCourseGPA()) )
        {
            et.setText(((Applicant) rm.getLoggedIn()).getApplication(applicationNumber).getCourseGPA());
        }
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
        moveTo(CurrentApplicationActivity.class, null);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_login, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
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

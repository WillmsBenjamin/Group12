package ecse321.group12.tamas.androidtamas;

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
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;

import ecse321.group12.tamas.controller.InvalidInputException;
import ecse321.group12.tamas.controller.TamasController;
import ecse321.group12.tamas.model.Applicant;
import ecse321.group12.tamas.model.Job;
import ecse321.group12.tamas.model.ResourceManager;
import ecse321.group12.tamas.persistence.PersistenceXStream;

public class ApplicationActivity extends AppCompatActivity {

    private ResourceManager rm;
    private String fileName;
    String error = null;

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_application);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener
                (
                view -> Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
            );

        fileName = getFilesDir().getAbsolutePath() + "/eventregistration.xml";
        rm = PersistenceXStream.initializeModelManager(fileName);
        refreshData();
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    private void refreshData()
    {
        TextView tv = (TextView) findViewById(R.id.application_coursegpa);
        tv.setText("");
        tv = (TextView) findViewById(R.id.application_courseexperience);
        tv.setText("");
        tv = (TextView) findViewById(R.id.application_required_experience);
        tv.setText("");

        // Initialize the data in the job spinner
        Spinner spinner = (Spinner) findViewById(R.id.spinner_joblist);

        ArrayAdapter<CharSequence> jobAdapter = new ArrayAdapter<CharSequence>(this, android.R.layout.simple_spinner_item);

        jobAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        for (Job j: rm.getJobs() )
        {
            jobAdapter.add(j.getCourse().toString());
        }
        spinner.setAdapter(jobAdapter);
    }
    public void createApplication(View v)
    {
        TamasController tc = new TamasController(rm);

        TextView tv = (TextView) findViewById(R.id.application_coursegpa);
        String coursegpa = tv.getText().toString();
        tv = (TextView) findViewById(R.id.application_courseexperience);
        String experience = tv.getText().toString();

        Spinner spinner = (Spinner) findViewById (R.id.spinner_joblist);
        int jobindex = spinner.getSelectedItemPosition();
        
        try
        {
            tc.applyToJob(experience,coursegpa,(Applicant)rm.getLoggedIn(),rm.getJob(jobindex));
        }
        catch(InvalidInputException e)
        {
            error=e.getMessage();
            Toast.makeText(getApplicationContext(),error,Toast.LENGTH_SHORT).show();
        }
        refreshData();
        moveToMainPage();
    }
    public void moveToMainPage()
    {
        Intent i = new Intent(getApplicationContext(), MainActivity.class);

        startActivity(i);
        refreshData();
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
}

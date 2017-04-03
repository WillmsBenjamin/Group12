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
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_application);
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
                                            moveTo(LoginActivity.class);
                                        }
                                ).show()
                );
        fileName = getFilesDir().getAbsolutePath() + "/tamas_data.xml";
        rm = PersistenceXStream.initializeModelManager(fileName);
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
        refreshData();
    }

    private void moveTo(Class target)
    {
        Intent i = new Intent(getApplicationContext(), target);

        startActivity(i);
        finish();
    }
    public void onBackPressed()
    {
        moveTo(HomeActivity.class);
    }
    private void refreshData()
    {
        TextView tv = (TextView) findViewById(R.id.application_edittext_coursegpa);
        tv.setText("");
        tv = (TextView) findViewById(R.id.application_edittext_relevantexperience);
        tv.setText("");
        tv = (TextView) findViewById(R.id.application_textview_job_title);
        String name=getIntent().getStringExtra("name");
        tv.setCompoundDrawablesWithIntrinsicBounds(new TextDrawable(name), null, null, null);
        tv.setCompoundDrawablePadding(name.length()*10);

    }
    public void createApplication(View v)
    {
        TextView tv = (TextView) findViewById(R.id.application_edittext_coursegpa);
        String coursegpa = tv.getText().toString();
        tv = (TextView) findViewById(R.id.application_edittext_relevantexperience);
        String experience = tv.getText().toString();

        Bundle bundle = getIntent().getExtras();
        int jobindex = bundle.getInt("jindex",-1);
        Job J = rm.getJob(jobindex);
        Applicant A = (Applicant) rm.getLoggedIn();
        try
        {
            TamasController tc = new TamasController(rm);
            tc.applyToJob(experience,coursegpa,A,J);
            Toast.makeText(getApplicationContext(),"Application Successful",Toast.LENGTH_SHORT).show();
            moveTo(ViewJobsActivity.class);
        }
        catch(InvalidInputException e)
        {
            error=e.getMessage();
            Toast.makeText(getApplicationContext(),error,Toast.LENGTH_SHORT).show();
            refreshData();
        }

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

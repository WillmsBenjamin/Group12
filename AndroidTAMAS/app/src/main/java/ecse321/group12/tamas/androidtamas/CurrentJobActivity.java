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
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;

import java.util.Calendar;

import ecse321.group12.tamas.controller.InvalidInputException;
import ecse321.group12.tamas.controller.TamasController;
import ecse321.group12.tamas.model.Applicant;
import ecse321.group12.tamas.model.Assignment;
import ecse321.group12.tamas.model.Course;
import ecse321.group12.tamas.model.Instructor;
import ecse321.group12.tamas.model.ResourceManager;
import ecse321.group12.tamas.model.TAjob;
import ecse321.group12.tamas.persistence.PersistenceXStream;

public class CurrentJobActivity extends AppCompatActivity
{
    private ResourceManager rm;
    private String fileName;
    private String error=null;
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_current_job);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        fileName = getFilesDir().getAbsolutePath() + "/eventregistration.xml";
        rm = PersistenceXStream.initializeModelManager(fileName);

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();

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
        Button done = (Button) findViewById(R.id.current_job_button_done);
        done.setOnClickListener(v -> moveTo(HomeActivity.class));
        refreshData();
    }


    private void moveTo(Class target) {
        Intent i = new Intent(getApplicationContext(), target);
        startActivity(i);
        finish();
    }
    private void refreshData()
    {
        Spinner spinner = (Spinner) findViewById(R.id.current_job_spinner_activejobs);

        ArrayAdapter<CharSequence> jAdapter = new
                ArrayAdapter<>(this, android.R.layout.simple_spinner_item);

        jAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        for (Assignment a: rm.getAssignments() )
        {
            if (a.getApplicant()==rm.getLoggedIn())
            {
                jAdapter.add(a.getJob().getCourse().getName());
            }
        }
        spinner.setAdapter(jAdapter);

        if (jAdapter.isEmpty())
        {
            Toast.makeText(getApplicationContext(),"Creating Dummy Jobs",Toast.LENGTH_SHORT).show();
            //createDummyAssignments();
        }
        else
        {
            Assignment a = rm.getAssignment(spinner.getSelectedItemPosition());

            String position;
            String positiontype;

            if (a.getJob().getClass().equals(TAjob.class))
            {
                position = "Teaching Assistant";

                TAjob j =(TAjob)a.getJob();
                boolean type =j.isIsLab();
                if(type==true)
                {
                    positiontype="Laboratory";
                }
                else
                {
                    positiontype="Tutorial";
                }
            }
            else
            {
                position = "Grader";
                positiontype="";
            }
            drawField(position, positiontype, a.getFeedback());
        }
        refreshData();
    }

    private void createDummyAssignments()
    {
        TamasController tc = new TamasController(rm);
        Applicant A=(Applicant)rm.getLoggedIn();
        try {
            Course C= new Course("ECSE321-001",2,0,50,400);
            Instructor I= new Instructor("Daniel Varro","133713371");
            tc.addInstructorToCourse(I,C);

            int year=2050;
            int month=11;
            int day = 12;

            Calendar cal = Calendar.getInstance();
            cal.set(Calendar.YEAR, year);
            cal.set(Calendar.MONTH, month - 1);
            cal.set(Calendar.DAY_OF_MONTH, day);

            java.sql.Date date = new java.sql.Date(cal.getTimeInMillis());
            tc.postGraderJob(100,10,date,"Being a Duck","3.90","3.90","not Being a Duck",C,true);
            tc.postTAJob(100,10.0,date,"Being a DuckerTruck","3.90","3.90","Being a Duck",C, 45, true,true);

            /*
            tc.assignToJob(Applicant,Assignment);
            tc.assignToJob(A,);
             */
            Toast.makeText(getApplicationContext(),"Dummy Jobs Created",Toast.LENGTH_SHORT).show();
        }
        catch (InvalidInputException e)
        {
            error=e.getMessage();
            Toast.makeText(getApplicationContext(),error,Toast.LENGTH_SHORT);
        }

    }

    private void drawField(String position, String positionType, String feedback)
    {
        TextView tv = (TextView) findViewById(R.id.current_job_textview_position);
        tv.setCompoundDrawablesWithIntrinsicBounds(new TextDrawable(position+positionType), null, null, null);
        tv.setCompoundDrawablePadding((position+" "+positionType).length()*10);

        tv = (TextView) findViewById(R.id.current_job_textview_feedback);
        tv.setCompoundDrawablesWithIntrinsicBounds(new TextDrawable(feedback), null, null, null);
        tv.setCompoundDrawablePadding(feedback.length()*10);
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
    public Action getIndexApiAction() {
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

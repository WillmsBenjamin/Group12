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
import ecse321.group12.tamas.model.ResourceManager;
import ecse321.group12.tamas.persistence.PersistenceXStream;

import static android.R.attr.data;

public abstract class EditProfileActivity extends AppCompatActivity implements EditProfileFragment.ProfileDeletionListener
{
    private ResourceManager rm;
    private String fileName;
    String error = null;
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);
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
                                            fileName = getFilesDir().getAbsolutePath() + "/tamas_data.xml";
                                            rm = PersistenceXStream.initializeModelManager(fileName);
                                            tc.logOut();
                                            moveTo(LoginActivity.class);
                                        }
                                ).show()
                );

        TextView tv = (TextView) findViewById(R.id.edit_profile_immutable_name);
        tv.setText(rm.getLoggedIn().getName());
        tv = (TextView) findViewById(R.id.edit_profile_immutable_identification_number);
        tv.setText(rm.getLoggedIn().getId());
        Button delete = (Button) findViewById(R.id.edit_profile_button_delete_profile);
        delete.setOnClickListener(v -> {
            EditProfileFragment deletionWarning = new EditProfileFragment();
            FragmentManager fm = getFragmentManager();
            deletionWarning.show(fm,"DeletionDialogFragment");
            deletionWarning.setDeletionListener(data1 -> {
                if (data1 ==1)
                {
                    TamasController tc = new TamasController(rm);
                    rm.removeApplicant((Applicant) rm.getLoggedIn());
                    tc.logOut();
                }
            });


        });
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();

        fileName = getFilesDir().getAbsolutePath() + "/tamas_data.xml";
        rm = PersistenceXStream.initializeModelManager(fileName);
        tv.setText(rm.getLoggedIn().getId());

        refreshData();
    }

    private void refreshData()
    {
        EditText et = (EditText) findViewById(R.id.edit_profile_mutable_applicant_cgpa);
        Applicant a = (Applicant) rm.getLoggedIn();
        if (!et.equals(a.getCGPA()))
        {
            et.setText(a.getCGPA());
        }
        et = (EditText) findViewById(R.id.edit_profile_mutable_applicant_cgpa);
        if (!et.equals(a.getSkills()))
        {
            et.setText(a.getSkills());
        }
        RadioButton isGraduate =(RadioButton) findViewById(R.id.register_radiobutton_graduate_student);
        isGraduate.setChecked(a.getIsGraduate());
    }
    public void modifyApplicant(View v)
    {
        TamasController tc = new TamasController(rm);

        TextView tv = (TextView) findViewById(R.id.edit_profile_mutable_applicant_cgpa);
        String cgpa = tv.getText().toString();

        tv = (TextView) findViewById(R.id.edit_profile_mutable_applicant_skills);
        String skills =tv.getText().toString();

        RadioButton isGraduate =(RadioButton) findViewById(R.id.register_radiobutton_graduate_student);
        Boolean studentType = isGraduate.isChecked();
        try
        {
            tc.modifyApplicant(null,null,cgpa,skills,studentType);
            Toast.makeText(getApplicationContext(),"Update Successfully",Toast.LENGTH_SHORT).show();
            moveTo(HomeActivity.class);
        }
        catch (InvalidInputException e)
        {
            error=e.getMessage();
            Toast.makeText(getApplicationContext(),error,Toast.LENGTH_LONG).show();
            refreshData();
        }
    }
    private void moveTo(Class target)
    {
        Intent i = new Intent(getApplicationContext(), target);
        startActivity(i);
        finish();
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

package ecse321.group12.tamas.androidtamas;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;

import ecse321.group12.tamas.controller.TamasController;
import ecse321.group12.tamas.model.ResourceManager;

public class HomeActivity extends AppCompatActivity {

    private ResourceManager rm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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
        Button jp = (Button) findViewById(R.id.main_button_job_postings);
        jp.setOnClickListener(v -> moveTo(ViewJobsActivity.class));
        Button ca = (Button) findViewById(R.id.main_button_current_applications);
        ca.setOnClickListener(v -> moveTo(CurrentApplicationActivity.class));
        Button oj = (Button) findViewById(R.id.main_button_ongoing_jobs);
        oj.setOnClickListener(v -> moveTo(CurrentJobActivity.class));
        Button ep = (Button) findViewById(R.id.main_button_edit_profile);
        ep.setOnClickListener(v -> moveTo(EditProfileActivity.class));

    }
    private void moveTo(Class target)
    {
        Intent i = new Intent(getApplicationContext(), target);
        startActivity(i);
        finish();
    }
    @Override
    public void onBackPressed()
    {
        //Trigger the alert Dialog and tell the user that they will log out!
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
}

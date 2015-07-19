package jp.gr.java_conf.chinachuroid;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;


public class MainActivity extends AppCompatActivity {
    public static final String SERVER_HOST = "ServerHost",
            SERVER_PORT = "ServerPort",
            USER_ID = "UserId",
            USER_PASSWORD = "UserPassword";

    private EditText server_host, server_port, user_id, user_password;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d("Chinachuroid", "onCreate");

        server_host = (EditText) findViewById(R.id.etx_server_host);
        server_port = (EditText) findViewById(R.id.etx_server_port);
        user_id = (EditText) findViewById(R.id.etx_user_id);
        user_password = (EditText) findViewById(R.id.etx_user_password);

        findViewById(R.id.btn_login).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(getApplicationContext(), RecordedActivity.class);
                it.putExtra(SERVER_HOST, server_host.getText().toString());
                it.putExtra(SERVER_PORT, server_port.getText().toString());
                it.putExtra(USER_ID, user_id.getText().toString());
                it.putExtra(USER_PASSWORD, user_password.getText().toString());
                startActivity(it);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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

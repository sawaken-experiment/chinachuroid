package jp.gr.java_conf.chinachuroid;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;


public class MainActivity extends AppCompatActivity {
    public static final String SERVER_HOST = "ServerHost",
            SERVER_PORT = "ServerPort",
            USER_ID = "UserId",
            USER_PASSWORD = "UserPassword";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText server_host = (EditText) findViewById(R.id.etx_server_host);
        EditText server_port = (EditText) findViewById(R.id.etx_server_port);
        EditText user_id = (EditText) findViewById(R.id.etx_user_id);
        EditText user_password = (EditText) findViewById(R.id.etx_user_password);

        SharedPreferences pf = getPreferences(MODE_PRIVATE);
        server_host.setText(pf.getString(SERVER_HOST, ""));
        server_port.setText(pf.getString(SERVER_PORT, ""));
        user_id.setText(pf.getString(USER_ID, ""));
        user_password.setText(pf.getString(USER_PASSWORD, ""));

        findViewById(R.id.btn_login).setOnClickListener(v -> {
            String host = server_host.getText().toString();
            String port = server_port.getText().toString();
            String id = user_id.getText().toString();
            String pw = user_password.getText().toString();

            SharedPreferences.Editor editor = pf.edit();
            editor.putString(SERVER_HOST, host);
            editor.putString(SERVER_PORT, port);
            editor.putString(USER_ID, id);
            editor.putString(USER_PASSWORD, pw);
            editor.commit();

            Intent it = new Intent(getApplicationContext(), RecordedActivity.class);
            it.putExtra(SERVER_HOST, host);
            it.putExtra(SERVER_PORT, port);
            it.putExtra(USER_ID, id);
            it.putExtra(USER_PASSWORD, pw);
            startActivity(it);

        });
    }
}

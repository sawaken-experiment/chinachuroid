package jp.gr.java_conf.chinachuroid;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Authenticator;
import java.net.HttpURLConnection;
import java.net.PasswordAuthentication;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Owner on 2015/07/19.
 */
public class RecordedActivity extends AppCompatActivity {
    private RecordedListAdapter rcdls_adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recorded);

        ChinachuServerURL chinachu_url = new ChinachuServerURL(getIntent().getStringExtra(MainActivity.SERVER_HOST),
                getIntent().getStringExtra(MainActivity.SERVER_PORT));

        ListView lsview = (ListView) findViewById(R.id.rcd_lsvItem);
        rcdls_adapter = new RecordedListAdapter(this, 0, new ArrayList<RecordedItem>());
        lsview.setAdapter(rcdls_adapter);

        Authenticator.setDefault(new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                String id = getIntent().getStringExtra(MainActivity.USER_ID);
                String pw = getIntent().getStringExtra(MainActivity.USER_PASSWORD);
                return new PasswordAuthentication(id, pw.toCharArray());
            }
        });
        new GetRecordedTask().execute(chinachu_url.getURL("/recorded.json"));
    }

    private static class ChinachuServerURL {
        private String server_host, server_port;

        public ChinachuServerURL(String server_host, String server_port) {
            this.server_host = server_host;
            this.server_port = server_port;
        }

        public String getBaseURL() {
            return "http://" + server_host + ":" + server_port + "/api";
        }

        public String getURL(String query) {
            return getBaseURL() + query;
        }
    }

    private class GetRecordedTask extends AsyncTask<String, Void, List<RecordedItem>> {

        @Override
        protected List<RecordedItem> doInBackground(String... params) {
            List<RecordedItem> result = new ArrayList<>();
            try {

                URL url = new URL(params[0]);
                HttpURLConnection con = (HttpURLConnection) url.openConnection();
                con.setRequestMethod("GET");
                Log.d("Chinachu", "Status:" + con.getResponseCode());
                JSONArray recordeds = new JSONArray(inputStreamToString(con.getInputStream()));
                for (int i = 0; i < recordeds.length(); i++) {
                    JSONObject recorded = recordeds.getJSONObject(i);
                    String channel = recorded.getJSONObject("channel").getString("name");
                    String title = recorded.getString("fullTitle");
                    result.add(new RecordedItem(channel, title));
                }
            } catch (Exception ex) {
                Log.e("Chinachu", "API GET Recorded Error", ex);
            }
            return result;
        }

        String inputStreamToString(InputStream is) throws IOException {
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }
            br.close();
            return sb.toString();
        }

        @Override
        protected void onPostExecute(List<RecordedItem> recordedItems) {
            rcdls_adapter.clear();
            rcdls_adapter.addAll(recordedItems);
        }
    }
}

package jp.gr.java_conf.chinachuroid;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;

import com.squareup.okhttp.OkHttpClient;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import jp.gr.java_conf.chinachuroid.model.ChinachuAPI;
import jp.gr.java_conf.chinachuroid.model.recorded.RecordedItem;
import retrofit.RequestInterceptor;
import retrofit.RestAdapter;
import retrofit.client.OkClient;
import rx.Observable;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.android.widget.WidgetObservable;
import rx.schedulers.Schedulers;
import rx.subjects.PublishSubject;

/**
 * Created by Owner on 2015/07/19.
 */
public class RecordedActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recorded);

        ImageView mReload = (ImageView) findViewById(R.id.imgb_rcd_reload);
        EditText mSearchText = (EditText) findViewById(R.id.etx_rcd_search_text);
        Spinner mSortKey = (Spinner) findViewById(R.id.spn_rcd_sort_key);
        ListView mLsview = (ListView) findViewById(R.id.rcd_lsvItem);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item);
        adapter.add("Date");
        adapter.add("Channel");
        adapter.add("Title");
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mSortKey.setAdapter(adapter);

        RecordedListAdapter rcdls_adapter = new RecordedListAdapter(this, 0, new ArrayList<RecordedItem>());
        mLsview.setAdapter(rcdls_adapter);

        ChinachuAPI api;
        Boolean authEnable = getIntent().getStringExtra(MainActivity.SERVER_AUTH).equals("yes");
        String protocol = getIntent().getStringExtra(MainActivity.SERVER_PROTOCOL);
        String host = getIntent().getStringExtra(MainActivity.SERVER_HOST);
        String port = getIntent().getStringExtra(MainActivity.SERVER_PORT);
        String id = getIntent().getStringExtra(MainActivity.USER_ID);
        String pw = getIntent().getStringExtra(MainActivity.USER_PASSWORD);
        if (authEnable) {
            api = setupAPIWithAuth(protocol, host, port, id, pw);
        } else {
            api = setupAPI(protocol, host, port);
        }

        Observable<String> oSearchText = Observable.concat(Observable.just(""),
                WidgetObservable.text(mSearchText).map(onTextChangeEvent -> onTextChangeEvent.text().toString()));
        Observable<String> oSortKey = observeSelect(mSortKey);
        Observable<Boolean> oAPIReqTrigger = Observable.concat(Observable.just(true), observeClick(mReload));

        Observable<List<RecordedItem>> oRecordedList = oAPIReqTrigger
                .observeOn(Schedulers.newThread())
                .map(b -> api.getRecordedList());

        Observable.combineLatest(oSearchText, oSortKey, oRecordedList, RecordedActivity::selectRecordedList)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<RecordedItem>>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                        Intent it = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(it);
                    }

                    @Override
                    public void onNext(List<RecordedItem> recordedItems) {
                        rcdls_adapter.clear();
                        rcdls_adapter.addAll(recordedItems);
                    }
                });
    }

    public static rx.Observable<String> observeSelect(Spinner spinner) {
        final PublishSubject<String> selectSubject = PublishSubject.create();
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String item = (String) parent.getItemAtPosition(position);
                selectSubject.onNext(item);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        return selectSubject;
    }

    public static <T extends View> rx.Observable<Boolean> observeClick(T view) {
        final PublishSubject<Boolean> clickSubject = PublishSubject.create();
        view.setOnClickListener(v -> {
            clickSubject.onNext(true);
        });
        return clickSubject;
    }

    public static ChinachuAPI setupAPIWithAuth(String protocol, String server_host, String server_port, String user_id, String user_password) {
        RestAdapter.Builder builder = new RestAdapter.Builder()
                .setEndpoint(protocol + "://" + server_host + ":" + server_port + "/api")
                .setClient(new OkClient(new OkHttpClient()));

        builder.setRequestInterceptor(new RequestInterceptor() {
            @Override
            public void intercept(RequestInterceptor.RequestFacade request) {
                String credentials = user_id + ":" + user_password;
                String string = "Basic " + Base64.encodeToString(credentials.getBytes(), Base64.NO_WRAP);
                request.addHeader("Accept", "application/json");
                request.addHeader("Authorization", string);
            }
        });

        RestAdapter restAdapter = builder.build();
        return restAdapter.create(ChinachuAPI.class);
    }

    public static ChinachuAPI setupAPI(String protocol, String server_host, String server_port) {
        return new RestAdapter.Builder()
                .setEndpoint(protocol + "://" + server_host + ":" + server_port + "/api")
                .setClient(new OkClient(new OkHttpClient())).build().create(ChinachuAPI.class);
    }

    public static List<RecordedItem> selectRecordedList(String searchText, String sortKey, List<RecordedItem> ls) {
        switch (sortKey) {
            case "Date":
                Collections.sort(ls, (RecordedItem a, RecordedItem b) -> a.getStart().compareTo(b.getStart()));
                break;
            case "Title":
                Collections.sort(ls, (RecordedItem a, RecordedItem b) -> a.getFullTitle().compareTo(b.getFullTitle()));
                break;
            case "Channel":
                Collections.sort(ls, (RecordedItem a, RecordedItem b) -> a.getChannel().getName().compareTo(b.getChannel().getName()));
                break;
        }
        List<RecordedItem> result = new ArrayList<>();
        for (RecordedItem item : ls) {
            if (item.getFullTitle().contains(searchText) || item.getChannel().getName().contains(searchText)) {
                result.add(item);
            }
        }
        return result;
    }
}

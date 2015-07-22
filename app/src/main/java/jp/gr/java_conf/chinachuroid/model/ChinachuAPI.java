package jp.gr.java_conf.chinachuroid.model;

import java.util.List;

import jp.gr.java_conf.chinachuroid.model.recorded.RecordedItem;
import retrofit.http.GET;

/**
 * Created by Owner on 2015/07/22.
 */
public interface ChinachuAPI {
    @GET("/recorded.json")
    List<RecordedItem> getRecordedList();
}

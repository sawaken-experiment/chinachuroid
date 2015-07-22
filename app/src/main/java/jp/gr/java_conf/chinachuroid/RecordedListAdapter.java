package jp.gr.java_conf.chinachuroid;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import jp.gr.java_conf.chinachuroid.model.recorded.RecordedItem;

/**
 * Created by Owner on 2015/07/19.
 */
public class RecordedListAdapter extends ArrayAdapter<RecordedItem> {
    public RecordedListAdapter(Context context, int resource, List<RecordedItem> ls) {
        super(context, resource, ls);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder vh;
        if (convertView == null) {
            LayoutInflater inf = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inf.inflate(R.layout.recorded_list_item, null);
            vh = new ViewHolder(convertView);
            convertView.setTag(vh);
        } else {
            vh = (ViewHolder) convertView.getTag();
        }

        RecordedItem item = getItem(position);
        vh.txvChannel.setText(item.getChannel().getName());
        vh.txvTitle.setText(item.getFullTitle());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy”NMMŒŽdd“ú(E) HH:mm:ss");
        vh.txvDate.setText(sdf.format(new Date(item.getStart())));
        return convertView;
    }

    private static class ViewHolder {
        public TextView txvChannel;
        public TextView txvTitle;
        public TextView txvDate;

        public ViewHolder(View v) {
            txvChannel = (TextView) v.findViewById(R.id.txv_rcd_ch);
            txvTitle = (TextView) v.findViewById(R.id.txv_rcd_title);
            txvDate = (TextView) v.findViewById(R.id.txv_rcd_date);
        }
    }
}

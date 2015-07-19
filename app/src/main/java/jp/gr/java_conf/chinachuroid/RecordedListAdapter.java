package jp.gr.java_conf.chinachuroid;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Owner on 2015/07/19.
 */
public class RecordedListAdapter extends ArrayAdapter<RecordedItem> {
    public RecordedListAdapter(Context c, int resource, List<RecordedItem> ls) {
        super(c, resource, ls);
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
        vh.txvCh.setText(item.channel);
        vh.txvTi.setText(item.title);
        return convertView;
    }

    private static class ViewHolder {
        public TextView txvCh;
        public TextView txvTi;

        public ViewHolder(View v) {
            txvCh = (TextView) v.findViewById(R.id.txv_rcd_ch);
            txvTi = (TextView) v.findViewById(R.id.txv_rcd_title);
        }
    }
}

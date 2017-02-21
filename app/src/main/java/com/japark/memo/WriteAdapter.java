package com.japark.memo;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Map;

/**
 * Created by jinapark on 2016. 8. 11..
 */
public class WriteAdapter extends BaseAdapter {

    private LayoutInflater inflater;
    private ArrayList<Map<String, Object>> list;
    private Context context;

    public WriteAdapter(Context _context, ArrayList<Map<String, Object>> _list) {
        this.list = _list;
        this.inflater = LayoutInflater.from(_context);
        this.context = _context;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    static class ViewHolder {
        LinearLayout memoList;
        TextView title;
        TextView contents;
        TextView date;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        final ViewHolder holder;

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.item_write, parent, false);
            holder = new ViewHolder();

            holder.memoList = (LinearLayout) convertView.findViewById(R.id.memo_list);
            holder.title = (TextView)convertView.findViewById(R.id.title);
            holder.contents = (TextView)convertView.findViewById(R.id.contents);
            holder.date = (TextView)convertView.findViewById(R.id.date);

            convertView.setTag(holder);

        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.memoList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, update.class);
                intent.putExtra("title", list.get(position).get("title").toString());
                intent.putExtra("contents", list.get(position).get("contents").toString());
                intent.putExtra("date", list.get(position).get("date").toString());
                intent.putExtra("id", list.get(position).get("id").toString());
                context.startActivity(intent);
            }
        });

        holder.title.setText(list.get(position).get("title").toString());
        holder.contents.setText(list.get(position).get("contents").toString());
        holder.date.setText(list.get(position).get("date").toString());

        return convertView;
    }
}

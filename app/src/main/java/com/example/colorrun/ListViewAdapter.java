package com.example.colorrun;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class ListViewAdapter extends BaseAdapter {
    private ArrayList < Joueur > list;

    public ListViewAdapter(ArrayList < Joueur > list) {
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Joueur getItem(int position) {
        return list.get(position);
    }

    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup container) {
        if (convertView == null) {
            convertView = LayoutInflater.from(container.getContext()).inflate(R.layout.list_item, container, false);
        }
        ((TextView) convertView.findViewById(R.id.textView1))
                .setText(getItem(position).getName());
        ((TextView) convertView.findViewById(R.id.textView2))
                .setText(String.valueOf(getItem(position).getScore()));
        return convertView;
    }
}



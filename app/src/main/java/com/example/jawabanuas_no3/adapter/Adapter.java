package com.example.jawabanuas_no3.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.jawabanuas_no3.R;
import com.example.jawabanuas_no3.model.Data;

import java.util.List;

public class Adapter extends BaseAdapter {
    private Activity activity;
    private LayoutInflater inflater;
    private List<Data> lists;

    public Adapter(Activity activity, List<Data> lists) {
        this.activity = activity;
        this.lists = lists;
    }

    @Override
    public int getCount() {
        return lists.size();
    }

    @Override
    public Object getItem(int i) {
        return lists.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (inflater == null) {
            inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }
        if (view == null && inflater != null) {
            view = inflater.inflate(R.layout.list_users, null);
        }
        if (view != null) {
            TextView name = view.findViewById(R.id.text_name);
            TextView nim = view.findViewById(R.id.text_nim);
            TextView ipk = view.findViewById(R.id.text_ipk);
            TextView matkul = view.findViewById(R.id.text_matkul);

            Data data = lists.get(i);
            name.setText(data.getName());
            nim.setText(data.getNim());
            ipk.setText(String.valueOf(data.getIpk()));
            matkul.setText(data.getMatkul());
        }
        return view;
    }
}

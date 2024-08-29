package com.example.jawabanuas_no3;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.example.jawabanuas_no3.adapter.Adapter;
import com.example.jawabanuas_no3.helper.DatabaseHelper;
import com.example.jawabanuas_no3.model.Data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    ListView listView;
    AlertDialog.Builder dialog;
    List<Data> list = new ArrayList<>();
    Adapter adapter;
    DatabaseHelper db = new DatabaseHelper(this);
    Button btnAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = new DatabaseHelper(getApplicationContext());
        listView = findViewById(R.id.list_item);
        btnAdd = findViewById(R.id.btnAdd);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, EditorActivity.class);
                startActivity(intent);
            }
        });
        listView = findViewById(R.id.list_item);
        adapter = new Adapter(MainActivity.this, list);
        listView.setAdapter(adapter);

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                final String id = list.get(i).getId();
                final String name = list.get(i).getName();
                final String nim = list.get(i).getNim();
                final Float ipk = list.get(i).getIpk();
                final String matkul = list.get(i).getMatkul();
                final CharSequence[] dialogItem = {"Edit", "Hapus"};
                dialog = new AlertDialog.Builder(MainActivity.this);
                dialog.setItems(dialogItem, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        switch (i) {
                            case 0: // Edit
                                Intent intent = new Intent(MainActivity.this, EditorActivity.class);
                                intent.putExtra("id", id);
                                intent.putExtra("name", name);
                                intent.putExtra("nim", nim);
                                intent.putExtra("ipk", String.valueOf(ipk));
                                intent.putExtra("matkul", matkul);
                                startActivity(intent);
                                break;
                            case 1: // Hapus
                                db.delete(Integer.parseInt(id));
                                list.clear();
                                getData();
                                break;
                        }
                    }
                }).show();
                return false;
            }
        });
        getData();
    }

    @Override
    protected void onResume() {
        super.onResume();
        // Clear list and reload data
        list.clear();
        getData();
    }

    private void getData() {
        list.clear();
        ArrayList<HashMap<String, String>> rows = db.getAll();
        for (int i = 0; i < rows.size(); i++) {
            String id = rows.get(i).get("id");
            String name = rows.get(i).get("name");
            String nim = rows.get(i).get("nim");
            String ipk = rows.get(i).get("ipk");
            String matkul = rows.get(i).get("matkul");

            Data data = new Data();
            data.setId(id);
            data.setName(name);
            data.setNim(nim);
            data.setIpk(Float.valueOf(ipk));
            data.setMatkul(matkul);
            list.add(data);
        }
        adapter.notifyDataSetChanged();
    }

    public void deleteData(int id) {
        db.delete(id);
        list.clear();
        getData(); // Memuat ulang data setelah penghapusan
    }


    public void reloadData() {
        list.clear();
        getData();
    }

}

package com.example.jawabanuas_no3;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.jawabanuas_no3.helper.DatabaseHelper;

public class EditorActivity extends AppCompatActivity {
    private EditText editName, editNim, editIpk, editMatkul;
    private Button btnSubmit;
    private DatabaseHelper db = new DatabaseHelper(this);
    private String id, name, nim, matkul;
    private Float ipk;

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editor);

        editName = findViewById(R.id.edit_name);
        editNim = findViewById(R.id.edit_nim);
        editIpk = findViewById(R.id.edit_ipk);
        editMatkul = findViewById(R.id.edit_matkul);
        btnSubmit = findViewById(R.id.btnSubmit);

        id = getIntent().getStringExtra("id");
        name = getIntent().getStringExtra("name");
        nim = getIntent().getStringExtra("nim");
        String ipkString = getIntent().getStringExtra("ipk");
        matkul = getIntent().getStringExtra("matkul");

        if (ipkString != null && !ipkString.trim().isEmpty()) {
            ipk = Float.valueOf(ipkString);
        }

        if (id == null || id.equals("")) {
            setTitle("Tambah User");
        } else {
            setTitle("Edit User");
            editName.setText(name);
            editNim.setText(nim);
            editIpk.setText(String.valueOf(ipk));
            editMatkul.setText(matkul);
        }

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (editName.getText().toString().equals("") ||
                        editNim.getText().toString().equals("") ||
                        editIpk.getText().toString().equals("") ||
                        editMatkul.getText().toString().equals("")) {
                    Toast.makeText(getApplicationContext(), "Silahkan isi semua data!", Toast.LENGTH_SHORT).show();
                } else {
                    if (id == null || id.equals("")) {
                        db.insert(
                                editName.getText().toString().trim(),
                                editNim.getText().toString().trim(),
                                editIpk.getText().toString().trim(),
                                editMatkul.getText().toString().trim()
                        );
                        Toast.makeText(getApplicationContext(), "Data Ditambahkan", Toast.LENGTH_SHORT).show();
                    } else {
                        db.update(
                                Integer.parseInt(id),
                                editName.getText().toString().trim(),
                                editNim.getText().toString().trim(),
                                editIpk.getText().toString().trim(),
                                editMatkul.getText().toString().trim()
                        );
                        Toast.makeText(getApplicationContext(), "Data Diubah", Toast.LENGTH_SHORT).show();
                    }
                    // Panggil metode reloadData() pada MainActivity
                    MainActivity mainActivity = (MainActivity) view.getContext();
                    mainActivity.reloadData();
                    finish();
                }
            }

        });
    }
}

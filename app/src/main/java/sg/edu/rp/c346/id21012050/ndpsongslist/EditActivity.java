package sg.edu.rp.c346.id21012050.ndpsongslist;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;

public class EditActivity extends AppCompatActivity {

    Button btnUpdate, btnDelete, btnCancel;
    EditText etID, etTitle, etSingers, etYear;
    RadioGroup rgStars;
    Song data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        btnUpdate = findViewById(R.id.btnUpdate);
        btnDelete = findViewById(R.id.btnDelete);
        btnCancel = findViewById(R.id.btnCancel);
        etID = findViewById(R.id.etID);
        etTitle = findViewById(R.id.etSongTitle);
        etSingers = findViewById(R.id.etSingers);
        etYear = findViewById(R.id.etYears);
        rgStars = findViewById(R.id.rgStars);

        Intent i = getIntent();
        data = (Song) i.getSerializableExtra("data");

        etID.setText("ID: " + data.get_id());
        etTitle.setText(data.getTitle());
        etSingers.setText(data.getSingers());
        etYear.setText(data.getYear());

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper dbh = new DBHelper(EditActivity.this);
                data.setSingers(etSingers.getText().toString());
                data.setTitle(etTitle.getText().toString());
                data.get_id();
                dbh.updateSong(data);
                dbh.close();
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper dbh = new DBHelper(EditActivity.this);
                dbh.deleteSong(data.get_id());

            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(EditActivity.this,
                        ShowListActivity.class);
                startActivity(i);
            }
        });

    }
}
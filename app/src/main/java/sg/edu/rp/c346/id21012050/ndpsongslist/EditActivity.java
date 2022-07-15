package sg.edu.rp.c346.id21012050.ndpsongslist;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import java.util.ArrayList;

public class EditActivity extends AppCompatActivity {

    Button btnUpdate, btnDelete, btnCancel;
    EditText etID, etTitle, etSingers, etYear;
    RadioGroup rgStars;
    RadioButton rb1, rb2, rb3, rb4, rb5;
    ArrayList<Song> al;
    ArrayAdapter<Song> aa;
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
        rb1 = findViewById(R.id.radioButton1);
        rb2 = findViewById(R.id.radioButton2);
        rb3 = findViewById(R.id.radioButton3);
        rb4 = findViewById(R.id.radioButton4);
        rb5 = findViewById(R.id.radioButton5);

        Intent i = getIntent();
        long id  = (long) i.getSerializableExtra("data");

        etID.setText(id+"");

        DBHelper dbh = new DBHelper(EditActivity.this);

        data = dbh.getSongByID(id);

        etTitle.setText(data.getTitle());
        etSingers.setText(data.getSingers());
        etYear.setText(data.getYear()+"");

        if (rb1.getText().equals(data.getStar()+"")) {
            rb1.setChecked(true);
        } else if (rb2.getText().equals(data.getStar()+"")) {
            rb2.setChecked(true);
        } else if (rb3.getText().equals(data.getStar()+"")) {
            rb3.setChecked(true);
        } else if (rb4.getText().equals(data.getStar()+"")) {
            rb4.setChecked(true);
        } else if (rb5.getText().equals(data.getStar()+"")) {
            rb5.setChecked(true);
        }


        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DBHelper dbh = new DBHelper(EditActivity.this);
                data.setSingers(etSingers.getText().toString());
                data.setTitle(etTitle.getText().toString());
                data.setYear(etYear.getId());
                int rbStarsID = rgStars.getCheckedRadioButtonId();
                RadioButton radioButton = (RadioButton) findViewById(rbStarsID);
                data.setStar(Integer.parseInt(radioButton.getText().toString()));
                dbh.updateSong(data);
                dbh.close();
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
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
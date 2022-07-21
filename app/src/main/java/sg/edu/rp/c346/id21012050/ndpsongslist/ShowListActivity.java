package sg.edu.rp.c346.id21012050.ndpsongslist;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class ShowListActivity extends AppCompatActivity {

    Button btnFiveStar;
    Spinner spnSongs;
    ListView lvSongs;
    ArrayList<Song> al;
    ArrayAdapter<Song> aa;
    CustomAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_list);

        btnFiveStar = findViewById(R.id.btnFiveStar);
        lvSongs = findViewById(R.id.songList);
        spnSongs = findViewById(R.id.spinnerSongs);

        al = new ArrayList<Song>();

        aa = new ArrayAdapter<Song>(this, android.R.layout.simple_list_item_1, al);

        adapter = new CustomAdapter(this, R.layout.row, al);

        lvSongs.setAdapter(adapter);

       lvSongs.setOnItemClickListener(new AdapterView.OnItemClickListener() {
           @Override
           public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
               Intent i = new Intent(ShowListActivity.this,
                       EditActivity.class);
               Song data = al.get(position);
               i.putExtra("data", data);
               startActivity(i);
           }
       });

       spnSongs.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
           @Override
           public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

               DBHelper dbh = new DBHelper(ShowListActivity.this);
               int year = Integer.parseInt(spnSongs.getSelectedItem().toString());
               al.clear();
               al.addAll(dbh.getAllSongsByYear(year));
               adapter.notifyDataSetChanged();

               ArrayList<String> alYear = new ArrayList<>();

               for (int a = 0; a < al.size(); a++) {
                   for (int x = 0; x < alYear.size(); x++) {
                       String yearItem = al.get(x).getYear() + "";
                       if (alYear.get(x) != yearItem) {
                           alYear.add(al.get(x).getYear() + "");
                       }
                   }
               }

               al.clear();
               al.addAll(dbh.getAllSongsByYear(year));
               adapter.notifyDataSetChanged();

               switch (i) {
//                   case 0:
////                       al.clear();
////                       al.addAll(dbh.getAllSongsByYear(year));
//                       Toast.makeText(ShowListActivity.this, "1998", Toast.LENGTH_SHORT).show();
//                       break;
//                   case 1:
////                       al.clear();
////                       al.addAll(dbh.getAllSongsByYear(year));
//                       Toast.makeText(ShowListActivity.this, "2015", Toast.LENGTH_SHORT).show();
//                       break;
//                   case 2:
////                       al.clear();
////                       al.addAll(dbh.getAllSongsByYear(year));
//                       Toast.makeText(ShowListActivity.this, "2002", Toast.LENGTH_SHORT).show();
//                       break;
               }
           }

           @Override
           public void onNothingSelected(AdapterView<?> adapterView) {

           }
       });

       btnFiveStar.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {

               DBHelper dbh = new DBHelper(ShowListActivity.this);

               al.clear();
               al.addAll(dbh.getAllFiveStarSongs());
               adapter.notifyDataSetChanged();
           }
       });

   }

   @Override
   protected void onResume() {
       super.onResume();
       DBHelper dbh = new DBHelper(ShowListActivity.this);
       al.clear();
       al.addAll(dbh.getAllSongs());
       adapter.notifyDataSetChanged();
       lvSongs.performClick();
    }

}
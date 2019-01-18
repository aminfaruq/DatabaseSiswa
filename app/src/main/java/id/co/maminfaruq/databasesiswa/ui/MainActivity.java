package id.co.maminfaruq.databasesiswa.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import id.co.maminfaruq.databasesiswa.R;
import id.co.maminfaruq.databasesiswa.adapter.KelasAdapter;
import id.co.maminfaruq.databasesiswa.db.SiswaDatabase;
import id.co.maminfaruq.databasesiswa.model.KelasModel;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.rv_kelas)
    RecyclerView rvKelas;

//    private KelasAdapter kelasAdapter;

    private SiswaDatabase siswaDatabase;

    private List<KelasModel>kelasModelList;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        Toolbar toolbar =  findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //setting variable
        //buat object database
        siswaDatabase = SiswaDatabase.createDatabase(this);

        //membuat inisiasi object list
        kelasModelList = new ArrayList<>();



        FloatingActionButton fab =  findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, TambahKelasActivity.class));
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        //menghapus list agar bisa diisi dengan yng baru
        kelasModelList.clear();
        //mengambil data dari sqlite
        getData();
        //seting adapter
        rvKelas.setLayoutManager(new GridLayoutManager(this,2));
        rvKelas.setAdapter(new KelasAdapter(this,kelasModelList));

    }

    private void getData() {
        kelasModelList = siswaDatabase.kelasDao().select();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}

package id.co.maminfaruq.databasesiswa.ui;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import id.co.maminfaruq.databasesiswa.R;
import id.co.maminfaruq.databasesiswa.db.SiswaDatabase;
import id.co.maminfaruq.databasesiswa.model.KelasModel;

public class TambahKelasActivity extends AppCompatActivity {

    @BindView(R.id.edtNamaKelas)
    EditText edtNamaKelas;
    @BindView(R.id.edtNamaWali)
    EditText edtNamaWali;
    @BindView(R.id.btnSave)
    Button btnSave;

    //TODO 1 membuat variable yang di butuhkan
    private SiswaDatabase siswaDatabase;

    private String namaKelas,namaWali;
    private int idKelas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah_kelas);
        ButterKnife.bind(this);

        //TODO 2 mensetting judul activity
        if (getActionBar() != null){
            setTitle("Add Kelas");
        }

        //TODO 3 membuat object database siswaDatabase
        siswaDatabase = SiswaDatabase.createDatabase(this);
    }

    @OnClick(R.id.btnSave)
    public void onViewClicked() {
        //TODO 4 mengambil data
        getData();

        //TODO 5 menyimpan data ke sqlite
        saveData();

        Toast.makeText(this, "Berhasil disimpan", Toast.LENGTH_SHORT).show();

        finish();
    }

    private void saveData() {
        //membuat variable list kelas model
        List<KelasModel> kelasModels = new ArrayList<>();

        //membuat object kelasmodel untuk menampung data
        KelasModel kelasModel = new KelasModel();

        //memasukkan data ke dalam kelas model
        kelasModel.setNama_kelas(namaKelas);
        kelasModel.setNama_wali(namaWali);

        //memasukkan data yang sudah terkumpul di daam kelas model
        kelasModels.add(kelasModel);

        //perintah untuk melakukan operasi insert menggunakan siswa database
        siswaDatabase.kelasDao()
                .insert(kelasModels);
    }

    private void getData() {
        namaKelas = edtNamaKelas.getText().toString();
        namaWali = edtNamaWali.getText().toString();
    }
}

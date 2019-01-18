package id.co.maminfaruq.databasesiswa.ui;

import android.os.Bundle;
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
import id.co.maminfaruq.databasesiswa.db.Constant;
import id.co.maminfaruq.databasesiswa.db.SiswaDatabase;
import id.co.maminfaruq.databasesiswa.model.KelasModel;

public class UpdateKelasActivity extends AppCompatActivity {

    @BindView(R.id.edtNamaKelasUpdate)
    EditText edtNamaKelasUpdate;
    @BindView(R.id.edtNamaWaliUpdate)
    EditText edtNamaWaliUpdate;
    @BindView(R.id.btnSaveUpdate)
    Button btnSaveUpdate;

    //membuat variable bundle
    private Bundle bundle;

    //membuat variable database
    private SiswaDatabase siswaDatabase;

    //membuat variable list
    private List<KelasModel>kelasModelList;

    private int idKelas;
    private String nama_kelas,nama_wali;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_kelas);
        ButterKnife.bind(this);

        setTitle("Update Data Kelas");

        //menangkap data dari activity sebelumnya
        bundle = getIntent().getExtras();

        //buat object list
        kelasModelList = new ArrayList<>();

        //buat object database
        siswaDatabase = SiswaDatabase.createDatabase(this);

        //menampilkan data sebelumnya ke layar
        showData();
    }

    private void showData() {
        //mengambil data di dalam bundle
        idKelas = bundle.getInt(Constant.id_kelas);
        nama_kelas = bundle.getString(Constant.KEY_NAMA_KELAS);
        nama_wali = bundle.getString(Constant.KEY_NAMA_WALI);

        //menampilkan ke layar
        edtNamaKelasUpdate.setText(nama_kelas);
        edtNamaWaliUpdate.setText(nama_wali);
    }

    @OnClick(R.id.btnSaveUpdate)
    public void onViewClicked() {
        //mengambil data
        getData();

        //mengirim data ke sqlite
        saveData();

        Toast.makeText(this, "Berhasil Di Update", Toast.LENGTH_SHORT).show();
        finish();
    }

    private void saveData() {
        KelasModel kelasModel = new KelasModel() ;
        kelasModel.setId_kelas(idKelas);
        kelasModel.setNama_kelas(nama_kelas);
        kelasModel.setNama_wali(nama_wali);
        siswaDatabase.kelasDao().update(kelasModel);
    }

    private void getData() {
        nama_kelas = edtNamaKelasUpdate.getText().toString();
        nama_wali = edtNamaWaliUpdate.getText().toString();
    }
}

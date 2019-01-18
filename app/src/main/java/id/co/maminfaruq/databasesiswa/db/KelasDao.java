package id.co.maminfaruq.databasesiswa.db;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import id.co.maminfaruq.databasesiswa.model.KelasModel;

@Dao
public interface KelasDao {

    @Query("SELECT * FROM KELAS ORDER BY nama_kelas DESC  ")
    List<KelasModel> select();

    // memasukkan data
    @Insert
    void insert(List<KelasModel> kelasModels);

    //menghapus data
    @Delete
    void delete (KelasModel kelasModel);

    //mengupdate data
    @Update
    void update (KelasModel kelasModel);
}

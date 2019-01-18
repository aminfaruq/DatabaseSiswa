package id.co.maminfaruq.databasesiswa.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import id.co.maminfaruq.databasesiswa.model.KelasModel;

@Database(entities = KelasModel.class,version = 1)
public abstract class SiswaDatabase extends RoomDatabase{

    public abstract KelasDao kelasDao();

    public static SiswaDatabase INSTANCE;

    //membuat method return untuk membuat database
    public static SiswaDatabase createDatabase(Context context){
        synchronized (SiswaDatabase.class){
            if (INSTANCE == null){
                INSTANCE = Room.databaseBuilder(context,SiswaDatabase.class,"db_siswa")
                        .allowMainThreadQueries()
                        .build();
            }
        }return INSTANCE;
    }
}

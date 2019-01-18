package id.co.maminfaruq.databasesiswa.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;
import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;
import id.co.maminfaruq.databasesiswa.R;
import id.co.maminfaruq.databasesiswa.db.Constant;
import id.co.maminfaruq.databasesiswa.db.SiswaDatabase;
import id.co.maminfaruq.databasesiswa.model.KelasModel;
import id.co.maminfaruq.databasesiswa.ui.UpdateKelasActivity;

public class KelasAdapter extends RecyclerView.Adapter<KelasAdapter.ViewHolder> {


    //membuat variable untuk menampung context
    private Context context;
    //membuat variable list dengan cetakan kelas model
    private List<KelasModel> kelasModelList;
    //untuk menampung banyak data
    private Bundle bundle;

    private AlertDialog alertDialog;

    //variable database
    private SiswaDatabase siswaDatabase;

    public KelasAdapter(Context context, List<KelasModel> kelasModelList) {
        this.context = context;
        this.kelasModelList = kelasModelList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_kelas, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {
        //memindahkan data di dalam list dengan index position
        final KelasModel kelasModel = kelasModelList.get(i);

        //menampilkan data ke layar
        viewHolder.tvNamaKelas.setText(kelasModel.getNama_kelas());
        viewHolder.tvNamaWali.setText(kelasModel.getNama_wali());

        //membuat variable random
        Random random = new Random();
        int color = Color.argb(255, random.nextInt(255), random.nextInt(255), random.nextInt(255));

        //menseting backround cardview
        viewHolder.cardViewKelas.setCardBackgroundColor(color);

        //membuat onClick icon overflow
        viewHolder.overflow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                siswaDatabase = SiswaDatabase.createDatabase(context);

                //membuat object popup menu
                PopupMenu popupMenu = new PopupMenu(context, v);

                //inflate menu ke dalam popup menu
                popupMenu.inflate(R.menu.pop_menu);

                //menampilkan menu
                popupMenu.show();

                //onClick pada salah satu menu pada popup menu
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.delete:
                                //membuat object alertDialog


                                //operasi delete
                                siswaDatabase.kelasDao().delete(kelasModel);

                                kelasModelList.remove(i);

                                notifyItemRemoved(i);
                                notifyItemRangeChanged(0, kelasModelList.size());

                                Toast.makeText(context, "Berhasil di hapus", Toast.LENGTH_SHORT).show();
                                break;
                            case R.id.edit:
                                //membuat object bundle
                                bundle = new Bundle();

                                //mengisi bundle dengan data
                                bundle.putInt(Constant.KEY_ID_KELAS, kelasModel.getId_kelas());
                                bundle.putString(Constant.KEY_NAMA_KELAS,kelasModel.getNama_kelas());
                                bundle.putString(Constant.KEY_NAMA_WALI,kelasModel.getNama_wali());

                                //berpindah halaman dengan membawa data
                                context.startActivity(new Intent(context, UpdateKelasActivity.class).putExtras(bundle));
                                break;
                        }
                        return true;
                    }
                });
            }
        });
    }

    @Override
    public int getItemCount() {
        return kelasModelList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.overflow)
        ImageButton overflow;
        @BindView(R.id.tvNamaKelas)
        TextView tvNamaKelas;
        @BindView(R.id.tv_nama_wali)
        TextView tvNamaWali;
        @BindView(R.id.cardViewKelas)
        CardView cardViewKelas;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}

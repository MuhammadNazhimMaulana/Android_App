package com.example.generalapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.generalapp.R;
import com.example.generalapp.database.entity.Buku;
import com.example.generalapp.database.model.BukuWithGenre;
import com.example.generalapp.database.model.BukuWithPenulis;

import java.util.List;

public class AdapterBuku extends RecyclerView.Adapter<AdapterBuku.ViewAdapter>{
    private List<BukuWithGenre> list;
    private Context context;
    private AdapterBuku.Dialog dialog;

    public interface Dialog {
        void onClick(int position);
    }

    public void setDialog(AdapterBuku.Dialog dialog) {
        this.dialog = dialog;
    }

    public AdapterBuku(Context context, List<BukuWithGenre> list){
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewAdapter onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View view =
                LayoutInflater.from(parent.getContext()).inflate(R.layout.row_buku, parent, false);
        return new ViewAdapter(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewAdapter holder, int position) {
        holder.judulBuku.setText(list.get(position).buku.judulBuku);
        holder.jumlahHalaman.setText(list.get(position).buku.jumlahHalaman);
        holder.genreId.setText(list.get(position).buku.genreId);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewAdapter extends RecyclerView.ViewHolder{
        TextView judulBuku, penulisId, jumlahHalaman, genreId;
        public ViewAdapter(@NonNull View itemView) {
            super(itemView);
            judulBuku = itemView.findViewById(R.id.judul_buku);
            jumlahHalaman = itemView.findViewById(R.id.jumlah_halaman);
            genreId = itemView.findViewById(R.id.genreId);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (dialog!=null){
                        dialog.onClick(getLayoutPosition());
                    }
                }
            });
        }
    }
}

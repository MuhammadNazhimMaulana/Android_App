package com.example.generalapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.generalapp.R;
import com.example.generalapp.database.entity.Peminjaman;
import com.example.generalapp.database.model.PeminjamanWithBuku;

import java.util.List;

public class AdapterPeminjaman extends RecyclerView.Adapter<AdapterPeminjaman.ViewAdapter>{

    private List<PeminjamanWithBuku> list;
    private Context context;
    private Dialog dialog;
    public interface Dialog {
        void onClick(int position);
    }
    public void setDialog(Dialog dialog) {
        this.dialog = dialog;
    }
    public AdapterPeminjaman(Context context, List<PeminjamanWithBuku> list){
        this.context = context;
        this.list = list;
    }
    @NonNull
    @Override
    public ViewAdapter onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View view =
                LayoutInflater.from(parent.getContext()).inflate(R.layout.row_peminjaman, parent, false);
        return new ViewAdapter(view);
    }
    @Override
    public void onBindViewHolder(@NonNull ViewAdapter holder, int position) {
        holder.namaPeminjam.setText(list.get(position).peminjaman.namaPeminjam);
        holder.bukuId.setText(list.get(position).peminjaman.bukuId);
    }
    @Override
    public int getItemCount() {
        return list.size();
    }
    class ViewAdapter extends RecyclerView.ViewHolder{
        TextView namaPeminjam, bukuId;
        public ViewAdapter(@NonNull View itemView) {
            super(itemView);
            namaPeminjam = itemView.findViewById(R.id.nama_peminjam);
            bukuId = itemView.findViewById(R.id.bukuId);
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

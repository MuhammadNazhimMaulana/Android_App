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
import com.example.generalapp.database.model.PengembalianWithPeminjaman;

import java.util.List;

public class AdapterPengembalian extends RecyclerView.Adapter<AdapterPengembalian.ViewAdapter>{

    private List<PengembalianWithPeminjaman> list;
    private Context context;
    private Dialog dialog;
    public interface Dialog {
        void onClick(int position);
    }
    public void setDialog(Dialog dialog) {
        this.dialog = dialog;
    }
    public AdapterPengembalian(Context context, List<PengembalianWithPeminjaman> list){
        this.context = context;
        this.list = list;
    }
    @NonNull
    @Override
    public ViewAdapter onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View view =
                LayoutInflater.from(parent.getContext()).inflate(R.layout.row_pengembalian, parent, false);
        return new ViewAdapter(view);
    }
    @Override
    public void onBindViewHolder(@NonNull ViewAdapter holder, int position) {
        holder.tanggal_pengembalian.setText(list.get(position).pengembalian.tanggalPengembalian);
        holder.peminjamanIdId.setText(list.get(position).pengembalian.peminjamanId);
    }
    @Override
    public int getItemCount() {
        return list.size();
    }
    class ViewAdapter extends RecyclerView.ViewHolder{
        TextView tanggal_pengembalian, peminjamanIdId;
        public ViewAdapter(@NonNull View itemView) {
            super(itemView);
            tanggal_pengembalian = itemView.findViewById(R.id.tanggal_pengembalian);
            peminjamanIdId = itemView.findViewById(R.id.peminjamanId);
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

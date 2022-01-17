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
import com.example.generalapp.database.model.DendaWithPengembalian;

import java.util.List;

public class AdapterDenda extends RecyclerView.Adapter<AdapterDenda.ViewAdapter>{

    private List<DendaWithPengembalian> list;
    private Context context;
    private Dialog dialog;
    public interface Dialog {
        void onClick(int position);
    }
    public void setDialog(Dialog dialog) {
        this.dialog = dialog;
    }
    public AdapterDenda(Context context, List<DendaWithPengembalian> list){
        this.context = context;
        this.list = list;
    }
    @NonNull
    @Override
    public ViewAdapter onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View view =
                LayoutInflater.from(parent.getContext()).inflate(R.layout.row_denda, parent, false);
        return new ViewAdapter(view);
    }
    @Override
    public void onBindViewHolder(@NonNull ViewAdapter holder, int position) {
        holder.total_denda.setText(list.get(position).denda.totalDenda);
        holder.pengembalian_id.setText(list.get(position).denda.pengembalianId);
    }
    @Override
    public int getItemCount() {
        return list.size();
    }
    class ViewAdapter extends RecyclerView.ViewHolder{
        TextView total_denda, pengembalian_id;
        public ViewAdapter(@NonNull View itemView) {
            super(itemView);
            total_denda = itemView.findViewById(R.id.total_denda);
            pengembalian_id = itemView.findViewById(R.id.pengembalianId);
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

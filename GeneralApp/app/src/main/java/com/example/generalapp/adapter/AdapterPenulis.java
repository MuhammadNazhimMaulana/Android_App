package com.example.generalapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.generalapp.R;
import com.example.generalapp.database.entity.Penulis;

import java.util.List;

public class AdapterPenulis extends RecyclerView.Adapter<AdapterPenulis.ViewAdapter>{

    private List<Penulis> list;
    private Context context;
    private AdapterPenulis.Dialog dialog;

    public interface Dialog {
        void onClick(int position);
    }

    public void setDialog(AdapterPenulis.Dialog dialog) {
        this.dialog = dialog;
    }

    public AdapterPenulis(Context context, List<Penulis> list){
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public AdapterPenulis.ViewAdapter onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View view =
                LayoutInflater.from(parent.getContext()).inflate(R.layout.row_penulis, parent, false);
        return new AdapterPenulis.ViewAdapter(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterPenulis.ViewAdapter holder, int position) {
        holder.penulisBuku.setText(list.get(position).penulisBuku);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewAdapter extends RecyclerView.ViewHolder{
        TextView penulisBuku;
        public ViewAdapter(@NonNull View itemView) {
            super(itemView);
            penulisBuku = itemView.findViewById(R.id.penulis_buku);
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

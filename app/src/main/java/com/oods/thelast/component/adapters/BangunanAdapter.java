package com.oods.thelast.component.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.oods.thelast.R;
import com.oods.thelast.dependencies.realms.PerusahaanRealm;

import java.util.List;

public class BangunanAdapter extends RecyclerView.Adapter<BangunanAdapter.MyViewHolder> {

    private List<PerusahaanRealm> listData;
    private OnClickBangunanListener onClickBangunan;

    public BangunanAdapter(Context context, List<PerusahaanRealm> listData, OnClickBangunanListener onClick) {
        this.listData = listData;
        LayoutInflater.from(context);
        this.onClickBangunan = onClick;
    }

    public interface OnClickBangunanListener {
        void OnClickBangunan(String idBangunan);
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_bangunan, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        final PerusahaanRealm perusahaanRealm = listData.get(position);

        holder.perusahaan.setText(perusahaanRealm.getNamaPerusahaan());
        holder.pemimpin.setText(perusahaanRealm.getNamaPemimpin());

        holder.cardview.setOnClickListener(view -> onClickBangunan.OnClickBangunan(perusahaanRealm.getKey()));
    }

    @Override
    public int getItemCount() {
        return listData.size();
    }
    @Override
    public long getItemId(int position) {
        return position;
    }

    static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView perusahaan, pemimpin;
        CardView cardview;
        MyViewHolder(View itemView) {
            super(itemView);
            perusahaan = itemView.findViewById(R.id.perusahaan);
            pemimpin = itemView.findViewById(R.id.pemimpin);
            cardview = itemView.findViewById(R.id.cardViewPerusahaan);
        }
    }
    public void animateTo(List<PerusahaanRealm> models) {
        applyAndAnimateRemovals(models);
        applyAndAnimateAdditions(models);
        applyAndAnimateMovedItems(models);
    }
    private void applyAndAnimateRemovals(List<PerusahaanRealm> newModels) {
        for (int i = listData.size() - 1; i >= 0; i--) {
            final PerusahaanRealm model = listData.get(i);
            if (!newModels.contains(model)) {
                removeItem(i);
            }
        }
    }

    private void applyAndAnimateAdditions(List<PerusahaanRealm> newModels) {
        for (int i = 0, count = newModels.size(); i < count; i++) {
            final PerusahaanRealm model = newModels.get(i);
            if (!listData.contains(model)) {
                addItem(i, model);
            }
        }
    }
    private void addItem(int position, PerusahaanRealm model) {
        listData.add(position, model);
        notifyItemInserted(position);
    }
    private void applyAndAnimateMovedItems(List<PerusahaanRealm> newModels) {
        for (int toPosition = newModels.size() - 1; toPosition >= 0; toPosition--) {
            final PerusahaanRealm model = newModels.get(toPosition);
            final int fromPosition = listData.indexOf(model);
            if (fromPosition >= 0 && fromPosition != toPosition) {
                moveItem(fromPosition, toPosition);
            }
        }
    }

    private void moveItem(int fromPosition, int toPosition) {
        final PerusahaanRealm model = listData.remove(fromPosition);
        listData.add(toPosition, model);
        notifyItemMoved(fromPosition, toPosition);
    }
    private void removeItem(int position) {
        listData.remove(position);
        notifyItemRemoved(position);
    }
}

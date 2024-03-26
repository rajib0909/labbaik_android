package com.labbaiik.app.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.labbaiik.app.R;
import com.labbaiik.app.databinding.DuaListBinding;
import com.labbaiik.app.model.duaList.Dua;

import java.util.List;


public class ShowDuaListAdapter extends RecyclerView.Adapter<ShowDuaListAdapter.ViewHolder> {
    private List<Dua> allResultList;
    private Context context;
    private OnClickDuaList onClickDuaList;

    public void setOnClickDuaList(OnClickDuaList onClickDuaList) {
        this.onClickDuaList = onClickDuaList;
    }

    public ShowDuaListAdapter(List<Dua> allResultList, Context context) {
        this.allResultList = allResultList;
        this.context = context;
    }

    public void clearAll() {
        this.allResultList.clear();
    }

    public interface OnClickDuaList {
        void onClickDua(Dua dua);
    }

    public void updateDuaList(List<Dua> allResultList) {
        this.allResultList.addAll(allResultList);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        DuaListBinding binding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()), R.layout.dua_list, parent, false);

        return new ViewHolder(binding.getRoot(), binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Dua datum = allResultList.get(position);
        holder.bind(datum);
    }

    @Override
    public int getItemCount() {
        return allResultList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private final DuaListBinding binding;

        public ViewHolder(@NonNull View itemView, DuaListBinding binding) {
            super(itemView);
            this.binding = binding;
        }

        public void bind(Dua datum) {
            binding.duaTitle.setText(datum.getTitle());

            binding.btnDuaDetails.setOnClickListener(l->{
                onClickDuaList.onClickDua(datum);
            });
            binding.executePendingBindings();

        }
    }
}

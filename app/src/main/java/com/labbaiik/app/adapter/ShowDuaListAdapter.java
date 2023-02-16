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

import java.util.List;


public class ShowDuaListAdapter extends RecyclerView.Adapter<ShowDuaListAdapter.ViewHolder> {
    private List<String> allResultList;
    private Context context;
    private OnClickDuaList onClickLesson;

    public void setOnClickLesson(OnClickDuaList onClickLesson) {
        this.onClickLesson = onClickLesson;
    }

    public ShowDuaListAdapter(List<String> allResultList, Context context) {
        this.allResultList = allResultList;
        this.context = context;
    }

    public void clearAll() {
        this.allResultList.clear();
    }

    public interface OnClickDuaList {
        void onClickLesson(String data);
    }

    public void updateDuaList(List<String> allResultList) {
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
        String datum = allResultList.get(position);
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

        public void bind(String datum) {
            binding.duaTitle.setText(datum);

//            lessonItemBinding.pushUps.setOnClickListener(l->{
//                onClickLesson.onClickLesson(datum);
//            });
            binding.executePendingBindings();

        }
    }
}

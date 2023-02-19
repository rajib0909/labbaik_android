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
import com.labbaiik.app.databinding.HomeQuestiondListBinding;

import java.util.List;


public class ShowPrimaryQuestionListAdapter extends RecyclerView.Adapter<ShowPrimaryQuestionListAdapter.ViewHolder> {
    private List<String> allResultList;
    private Context context;
    private OnClickQuestionList onClickQuestionList;

    public void setOnClickQuestion(OnClickQuestionList onClickQuestionList) {
        this.onClickQuestionList = onClickQuestionList;
    }

    public ShowPrimaryQuestionListAdapter(List<String> allResultList, Context context) {
        this.allResultList = allResultList;
        this.context = context;
    }

    public void clearAll() {
        this.allResultList.clear();
    }

    public interface OnClickQuestionList {
        void onClickQuestion(String data);
    }

    public void updateDuaList(List<String> allResultList) {
        this.allResultList.addAll(allResultList);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        HomeQuestiondListBinding binding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()), R.layout.home_questiond_list, parent, false);

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
        private final HomeQuestiondListBinding binding;

        public ViewHolder(@NonNull View itemView, HomeQuestiondListBinding binding) {
            super(itemView);
            this.binding = binding;
        }

        public void bind(String datum) {
       //     binding.duaTitle.setText(datum);

//            lessonItemBinding.pushUps.setOnClickListener(l->{
//                onClickLesson.onClickLesson(datum);
//            });
            binding.executePendingBindings();

        }
    }
}

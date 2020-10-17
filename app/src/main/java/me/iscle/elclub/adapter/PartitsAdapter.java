package me.iscle.elclub.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import me.iscle.elclub.databinding.PartitBinding;
import me.iscle.elclub.model.Partit;

public class PartitsAdapter extends RecyclerView.Adapter<PartitsAdapter.ViewHolder> {

    private List<Partit> partits;
    private OnClickListener listener;

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        final PartitBinding binding = PartitBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final Partit partit = partits.get(position);

        holder.binding.location.setText(partit.getLocation());
        holder.binding.date.setText(DateFormat.getDateInstance(DateFormat.SHORT).format(new Date(TimeUnit.MILLISECONDS.convert(partit.getDate(), TimeUnit.SECONDS))));
        holder.binding.team.setText(partit.getTeam());
        holder.binding.playerCount.setText(String.valueOf(partit.getPlayerCount()));
        holder.binding.getRoot().setOnClickListener(v -> listener.onClick(partits.get(position).getId()));
    }

    @Override
    public int getItemCount() {
        return partits == null ? 0 : partits.size();
    }

    public void setPartits(List<Partit> partits) {
        this.partits = partits;
        notifyDataSetChanged();
    }

    public void setOnClickListener(OnClickListener listener) {
        this.listener = listener;
    }

    public void addPartit(Partit partit) {
        if (partits == null) partits = new ArrayList<>();

        partits.add(partit);
        notifyDataSetChanged();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        final PartitBinding binding;

        public ViewHolder(PartitBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

    public interface OnClickListener {
        void onClick(int matchId);
    }
}

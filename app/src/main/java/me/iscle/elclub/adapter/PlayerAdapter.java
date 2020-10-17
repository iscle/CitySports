package me.iscle.elclub.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import me.iscle.elclub.databinding.PlayerBinding;
import me.iscle.elclub.model.Player;

public class PlayerAdapter extends RecyclerView.Adapter<PlayerAdapter.ViewHolder> {

    private List<Player> players;

    public PlayerAdapter() {
        this.players = new ArrayList<>();
    }

    public PlayerAdapter(List<Player> players) {
        this.players = players;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        PlayerBinding binding = PlayerBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final Player p = players.get(position);
        holder.binding.name.setText(p.getName());
        holder.binding.license.setText("Llicència: " + p.getLicense());
        holder.binding.phone.setText("Tel.: " + p.getPhone());
    }

    @Override
    public int getItemCount() {
        return players == null ? 0 : players.size();
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
        notifyDataSetChanged();
    }

    public void addPlayer(Player player) {
        players.add(player);
        notifyItemInserted(players.size() - 1);
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        final PlayerBinding binding;
        public ViewHolder(PlayerBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}

package me.iscle.elclub.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import me.iscle.elclub.databinding.JornadaCalendariBinding;
import me.iscle.elclub.model.Jornada;

public class CalendariAdapter extends RecyclerView.Adapter<CalendariAdapter.ViewHolder> {

    private List<Jornada> jornades;
    private OnClickListener listener;

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        final JornadaCalendariBinding binding = JornadaCalendariBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final Jornada jornada = jornades.get(position);
        final Jornada.Match[] matches = jornada.getMatches();

        if (matches.length != 7) return;

        holder.binding.player11.setText(matches[0].getPlayer1());
        holder.binding.player12.setText(matches[0].getPlayer2());
        holder.binding.player21.setText(matches[1].getPlayer1());
        holder.binding.player22.setText(matches[1].getPlayer2());
        holder.binding.player31.setText(matches[2].getPlayer1());
        holder.binding.player32.setText(matches[2].getPlayer2());
        holder.binding.player41.setText(matches[3].getPlayer1());
        holder.binding.player42.setText(matches[3].getPlayer2());
        holder.binding.player51.setText(matches[4].getPlayer1());
        holder.binding.player52.setText(matches[4].getPlayer2());
        holder.binding.player61.setText(matches[5].getPlayer1());
        holder.binding.player62.setText(matches[5].getPlayer2());
        holder.binding.player71.setText(matches[6].getPlayer1());
        holder.binding.player72.setText(matches[6].getPlayer2());
    }

    @Override
    public int getItemCount() {
        return jornades == null ? 0 : jornades.size();
    }

    public void setJornades(List<Jornada> jornades) {
        this.jornades = jornades;
        notifyDataSetChanged();
    }

    public void setOnClickListener(OnClickListener listener) {
        this.listener = listener;
    }

    public void addJornada(Jornada jornada) {
        if (jornades == null) jornades = new ArrayList<>();

        jornades.add(jornada);
        notifyDataSetChanged();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        final JornadaCalendariBinding binding;

        public ViewHolder(JornadaCalendariBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

    public interface OnClickListener {
        void onClick(int matchId);
    }
}

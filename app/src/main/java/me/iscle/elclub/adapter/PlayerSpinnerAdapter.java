package me.iscle.elclub.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import me.iscle.elclub.model.Player;

public class PlayerSpinnerAdapter extends ArrayAdapter<Player> {
    private Player[] players;

    public PlayerSpinnerAdapter(@NonNull Context context, int resource) {
        super(context, resource);
    }

    public void setPlayers(Player[] players) {
        this.players = players;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return players == null ? 0 : players.length;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        TextView tv = (TextView) super.getView(position, convertView, parent);

        tv.setText(players[position].getName());

        return tv;
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        TextView tv = (TextView) super.getDropDownView(position, convertView, parent);

        tv.setText(players[position].getName());

        return tv;
    }

    @Nullable
    @Override
    public Player getItem(int position) {
        return players[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
}

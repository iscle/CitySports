package me.iscle.elclub;

import android.app.Dialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.text.DateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import me.iscle.elclub.adapter.PlayerAdapter;
import me.iscle.elclub.adapter.PlayerSpinnerAdapter;
import me.iscle.elclub.databinding.DialogAddPlayerBinding;
import me.iscle.elclub.databinding.FragmentMatchBinding;
import me.iscle.elclub.model.Partit;
import me.iscle.elclub.model.Player;
import me.iscle.elclub.network.ElClubRepository;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MatchFragment extends Fragment {
    private static final String TAG = "MatchFragment";

    private FragmentMatchBinding binding;
    private PlayerAdapter playerAdapter;
    private int matchId;
    private Partit match;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentMatchBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        matchId = MatchFragmentArgs.fromBundle(getArguments()).getMatchId();

        playerAdapter = new PlayerAdapter();
        binding.playerRecycler.setAdapter(playerAdapter);
        binding.playerRecycler.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        binding.playerRecycler.addItemDecoration(new DividerItemDecoration(getContext(), LinearLayoutManager.VERTICAL));
        binding.playerRecycler.addItemDecoration(new MarginItemDecoration());
        binding.addPlayer.setOnClickListener(v -> new AddPlayerDialog(matchId, player -> {
            ElClubRepository.getInstance().getService().addMatchPlayer(matchId, player.getId()).enqueue(new Callback<Void>() {
                @Override
                public void onResponse(Call<Void> call, Response<Void> response) {
                    if (response.isSuccessful()) {
                        Toast.makeText(getContext(), "Added correctly!", Toast.LENGTH_SHORT).show();
                        playerAdapter.addPlayer(player);
                        binding.playerCount.setText(String.valueOf(playerAdapter.getItemCount()));
                    } else {
                        Toast.makeText(getContext(), "Error 1!", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<Void> call, Throwable t) {
                    Toast.makeText(getContext(), "Error 2!", Toast.LENGTH_SHORT).show();
                    t.printStackTrace();
                }
            });
        }).show(getParentFragmentManager(), "AddPlayerDialog"));

        ElClubRepository.getInstance().getService().getMatch(matchId).enqueue(new Callback<Partit>() {
            @Override
            public void onResponse(Call<Partit> call, Response<Partit> response) {
                if (response.isSuccessful()) {
                    match = response.body();
                    binding.location.setText(match.getLocation());
                    binding.date.setText(DateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.SHORT).format(new Date(TimeUnit.MILLISECONDS.convert(match.getDate(), TimeUnit.SECONDS))));
                    binding.team.setText(match.getTeam());
                    binding.playerCount.setText(String.valueOf(match.getPlayers().size()));
                    playerAdapter.setPlayers(match.getPlayers());
                } else {

                }
            }

            @Override
            public void onFailure(Call<Partit> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    public static class AddPlayerDialog extends DialogFragment {
        private DialogAddPlayerBinding binding;
        private int matchId;
        private OnAddCallback callback;
        private PlayerSpinnerAdapter playerSpinnerAdapter;

        public AddPlayerDialog(int matchId, OnAddCallback callback) {
            this.matchId = matchId;
            this.callback = callback;
        }

        @Nullable
        @Override
        public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            binding = DialogAddPlayerBinding.inflate(inflater, container, false);
            return binding.getRoot();
        }

        @Override
        public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
            playerSpinnerAdapter = new PlayerSpinnerAdapter(getContext(), android.R.layout.simple_spinner_item);
            binding.playerList.setAdapter(playerSpinnerAdapter);

            binding.cancel.setOnClickListener(v -> dismiss());
            binding.add.setOnClickListener(v -> {
                callback.onAdd((Player) binding.playerList.getSelectedItem());
                dismiss();
            });

            ElClubRepository.getInstance().getService().getPlayersForMatch(matchId).enqueue(new Callback<Player[]>() {
                @Override
                public void onResponse(Call<Player[]> call, Response<Player[]> response) {
                    if (response.isSuccessful()) {
                        playerSpinnerAdapter.setPlayers(response.body());
                    } else {

                    }
                }

                @Override
                public void onFailure(Call<Player[]> call, Throwable t) {
                    t.printStackTrace();
                }
            });
        }

        interface OnAddCallback {
            void onAdd(Player player);
        }
    }
}
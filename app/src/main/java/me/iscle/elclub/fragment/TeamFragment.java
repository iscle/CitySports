package me.iscle.elclub.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;

import java.util.List;

import me.iscle.elclub.MarginItemDecoration;
import me.iscle.elclub.adapter.PlayerAdapter;
import me.iscle.elclub.databinding.FragmentTeamBinding;
import me.iscle.elclub.model.Player;
import me.iscle.elclub.network.ElClubRepository;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TeamFragment extends Fragment {
    private static final String TAG = "TeamFragment";

    private FragmentTeamBinding binding;
    private PlayerAdapter playerAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentTeamBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        playerAdapter = new PlayerAdapter();
        binding.equipRecycler.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        binding.equipRecycler.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));
        binding.equipRecycler.addItemDecoration(new MarginItemDecoration(true));
        binding.equipRecycler.setAdapter(playerAdapter);

        ElClubRepository.getInstance().getService().getTeamPlayers(1).enqueue(new Callback<List<Player>>() {
            @Override
            public void onResponse(Call<List<Player>> call, Response<List<Player>> response) {
                if (response.isSuccessful()) {
                    for (Player p : response.body()) {
                        playerAdapter.addPlayer(p);
                    }
                } else {
                    Log.d(TAG, "onResponse: fail xd");
                }
            }

            @Override
            public void onFailure(Call<List<Player>> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }
}
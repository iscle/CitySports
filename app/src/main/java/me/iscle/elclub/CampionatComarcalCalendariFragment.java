package me.iscle.elclub;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;

import java.util.List;

import me.iscle.elclub.adapter.PartitsAdapter;
import me.iscle.elclub.databinding.FragmentCampionatComarcalCalendariBinding;
import me.iscle.elclub.model.Partit;
import me.iscle.elclub.network.ElClubRepository;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CampionatComarcalCalendariFragment extends Fragment {
    private FragmentCampionatComarcalCalendariBinding binding;
    private PartitsAdapter partitsAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentCampionatComarcalCalendariBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        partitsAdapter = new PartitsAdapter();
        binding.recyclerCalendari.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        binding.recyclerCalendari.addItemDecoration(new MarginItemDecoration(false));
        binding.recyclerCalendari.setAdapter(partitsAdapter);
        partitsAdapter.setOnClickListener(matchId ->  {
            Navigation.findNavController(view).navigate(CampionatComarcalCalendariFragmentDirections.actionCampionatComarcalCalendariFragmentToMatchFragment(matchId));
        });

        ElClubRepository.getInstance().getService().getMatches().enqueue(new Callback<List<Partit>>() {
            @Override
            public void onResponse(Call<List<Partit>> call, Response<List<Partit>> response) {
                if (response.isSuccessful()) {
                    partitsAdapter.setPartits(response.body());
                } else {
                    Toast.makeText(getContext(), "Error1!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<Partit>> call, Throwable t) {
                Toast.makeText(getContext(), "Error2!", Toast.LENGTH_SHORT).show();
                t.printStackTrace();
            }
        });
    }
}
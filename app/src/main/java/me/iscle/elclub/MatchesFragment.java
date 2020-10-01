package me.iscle.elclub;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import me.iscle.elclub.adapter.PartitsAdapter;
import me.iscle.elclub.databinding.FragmentMatchesBinding;
import me.iscle.elclub.model.Partit;
import me.iscle.elclub.network.ElClubRepository;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MatchesFragment extends Fragment {
    private static final String TAG = "MatchesFragment";

    private FragmentMatchesBinding binding;

    private RecyclerView recyclerPartits;
    private PartitsAdapter adapterPartits;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentMatchesBinding.inflate(inflater, container, false);
        setHasOptionsMenu(true);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerPartits = binding.recyclerPartits;
        recyclerPartits.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        recyclerPartits.addItemDecoration(new DividerItemDecoration(getContext(), LinearLayoutManager.VERTICAL));
        recyclerPartits.addItemDecoration(new MarginItemDecoration());
        adapterPartits = new PartitsAdapter();
        recyclerPartits.setAdapter(adapterPartits);
        adapterPartits.setOnClickListener(matchId ->  {
            Log.d(TAG, "onViewCreated: " + matchId);
            Navigation.findNavController(view).navigate(MatchesFragmentDirections.actionMatchesFragmentToMatchFragment(matchId));
        });

        ElClubRepository.getInstance().getService().getMatches().enqueue(new Callback<List<Partit>>() {
            @Override
            public void onResponse(Call<List<Partit>> call, Response<List<Partit>> response) {
                if (response.isSuccessful()) {
                    adapterPartits.setPartits(response.body());
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

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_matches, menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.edit:

                return true;
            case R.id.refresh:

                return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
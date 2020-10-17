package me.iscle.elclub.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import me.iscle.elclub.adapter.CalendariAdapter;
import me.iscle.elclub.databinding.FragmentTorneigRegularCalendariBinding;

public class TorneigRegularCalendariFragment extends Fragment {
    private FragmentTorneigRegularCalendariBinding binding;
    private CalendariAdapter calendariAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentTorneigRegularCalendariBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        calendariAdapter = new CalendariAdapter();
        binding.recycler.setAdapter(calendariAdapter);
        binding.recycler.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));

        // Make API call here
    }
}
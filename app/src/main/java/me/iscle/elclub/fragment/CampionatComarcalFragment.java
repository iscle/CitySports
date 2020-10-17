package me.iscle.elclub.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import me.iscle.elclub.databinding.FragmentCampionatComarcalBinding;

public class CampionatComarcalFragment extends Fragment {
    private FragmentCampionatComarcalBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentCampionatComarcalBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        binding.reglament.setOnClickListener(v -> Navigation.findNavController(view).navigate(CampionatComarcalFragmentDirections.actionCampionatComarcalFragmentToCampionatComarcalReglamentFragment()));
        binding.calendari.setOnClickListener(v -> Navigation.findNavController(view).navigate(CampionatComarcalFragmentDirections.actionCampionatComarcalFragmentToCampionatComarcalCalendariFragment()));
        binding.classificacio.setOnClickListener(v -> Navigation.findNavController(view).navigate(CampionatComarcalFragmentDirections.actionCampionatComarcalFragmentToCampionatComarcalClassificacioFragment()));
    }
}
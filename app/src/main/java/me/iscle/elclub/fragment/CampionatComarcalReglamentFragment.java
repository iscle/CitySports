package me.iscle.elclub.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import me.iscle.elclub.databinding.FragmentCampionatComarcalReglamentBinding;
import me.iscle.elclub.databinding.FragmentTorneigRegularReglamentBinding;

public class CampionatComarcalReglamentFragment extends Fragment {
    private FragmentCampionatComarcalReglamentBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentCampionatComarcalReglamentBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        binding.pdfView.fromAsset("reglament_comarcal.pdf").load();
    }
}
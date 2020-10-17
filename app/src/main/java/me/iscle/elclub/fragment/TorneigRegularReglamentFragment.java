package me.iscle.elclub.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import me.iscle.elclub.databinding.FragmentTorneigRegularBinding;
import me.iscle.elclub.databinding.FragmentTorneigRegularReglamentBinding;

public class TorneigRegularReglamentFragment extends Fragment {
    private FragmentTorneigRegularReglamentBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentTorneigRegularReglamentBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        binding.pdfView.fromAsset("reglament_regular.pdf").load();
    }
}
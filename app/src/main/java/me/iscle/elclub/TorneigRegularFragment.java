package me.iscle.elclub;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import me.iscle.elclub.databinding.FragmentTorneigRegularBinding;

public class TorneigRegularFragment extends Fragment {
    private FragmentTorneigRegularBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentTorneigRegularBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        binding.reglament.setOnClickListener(v -> Navigation.findNavController(view).navigate(TorneigRegularFragmentDirections.actionTorneigRegularFragmentToTorneigRegularReglamentFragment()));
        binding.jugadors.setOnClickListener(v -> Navigation.findNavController(view).navigate(TorneigRegularFragmentDirections.actionTorneigRegularFragmentToTeamFragment()));
        binding.calendari.setOnClickListener(v -> Navigation.findNavController(view).navigate(TorneigRegularFragmentDirections.actionTorneigRegularFragmentToTorneigRegularCalendariFragment()));
    }
}
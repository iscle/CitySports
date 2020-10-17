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

import me.iscle.elclub.databinding.FragmentHomeBinding;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.equip.setOnClickListener(v -> Navigation.findNavController(view).navigate(HomeFragmentDirections.actionHomeFragmentToTeamFragment()));
        binding.campionatComarcal.setOnClickListener(v -> Navigation.findNavController(view).navigate(HomeFragmentDirections.actionHomeFragmentToCampionatComarcalFragment()));
        binding.torneigRegular.setOnClickListener(v -> Navigation.findNavController(view).navigate(HomeFragmentDirections.actionHomeFragmentToTorneigRegularFragment()));
        binding.signUp.setOnClickListener(v -> Navigation.findNavController(view).navigate(HomeFragmentDirections.actionHomeFragmentToRegisterFragment()));
        binding.signIn.setOnClickListener(v -> Navigation.findNavController(view).navigate(HomeFragmentDirections.actionHomeFragmentToLoginFragment()));
    }
}
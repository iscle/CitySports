package me.iscle.elclub.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import me.iscle.elclub.databinding.FragmentCampionatComarcalReglamentBinding;

public class CampionatComarcalClassificacioFragment extends Fragment {
    private static final String CLASSIFICACIO_PDF_URL = "http://192.168.1.33:3000/uploads/classificacio_comarcal.pdf";
    private FragmentCampionatComarcalReglamentBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentCampionatComarcalReglamentBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        new Thread() {
            @Override
            public void run() {
                try {
                    final InputStream inputStream =
                            new URL(CLASSIFICACIO_PDF_URL).openConnection().getInputStream();
                    binding.pdfView.fromStream(inputStream)
                            .onLoad(nbPages -> {
                                try {
                                    inputStream.close();
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            })
                            .onError(t -> {
                                try {
                                    inputStream.close();
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            })
                            .load();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }
}
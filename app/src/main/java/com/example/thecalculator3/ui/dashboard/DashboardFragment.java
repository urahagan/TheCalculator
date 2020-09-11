package com.example.thecalculator3.ui.dashboard;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.thecalculator3.R;

public class DashboardFragment extends Fragment implements TextWatcher {

    private DashboardViewModel dashboardViewModel;
    TextView tvBin,tvOct,tvDec,tvHex;
    int inputText = 0;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        dashboardViewModel =
                ViewModelProviders.of(this).get(DashboardViewModel.class);
        View root = inflater.inflate(R.layout.fragment_dashboard, container, false);
        final TextView textView = root.findViewById(R.id.text_dashboard);
        tvBin = root.findViewById(R.id.editBin);
        tvOct = root.findViewById(R.id.editOct);
        tvDec = root.findViewById(R.id.editDec);
        tvHex = root.findViewById(R.id.editHex);

        tvDec.addTextChangedListener(this);


        return root;
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable editable) {
        inputText = Integer.parseInt(editable.toString());
        tvBin.setText(Integer.toBinaryString(inputText));
    }
}
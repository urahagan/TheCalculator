package com.example.thecalculator3.ui.notifications;

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

import java.math.BigDecimal;

public class NotificationsFragment extends Fragment implements TextWatcher  {

    private NotificationsViewModel notificationsViewModel;

    TextView tvBin;
    TextView tvOct;
    TextView tvDec;
    TextView tvHex;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        notificationsViewModel =
                ViewModelProviders.of(this).get(NotificationsViewModel.class);
        View root = inflater.inflate(R.layout.fragment_notifications, container, false);
        final TextView textView = root.findViewById(R.id.text_notifications);

        tvBin = root.findViewById(R.id.tvBin);
        tvOct = root.findViewById(R.id.tvOct);
        tvDec = root.findViewById(R.id.tvDec);
        tvHex = root.findViewById(R.id.tvHex);

        tvDec.addTextChangedListener(this);

        return root;
    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void afterTextChanged(Editable editable) {
        try {
            if (editable.length() != 0){
                int num = Integer.parseInt(editable.toString());
                String Dec = Integer.toBinaryString(num);
                tvBin.setText(Dec);
            } else {
                System.out.println("なんぞかんぞのエラー");
            }
        }catch (NumberFormatException e) {
            System.err.println("NumberFormatException : " + e );
        }
    }
}
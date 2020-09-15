package com.example.thecalculator3.ui.notifications;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.thecalculator3.R;

import java.math.BigDecimal;

public class NotificationsFragment extends Fragment implements TextWatcher {

    private NotificationsViewModel notificationsViewModel;
    TextView tvByte,tvKilo,tvGiga,tvMega;
    TextView inputValue;
    long inputText = 0;
    String strText = "";

    RadioGroup rg;
    String radioFlag = "byte";


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        notificationsViewModel =
                ViewModelProviders.of(this).get(NotificationsViewModel.class);
        View root = inflater.inflate(R.layout.fragment_notifications, container, false);
        final TextView textView = root.findViewById(R.id.text_notifications);

        inputValue = root.findViewById(R.id.inputValueCapa);

        tvByte = root.findViewById(R.id.editByte);
        tvKilo = root.findViewById(R.id.editKilo);
        tvGiga = root.findViewById(R.id.editGiga);
        tvMega = root.findViewById(R.id.editMega);

        rg = root.findViewById(R.id.radioGroup);
        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.rbByte:
                        radioFlag = "byte";
                        break;
                    case R.id.rbKilo:
                        radioFlag = "kilo";
                        break;
                    case R.id.rbGiga:
                        radioFlag = "giga";
                        break;
                    case R.id.rbMega:
                        radioFlag = "mega";
                        break;
                }
            }
        });

        inputValue.addTextChangedListener(this);
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
        if(editable.length() != 0) {
            inputText = Integer.parseInt(editable.toString());
            try {
                if (radioFlag.equals("byte")) {
                    System.out.println("■:Byte");
                    BigDecimal bd1 = new BigDecimal(inputText);
                    BigDecimal bd2 = new BigDecimal(1024);
                    tvByte.setText(String.valueOf(inputText));
                    tvKilo.setText(String.valueOf(bd1.divide(bd2)));
                    tvGiga.setText(String.valueOf(bd1.divide(bd2).divide(bd2)));
                    tvMega.setText(String.valueOf(bd1.divide(bd2).divide(bd2).divide(bd2)));
                }else if(radioFlag.equals("kilo")){
                    System.out.println("■:Kilo");
                    BigDecimal bd1 = new BigDecimal(inputText);
                    BigDecimal bd2 = new BigDecimal(1024);
                    tvByte.setText(String.valueOf(bd1.multiply(bd2)));
                    tvKilo.setText(String.valueOf(inputText));
                    tvGiga.setText(String.valueOf(bd1.divide(bd2)));
                    tvMega.setText(String.valueOf(bd1.divide(bd2).divide(bd2)));
                }else if(radioFlag.equals("giga")){
                    System.out.println("■:Giga");
                    BigDecimal bd1 = new BigDecimal(inputText);
                    BigDecimal bd2 = new BigDecimal(1024);
                    tvByte.setText(String.valueOf(bd1.multiply(bd2).multiply(bd2)));
                    tvKilo.setText(String.valueOf(bd1.multiply(bd2)));
                    tvGiga.setText(String.valueOf(inputText));
                    tvMega.setText(String.valueOf(bd1.divide(bd2)));
                } else if(radioFlag.equals("mega")){
                    System.out.println("■:Mega");
                    BigDecimal bd1 = new BigDecimal(inputText);
                    BigDecimal bd2 = new BigDecimal(1024);
                    tvByte.setText(String.valueOf(bd1.multiply(bd2).multiply(bd2).multiply(bd2)));
                    tvKilo.setText(String.valueOf(bd1.multiply(bd2).multiply(bd2)));
                    tvGiga.setText(String.valueOf(bd1.multiply(bd2)));
                    tvMega.setText(String.valueOf(inputText));
                }
            } catch (NumberFormatException e) {
                System.err.println("NumberFormatException : " + e);
            }
        }else {
            System.out.println("なんぞかんぞのエラー");
        }
    }
}
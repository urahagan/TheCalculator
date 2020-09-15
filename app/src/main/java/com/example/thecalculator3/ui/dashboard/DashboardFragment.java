package com.example.thecalculator3.ui.dashboard;

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

public class DashboardFragment extends Fragment implements TextWatcher {

    private DashboardViewModel dashboardViewModel;
    TextView tvBin,tvOct,tvDec,tvHex;
    TextView inputValue;
    int inputText = 0;
    String strText = "";

    RadioGroup rg;
    RadioButton rbBin,rbOct,rbDec,rbHex;
    int radioFlag = 2;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        dashboardViewModel =
                ViewModelProviders.of(this).get(DashboardViewModel.class);
        View root = inflater.inflate(R.layout.fragment_dashboard, container, false);
        final TextView textView = root.findViewById(R.id.text_dashboard);

        inputValue = root.findViewById(R.id.inputValue);

        tvBin = root.findViewById(R.id.editBin);
        tvOct = root.findViewById(R.id.editOct);
        tvDec = root.findViewById(R.id.editDec);
        tvHex = root.findViewById(R.id.editHex);

        rg = root.findViewById(R.id.radiogroup);
        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.rbBin:
                        radioFlag = 2;
                        break;
                    case R.id.rbOct:
                        radioFlag = 8;
                        break;
                    case R.id.rbDec:
                        radioFlag = 10;
                        break;
                    case R.id.rbHex:
                        radioFlag = 16;
                        break;
                }
            }
        });
//        rbBin = root.findViewById(R.id.rbBin);

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
            try {
                if (radioFlag == 2) {
                    inputText = Integer.parseInt(editable.toString());
                    System.out.println("■:2進数");
                    strText = String.valueOf(inputText);
                    tvBin.setText(strText);
                    tvOct.setText(Integer.toString(Integer.parseInt(strText,2),8));
                    tvDec.setText(Integer.toString(Integer.parseInt(strText,2),10));
                    tvHex.setText(Integer.toString(Integer.parseInt(strText,2),16));
                }else if(radioFlag == 8){
                    inputText = Integer.parseInt(editable.toString());
                    System.out.println("■:8進数");
                    strText = String.valueOf(inputText);
                    tvBin.setText(Integer.toString(Integer.parseInt(strText,8),2));
                    tvOct.setText(strText);
                    tvDec.setText(Integer.toString(Integer.parseInt(strText,8),10));
                    tvHex.setText(Integer.toString(Integer.parseInt(strText,8),16));
                }else if(radioFlag == 10){
                    inputText = Integer.parseInt(editable.toString());
                    System.out.println("■:10進数");
                    tvBin.setText(Integer.toBinaryString(inputText));
                    tvOct.setText(Integer.toOctalString(inputText));
                    tvDec.setText(Integer.toString(inputText));
                    tvHex.setText(Integer.toHexString(inputText));
                }
                else if(radioFlag == 16){
                    System.out.println("■:16進数");
                    strText = editable.toString();
                    tvBin.setText(Integer.toString(Integer.parseInt(strText,16),2));
                    tvOct.setText(Integer.toString(Integer.parseInt(strText,16),8));
                    tvDec.setText(Integer.toString(Integer.parseInt(strText,16),10));
                    tvHex.setText(strText);
                }
            } catch (NumberFormatException e) {
                System.err.println("NumberFormatException : " + e);
            }
        }else {
            System.out.println("なんぞかんぞのエラー");
        }
    }
}
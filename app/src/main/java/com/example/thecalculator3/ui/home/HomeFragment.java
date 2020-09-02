package com.example.thecalculator3.ui.home;

import android.os.Bundle;
import android.text.Editable;
import android.text.SpannableStringBuilder;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
//import android.widget.Button;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.thecalculator3.R;

import java.math.BigDecimal;
import java.net.MalformedURLException;
import java.net.URL;

public class HomeFragment extends Fragment implements TextWatcher {

    private HomeViewModel homeViewModel;
    String url1 = "https://m.finance.yahoo.co.jp/stock?code=usdjpy=x";
    private static String rate = "";

    TextView tvJPY;
    TextView tvUSD;

    boolean flg = true;
    String inputText = "";

    public View onCreateView(@NonNull LayoutInflater inflater,
                             final ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        final TextView textView = root.findViewById(R.id.text_home);
        tvJPY = root.findViewById(R.id.rateJPY);
        tvUSD = root.findViewById(R.id.rateUSD);

        textView.setText("レート取得中....");
        if(rate.equals("")){
            //起動時に今いくらなのかを表示
            try{
                new GetRating(this).execute(new URL(url1));
            }catch (MalformedURLException e){
                e.printStackTrace();
            }
            Log.d("■","555");
        }else{
            textView.setText(rate);
            Log.d("■","666" + rate + "←");
        }


        final HomeFragment homeflagment = this;
        Button test = root.findViewById(R.id.testBtn);
        test.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    new GetRating(homeflagment).execute(new URL(url1));
                }catch (Exception e){

                }
                Log.d("■","444");
            }
        });

            tvJPY.addTextChangedListener(this);
            //tvUSD.addTextChangedListener(this);


        return root;
    }


    public void setRate(String s){
        rate = s;
        Log.d("■","777");
    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void afterTextChanged(Editable editable) {
        inputText = editable.toString();
        Log.d("●",inputText);
        try {
            if (editable.length() != 0) {
                if(flg) {
                    BigDecimal bd1 = new BigDecimal(editable.toString());
                    BigDecimal bd2 = new BigDecimal(rate);
                    BigDecimal bd3 = bd1.multiply(bd2);
                    tvUSD.setText(bd3.toString());
                }else{
                    BigDecimal bd1 = new BigDecimal(editable.toString());
                    BigDecimal bd2 = new BigDecimal(rate);
                    BigDecimal bd3 = bd1.divide(bd2,3);
                    tvUSD.setText(bd3.toString());
                }
            } else {
                System.out.println("なんぞかんぞのエラー");
            }
        }catch (NumberFormatException e) {
            System.err.println("NumberFormatException : " + e );
        }

    }

    public void inputUSDJPY(boolean flg){

    }
    public boolean isNumMatch(String number) {
        java.util.regex.Pattern pattern = java.util.regex.Pattern.compile("^[0-9]*$");
        java.util.regex.Matcher matcher = pattern.matcher(number);
        return matcher.matches();
    }

}
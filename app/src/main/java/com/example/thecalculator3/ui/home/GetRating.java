package com.example.thecalculator3.ui.home;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.TextView;

import com.example.thecalculator3.R;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;


final class GetRating extends AsyncTask<URL, Void, String> {

    private HomeFragment fragment;
    String url1 = "https://m.finance.yahoo.co.jp/stock?code=usdjpy=x";

    public GetRating(HomeFragment fragment){
        this.fragment = fragment;
    }
    @Override
    protected String doInBackground(URL... urls) {
        final URL url = urls[0];
        HttpURLConnection con = null;

        try{
            con = (HttpURLConnection)url.openConnection();
            con.setRequestMethod("GET");
            con.setInstanceFollowRedirects(false);
            con.connect();
            final int statusCode = con.getResponseCode();
            if(statusCode != HttpURLConnection.HTTP_OK){
                System.err.println("正常に接続できません statusCode:" + statusCode);
                return null;
            }
            Document doc = Jsoup.connect(url1).get();
            Elements elm = doc.select("dd.priceFin");
            Element elm2 = elm.get(0);
            String title = elm2.text();
            return title;
        }catch (IOException e){
            e.printStackTrace();
            return null;
        }finally {
            if(con != null){
                con.disconnect();
            }
        }


    }

    @Override
    protected void onPostExecute(String s) {
        fragment.setRate(s);
        TextView tv = fragment.getActivity().findViewById(R.id.text_home);
        tv.setText(s);
        tv = fragment.getActivity().findViewById(R.id.rateUSD);
        tv.setText(s);
        Log.d("■",s);
    }
}

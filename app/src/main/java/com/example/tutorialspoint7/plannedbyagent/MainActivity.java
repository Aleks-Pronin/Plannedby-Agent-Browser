package com.example.tutorialspoint7.plannedbyagent;

import android.annotation.TargetApi;
import android.content.SharedPreferences;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPreferences.Editor editor = getSharedPreferences("MY_PREFS_NAME", MODE_PRIVATE).edit();
        setContentView(R.layout.activity_main);
        webView = findViewById(R.id.webView);
        webView.setWebViewClient(new MyWebViewClient());
        webView.getSettings().setJavaScriptEnabled(true);
        SharedPreferences prefs = getSharedPreferences("MY_PREFS_NAME", MODE_PRIVATE);
        if(prefs.getInt("registered", 0)>0){
            webView.loadUrl("https://plannedby.hz.cz/");
            editor.putInt("registered", 1);editor.commit();
            Toast.makeText(getApplicationContext(),"Hello",Toast.LENGTH_LONG).show();}
        else{
            webView.loadUrl("https://plannedby.hz.cz/?reg");
            editor.putInt("registered", 1);editor.commit();
            Toast.makeText(getApplicationContext(),"First RUN",Toast.LENGTH_LONG).show();}
    }

    private WebView webView;

    private class MyWebViewClient extends WebViewClient {
        @TargetApi(Build.VERSION_CODES.N)
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
            view.loadUrl(request.getUrl().toString());
            return true;
        }

        // Для старых устройств
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }

    }


}

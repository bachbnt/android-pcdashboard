package com.example.pcdashboard.Fragment;


import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.pcdashboard.Manager.ScreenManager;
import com.example.pcdashboard.Manager.SharedPreferencesUtils;
import com.example.pcdashboard.R;
import com.example.pcdashboard.Services.IWebService;

import static com.example.pcdashboard.Manager.IScreenManager.DASHBOARD_FRAGMENT;
import static com.example.pcdashboard.Manager.IScreenManager.TAB_DEPARTMENT;

/**
 * A simple {@link Fragment} subclass.
 */
public class WebFragment extends Fragment implements View.OnClickListener {
    private ScreenManager screenManager;
    private WebView webView;
    private ImageButton ibBack, ibLeft, ibRight;

    public WebFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_web, container, false);
        initialize(view);
        return view;
    }

    private void initialize(View view) {
        screenManager = ScreenManager.getInstance();
        webView = view.findViewById(R.id.wv_home_web);
        ibBack = view.findViewById(R.id.ib_back_web);
        ibLeft = view.findViewById(R.id.ib_left_web);
        ibRight = view.findViewById(R.id.ib_right_web);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl(IWebService.urlHome);
        webView.setWebViewClient(new WebViewController());
        ibBack.setOnClickListener(this);
        ibLeft.setOnClickListener(this);
        ibRight.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ib_back_web:
                ibBack.startAnimation(AnimationUtils.loadAnimation(getContext(), R.anim.fade_in));
                SharedPreferencesUtils.saveTabId(getContext(), TAB_DEPARTMENT);
                screenManager.openFeatureScreen(DASHBOARD_FRAGMENT);
                break;
            case R.id.ib_left_web:
                ibLeft.startAnimation(AnimationUtils.loadAnimation(getContext(), R.anim.fade_in));
                if (webView.canGoBack()) {
                    webView.goBack();
                }
                break;
            case R.id.ib_right_web:
                ibRight.startAnimation(AnimationUtils.loadAnimation(getContext(), R.anim.fade_in));
                if (webView.canGoForward()) {
                    webView.goForward();
                }
                break;
        }
    }

    class WebViewController extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }
    }
}


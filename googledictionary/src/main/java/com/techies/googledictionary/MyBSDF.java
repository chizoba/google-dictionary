package com.techies.googledictionary;

import android.app.Dialog;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.BottomSheetDialogFragment;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Window;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Chizoba Ogbonna on 12/19/2016.
 */

public class MyBSDF extends BottomSheetDialogFragment {

    TextView textView;

    ProgressBar progressBar;

    CoordinatorLayout.LayoutParams params;

    CoordinatorLayout.Behavior behavior;

    final static String GOOGLE_EEARCH_BASE_URL = "https://www.google.com/search";

    final static String PARAM_QUERY = "q";

    WebView webView;
    private BottomSheetBehavior.BottomSheetCallback mBottomSheetBehaviorCallback = new BottomSheetBehavior.BottomSheetCallback() {

        @Override
        public void onStateChanged(@NonNull View bottomSheet, int newState) {
            if (newState == BottomSheetBehavior.STATE_HIDDEN) {
                dismiss();
            }
        }

        @Override
        public void onSlide(@NonNull View bottomSheet, float slideOffset) {
        }
    };

    @Override
    public void setupDialog(Dialog dialog, int style) {
        super.setupDialog(dialog, style);

        Bundle args = getArguments();

        String text = args.getString("TEXT");

        URL searchURL = buildUrl("define " + text);

        View contentView = View.inflate(getContext(), R.layout.fragment_bottom_sheet, null);

        // instanciating widgets
        textView = (TextView) contentView.findViewById(R.id.toolbar_title);
        webView = (WebView) contentView.findViewById(R.id.webview);
        textView.setText(text);

        Toolbar toolbar = (Toolbar) contentView.findViewById(R.id.toolbar);
        toolbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((BottomSheetBehavior) behavior).setState(BottomSheetBehavior.STATE_EXPANDED);
            }
        });

        progressBar = (ProgressBar) contentView.findViewById(R.id.progress_bar);
        progressBar.setMax(100);
        MyBSDF.this.progressBar.setProgress(0);

        getActivity().getWindow().setFeatureInt(Window.FEATURE_PROGRESS, Window.PROGRESS_VISIBILITY_ON);

        webView.setWebChromeClient(new MyWebViewClient());
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
        webView.loadUrl(searchURL.toString());

        dialog.setContentView(contentView);

        params = (CoordinatorLayout.LayoutParams) ((View) contentView.getParent()).getLayoutParams();
        behavior = params.getBehavior();

        if (behavior != null && behavior instanceof BottomSheetBehavior) {
            ((BottomSheetBehavior) behavior).setPeekHeight(168);
            ((BottomSheetBehavior) behavior).setBottomSheetCallback(mBottomSheetBehaviorCallback);
        }
    }

    private class MyWebViewClient extends WebChromeClient {
        @Override
        public void onProgressChanged(WebView view, int newProgress) {
            MyBSDF.this.setValue(newProgress);

            if (newProgress == 100) {
                progressBar.setVisibility(View.GONE);
            }

            super.onProgressChanged(view, newProgress);
        }

    }

    public void setValue(int progress) {
        this.progressBar.setProgress(progress);
    }

    /**
     * Builds the URL used to query Google Search.
     *
     * @param searchQuery The keyword that will be queried for.
     * @return The URL to use to query the Google Search.
     */
    public static URL buildUrl(String searchQuery) {
        Uri builtUri = Uri.parse(GOOGLE_EEARCH_BASE_URL).buildUpon()
                .appendQueryParameter(PARAM_QUERY, searchQuery)
                .build();

        URL url = null;

        try {
            url = new URL(builtUri.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        return url;
    }
}

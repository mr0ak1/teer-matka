package com.matkabook;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import com.getcapacitor.BridgeActivity;
import com.getcapacitor.BridgeWebViewClient;

public class MainActivity extends BridgeActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Fix: WhatsApp, UPI, PhonePe, Paytm, GPay links open in native apps instead of Chrome
        getBridge().getWebView().setWebViewClient(new BridgeWebViewClient(getBridge()) {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                Uri uri = request.getUrl();
                String scheme = uri.getScheme();
                if ("whatsapp".equals(scheme) || "upi".equals(scheme) ||
                    "phonepe".equals(scheme) || "paytm".equals(scheme) ||
                    "gpay".equals(scheme) || "tel".equals(scheme) ||
                    "intent".equals(scheme)) {
                    try {
                        startActivity(new Intent(Intent.ACTION_VIEW, uri));
                    } catch (Exception ignored) {}
                    return true;
                }
                return super.shouldOverrideUrlLoading(view, request);
            }
        });
    }

    // Fix: Back button goes one page back instead of closing the app
    @Override
    public void onBackPressed() {
        WebView webView = getBridge().getWebView();
        if (webView != null && webView.canGoBack()) {
            webView.goBack();
        } else {
            super.onBackPressed();
        }
    }
}

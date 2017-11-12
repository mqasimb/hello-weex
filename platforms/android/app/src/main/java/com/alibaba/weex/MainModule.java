package com.alibaba.weex;

import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;

import com.alibaba.weex.WXPageActivity;
import com.journeyapps.barcodescanner.CaptureActivity;
import com.taobao.weex.annotation.JSMethod;
import com.taobao.weex.bridge.JSCallback;
import com.taobao.weex.common.WXModule;


import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import java.util.HashMap;
import java.util.Map;


public class MainModule extends WXModule {
    
    public static JSCallback resultCallback;

    @JSMethod(uiThread = true)
    public void returnResults(String result) {
        Map<String,Object> returnResult = new HashMap();
        returnResult.put("result",result);
        resultCallback.invoke(returnResult);
    }

    @JSMethod(uiThread = true)
    public void scanCapture(final JSCallback jsCallback) {
        resultCallback = jsCallback;
        Intent intent = new Intent(mWXSDKInstance.getContext(),MainActivity.class);
        mWXSDKInstance.getContext().startActivity(intent);
    }

    @JSMethod(uiThread = true)
    public void scanBarcode(View view) {
        new IntentIntegrator((Activity) mWXSDKInstance.getContext()).initiateScan();
    }

    public void scanBarcodeCustomOptions(View view) {
        IntentIntegrator integrator = new IntentIntegrator((Activity) mWXSDKInstance.getContext());
        integrator.setDesiredBarcodeFormats(IntentIntegrator.ONE_D_CODE_TYPES);
        integrator.initiateScan();
    }
}
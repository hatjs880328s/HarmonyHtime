package com.inspur.htime.slice;

import com.example.httplibrary.utils.AsyncHttpClient;
import com.example.httplibrary.utils.JsonHttpResponseHandler;
import com.example.httplibrary.utils.RequestParams;
import com.inspur.bll.NotificationCenter;
import com.inspur.bll.NotificationObserver;
import com.inspur.htime.ResourceTable;
import ohos.aafwk.ability.AbilitySlice;
import cz.msebera.android.httpclient.Header;
import ohos.aafwk.content.Intent;
import ohos.aafwk.content.Operation;
import ohos.agp.components.Text;
import ohos.agp.utils.LayoutAlignment;
import ohos.agp.window.dialog.ToastDialog;
import ohos.hiviewdfx.HiLog;
import ohos.hiviewdfx.HiLogLabel;

import java.util.HashMap;

public class MainAbilitySlice extends AbilitySlice implements NotificationObserver {
    private HiLogLabel label = new HiLogLabel(HiLog.LOG_APP,0x00100,"label");

    @Override
    public void onStart(Intent intent) {
        super.onStart(intent);
        super.setUIContent(ResourceTable.Layout_ability_main);
        HiLog.error(label, "start....0");
        this.getHttpInfo();

        NotificationCenter.getInstance().addObserver("noti_name_one", this);
    }

    @Override
    public void onActive() {
        super.onActive();
    }

    @Override
    public void onForeground(Intent intent) {
        super.onForeground(intent);
    }

    private void getHttpInfo() {
        Text selfTextLb = (Text)this.findComponentById(ResourceTable.Id_text_helloworld);
        if (selfTextLb == null) { return ; }
        String url = "https://apis.juhe.cn/simpleWeather/query";
        String key = "32becf485f7f174d4385957b62f28f61";
        //这里获取AsyncHttpClient实例， 这个类提供了get post delete put 请求对外的接口方法
        AsyncHttpClient client = new AsyncHttpClient();
        //这里是我们包装参数的实体类
        RequestParams params = new RequestParams();
        params.put("city", "济南");
        params.put("key", key);
        //这里是实现get请求的方，JsonHttpResponseHandler会重写请求成功的onSuccess和onFailure两个方法，两个方法内部做具体业务逻辑
        client.get(url, params, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, String responseString) {
                super.onSuccess(statusCode, headers, responseString);
                HiLog.error(label, "zel-onSuccess:" + responseString, responseString);
                // 通知主线程更新UI
                getUITaskDispatcher().asyncDispatch(new Runnable() {
                    @Override
                    public void run() {
                        // 这里具体业务Text文本显示请求数据
                        selfTextLb.setText(responseString);
                        // jump
                        Intent intent = new Intent();
                        Operation oper = new Intent.OperationBuilder()
                                .withDeviceId("")
                                .withBundleName("com.inspur.htime")
                                .withAbilityName("com.inspur.htime.Login")
                                .build();
                        intent.setOperation(oper);
                        startAbility(intent);
                    }
                });
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                super.onFailure(statusCode, headers, responseString, throwable);
                HiLog.error(label, "fail....");
                getUITaskDispatcher().asyncDispatch(new Runnable() {
                    @Override
                    public void run() {
                        // 这里具体业务Text文本显示请求数据
                        // selfTextLb.setText(responseString);
                    }
                });
            }
        });
    }

    @Override
    public void executeSome(HashMap<String, Object> infos) {
        // 监听处理
        new ToastDialog(getContext())
                .setText(infos.toString())
                .setAlignment(LayoutAlignment.BOTTOM)
                .show();
    }
}

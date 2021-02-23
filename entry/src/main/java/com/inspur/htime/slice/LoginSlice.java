package com.inspur.htime.slice;

import com.inspur.bll.NotificationCenter;
import com.inspur.htime.ResourceTable;
import ohos.aafwk.ability.AbilitySlice;
import ohos.aafwk.content.Intent;
import ohos.aafwk.content.Operation;
import ohos.agp.components.Button;
import ohos.agp.components.Component;
import ohos.agp.components.Text;
import ohos.agp.components.TextField;
import ohos.agp.utils.LayoutAlignment;
import ohos.agp.window.dialog.ToastDialog;
import ohos.hiviewdfx.HiLog;
import ohos.hiviewdfx.HiLogLabel;

import java.util.Dictionary;
import java.util.HashMap;

public class LoginSlice extends AbilitySlice {

    private HiLogLabel label = new HiLogLabel(HiLog.LOG_APP, 0x0021, "printlog");

    private ToastDialog toast;

    @Override
    public void onStart(Intent intent) {
        super.onStart(intent);
        super.setUIContent(ResourceTable.Layout_ability_login);

        this.progressUI();
    }

    private void progressUI() {
        TextField username = (TextField) findComponentById(ResourceTable.Id_filed_email);
        TextField userpwd = (TextField) findComponentById(ResourceTable.Id_filed_pwd);
        Button loginBtn = (Button)findComponentById(ResourceTable.Id_button_login);

        loginBtn.setClickedListener(new Component.ClickedListener() {
            @Override
            public void onClick(Component component) {
                HiLog.error(label, "username is " + username.getText());
                HiLog.error(label, "userpwd is " + userpwd.getText());
                new ToastDialog(getContext())
                        .setText("user name is : " + username.getText() + " user pwd is: " + userpwd.getText())
                        .setAlignment(LayoutAlignment.CENTER)
                        .show();

                HashMap<String, Object> infos = new HashMap<String, Object>();
                infos.put("123", 321);
                NotificationCenter.getInstance().postNotification("noti_name_one", infos);

                // page jump
                Intent intent = new Intent();
                Operation oper = new Intent.OperationBuilder()
                        .withDeviceId("")
                        .withBundleName("com.inspur.htime")
                        .withAbilityName("com.inspur.roottabbar.Roottabbar")
                        .build();
                intent.setOperation(oper);
                startAbility(intent);
            }
        });
    }

    @Override
    public void onActive() {
        super.onActive();
    }

    @Override
    public void onForeground(Intent intent) {
        super.onForeground(intent);
    }
}

package com.inspur.htimeuti;

import com.inspur.htimeuti.slice.htimeutiabilitySlice;
import ohos.aafwk.ability.Ability;
import ohos.aafwk.content.Intent;

public class htimeutiability extends Ability {
    @Override
    public void onStart(Intent intent) {
        super.onStart(intent);
        super.setMainRoute(htimeutiabilitySlice.class.getName());
    }
}

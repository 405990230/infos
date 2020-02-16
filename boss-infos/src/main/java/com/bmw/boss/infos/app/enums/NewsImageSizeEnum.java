package com.bmw.boss.infos.app.enums;

/**
 * Created by qxr4383 on 2018/3/30.
 */
public enum NewsImageSizeEnum {
    THUMB_IMAGE_X(135),
    THUMB_IMAGE_Y(135),
    FULL_IMAGE_X(324),
    FULL_IMAGE_Y(225);

    private int value;

    private NewsImageSizeEnum(int value) {
        this.value = value;
    }

    public int getNewsImageSizeEnum() {
        return this.value;
    }
}

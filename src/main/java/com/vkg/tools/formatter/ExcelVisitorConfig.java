package com.vkg.tools.formatter;

/**
 * Created by Vishnu on 12/16/2018.
 */
public class ExcelVisitorConfig implements VisitorConfig {
    private final boolean keyRequired;
    private final boolean flip;

    public ExcelVisitorConfig(boolean keyRequired, boolean flip) {
        this.keyRequired = keyRequired;
        this.flip = flip;
    }

    public boolean isKeyRequired() {
        return keyRequired;
    }

    public boolean isFlip() {
        return flip;
    }
}

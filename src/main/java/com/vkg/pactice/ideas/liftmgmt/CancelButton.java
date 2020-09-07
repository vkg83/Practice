package com.vkg.pactice.ideas.liftmgmt;

import com.vkg.pactice.ideas.liftmgmt.buttons.AbstractButton;
import com.vkg.pactice.ideas.liftmgmt.buttons.RequestHandler;

/**
 * Created by Vishnu on 2/3/2018.
 */
public class CancelButton extends AbstractButton {
    private final Request request;

    public CancelButton(RequestHandler handler, Request request) {
        super(handler);
        this.request = request;
    }

    @Override
    protected Request createRequest() {
        return request;
    }
}

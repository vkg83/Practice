package com.vkg.pactice.ideas.liftmgmt.buttons;

import com.vkg.pactice.ideas.liftmgmt.Request;

public abstract class AbstractButton implements Button {
    protected final RequestHandler handler;

    public AbstractButton(RequestHandler handler) {
        this.handler = handler;
    }
    @Override
    public final void press() {
        System.out.println(this + " pressed");
        Request req = createRequest();
        handler.handle(req);
    }

    protected abstract Request createRequest();
}

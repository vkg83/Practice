package com.vkg.pactice.ideas.liftmgmt.buttons;

import com.vkg.pactice.ideas.liftmgmt.Request;

/**
 * Created by Vishnu on 10/25/2017.
 */
public interface RequestHandler<T extends Request> {
    void handle(T req);
}

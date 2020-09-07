package com.vkg.pactice.generic;

import java.util.List;
import java.util.Map;

/**
 * Created by Vishnu on 7/22/2018.
 */
public class ActionClient {
    public static void main(String[] args) {
        Action a = SheetAction.READ;
        List<Action> other = a.other();
        ActionInfo info = new ActionInfo();
        info.status = a.getStatus();
        info.allowedAction = other;
        //info.setPastActions(new PayloadAction())
        System.out.println(other.get(0).getStatus());
    }
}

class ActionInfo {
    String status;
    Map<String, PayloadAction> pastActions;
    List<Action> allowedAction;
}


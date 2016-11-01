package com.yanyuanquan.keeplive;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.Nullable;

/**
 * Created by apple on 16/11/1.
 */

public class ClientService extends Service {

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {

        return stub;
    }


    BindInterface.Stub stub = new BindInterface.Stub(){
        @Override
        public void onBind() throws RemoteException {

        }
    };

}

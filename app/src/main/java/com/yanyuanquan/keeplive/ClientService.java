package com.yanyuanquan.keeplive;

import android.app.Service;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Binder;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by apple on 16/11/1.
 */

public class ClientService extends Service {

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return stub;
    }

    @Override
    public void onStart(Intent intent, int startId) {
        super.onStart(intent, startId);
        this.bindService(new Intent(this, ServerService.class), connection, Context.BIND_IMPORTANT);
    }


    BindInterface.Stub stub = new BindInterface.Stub() {
        @Override
        public String getServiceName() throws RemoteException {
            return this.getClass().getSimpleName();
        }

        @Override
        public void onBind() throws RemoteException {


        }
    };

    ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            Log.d("zjw", " ClientService  连接远程Service 成功");

        }

        // 正常解绑的时候不会被调用，出现异常断开会被调用
        @Override
        public void onServiceDisconnected(ComponentName componentName) {

        }
    };

}

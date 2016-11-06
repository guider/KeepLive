package com.yanyuanquan.keeplive;

import android.app.Service;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by apple on 16/11/1.
 */

public class ServerService extends Service {
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return stub;
    }

    @Override
    public void onStart(Intent intent, int startId) {
        super.onStart(intent, startId);
        this.bindService(new Intent(this, ClientService.class), connection, Context.BIND_IMPORTANT);
    }

    BindInterface.Stub stub = new BindInterface.Stub() {
        @Override
        public void onBind() throws RemoteException {

        }

        @Override
        public String getServiceName() throws RemoteException {
            return this.getClass().getSimpleName();
        }
    };
    ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            Log.d("zjw"," ServerService  连接本地Service 成功");
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {

        }
    };
}

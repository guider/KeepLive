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
import android.widget.Toast;

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
    public int onStartCommand(Intent intent, int flags, int startId) {
        this.bindService(new Intent(this, ClientService.class), connection, Context.BIND_IMPORTANT);

        return super.onStartCommand(intent, flags, startId);
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
            Toast.makeText(ServerService.this,"本地服务被杀",Toast.LENGTH_LONG).show();
            ServerService.this.startService(new Intent(ServerService.this,ClientService.class));
            ServerService.this.bindService(new Intent(ServerService.this, ClientService.class), connection, Context.BIND_IMPORTANT);

        }
    };
}

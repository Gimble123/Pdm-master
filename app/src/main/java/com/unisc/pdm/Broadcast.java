package com.unisc.pdm;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

/**
 * Created by ghelfer on 25/05/2017.
 */

public class Broadcast extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {

        Intent in = new Intent(context, NotificacaoService.class);
        context.startService(in);

        Log.d("CST", "O serviço foi iniciado com sucesso");
    }


}

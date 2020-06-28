package com.unisc.pdm;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.util.List;
import java.util.Map;

class MeuAdaptador extends SimpleAdapter {

    public MeuAdaptador(Context ctx, List<Map<String, Object>> list, int resources, String[] from, int[] to) {
        super(ctx, list, resources, from, to);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = super.getView(position, convertView, parent);

        if (position%2 ==0) {
            view.setBackgroundColor(Color.CYAN);
        }
        else {
            view.setBackgroundColor(Color.YELLOW);
        }
        return view;
    }
}

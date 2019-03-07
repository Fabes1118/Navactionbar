package com.example.navactionbar;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class PageOneFragment extends Fragment {

    @Override

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_detail, container, false);
    }

    @Override
    public void onStart() {

        super.onStart();
        View view = getView();

        if (view != null) {
            TextView title = (TextView) view.findViewById(R.id.textTitle);
            TextView desc = (TextView) view.findViewById(R.id.textDesc);
            ImageView img = (ImageView) view.findViewById(R.id.imageView);

            title.setText(R.string.P11);
            desc.setText(R.string.P12);
            img.setImageResource(R.drawable.playas);
            img.setContentDescription("title");
        }
    }
}

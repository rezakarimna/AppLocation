package com.Millionaire.Location.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.Millionaire.Location.MainActivity;
import com.Millionaire.Location.R;
import com.Millionaire.Location.Setting.SharedPrefManage;
import com.Millionaire.Location.Setting.mAnimation;

/**
 * A simple {@link Fragment} subclass.
 */
public class MainFragment extends Fragment {
    private Button btnRequestLocationUpdates , btnRemoveLocationUpdates;
    private SharedPrefManage prefManage;
    View parent;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View parent = inflater.inflate(R.layout.fragment_main, container, false);
        this.parent = parent;
        initView();
        prefManage = new SharedPrefManage(getContext());
      /*  btnEndLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAnimation.PressClick(v);
                btnEndLocation.setEnabled(false);
                btnStartLocation.setEnabled(true);
                btnEndLocation.getBackground().setAlpha(65);
                btnStartLocation.getBackground().setAlpha(255);
            }
        });
        btnStartLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAnimation.PressClick(v);
                btnStartLocation.setEnabled(false);
                btnEndLocation.setEnabled(true);
                btnStartLocation.getBackground().setAlpha(65);
                btnEndLocation.getBackground().setAlpha(255);
                ((MainActivity) getActivity()).statusCheck();
            }
        });*/
        return parent;
    }

    private void initView() {
        btnRemoveLocationUpdates = parent.findViewById(R.id.btnEndLocation);
        btnRequestLocationUpdates = parent.findViewById(R.id.btnStartLocation);
    }

}

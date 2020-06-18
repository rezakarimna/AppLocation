package com.Millionaire.Location.Fragment;

import android.os.Bundle;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.URLUtil;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.Millionaire.Location.MainActivity;
import com.Millionaire.Location.R;
import com.Millionaire.Location.Setting.Setting;
import com.Millionaire.Location.Setting.SharedPrefManage;
import com.Millionaire.Location.Setting.mAnimation;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class SplashFragment extends Fragment {
    private ConstraintLayout constLoading , constUrl;
    private CircleImageView imgLogoUrl , imgLogo;
    private EditText editUrl;
    private Button btnSaveUrl;
    private ProgressBar progLoading;
    private Handler handler = new Handler();
    private SharedPrefManage sharedPref;
    private RelativeLayout ConstMaster;
    View parent;

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View parent = inflater.inflate(R.layout.fragment_splash, container, false);
        this.parent = parent;
        initView();
        sharedPref = new SharedPrefManage(getContext());
        if (sharedPref.getIsLogin()) {
            constLoading.setVisibility(View.VISIBLE);
            constUrl.setVisibility(View.GONE);
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    ConstMaster = ((MainActivity) getActivity()).findViewById(R.id.ConstMaster);
                    ConstMaster.removeAllViews();
                    ConstMaster.setVisibility(View.GONE);
                    ((MainActivity) getActivity()).findViewById(R.id.relMain).setVisibility(View.VISIBLE);
                  //  Setting.setFragment(new MainFragment() , getContext());
                }
            },2000);
        } else {
            constLoading.setVisibility(View.GONE);
            constUrl.setVisibility(View.VISIBLE);
            ConstMaster = ((MainActivity) getActivity()).findViewById(R.id.ConstMaster);
            ConstMaster.removeAllViews();
            ConstMaster.setVisibility(View.VISIBLE);
            ((MainActivity) getActivity()).findViewById(R.id.relMain).setVisibility(View.GONE);
        }
        btnSaveUrl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAnimation.PressClick(v);
                if (editUrl.length() < 1) {
                    Toast.makeText(getContext(), "هیچ ادرسی وارد نشده است", Toast.LENGTH_SHORT).show();
                } else if (URLUtil.isValidUrl(editUrl.getText() + "/")) {
                    sharedPref.setSaveUrl(editUrl.getText() + "/");
                    List<View> views = new ArrayList<>();
                    views.add(parent.findViewById(R.id.imgLogo));
                    Setting.FinishFragStartFrag_ShareElement_NoBack(new LoginFragment(), views, R.transition.transition_bound , getContext());
                } else
                    Toast.makeText(getContext(), "آدرس وارد شده نا معتبر می باشد", Toast.LENGTH_SHORT).show();
            }
        });
        return parent;
    }

    private void initView(){
        constLoading = parent.findViewById(R.id.constLoading);
        constUrl = parent.findViewById(R.id.constUrl);
        imgLogoUrl = parent.findViewById(R.id.imgLogoUrl);
        imgLogo = parent.findViewById(R.id.imgLogo);
        editUrl = parent.findViewById(R.id.editUrl);
        btnSaveUrl = parent.findViewById(R.id.btnSaveUrl);
        progLoading = parent.findViewById(R.id.progLoading);
    }
}

package com.Millionaire.Location.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.Millionaire.Location.DataBase.AppDatabase;
import com.Millionaire.Location.DataBase.DModel.Login.ResultUser;
import com.Millionaire.Location.DataBase.DModel.Login.User;
import com.Millionaire.Location.MainActivity;
import com.Millionaire.Location.NetWork.ApiClient;
import com.Millionaire.Location.NetWork.ApiInterface;
import com.Millionaire.Location.R;
import com.Millionaire.Location.Setting.Setting;
import com.Millionaire.Location.Setting.SharedPrefManage;
import com.Millionaire.Location.Setting.mAnimation;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class LoginFragment extends Fragment {
    private TextView txtBtnLogin, txtError;
    private ProgressBar progLogin;
    private RelativeLayout relBtnLogin , ConstMaster;
    private EditText editUserName, editPass;
    private SharedPrefManage prefManage;
    private FrameLayout frameError;

    View parent;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View parent = inflater.inflate(R.layout.fragment_login, container, false);
        this.parent = parent;
        initView();
        prefManage = new SharedPrefManage(getContext());
        relBtnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAnimation.PressClick(v);
                txtError.setVisibility(View.GONE);
                if (editUserName.length() <= 0 || editPass.length() <= 0) {
                    txtError.setVisibility(View.VISIBLE);
                    mAnimation.Viberation(txtError);
                } else {
                    progLogin.setVisibility(View.VISIBLE);
                    txtBtnLogin.setVisibility(View.GONE);
                   // setLogin(editUserName.getText().toString() , editPass.getText().toString() , "0" , "0");
                    //   getPoints(setPostModel());
                    setLogin(setPostModel());
                }
            }
        });
        return parent;
    }

    private void initView() {
        editUserName = parent.findViewById(R.id.editUserName);
        editPass = parent.findViewById(R.id.editUserPass);
        relBtnLogin = parent.findViewById(R.id.relBtnLogin);
        txtBtnLogin = parent.findViewById(R.id.txtBtnLogin);
        progLogin = parent.findViewById(R.id.progLogin);
        txtError = parent.findViewById(R.id.txtError);
        frameError = parent.findViewById(R.id.frameError);

    }
    private JsonObject setPostModel() {
        JsonObject Object = new JsonObject();
        Object.addProperty("codev", editUserName.getText().toString());
        Object.addProperty("passv", editPass.getText().toString());
        Object.addProperty("Ime", editUserName.getText().toString());
        // Object.addProperty("Ime", Ime);
        Object.addProperty("Typev", "0");

        return Object;
    }
    private void setLogin(JsonObject object) {
        ApiInterface apiInterface = ApiClient.getRetrofit(getContext()).create(ApiInterface.class);
        final Call<List<User>> userCall = apiInterface.getUser(object);
        userCall.enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                List<User> users = new ArrayList<>();
                if (response.isSuccessful()) {
                    if ((response.body() != null ? response.body().size() : 0) == 0) {
                        frameError.setVisibility(View.VISIBLE);
                        txtError.setText("کاربری با این مشخصات وجود ندارد");
                        mAnimation.Viberation(txtError);
                        progLogin.setVisibility(View.GONE);
                        txtBtnLogin.setVisibility(View.VISIBLE);
                    } else {
                        AppDatabase.getInstance(getContext()).getuserDA().deleteAll();
                        for (int i = 0; i < response.body().size(); i++) {
                            users.add(new User(response.body().get(i).getUserName(),response.body().get(i).getUserCode()));
                            AppDatabase.getInstance(getContext()).getuserDA().SetInsert(users.get(0));
                        }
                        Log.i("infoUSer", "onCreateView: "+ response.body().get(0).getUserName() + response.body().get(0).getUserCode());
                        prefManage.setIslogin(true);
                        ConstMaster = ((MainActivity) getActivity()).findViewById(R.id.ConstMaster);
                        ConstMaster.removeAllViews();
                        ConstMaster.setVisibility(View.GONE);
                        ((MainActivity) getActivity()).findViewById(R.id.relMain).setVisibility(View.VISIBLE);
                      //  Setting.setFragment(new MainFragment(), getContext());
                        progLogin.setVisibility(View.GONE);
                        txtBtnLogin.setVisibility(View.VISIBLE);
                    }
                } else {
                    Toast.makeText(getContext(), response.errorBody() +"خطا2 در برقراری با سرور", Toast.LENGTH_SHORT).show();
                    progLogin.setVisibility(View.GONE);
                    txtBtnLogin.setVisibility(View.VISIBLE);
                    Log.i("isLogin", "onResponse: " + response.errorBody());
                    Log.i("isLogin", "onResponse: " + response.message());


                }
            }
            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {
                Log.i("isLogin", "onResponse: " + t.getMessage());
                Toast.makeText(getContext(), "خطا3 در برقراری با سرور", Toast.LENGTH_SHORT).show();
                progLogin.setVisibility(View.GONE);
                txtBtnLogin.setVisibility(View.VISIBLE);

            }
        });
    }
    // متد برای پورت 211
    /*private void setLogin(String userId, String Pass, String Ime, String Flag) {
        ApiInterface apiInterface = ApiClient.getRetrofit(getContext()).create(ApiInterface.class);
        final Call<ResultUser> userCall = apiInterface.getUser(userId, Pass, Ime, Flag);
        userCall.enqueue(new Callback<ResultUser>() {
            @Override
            public void onResponse(Call<ResultUser> call, Response<ResultUser> response) {
                List<User> users = new ArrayList<>();
                if (response.isSuccessful()) {
                    if (response.body() == null || response.body().getResultUser().size() == 0 ||
                            response.body().getResultUser().get(0).getFDS_GetUser().size() == 0) {
                        frameError.setVisibility(View.VISIBLE);
                        txtError.setText("کاربری با این مشخصات وجود ندارد");
                        mAnimation.Viberation(txtError);
                        progLogin.setVisibility(View.GONE);
                        txtBtnLogin.setVisibility(View.VISIBLE);
                    } else {
                        AppDatabase.getInstance(getContext()).getuserDA().deleteAll();
                        for (int i = 0; i <  response.body().getResultUser().get(0).getFDS_GetUser().size(); i++) {
                            AppDatabase.getInstance(getContext()).getuserDA().SetInsert(response.body().getResultUser().get(0).getFDS_GetUser().get(i));
                        }
                        prefManage.setIslogin(true);

                        progLogin.setVisibility(View.GONE);
                        txtBtnLogin.setVisibility(View.VISIBLE);
                    }
                } else {
                    Toast.makeText(getContext(), "خطا2 در برقراری با سرور", Toast.LENGTH_SHORT).show();
                    progLogin.setVisibility(View.GONE);
                    txtBtnLogin.setVisibility(View.VISIBLE);

                }
            }

            @Override
            public void onFailure(Call<ResultUser> call, Throwable t) {
                Log.i("isLogin", "onResponse: " + t.getMessage());
                Toast.makeText(getContext(), "خطا3 در برقراری با سرور", Toast.LENGTH_SHORT).show();
                progLogin.setVisibility(View.GONE);
                txtBtnLogin.setVisibility(View.VISIBLE);

            }
        });
    }*/
}

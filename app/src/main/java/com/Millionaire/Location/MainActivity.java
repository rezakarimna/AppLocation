package com.Millionaire.Location;

import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
import android.provider.Settings;
import android.transition.TransitionInflater;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import com.Millionaire.Location.DataBase.AppDatabase;
import com.Millionaire.Location.DataBase.DModel.LocationUser.LocationUser;
import com.Millionaire.Location.DataBase.DModel.Login.User;
import com.Millionaire.Location.Fragment.LoginFragment;
import com.Millionaire.Location.Fragment.SplashFragment;
import com.Millionaire.Location.NetWork.ApiClient;
import com.Millionaire.Location.NetWork.ApiInterface;
import com.Millionaire.Location.Services.LocationUpdatesService;
import com.Millionaire.Location.Setting.Setting;
import com.Millionaire.Location.Setting.SharedPrefManage;
import com.Millionaire.Location.Setting.Utils;
import com.Millionaire.Location.Setting.mAnimation;
import com.google.android.material.snackbar.Snackbar;
import com.google.gson.JsonObject;
import com.intuit.sdp.BuildConfig;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private RelativeLayout relExitDig;
    private Button btnOk, btnCancel, btnRequestLocationUpdates, btnRemoveLocationUpdates;
    private TextView txtUserID, txtUserName;
    private static final int REQUEST_CODE = 101;
    boolean mLocationPermissionGranted;
    private int counter = 0;
    private User user = new User();
    private List<User> userList = new ArrayList<>();
    private SharedPrefManage prefManage;
    private static final String TAG = MainActivity.class.getSimpleName();
    // Used in checking for runtime permissions.
    private static final int REQUEST_PERMISSIONS_REQUEST_CODE = 34;

    // The BroadcastReceiver used to listen from broadcasts from the service.
    private MyReceiver myReceiver;

    // A reference to the service used to get location updates.
    private LocationUpdatesService mService = null;
    // Tracks the bound state of the service.
    private boolean mBound = false;

    // UI elements.
    private List<LocationUser> locationUsers = new ArrayList<>();
    // Monitors the state of the connection to the service.
    private final ServiceConnection mServiceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            LocationUpdatesService.LocalBinder binder = (LocationUpdatesService.LocalBinder) service;
            mService = binder.getService();
            mBound = true;
            request();

            if (!checkPermissions()) {
                requestPermissions();
            } else {
                mService.requestLocationUpdates();
            }
            Log.i("SearchActivity", "onServiceConnected: ");
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            mService = null;
            mBound = false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        System.out.println("----------------------------------oncreate");
        //  AlarmShowLocation();
        setContentView(R.layout.activity_main);
        initView();
        prefManage = new SharedPrefManage(this);

        startFragment(new SplashFragment());
        if (Utils.requestingLocationUpdates(this)) {
            if (!checkPermissions()) {
                requestPermissions();
            }
        }
        myReceiver = new MyReceiver();
        Log.i("SearchActivity", "onCreate: ");


    }

    private void initView() {
        btnRemoveLocationUpdates = findViewById(R.id.btnRemoveLocationUpdates);
        btnRequestLocationUpdates = findViewById(R.id.btnRequestLocationUpdates);
        relExitDig = findViewById(R.id.relExitDig);
        btnCancel = findViewById(R.id.btnCancel);
        btnOk = findViewById(R.id.btnOk);
        txtUserID = findViewById(R.id.txtUserID);
        txtUserName = findViewById(R.id.txtUserName);

    }

    @Override
    protected void onStart() {
        super.onStart();
        //  PreferenceManager.getDefaultSharedPreferences(this).registerOnSharedPreferenceChangeListener(this);
        initView();
        userList = AppDatabase.getInstance(this).getuserDA().getAll();
        if (userList.size() != 0) {
            user = userList.get(0);
            txtUserID.setText(user.getUserCode());
            txtUserName.setText(user.getUserName());
        }
        btnRequestLocationUpdates.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mAnimation.PressClick(view);
                statusCheck();
                prefManage.setTurnOnLocation(true);
            }
        });
        btnRemoveLocationUpdates.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mAnimation.PressClick(view);
                prefManage.setTurnOnLocation(false);
                final LocationManager manager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
                if (manager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
                    startActivity(new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS));
                }
                if (mService != null) {
                    mService.removeLocationUpdates();
                }

            }
        });

        final LocationManager manager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        if (manager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            setButtonsState(true);
            prefManage.setTurnOnLocation(true);
        } else {
            setButtonsState(false);
            prefManage.setTurnOnLocation(false);
        }
        // Restore the state of the buttons when the activity (re)launches.
        // Bind to the service. If the service is in foreground mode, this signals to the service
        // that since this activity is in the foreground, the service can exit foreground mode.
        bindService(new Intent(this, LocationUpdatesService.class), mServiceConnection, Context.BIND_AUTO_CREATE);
        Log.i("SearchActivity", "onStart: ");
    }

    private void request() {
        if (mService != null) {
            if (!checkPermissions()) {
                requestPermissions();
            } else {
                mService.requestLocationUpdates();
            }
        } else {
            //  Toast.makeText(MainActivity.this, mService + "null" + "  " + mServiceConnection, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        LocalBroadcastManager.getInstance(this).registerReceiver(myReceiver,
                new IntentFilter(LocationUpdatesService.ACTION_BROADCAST));
        Log.i("SearchActivity", "onResume: ");

    }

    @Override
    protected void onPause() {
        LocalBroadcastManager.getInstance(this).unregisterReceiver(myReceiver);
        super.onPause();
        Log.i("SearchActivity", "onPause: ");
    }

    @Override
    protected void onStop() {
        if (mBound) {
            // Unbind from the service. This signals to the service that this activity is no longer
            // in the foreground, and the service can respond by promoting itself to a foreground
            // service.
            unbindService(mServiceConnection);
            mBound = false;
        } else {
            LocalBroadcastManager.getInstance(this).registerReceiver(myReceiver,
                    new IntentFilter(LocationUpdatesService.ACTION_BROADCAST));
        }
       /* PreferenceManager.getDefaultSharedPreferences(this)
                .unregisterOnSharedPreferenceChangeListener(MainActivity.this);*/
        Log.i("SearchActivity", "onStop: ");
        super.onStop();
    }

    public void statusCheck() {
        final LocationManager manager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        if (!manager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            startActivity(new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS));
        } else {
            if (mService != null) {
                if (!checkPermissions()) {
                    requestPermissions();
                } else {
                    mService.requestLocationUpdates();
                }
            }
        }
    }

    /**
     * Returns the current state of the permissions needed.
     */
    private boolean checkPermissions() {
        return PackageManager.PERMISSION_GRANTED == ActivityCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION);
    }

    private void requestPermissions() {
        boolean shouldProvideRationale =
                ActivityCompat.shouldShowRequestPermissionRationale(this,
                        Manifest.permission.ACCESS_FINE_LOCATION);
        // Provide an additional rationale to the user. This would happen if the user denied the
        // request previously, but didn't check the "Don't ask again" checkbox.
        if (shouldProvideRationale) {
            Log.i(TAG, "Displaying permission rationale to provide additional context.");
            Snackbar.make(
                    findViewById(R.id.imgSearch),
                    R.string.permission_rationale,
                    Snackbar.LENGTH_INDEFINITE)
                    .setAction(R.string.ok, new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            // Request permission
                            ActivityCompat.requestPermissions(MainActivity.this,
                                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                                    REQUEST_PERMISSIONS_REQUEST_CODE);
                        }
                    })
                    .show();
        } else {
            Log.i(TAG, "Requesting permission");
            // Request permission. It's possible this can be auto answered if device policy
            // sets the permission in a given state or the user denied the permission
            // previously and checked "Never ask again".
            ActivityCompat.requestPermissions(MainActivity.this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    REQUEST_PERMISSIONS_REQUEST_CODE);
        }
    }

    public void startFragment(Fragment newFragment) {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.setCustomAnimations(R.anim.fade_show, R.anim.fade_hide);
        ft.replace(R.id.ConstMaster, newFragment);
        //ft.commit();
        ft.commitAllowingStateLoss();
    }

    public void FinishFragStartFrag_ShareElement_NoBack(Fragment newFragment, List<View> views, int transId) {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            newFragment.setSharedElementEnterTransition(TransitionInflater.from(this).inflateTransition(transId));

            for (int i = 0; i < views.size(); i++)
                ft.addSharedElement(views.get(i), views.get(i).getTransitionName());
            ft.replace(R.id.ConstMaster, newFragment);
            ft.commitAllowingStateLoss();
        } else {
            ft.replace(R.id.ConstMaster, newFragment);
            ft.commitAllowingStateLoss();
        }
    }

    @Override
    public void onBackPressed() {
        FragmentManager fm = getSupportFragmentManager();
        Fragment fragment = fm.findFragmentById(R.id.ConstMaster);
        if (fragment instanceof LoginFragment) {
             startFragment(new SplashFragment());
        } else if (fragment == null){


        }
        relExitDig.setVisibility(View.VISIBLE);
        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAnimation.PressClick(v);
                Toast.makeText(MainActivity.this, "با آرزوی موفقیت بیشتر شرکت میلیونر", Toast.LENGTH_SHORT).show();
                //  MainActivity.this.moveTaskToBack(true);
                finishAffinity();
            }
        });
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAnimation.PressClick(v);
                relExitDig.setVisibility(View.GONE);
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        mLocationPermissionGranted = false;
        switch (requestCode) {
            case REQUEST_CODE: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    mLocationPermissionGranted = true;
                }
            }
            case REQUEST_PERMISSIONS_REQUEST_CODE: {
                Log.i(TAG, "onRequestPermissionResult");
                if (grantResults.length <= 0) {
                    // If user interaction was interrupted, the permission request is cancelled and you
                    // receive empty arrays.
                    Log.i(TAG, "User interaction was cancelled.");
                } else if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // Permission was granted.
                    mService.requestLocationUpdates();
                } else {
                    // Permission denied.
                    setButtonsState(false);
                    Snackbar.make(
                            findViewById(R.id.imgSearch),
                            R.string.permission_denied_explanation,
                            Snackbar.LENGTH_INDEFINITE)
                            .setAction(R.string.settings, new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    // Build intent that displays the App settings screen.
                                    Intent intent = new Intent();
                                    intent.setAction(
                                            Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                                    Uri uri = Uri.fromParts("package",
                                            BuildConfig.APPLICATION_ID, null);
                                    intent.setData(uri);
                                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                    startActivity(intent);
                                }
                            })
                            .show();
                }
            }
        }

    }

    private class MyReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (prefManage.getTurnOnLocation()) {
                final LocationManager manager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
                if (!manager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
                    startActivity(new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS));
                } else {
                    Location location = intent.getParcelableExtra(LocationUpdatesService.EXTRA_LOCATION);
                    if (location != null) {
                        Toast.makeText(MainActivity.this, Utils.getLocationText(location, MainActivity.this),
                                Toast.LENGTH_SHORT).show();
                        AppDatabase.getInstance(MainActivity.this).getlocationUser().SetInsert(new LocationUser(location.getLatitude(),
                                location.getLongitude(), Setting.getDateToday(), String.valueOf(Setting.getTime()), false, true));
                        Log.i("SearchActivity", "onReceive: ");
                        if (Utils.setCheckNetWork(MainActivity.this)) {
                            locationUsers = AppDatabase.getInstance(MainActivity.this).getlocationUser().getListSendServer(true);
                            if (locationUsers.size() != 0) {
                                userList = AppDatabase.getInstance(MainActivity.this).getuserDA().getAll();
                                if (userList.size() != 0) {
                                    user = userList.get(0);
                                    sendLocationToServer(counter , user.getUserCode() , locationUsers);
                                }
                            }
                        }
                    }
                }
            } else {

            }

        }
    }

    // port 211
  /*  private void setPostmodel(LocationUser item) {
        codev = "";
        xLocation = "";
        yLocation = "";
        locationTime = "";
        locationDate = "";
        codev = user.getUserCode();
        xLocation = item.getLat() + "";
        yLocation = item.getLng() + "";
        locationTime = item.getMtime();
        locationDate = item.getMdate().replaceAll("[^0-9]", "");
    }*/
    private JsonObject setPostmodel(LocationUser item , String userCode) {
        JsonObject Object = new JsonObject();
        Object.addProperty("codev", userCode);
        Object.addProperty("XLocation", item.getLat() + "");
        Object.addProperty("yLocation", item.getLng() + "");
        Object.addProperty("Time", item.getMtime());
        Object.addProperty("Date", item.getMdate());
        return Object;
    }

    public void sendLocationToServer(final int i , final String userCode , final List<LocationUser> locationUsers) {
        ApiInterface apiInterface = ApiClient.getRetrofit(MainActivity.this).create(ApiInterface.class);
        if (i < locationUsers.size()) {
            Call<String> call = apiInterface.RegisterLocation(setPostmodel(locationUsers.get(i) , userCode));
            call.enqueue(new Callback<String>() {
                @Override
                public void onResponse(Call<String> call, Response<String> response) {
                    if (response.isSuccessful()) {
                        if (response.body() == null) {
                            Toast.makeText(MainActivity.this, "null", Toast.LENGTH_SHORT).show();
                        } else {
                            AppDatabase.getInstance(MainActivity.this).getlocationUser().update(true, locationUsers.get(i).getId());
                            sendLocationToServer(++counter , userCode , locationUsers);
                            Toast.makeText(MainActivity.this, "" + locationUsers.get(i).getId(), Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Log.i("sendLocationToServer", "4= " + response.message());
                        Toast.makeText(MainActivity.this, "خطا 2", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<String> call, Throwable t) {
                    Log.i("sendLocationToServer", "4= " + t.getMessage());
                    Toast.makeText(MainActivity.this, "خطا 3", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    //
    /*public void sendLocationToServer(final int i) {
        ApiInterface apiInterface = ApiClient.getRetrofit(MainActivity.this).create(ApiInterface.class);
        if (i < locationUsers.size()) {
            setPostmodel(locationUsers.get(i));
            Call<ResultLocationUser> call = apiInterface.RegisterLocation(codev, xLocation, yLocation, locationTime, locationDate, 0);
            call.enqueue(new Callback<ResultLocationUser>() {
                @Override
                public void onResponse(Call<ResultLocationUser> call, Response<ResultLocationUser> response) {
                    if (response.isSuccessful()) {
                        if (response.body() == null || response.body().getResultLocationUser().size() == 0 ||
                                response.body().getResultLocationUser().get(0).getFDS_GetLocationUser().size() == 0) {
                        } else {
                            AppDatabase.getInstance(MainActivity.this).getlocationUser().update(true, locationUsers.get(i).getId());
                            sendLocationToServer(++counter);
                        }
                    } else {
                        Log.i("sendLocationToServer", "4= " + response.message());
                    }
                }

                @Override
                public void onFailure(Call<ResultLocationUser> call, Throwable t) {
                    Log.i("sendLocationToServer", "4= " + t.getMessage());
                }
            });
        }
    }*/

    private void setButtonsState(boolean requestingLocationUpdates) {
        if (requestingLocationUpdates) {
            btnRequestLocationUpdates.setEnabled(false);
            btnRemoveLocationUpdates.setEnabled(true);
            btnRequestLocationUpdates.getBackground().setAlpha(65);
            btnRemoveLocationUpdates.getBackground().setAlpha(255);
        } else {
            btnRemoveLocationUpdates.setEnabled(false);
            btnRequestLocationUpdates.setEnabled(true);
            btnRemoveLocationUpdates.getBackground().setAlpha(65);
            btnRequestLocationUpdates.getBackground().setAlpha(255);
        }
    }

}

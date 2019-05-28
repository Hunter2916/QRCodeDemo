package com.maijia.QR.activity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.databinding.DataBindingUtil;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.content.res.ResourcesCompat;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.baidu.location.BDAbstractLocationListener;
import com.baidu.location.BDLocation;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.location.Poi;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.InfoWindow;
import com.baidu.mapapi.map.MapPoi;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.Marker;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.MyLocationConfiguration;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.search.core.SearchResult;
import com.baidu.mapapi.search.geocode.GeoCodeResult;
import com.baidu.mapapi.search.geocode.GeoCoder;
import com.baidu.mapapi.search.geocode.OnGetGeoCoderResultListener;
import com.baidu.mapapi.search.geocode.ReverseGeoCodeOption;
import com.baidu.mapapi.search.geocode.ReverseGeoCodeResult;
import com.maijia.QR.R;
import com.maijia.QR.app.MyApplication;
import com.maijia.QR.bean.Info;
import com.maijia.QR.databinding.ActivityMainBinding;
import com.maijia.QR.listener.MyOrientationListener;
import com.maijia.QR.uitool.ShareBoard;
import com.maijia.QR.uitool.ShareBoardlistener;
import com.maijia.QR.uitool.SnsPlatform;
import com.maijia.QR.utils.QRCodeUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import cn.jiguang.share.android.api.JShareInterface;
import cn.jiguang.share.android.api.PlatActionListener;
import cn.jiguang.share.android.api.Platform;
import cn.jiguang.share.android.api.ShareParams;
import cn.jiguang.share.android.utils.Logger;
import cn.jiguang.share.qqmodel.QQ;
import cn.jiguang.share.qqmodel.QZone;
import cn.jiguang.share.wechat.Wechat;
import cn.jiguang.share.wechat.WechatFavorite;
import cn.jiguang.share.wechat.WechatMoments;
import me.weyye.hipermission.HiPermission;
import me.weyye.hipermission.PermissionCallback;
import me.weyye.hipermission.PermissionItem;

/**
 * 第二步将百度地图引入到APP中
 * 第三步引入定位功能，集合方向传感器实现方向定位
 * LocationClient 进行定位的一些设置(异步操作)
 * BDAbstractLocationListener 监听
 * BDLocation
 * MyLocationData
 * 自定义  需要
 * BitmapDesctiptor   自定义方向传感器
 * sensormanager-sensor
 * BDLocationListener对方向进行设置
 * <p>
 * 第四步添加覆盖物，覆盖物点击处理
 * Marker
 * Overlayoptions图层
 * mBaiduMap.setOnMarkerClickListener
 * InfoWindow在覆盖物上点击时显示信息
 * 第一部模式的切换
 */
public class MainActivity extends BaseActivity implements BaiduMap.OnMarkerClickListener {
    ActivityMainBinding binding;
    private Context context;
    private BaiduMap mBaiduMap;

    //定位相关
    private LocationClient mLocationClient;
    private MyLocationListener mLocationListener;
    private Boolean isFirstIn = true;
    private double latitude;
    private double longtitude;
    //定位图标
    private BitmapDescriptor mIconLocation;
    private MyOrientationListener myOrientationListener;
    private float mCurrentX;
    private LatLng mDestLocationData;
    private LatLng mLastLocationData;

    //覆盖物相关
    private BitmapDescriptor mMarker;
    private View markerLl;
    private GeoCoder geoCoder;

    //极光分享
//    private int mAction = Platform.ACTION_SHARE;
    private int mAction;
    private ShareBoard mShareBoard;
    private ProgressDialog progressDialog;

    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            String toastMsg = (String) msg.obj;
            Toast.makeText(MainActivity.this, toastMsg, Toast.LENGTH_SHORT).show();
            if (progressDialog != null && progressDialog.isShowing()) {
                progressDialog.dismiss();
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this;
        geoCoder = GeoCoder.newInstance();
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        setStatusBarColor();
        String codeURl = "https://www.pgyer.com/8n9z";
        Bitmap codeBitmap = QRCodeUtil.createBitmap(codeURl);
        binding.imageView.setImageBitmap(QRCodeUtil.addLogo(codeBitmap, BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher)));
        mBaiduMap = binding.bmapView.getMap();
        markerLl = findViewById(R.id.maker_ly);
        mLocationListener = new MyLocationListener();
        //
        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("请稍候");
        initView();
        //在定位之前进行一次动态的获取权限
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
        requestRunPermissions();
//        }
        initLocation();
        initListener();
        initMarkers();
    }

    private void requestRunPermissions() {
        List<PermissionItem> permisson = new ArrayList<>();
        permisson.add(new PermissionItem(Manifest.permission.READ_PHONE_STATE, "电话状态", R.drawable.permission_ic_phone));
        permisson.add(new PermissionItem(Manifest.permission.ACCESS_COARSE_LOCATION, "地理位置", R.drawable.permission_ic_location));
        permisson.add(new PermissionItem(Manifest.permission.ACCESS_FINE_LOCATION, "地理位置", R.drawable.permission_ic_location));
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            permisson.add(new PermissionItem(Manifest.permission.READ_EXTERNAL_STORAGE, "读取权限", R.drawable.permission_ic_storage));
            permisson.add(new PermissionItem(Manifest.permission.WRITE_EXTERNAL_STORAGE, "写入权限", R.drawable.permission_ic_storage));
        }
        HiPermission.create(this)
                .title("开启地图权限")
                .permissions(permisson)
                .filterColor(ResourcesCompat.getColor(getResources(), R.color.colorAccent, getTheme()))
                .style(R.style.PermissionBlueStyle)
                .msg("我们需要获得以下权限才能为您提供服务")
                .checkMutiPermission(new PermissionCallback() {
                    @Override
                    public void onClose() {

                    }

                    @Override
                    public void onFinish() {
//                        showToastShort("所有权限申请完成");
                    }

                    @Override
                    public void onDeny(String permission, int position) {

                    }

                    @Override
                    public void onGuarantee(String permission, int position) {

                    }
                });
    }

    /**
     * 覆盖物
     */
    private void initMarkers() {
        //添加标签
        mMarker = BitmapDescriptorFactory.fromResource(R.mipmap.maker);
    }

    private void initListener() {
        binding.typeNone.setOnClickListener(this);
        binding.typeNoraml.setOnClickListener(this);
//        binding.typeSatellite.setOnClickListener(this);
        binding.typeTraffic.setOnClickListener(this);
        binding.location.setOnClickListener(this);
        binding.overlay.setOnClickListener(this);
        mBaiduMap.setOnMarkerClickListener(this);
        binding.typeFalse.setOnClickListener(this);
        binding.typeTrue.setOnClickListener(this);
        binding.typeTrue.setOnClickListener(this);
        //一键分享
        binding.btnSharePop.setOnClickListener(this);
        mBaiduMap.setOnMapClickListener(new BaiduMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {
                //隐藏底部marker
                markerLl.setVisibility(View.GONE);
            }

            @Override
            public boolean onMapPoiClick(MapPoi mapPoi) {
                return false;
            }
        });
        /**
         * 长按定位图标
         */
        mBaiduMap.setOnMapLongClickListener(new BaiduMap.OnMapLongClickListener() {
            @Override
            public void onMapLongClick(LatLng latLng) {
//                showToastShort("设置目的地成功");
//                showToastShort("设置目的地成功"+latLng.toString());
                mDestLocationData = latLng;
                addDestInfoOverLay(latLng);
                latlngToAddress(latLng);
            }
        });
    }

    /**
     * 经纬度或地址相互转换
     *
     * @param latlng
     */
    private void latlngToAddress(LatLng latlng) {

        // 设置反地理经纬度坐标,请求位置时,需要一个经纬度
        geoCoder.reverseGeoCode(new ReverseGeoCodeOption().location(latlng));

        //设置地址或经纬度反编译后的监听,这里有两个回调方法,
        geoCoder.setOnGetGeoCodeResultListener(new OnGetGeoCoderResultListener() {
            //经纬度转换成地址
            @Override
            public void onGetReverseGeoCodeResult(ReverseGeoCodeResult result) {
                if (result == null || result.error != SearchResult.ERRORNO.NO_ERROR) {
                    Toast.makeText(MainActivity.this, "找不到该地址!", Toast.LENGTH_SHORT).show();
                }
//                tv_address.setText("地址:" + result.getAddress());
                assert result != null;
//                showToastShort("地址:" + result.getAddress());
                showToastShort("地址编码"+result.getAdcode());
            }

            //把地址转换成经纬度
            @Override
            public void onGetGeoCodeResult(GeoCodeResult result) {
                // 详细地址转换在经纬度
                String address = result.getAddress();
            }
        });
    }

    /**
     * 长按添加覆盖物
     *
     * @param latLng
     */
    private void addDestInfoOverLay(LatLng latLng) {
        //清除所有的覆盖物
        mBaiduMap.clear();
        OverlayOptions options = new MarkerOptions().position(latLng)
                .icon(BitmapDescriptorFactory.fromResource(R.mipmap.ic_myloc))
                .zIndex(5);//层数
        mBaiduMap.addOverlay(options);

    }


    /**
     * 定位的方法
     */
    private void initLocation() {
//        binding.LocationResult.setMovementMethod(ScrollingMovementMethod.getInstance());
        myOrientationListener = new MyOrientationListener(this);
        mLocationClient = new LocationClient(this);

        mLocationClient.registerLocationListener(mLocationListener);
        LocationClientOption option = new LocationClientOption();
        option.setLocationMode(LocationClientOption.LocationMode.Hight_Accuracy);//精确定位
        option.setCoorType("bd09ll");
        //获取详细地址
        option.setIsNeedAddress(true);
        option.setOpenGps(true);
//        option.setScanSpan(1000);
        option.setIsNeedLocationDescribe(true);
        option.setIsNeedLocationPoiList(true);
        mLocationClient.setLocOption(option);
        //初始化图标
        mIconLocation = BitmapDescriptorFactory.fromResource(R.mipmap.navi_map_gps_locked);
        myOrientationListener.setOnOrientationListener(new MyOrientationListener.OnOrientationListener() {
            @Override
            public void onOrientationChanged(float x) {
                mCurrentX = x;
            }
        });
    }

    private void initView() {
        //更新地图
        //设置初始化比例  500米左右   zoomto越大则地图越详细
        MapStatusUpdate msu = MapStatusUpdateFactory.zoomTo(15.0f);
        mBaiduMap.setMapStatus(msu);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.type_noraml:
                mBaiduMap.setMapType(BaiduMap.MAP_TYPE_NORMAL);
                break;
//            case R.id.type_satellite:
//                mBaiduMap.setMapType(BaiduMap.MAP_TYPE_SATELLITE);
//                break;
            case R.id.type_none:
                mBaiduMap.setMapType(BaiduMap.MAP_TYPE_NONE);
                break;
            case R.id.type_traffic:
                if (mBaiduMap.isTrafficEnabled()) {
                    mBaiduMap.setTrafficEnabled(false);
                    binding.typeTraffic.setText("实时路况(开)");
                } else {
                    mBaiduMap.setTrafficEnabled(true);
                    binding.typeTraffic.setText("实时路况(关)");
                }
                break;
            case R.id.location:
//                getMyLocation();
//                Intent intent = new Intent(MainActivity.this, PhotoPickerXmlActivity.class);
                Intent intent = new Intent(MainActivity.this, ImagePickerActivity.class);
                startActivity(intent);
                break;
            case R.id.overlay:
                addOverLays(Info.infos);
                break;
            case R.id.btn_share_pop:
                //显示底部图标
                mAction = Platform.ACTION_SHARE;
                showBroadView();
                break;
            case R.id.type_true:
                Intent intent1 = new Intent(MainActivity.this, SecondActivity.class);
                startActivity(intent1);
                break;

        }
    }

    /**
     * 展示底部栏
     */
    private void showBroadView() {
        if (mShareBoard == null) {
            mShareBoard = new ShareBoard(this);
            List<String> platforms = JShareInterface.getPlatformList();
            platforms.remove(2);
            if (platforms != null) {
                for (Object temp : platforms) {
                    SnsPlatform snsPlatform = createSnsPlatform(String.valueOf(temp));
                    //把数据添加到显示板
                    mShareBoard.addPlatform(snsPlatform);

                }
            }
            /**
             * 显示板设置点击事件
             */
            mShareBoard.setShareboardclickCallback(mShareBoardlistener);
        }
        //展示
        mShareBoard.show();
    }

    /**
     * 点击事件监听
     */
    private ShareBoardlistener mShareBoardlistener = new ShareBoardlistener() {
        @Override
        public void onclick(SnsPlatform snsPlatform, String platform) {

            switch (mAction) {

                case Platform.ACTION_SHARE:
//                    progressDialog.show();
                    //这里以分享链接为例
                    ShareParams shareParams = new ShareParams();
                    shareParams.setShareType(Platform.SHARE_WEBPAGE);//
                    shareParams.setTitle(ShareTypeActivity.share_title);//
                    shareParams.setText(ShareTypeActivity.share_text);//
                    shareParams.setShareType(Platform.SHARE_WEBPAGE);//
                    shareParams.setUrl(ShareTypeActivity.share_url);//
                    shareParams.setImagePath(MyApplication.ImagePath);//
                    JShareInterface.share(platform, shareParams, mShareListener);
                    break;
                default:
                    break;
            }

        }
    };
    /**
     * 分享监听，   失败，成功，取消
     */
    private PlatActionListener mShareListener = new PlatActionListener() {
        @Override
        public void onComplete(Platform platform, int action, HashMap<String, Object> data) {
            if (handler != null) {
                Message message = handler.obtainMessage();
                message.obj = "分享成功";
                handler.sendMessage(message);
            }
        }

        @Override
        public void onError(Platform platform, int action, int errorCode, Throwable error) {
            String TAG = "MainActivity";
            Logger.e(TAG, "error:" + errorCode + ",msg:" + error);
            if (handler != null) {
                Message message = handler.obtainMessage();
                message.obj = "分享失败:" + error.getMessage() + "---" + errorCode;
                handler.sendMessage(message);
            }
        }

        @Override
        public void onCancel(Platform platform, int action) {
            if (handler != null) {
                Message message = handler.obtainMessage();
                message.obj = "分享取消";
                handler.sendMessage(message);
            }
        }
    };

    /**
     * 获取图标和文字
     *
     * @param platformName
     * @return
     */
    public static SnsPlatform createSnsPlatform(String platformName) {
        String mShowWord = platformName;
        String mIcon = "";
        String mGrayIcon = "";
        //微信
        if (Wechat.Name.equals(platformName)) {
            mIcon = "jiguang_socialize_wechat";
            mGrayIcon = "jiguang_socialize_wechat";
            mShowWord = "jiguang_socialize_text_weixin_key";
            //微信朋友圈
        } else if (WechatMoments.Name.equals(platformName)) {
            mIcon = "jiguang_socialize_wxcircle";
            mGrayIcon = "jiguang_socialize_wxcircle";
            mShowWord = "jiguang_socialize_text_weixin_circle_key";
            //微信收藏
        } else if (WechatFavorite.Name.equals(platformName)) {
            mIcon = "jiguang_socialize_wxfavorite";
            mGrayIcon = "jiguang_socialize_wxfavorite";
            mShowWord = "jiguang_socialize_text_weixin_favorite_key";
            //QQ分享
        } else if (QQ.Name.equals(platformName)) {
            mIcon = "jiguang_socialize_qq";
            mGrayIcon = "jiguang_socialize_qq";
            mShowWord = "jiguang_socialize_text_qq_key";
            //QQ空间分享
        } else if (QZone.Name.equals(platformName)) {
            mIcon = "jiguang_socialize_qzone";
            mGrayIcon = "jiguang_socialize_qzone";
            mShowWord = "jiguang_socialize_text_qq_zone_key";
        }
        return ShareBoard.createSnsPlatform(mShowWord, platformName, mIcon, mGrayIcon, 0);
    }

    /**
     * 判断是够安装该软件
     */
    public static boolean hasApp(Context context, String packName) {
        boolean is = false;
        List<PackageInfo> packages = context.getPackageManager()
                .getInstalledPackages(0);
        for (int i = 0; i < packages.size(); i++) {
            PackageInfo packageInfo = packages.get(i);
            String packageName = packageInfo.packageName;
            if (packageName.equals(packName)) {
                is = true;
            }
        }
        return is;
    }

    /**
     * 添加覆盖物
     *
     * @param infos
     */
    private void addOverLays(List<Info> infos) {
        mBaiduMap.clear();
        LatLng latLng = null;
        Marker marker;
        OverlayOptions options;
        for (int i = 0; i < infos.size(); i++) {
            //经纬度
            latLng = new LatLng(infos.get(i).getLatitude(), infos.get(i).getLongitude());
            //图标
            options = new MarkerOptions().position(latLng).icon(mMarker).zIndex(5);
            marker = (Marker) mBaiduMap.addOverlay(options);
            Bundle bundle = new Bundle();
            bundle.putSerializable("info", infos.get(i));
            marker.setExtraInfo(bundle);
        }
        MapStatusUpdate msu = MapStatusUpdateFactory.newLatLng(latLng);
        mBaiduMap.setMapStatus(msu);
    }


    /**
     * 获取当前的定位
     */
    private void getMyLocation() {
        LatLng latLng = new LatLng(latitude, longtitude);
        MapStatusUpdate msu = MapStatusUpdateFactory.newLatLng(latLng);
        mBaiduMap.animateMapStatus(msu);
    }

    /***
     * Stop location service
     */
    @Override
    protected void onStop() {
        super.onStop();
        //停止定位
        mBaiduMap.setMyLocationEnabled(false);
        mLocationClient.stop();
        myOrientationListener.stop();
    }

    @Override
    protected void onStart() {
        super.onStart();
        //开启定位
        mBaiduMap.setMyLocationEnabled(true);
        //判断的时候注意
        if (!mLocationClient.isStarted()) {
            mLocationClient.start();
        }
        //开发方向传感器
        myOrientationListener.start();

    }

    @Override
    protected void onResume() {
        super.onResume();
        binding.bmapView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        binding.bmapView.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        binding.bmapView.onDestroy();
        if (handler != null) {
            handler.removeCallbacksAndMessages(null);
            handler = null;
        }
        if (progressDialog != null && progressDialog.isShowing()) {
            progressDialog.dismiss();
        }
    }

    /**
     * marker事件监听
     *
     * @param marker
     * @return
     */
    @SuppressLint("SetTextI18n")
    @Override
    public boolean onMarkerClick(Marker marker) {

        //点击marker的时候就把信息传给UI展示
        Bundle extraInfo = marker.getExtraInfo();
        Info info = (Info) extraInfo.getSerializable("info");
        binding.makerLy.idInfoImg.setImageResource(info.getImgId());
        binding.makerLy.idInfoDistance.setText(info.getDistance());
        binding.makerLy.idInfoName.setText(info.getName());
        binding.makerLy.idInfoZan.setText(info.getZan() + "");
        //显示点击覆盖物时的信息
        InfoWindow infoWindow;
        //自定义一个textView
        TextView textView = new TextView(context);
        textView.setBackgroundResource(R.mipmap.location_tips);
        textView.setPadding(30, 20, 30, 30);

        textView.setText(info.getName());
        textView.setTextColor(Color.parseColor("#ffffff"));

        //把经纬度转化为点
        LatLng latLng = marker.getPosition();
        android.graphics.Point point = mBaiduMap.getProjection().toScreenLocation(latLng);
//        showToastShort("你点击的位置是：："+point.toString().trim());
        //设置偏移量
        point.y -= 47;
        //把点转化为经纬度
        LatLng ll = mBaiduMap.getProjection().fromScreenLocation(point);
        infoWindow = new InfoWindow(textView, ll, CONTEXT_IGNORE_SECURITY);
//        mBaiduMap.showInfoWindow(infoWindow);

//        markerLl.setVisibility(View.VISIBLE);

        return true;
    }

    /**
     * 定位结果回调，重写onReceiveLocation方法，定位的回调  latitude 纬度
     */
    private class MyLocationListener extends BDAbstractLocationListener {

        @Override
        public void onReceiveLocation(BDLocation location) {
            if (location == null || binding.bmapView == null) {
                return;
            }
            MyLocationData data = new MyLocationData.Builder()
                    .accuracy(location.getRadius())
                    .direction(mCurrentX)//方向传感器
                    .latitude(location.getLatitude())
                    .longitude(location.getLongitude())
                    .build();
            mBaiduMap.setMyLocationData(data);

            latitude = location.getLatitude();
            longtitude = location.getLongitude();
            String locationDescribe = location.getLocationDescribe();    //获取位置描述信息
            List<Poi> poiList = location.getPoiList();
            String name = null;
            for (int i = 0; i < poiList.size(); i++) {
                name = poiList.get(i).getName();
                Log.i("TAG", "地理位置：" + name);
            }
            if (isFirstIn) {
                LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());
                MapStatusUpdate msu = MapStatusUpdateFactory.newLatLng(latLng);
                mBaiduMap.animateMapStatus(msu);
                isFirstIn = false;
//                Toast.makeText(context, "我的位置信息为：：" + poiList.get(0).getName(),
//                        Toast.LENGTH_SHORT).show();
                Toast.makeText(context, "我的位置信息为：：" + location.getCityCode(),
                        Toast.LENGTH_SHORT).show();
            }

            //设置自定以图标
            MyLocationConfiguration configuration = new
                    MyLocationConfiguration(MyLocationConfiguration.LocationMode.NORMAL, true, mIconLocation);
            mBaiduMap.setMyLocationConfiguration(configuration);
        }
    }
//    private BDAbstractLocationListener mListener = new BDAbstractLocationListener() {
//
//        @Override
//        public void onReceiveLocation(BDLocation location) {
//            if (location == null || binding.bmapView == null) {
//                return;
//            }
//            //开启定位图层
//            mBaiduMap.setMyLocationEnabled(true);
//            MyLocationData locationData = new MyLocationData.Builder()
//                    .accuracy(location.getRadius())
//                    //设置获取到的方向信息
//                    .direction(100)
//                    .latitude(location.getLatitude())
//                    .longitude(location.getLongitude())
//                    .build();
//            //设置定位数据
//            mBaiduMap.setMyLocationData(locationData);
//            mCurrentMarker = BitmapDescriptorFactory.fromResource(R.drawable.permission_ic_location);
//            MyLocationConfiguration configuration = new MyLocationConfiguration(MyLocationConfiguration.LocationMode.NORMAL, true, mCurrentMarker);
//            mBaiduMap.setMyLocationConfiguration(configuration);
//        }
//
//    };

}

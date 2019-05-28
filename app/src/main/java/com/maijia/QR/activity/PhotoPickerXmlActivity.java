package com.maijia.QR.activity;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.bumptech.glide.Glide;
import com.maijia.QR.R;
import com.maijia.QR.databinding.ActivityPhotoPickerBinding;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import me.iwf.photopicker.PhotoPicker;
import me.weyye.hipermission.HiPermission;
import me.weyye.hipermission.PermissionCallback;
import me.weyye.hipermission.PermissionItem;

public class PhotoPickerXmlActivity extends AppCompatActivity implements View.OnClickListener {
    ActivityPhotoPickerBinding binding;
    private Uri uri;
    private Context context;
    private Bitmap bitmap;
    private static final String TAG = "PhotoPickerXmlActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this;
        requestRunPermissions();
        binding = DataBindingUtil.setContentView(this, R.layout.activity_photo_picker);
        initView();
        initListener();
    }

    private void initListener() {
        binding.imageView.setOnClickListener(this);

    }

    private void initView() {

    }

    @Override
    public void onClick(View v) {
        PhotoPicker.builder()
                .setPhotoCount(9)
                .setShowCamera(true)
                .setShowGif(true)
                .setPreviewEnabled(true)
                .start(PhotoPickerXmlActivity.this, PhotoPicker.REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == PhotoPicker.REQUEST_CODE) {
            if (data != null) {
                ArrayList<String> photos =
                        data.getStringArrayListExtra(PhotoPicker.KEY_SELECTED_PHOTOS);
                binding.addres.setText(photos.get(0));
                Glide.with(context)
                        .load(new File(photos.get(0)))
                        .thumbnail(0.f)
                        .into(binding.imageView);
            }
        }
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

}

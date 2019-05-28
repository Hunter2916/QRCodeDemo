package com.maijia.QR.activity;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;

import com.lzy.imagepicker.ImagePicker;
import com.lzy.imagepicker.bean.ImageItem;
import com.lzy.imagepicker.ui.ImageGridActivity;
import com.lzy.imagepicker.ui.ImagePreviewDelActivity;
import com.lzy.imagepicker.view.CropImageView;
import com.maijia.QR.R;
import com.maijia.QR.SelectDialog;
import com.maijia.QR.ShareBottomDialog;
import com.maijia.QR.adapter.ImagePickerAdapter;
import com.maijia.QR.databinding.ActivityImagePickerBinding;
import com.maijia.QR.imageloader.GlideImageLoader;

import java.util.ArrayList;
import java.util.List;

public class ImagePickerActivity extends BaseActivity implements ImagePickerAdapter.OnRecyclerViewItemClickListener {
    private static final String TAG = "ImagePickerActivity";
    private Context context;
    ActivityImagePickerBinding binding;
    //    private ImageItem images;
    public static final int IMAGE_ITEM_ADD = -1;
    public static final int REQUEST_CODE_SELECT = 100;
    public static final int REQUEST_CODE_PREVIEW = 101;

    private ImagePickerAdapter adapter;
    private ArrayList<ImageItem> selImageList; //当前选择的所有图片
    private int maxImgCount = 8;               //允许选择图片最大数

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this;
        binding = DataBindingUtil.setContentView(this, R.layout.activity_image_picker);
        initVeiw();
        initListener();
        initWidget();
    }

    private void initListener() {
        binding.addres.setOnClickListener(this);
        binding.photo.setOnClickListener(this);
        binding.constraintLayout.setOnClickListener(this);
        binding.tvBob.setOnClickListener(this);

    }

    private void initWidget() {
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        selImageList = new ArrayList<>();
        adapter = new ImagePickerAdapter(this, selImageList, maxImgCount);
        adapter.setOnItemClickListener(this);

        recyclerView.setLayoutManager(new GridLayoutManager(this, 4));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);
    }

    private void initVeiw() {
        ImagePicker imagePicker = ImagePicker.getInstance();
        imagePicker.setImageLoader(new GlideImageLoader());   //设置图片加载器
        imagePicker.setShowCamera(true);  //显示拍照按钮
        imagePicker.setCrop(false);        //允许裁剪（单选才有效）
//        imagePicker.setSaveRectangle(true); //是否按矩形区域保存
        imagePicker.setSelectLimit(9);    //选中数量限制
        imagePicker.setStyle(CropImageView.Style.CIRCLE);  //裁剪框的形状
        imagePicker.setFocusWidth(800);   //裁剪框的宽度。单位像素（圆形自动取宽高最小值）
        imagePicker.setFocusHeight(800);  //裁剪框的高度。单位像素（圆形自动取宽高最小值）
        imagePicker.setOutPutX(1000);//保存文件的宽度。单位像素
        imagePicker.setOutPutY(1000);//保存文件的高度。单位像素
        imagePicker.setMultiMode(true);
    }

    @Override
    protected void onResume() {
        super.onResume();
//        if (images != null) {
//            Glide.with(this)
//                    .load(images.path)
//                    .fitCenter()
//                    .into(binding.photo);
//
//        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.photo:
                imagePick();
                break;
            case R.id.addres:
                ShareBottomDialog shareBottomDialog = new ShareBottomDialog();
                shareBottomDialog.show(getSupportFragmentManager());
//                BottomDialog.create(getSupportFragmentManager())
//                    .setTag("测试")
//                    .setDimAmount(0.0f)//透明度
//                    .setLayoutRes(R.layout.dialog_layout)     // dialog layout
//                    .setViewListener(new BottomDialog.ViewListener() {
//                        @Override
//                        public void bindView(View view) {
//
//                            view.findViewById(R.id.A).setOnClickListener(new View.OnClickListener() {
//                                @Override
//                                public void onClick(View v) {
//                                    showToastShort("我是A");
//                                }
//                            });
//                            view.findViewById(R.id.B).setOnClickListener(new View.OnClickListener() {
//                                @Override
//                                public void onClick(View v) {
//                                    showToastShort("我是B");
//                                }
//                            });
//
//                        }
//                    })
//                        .show();
                break;
            case R.id.constraint_layout:
                startActivity(new Intent(ImagePickerActivity.this, Main2Activity.class));
//                startActivity(new Intent(ImagePickerActivity.this, TweenAnimActivity.class));
                break;
            case R.id.tv_bob:
                startActivity(new Intent(ImagePickerActivity.this, AnimationActivity.class));
                break;
        }
    }

    private void imagePick() {


        Intent intent = new Intent(this, ImageGridActivity.class);
//        intent.putExtra(ImageGridActivity.EXTRAS_TAKE_PICKERS,true); // 是否是直接打开相机
        startActivityForResult(intent, REQUEST_CODE_SELECT);
    }

    ArrayList<ImageItem> images = null;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
//        if (resultCode == ImagePicker.RESULT_CODE_ITEMS) {
//            if (data != null && requestCode == REQUEST_CODE_SELECT) {
//                ArrayList<ImageItem> images1 = (ArrayList<ImageItem>) data.getSerializableExtra(ImagePicker.EXTRA_RESULT_ITEMS);
//                images = images1.get(0);
//                binding.addres.setText(images.toString());
//                selImageList.addAll(images1);
//                adapter.setImages(selImageList);
//            } else {
//                Toast.makeText(context, "没有数据", Toast.LENGTH_SHORT).show();
//            }
//        }else if (resultCode == ImagePicker.RESULT_CODE_BACK) {
//            //预览图片返回
//            if (data != null && requestCode == REQUEST_CODE_PREVIEW) {
//                ArrayList<ImageItem> images1 = (ArrayList<ImageItem>) data.getSerializableExtra(ImagePicker.EXTRA_IMAGE_ITEMS);
//                if (images != null) {
//                    selImageList.clear();
//                    selImageList.addAll(images1);
//                    adapter.setImages(selImageList);
//                }
//            }
//        }

        if (resultCode == ImagePicker.RESULT_CODE_ITEMS) {
            //添加图片返回
            if (data != null && requestCode == REQUEST_CODE_SELECT) {
                images = (ArrayList<ImageItem>) data.getSerializableExtra(ImagePicker.EXTRA_RESULT_ITEMS);
                if (images != null) {
                    selImageList.addAll(images);
                    adapter.setImages(selImageList);
                }
            }
        } else if (resultCode == ImagePicker.RESULT_CODE_BACK) {
            //预览图片返回
            if (data != null && requestCode == REQUEST_CODE_PREVIEW) {
                images = (ArrayList<ImageItem>) data.getSerializableExtra(ImagePicker.EXTRA_IMAGE_ITEMS);
                if (images != null) {
                    selImageList.clear();
                    selImageList.addAll(images);
                    adapter.setImages(selImageList);
                }
            }
        }
    }

    @Override
    public void onItemClick(View view, int position) {
        switch (position) {
            case IMAGE_ITEM_ADD:
                List<String> names = new ArrayList<>();
                names.add("拍照");
                names.add("相册");
                showDialog(new SelectDialog.SelectDialogListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        switch (position) {
                            case 0: // 直接调起相机
                                /**
                                 * 0.4.7 目前直接调起相机不支持裁剪，如果开启裁剪后不会返回图片，请注意，后续版本会解决
                                 *
                                 * 但是当前直接依赖的版本已经解决，考虑到版本改动很少，所以这次没有上传到远程仓库
                                 *
                                 * 如果实在有所需要，请直接下载源码引用。
                                 */
                                //打开选择,本次允许选择的数量
                                ImagePicker.getInstance().setSelectLimit(maxImgCount - selImageList.size());
                                Intent intent = new Intent(ImagePickerActivity.this, ImageGridActivity.class);
                                intent.putExtra(ImageGridActivity.EXTRAS_TAKE_PICKERS, true); // 是否是直接打开相机
                                startActivityForResult(intent, REQUEST_CODE_SELECT);
                                break;
                            case 1:
                                //打开选择,本次允许选择的数量
                                ImagePicker.getInstance().setSelectLimit(maxImgCount - selImageList.size());
                                Intent intent1 = new Intent(ImagePickerActivity.this, ImageGridActivity.class);
                                /* 如果需要进入选择的时候显示已经选中的图片，
                                 * 详情请查看ImagePickerActivity
                                 * */
//                                intent1.putExtra(ImageGridActivity.EXTRAS_IMAGES,images);
                                startActivityForResult(intent1, REQUEST_CODE_SELECT);
                                break;
                            default:
                                break;
                        }

                    }
                }, names);


                break;
            default:
                //打开预览
                Intent intentPreview = new Intent(this, ImagePreviewDelActivity.class);
                intentPreview.putExtra(ImagePicker.EXTRA_IMAGE_ITEMS, (ArrayList<ImageItem>) adapter.getImages());
                intentPreview.putExtra(ImagePicker.EXTRA_SELECTED_IMAGE_POSITION, position);
                intentPreview.putExtra(ImagePicker.EXTRA_FROM_ITEMS, true);
                startActivityForResult(intentPreview, REQUEST_CODE_PREVIEW);
                Log.i(TAG, "地址" + selImageList.get(0).path);
                break;
        }
    }

    private SelectDialog showDialog(SelectDialog.SelectDialogListener listener, List<String> names) {
        SelectDialog dialog = new SelectDialog(this, R.style
                .transparentFrameWindowStyle,
                listener, names);
        if (!this.isFinishing()) {
            dialog.show();
        }
        return dialog;
    }


}

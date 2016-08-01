package com.cfox.circleimageviewdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.cfox.circleimageviewdemo.imageview.CircleImageView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private CircleImageView mImageView;
    private ImageView mImageViewB;
    private boolean isA;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mImageView = (CircleImageView) findViewById(R.id.circle_image_view);
        mImageView.setIsFill(true);
        mImageView.setBorderColor(R.color.colorPrimaryDark);
        mImageView.setBorderWidth(15);
        mImageViewB = (ImageView) findViewById(R.id.image_view_b);

        Glide.with(this).load("http://img5.duitang.com/uploads/blog/201407/11/20140711171118_FxyTW.jpeg").into(mImageView);
        Glide.with(this).load("http://img5.duitang.com/uploads/blog/201407/11/20140711171118_FxyTW.jpeg").into(mImageViewB);

        Button button = (Button) findViewById(R.id.btn_change_image);
        button.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if(isA){
            mImageView.setBorderColor(R.color.colorAccent);
            Glide.with(this).load("http://h.hiphotos.baidu.com/image/pic/item/203fb80e7bec54e7f9c085c3be389b504fc26a3b.jpg").into(mImageView);
            Glide.with(this).load("http://h.hiphotos.baidu.com/image/pic/item/203fb80e7bec54e7f9c085c3be389b504fc26a3b.jpg").into(mImageViewB);
            isA = false;
        }else {
            Glide.with(this).load("http://img2.imgtn.bdimg.com/it/u=2099444318,3651034720&fm=21&gp=0.jpg").into(mImageView);
            Glide.with(this).load("http://img2.imgtn.bdimg.com/it/u=2099444318,3651034720&fm=21&gp=0.jpg").into(mImageViewB);
            isA = true;
        }
    }
}

package com.pattern.wistronits.patterntest;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.ImageView;

import java.util.Date;

public class PicActivity extends Activity {
    private ImageView mSourImage;
    private ImageView mWartermarkImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pic);
        initView();

    }

    private void initView() {
        mSourImage = (ImageView) findViewById(R.id.sour_pic);
        mWartermarkImage = (ImageView) findViewById(R.id.wartermark_pic);
        Bitmap idCardBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.customer_loan_img_bg);
        Bitmap logoBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.ic_launcher_round);

        mSourImage.setImageBitmap(idCardBitmap);
//        Bitmap watermarkBitmap = ImageUtil.createWaterMaskCenter(idCardBitmap, logoBitmap);
       // Bitmap watermarkBitmap = ImageUtil.createWaterMaskLeftBottom(this, idCardBitmap, logoBitmap, 0, 0);
//        watermarkBitmap = ImageUtil.createWaterMaskRightBottom(this, watermarkBitmap, waterBitmap, 0, 0);
//        watermarkBitmap = ImageUtil.createWaterMaskLeftTop(this, watermarkBitmap, waterBitmap, 0, 0);
//        watermarkBitmap = ImageUtil.createWaterMaskRightTop(this, watermarkBitmap, waterBitmap, 0, 0);
//        mWartermarkImage.setImageBitmap(watermarkBitmap);

        Bitmap textBitmap = ImageUtil.drawTextToRightBottom(this, idCardBitmap, String.valueOf(new Date(System.currentTimeMillis())), 16, Color.WHITE, 20, 25);
        Bitmap textBitmap2 = ImageUtil.drawTextToRightBottom(this, textBitmap, "南京市江宁区胜太西路南京市江宁区胜太西路南京市江宁区胜太西路南京市江宁区胜太西路", 16, Color.WHITE, 20, 5);
//        textBitmap = ImageUtil.drawTextToRightBottom(this, textBitmap, "右下角", 16, Color.RED, 0, 0);
//        textBitmap = ImageUtil.drawTextToRightTop(this, textBitmap, "右上角", 16, Color.RED, 0, 0);
//        textBitmap = ImageUtil.drawTextToLeftBottom(this, textBitmap, "左下角", 16, Color.RED, 0, 0);
        //textBitmap = ImageUtil.drawTextToCenter(this, textBitmap, "中间", 16, Color.RED);

        mWartermarkImage.setImageBitmap(textBitmap2);
    }

}

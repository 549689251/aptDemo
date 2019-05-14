package com.pattern.wistronits.patterntest;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.pattern.wistronits.patterntest.imgeload.LucImageLoader;

import java.io.IOException;
import java.io.PipedReader;
import java.io.PipedWriter;

/**
 * 生产者消费者模型，线程间的通讯
 * @author wistronits
 */
public class PipedConnectActivity extends Activity {

    private EditText mPipedEdt;
    private TextView mContentTv;
    private PipedReader mPiperReader;
    private PipedWriter mPipedWriter;
    private Thread workerThread;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_piped_cnnect);

        mPipedEdt = findViewById(R.id.edt_input);
        mContentTv = findViewById(R.id.tv_piped_content);
        mPiperReader = new PipedReader();
        mPipedWriter = new PipedWriter();
        try {
            mPiperReader.connect(mPipedWriter);
        } catch (IOException e) {
            e.printStackTrace();
        }


        ImageView view = findViewById(R.id.iv_content);

        LucImageLoader.displayImage("https://cdn2.jianshu.io/assets/web/qingteng-be381813e92784a4c01c608834f76eb2.png", view);

        mPipedEdt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int start, int before, int count) {
                try {
                    mPipedWriter.write(charSequence.subSequence(before, count).toString());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        workerThread = new Thread(new ReaderRunnable(mPiperReader));
        workerThread.start();

    }


    public class ReaderRunnable implements Runnable {

        private PipedReader piperReader;

        public ReaderRunnable(PipedReader piperReader) {
            this.piperReader = piperReader;
        }

        @Override
        public void run() {

            while (!Thread.currentThread().isInterrupted()) {
                int i;
                try {
                    while ((i = piperReader.read()) != -1) {
                        char c = (char) i;

                        Log.d("PipedConnectActivity", "char = " + c);
                    }
                } catch (IOException e) {

                }
            }

        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();

        workerThread.interrupt();
        try {
            mPipedWriter.close();
            mPiperReader.close();
        } catch (IOException e) {
            Log.e("", "");
        }
    }
}

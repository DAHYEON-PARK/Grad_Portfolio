package com.example.ttong.View;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ttong.R;

import java.io.IOException;

public class RecordView extends Activity implements View.OnClickListener,
        MediaPlayer.OnCompletionListener {
    private static final int REC_STOP = 0;
    private static final int RECORDING = 1;
    private static final int PLAY_STOP = 0;
    private static final int PLAYING = 1;
    private static final int PLAY_PAUSE = 2;

    private MediaRecorder mRecorder = null;
    private MediaPlayer mPlayer = null;
    private int mRecState = REC_STOP;
    private int mPlayerState = PLAY_STOP;
    private SeekBar mRecProgressBar, mPlayProgressBar;
    private Button mBtnStartRec, mBtnStartPlay;
    private String mFilePath, mFileName = "test.amr";
    private TextView mTvPlayMaxPoint;

    private int mCurRecTimeMs = 0;
    private int mCurProgressTimeDisplay = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_record);

        mFilePath = SunUtil.makeDir("progress_recorder");

        mBtnStartRec = (Button) findViewById(R.id.btnStartRec);
        mBtnStartPlay = (Button) findViewById(R.id.btnStartPlay);
        mRecProgressBar = (SeekBar) findViewById(R.id.recProgressBar);
        mPlayProgressBar = (SeekBar) findViewById(R.id.playProgressBar);
        mTvPlayMaxPoint = (TextView) findViewById(R.id.tvPlayMaxPoint);

        mBtnStartRec.setOnClickListener(this);
        mBtnStartPlay.setOnClickListener(this);
    }

    Handler mProgressHandler = new Handler() {
        public void handleMessage(Message msg) {
            mCurRecTimeMs = mCurRecTimeMs + 100;
            mCurProgressTimeDisplay = mCurProgressTimeDisplay + 100;

            if (mCurRecTimeMs < 0) {
            }

            else if (mCurRecTimeMs < 60000) {
                mRecProgressBar.setProgress(mCurProgressTimeDisplay);
                mProgressHandler.sendEmptyMessageDelayed(0, 100);
            }

            else {
                mBtnStartRecOnClick();
            }
        }
    };


    Handler mProgressHandler2 = new Handler() {
        public void handleMessage(Message msg) {
            if (mPlayer == null)
                return;

            try {
                if (mPlayer.isPlaying()) {
                    mPlayProgressBar.setProgress(mPlayer.getCurrentPosition());
                    mProgressHandler2.sendEmptyMessageDelayed(0, 100);
                } else {
                    mPlayer.release();
                    mPlayer = null;

                    updateUI();
                }
            } catch (IllegalStateException e) {
            } catch (Exception e) {
            }
        }
    };

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnStartRec:
                mBtnStartRecOnClick();
                break;
            case R.id.btnStartPlay:
                mBtnStartPlayOnClick();
                break;
            default:
                break;
        }
    }

    private void mBtnStartRecOnClick() {
        if (mRecState == REC_STOP) {
            mRecState = RECORDING;
            startRec();
            updateUI();
        } else if (mRecState == RECORDING) {
            mRecState = REC_STOP;
            stopRec();
            updateUI();
        }
    }

    private void startRec() {
        mCurRecTimeMs = 0;
        mCurProgressTimeDisplay = 0;

        mProgressHandler.sendEmptyMessageDelayed(0, 100);

        if (mRecorder == null) {
            mRecorder = new MediaRecorder();
            mRecorder.reset();
        } else {
            mRecorder.reset();
        }

        try {
            mRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
            mRecorder.setOutputFormat(MediaRecorder.OutputFormat.RAW_AMR);
            mRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.DEFAULT);
            mRecorder.setOutputFile(mFilePath + mFileName);
            mRecorder.prepare();
            mRecorder.start();
        } catch (IllegalStateException e) {
            Toast.makeText(this, "IllegalStateException", Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            Toast.makeText(this, "IOException", Toast.LENGTH_SHORT).show();
        }
    }

    private void stopRec() {
        try {
            mRecorder.stop();
        } catch (Exception e) {
        } finally {
            mRecorder.release();
            mRecorder = null;
        }

        mCurRecTimeMs = -999;
        mProgressHandler.sendEmptyMessageDelayed(0, 0);
    }

    private void mBtnStartPlayOnClick() {
        if (mPlayerState == PLAY_STOP) {
            mPlayerState = PLAYING;
            startPlay();
            updateUI();
        } else if (mPlayerState == PLAYING) {
            mPlayerState = PLAY_STOP;
            stopPlay();
            updateUI();
        }
    }

    private void startPlay() {
        if (mPlayer == null)
            mPlayer = new MediaPlayer();
        else
            mPlayer.reset();

        mPlayer.setOnCompletionListener(this);

        String fullFilePath = mFilePath + mFileName;
        Log.v("ProgressRecorder", "...... ==========> " + fullFilePath);

        try {
            mPlayer.setDataSource(fullFilePath);
            mPlayer.prepare();
            int point = mPlayer.getDuration();
            mPlayProgressBar.setMax(point);

            int maxMinPoint = point / 1000 / 60;
            int maxSecPoint = (point / 1000) % 60;
            String maxMinPointStr = "";
            String maxSecPointStr = "";

            if (maxMinPoint < 10)
                maxMinPointStr = "0" + maxMinPoint + ":";
            else
                maxMinPointStr = maxMinPoint + ":";

            if (maxSecPoint < 10)
                maxSecPointStr = "0" + maxSecPoint;
            else
                maxSecPointStr = String.valueOf(maxSecPoint);

            mTvPlayMaxPoint.setText(maxMinPointStr + maxSecPointStr);
        } catch (Exception e) {
            Log.v("ProgressRecorder", "...... Prepare Error ==========> " + e);
        }

        if (mPlayerState == PLAYING) {
            mPlayProgressBar.setProgress(0);

            try {
                mProgressHandler2.sendEmptyMessageDelayed(0, 100);
                mPlayer.start();
            } catch (Exception e) {
                Toast.makeText(this, "error : " + e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void stopPlay() {
        mPlayer.stop();
        mPlayer.release();
        mPlayer = null;
        mPlayProgressBar.setProgress(0);

        mProgressHandler2.sendEmptyMessageDelayed(0, 0);
    }

    public void onCompletion(MediaPlayer mp) {
        mPlayerState = PLAY_STOP;

        mProgressHandler2.sendEmptyMessageDelayed(0, 0);
    }

    private void updateUI() {
        if (mRecState == REC_STOP) {
            mBtnStartRec.setText("Rec");
            mRecProgressBar.setProgress(0);
        } else if (mRecState == RECORDING)
            mBtnStartRec.setText("Stop");

        if (mPlayerState == PLAY_STOP) {
            mBtnStartPlay.setText("Play");
            mPlayProgressBar.setProgress(0);
        } else if (mPlayerState == PLAYING)
            mBtnStartPlay.setText("Stop");
    }
}
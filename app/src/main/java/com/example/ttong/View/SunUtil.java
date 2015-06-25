package com.example.ttong.View;

import android.os.Environment;
import android.util.Log;

import java.io.File;

public class SunUtil {
    public static String makeDir(String dirName){
        String mRootPath = Environment.getExternalStorageDirectory().getAbsolutePath()+ File.separator+dirName;
        try{
            Log.d("path", mRootPath);
            File fRoot = new File(mRootPath);
            if(fRoot.exists() == false){
                if(fRoot.mkdirs() == false){
                    throw new Exception("");
                }
            }
        }catch(Exception e){
            mRootPath = "-1";
        }

        return mRootPath + "/";
    }
}

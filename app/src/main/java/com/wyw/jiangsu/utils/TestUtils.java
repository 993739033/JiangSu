package com.wyw.jiangsu.utils;

import android.content.Context;

import java.io.File;
import java.io.FileOutputStream;
import java.io.RandomAccessFile;

/**
 * Created by wyw on 2016/7/28.
 */
public class TestUtils {
    public static void writeFile(Context context, String text) {
        FileUtil fileUtil = new FileUtil(context);
        File file = new File(fileUtil.getDataDir(), "aaaa.txt");
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(file);
            fos.write(text.getBytes());
            fos.flush();
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void appendFile(Context context, String text) {
        try {
            File file = new File(new FileUtil(context).getDataDirPath() +
                    File.separator + "aaaa.txt");
            RandomAccessFile random = new RandomAccessFile(file, "rw");
            if (file.exists()) random.seek(random.length());
            random.writeUTF("\r\n" + text);
            random.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

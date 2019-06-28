package com.wyw.jiangsu.service.downpush;

import android.content.Context;

import com.wyw.jiangsu.bean.DownloadBean;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

import static com.igexin.sdk.GTServiceManager.context;

/**
 * Created by wyw on 2017/5/19.
 */

public class DownloadTask implements Runnable{

    private DownloadBean bean;
    private DownloadDB db;
    private OkHttpClient client;
    private Context mContext;

    private String id;
    private long maxSize;
    private long completedSize;
    private String url;
    private String saveDir;
    private RandomAccessFile file;
    private int UPDATE_SIZE = 50 * 1024;
    private int downStatus = DownloadStatus.DOWNLOAD_STATUS_INIT;
    private String fileName;
    private DownloadTaskListener listener;
    public DownloadTask(Context context, Builder builder) {
        db = DownloadDB.getInstance(context);
        init(builder);
    }

    private void init(Builder builder) {
        fileName = builder.fileName;
        mContext = builder.mContext;
        saveDir = builder.saveDir;
        url = builder.url;
        maxSize = builder.maxSize;
        completedSize = builder.completedSize;
        id = builder.id;
        downStatus = builder.downStauts;
        bean = builder.downBean;
        UPDATE_SIZE = builder.UPDATE_SIZE;
        listener = builder.listener;
    }
    @Override
    public void run() {
        downStatus = DownloadStatus.DOWNLOAD_STATUS_PREPARE;
        onPrepare();
        InputStream inputStream = null;
        BufferedInputStream bis = null;
        try {
            if (bean == null) {
                bean = db.getTaskById(id);
            }
            file = new RandomAccessFile(new File(saveDir,fileName),"rwd");
            if (bean != null) {
                completedSize = bean.getCompletedSize();
                maxSize = bean.getMaxSize();
            }
            if (file.length() < completedSize) {
                completedSize = file.length();
            }
            long fileLength = file.length();
            if (fileLength != 0 && maxSize == fileLength) {
                downStatus = DownloadStatus.DOWNLOAD_STATUS_COMPLETED;
                maxSize = completedSize = fileLength;
                bean = new DownloadBean(downStatus, completedSize, maxSize, url, fileName, saveDir, id);
                db.insert(bean);
                onCompleted();
                return;
            } else if (fileLength > maxSize) {
                completedSize = 0;
                maxSize = 0;
            }
            downStatus = DownloadStatus.DOWNLOAD_STATUS_START;
            onStart();
            Request request = new Request.Builder()
                    .url(url)
                    .header("RANGE","bytes="+ completedSize+"-")
//                    .addHeader("Referer",url)
                    .build();
            file.seek(completedSize);
            Response response = client.newCall(request).execute();
            ResponseBody body = response.body();
            if (body != null) {
                downStatus = DownloadStatus.DOWNLOAD_STATUS_DOWNLOADING;
                if (maxSize <=0){
                    maxSize = body.contentLength();
                }
                inputStream = body.byteStream();
                bis = new BufferedInputStream(inputStream);
                byte[] buf = new byte[1024 * 4];
                int length ;
                int buffOffset =0;
                if (bean == null) {
                    bean = new DownloadBean(downStatus, 0L, maxSize, url, fileName, saveDir, id);
                    db.insert(bean);
                }
                while ((length = bis.read(buf))>0 && downStatus != DownloadStatus.DOWNLOAD_STATUS_CANCEL &&  downStatus != DownloadStatus.DOWNLOAD_STATUS_PAUSE) {
                    file.write(buf, 0, length);
                    completedSize += length;
                    buffOffset += length;
                    if (buffOffset >= UPDATE_SIZE) {
                        if (maxSize <= 0 || bean.getMaxSize() <= 0) {
                            bean.setMaxSize(maxSize);
                        }
                        buffOffset = 0;
                        bean.setCompletedSize(completedSize);
                        bean.setStatus(downStatus);
                        db.update(bean);
                        onDownloading();
                    }
                }
                bean.setCompletedSize(completedSize);
                bean.setStatus(downStatus);
                db.update(bean);
                onDownloading();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            downStatus = DownloadStatus.DOWNLOAD_STATUS_ERROR;
            onError(DownloadTaskListener.DOWNLOAD_ERROR_FILE_NOT_FOUND);
            return;
        } catch (IOException e) {
            e.printStackTrace();
            downStatus = DownloadStatus.DOWNLOAD_STATUS_ERROR;
            onError(DownloadTaskListener.DOWNLOAD_ERROR_IO_ERROR);
            return;
        } catch (Exception e){
            //Method threw 'java.lang.IllegalArgumentException' exception.
            e.printStackTrace();
            downStatus = DownloadStatus.DOWNLOAD_STATUS_ERROR;
            onError(DownloadTaskListener.DOWNLOAD_ERROR_IO_ERROR);
            return;
        }finally {
            bean.setCompletedSize(completedSize);
            bean.setFileName(fileName);
            db.update(bean);
            if (bis != null) try {
                bis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (inputStream != null) try {
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (file != null) try {
                file.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (completedSize == maxSize) {
            downStatus = DownloadStatus.DOWNLOAD_STATUS_COMPLETED;
            bean.setStatus(downStatus);
            db.update(bean);
            //打开文件
//            OtherUtil.openFile(new File(saveDir,fileName));
        }
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        switch (downStatus) {
            case DownloadStatus.DOWNLOAD_STATUS_COMPLETED:
                onCompleted();
                break;
            case DownloadStatus.DOWNLOAD_STATUS_PAUSE:
                onPause();
                break;
            case DownloadStatus.DOWNLOAD_STATUS_CANCEL:
                db.delete(bean.getId());
                File temp = new File(saveDir,fileName);
                if (temp.exists()) temp.delete();
                onCancel();
                break;
        }
    }
    public DownloadBean getBean() {
        return bean;
    }

    public void setBean(DownloadBean bean) {
        this.bean = bean;
    }

    public DownloadDB getDb() {
        return db;
    }

    public void setDb(DownloadDB db) {
        this.db = db;
    }

    public OkHttpClient getClient() {
        return client;
    }

    public void setClient(OkHttpClient client) {
        this.client = client;
    }

    public Context getmContext() {
        return mContext;
    }

    public void setmContext(Context mContext) {
        this.mContext = mContext;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public long getMaxSize() {
        return maxSize;
    }

    public void setMaxSize(long maxSize) {
        this.maxSize = maxSize;
    }

    public long getCompletedSize() {
        return completedSize;
    }

    public void setCompletedSize(long completedSize) {
        this.completedSize = completedSize;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getSaveDir() {
        return saveDir;
    }

    public void setSaveDir(String saveDir) {
        this.saveDir = saveDir;
    }

    public RandomAccessFile getFile() {
        return file;
    }

    public void setFile(RandomAccessFile file) {
        this.file = file;
    }

    public int getUPDATE_SIZE() {
        return UPDATE_SIZE;
    }

    public void setUPDATE_SIZE(int UPDATE_SIZE) {
        this.UPDATE_SIZE = UPDATE_SIZE;
    }

    public int getDownStatus() {
        return downStatus;
    }

    public void setDownStatus(int downStatus) {
        this.downStatus = downStatus;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public DownloadTaskListener getListener() {
        return listener;
    }

    public void setListener(DownloadTaskListener listener) {
        this.listener = listener;
    }


    private void onPrepare() {
        if (listener != null) {
            listener.onPrepare(this);
        }
    }

    private void onStart() {
        if (listener != null) {
            listener.onStart(this);
        }
    }

    private void onDownloading() {
        if (listener != null) {
            listener.onDownloading(this);
        }
    }

    private void onCompleted() {
        if (listener != null) {
            listener.onCompleted(this);
        }
    }

    private void onPause() {
        if (listener != null) {
            listener.onPause(this);
        }
    }

    private void onCancel() {
        if (listener != null) {
            listener.onCancel(this);
        }
    }

    private void onError(int errorCode) {
        if (listener != null) {
            listener.onError(this,errorCode);
        }
    }
    public static class Builder {
        private String url;
        private String fileName;
        private String saveDir;
        private Context mContext;
        private DownloadBean downBean;
        private String id;
        private long maxSize;
        private long completedSize;
        private int UPDATE_SIZE = 50 * 1024;
        private int downStauts = DownloadStatus.DOWNLOAD_STATUS_INIT;
        private DownloadTaskListener listener;
        public Builder(Context context) {
            this.mContext = context.getApplicationContext();
        }
        public Builder(Context context, String url) {
            this.url = url;
            this.mContext = context.getApplicationContext();
        }
        public Builder setDBEntity(DownloadBean dbEntity) {
            this.downBean = dbEntity;
            downStauts = dbEntity.getStatus();
            url = dbEntity.getUrl();
            id = dbEntity.getId();
            fileName = dbEntity.getFileName();
            saveDir = dbEntity.getDir();
            completedSize = dbEntity.getCompletedSize();
            maxSize = dbEntity.getMaxSize();

            return this;
        }
        public DownloadTask build() {
            return new DownloadTask(context, this);
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getFileName() {
            return fileName;
        }

        public void setFileName(String fileName) {
            this.fileName = fileName;
        }

        public String getSaveDir() {
            return saveDir;
        }

        public void setSaveDir(String saveDir) {
            this.saveDir = saveDir;
        }

        public Context getmContext() {
            return mContext;
        }

        public void setmContext(Context mContext) {
            this.mContext = mContext;
        }

        public DownloadBean getDownBean() {
            return downBean;
        }

        public void setDownBean(DownloadBean downBean) {
            this.downBean = downBean;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public long getMaxSize() {
            return maxSize;
        }

        public void setMaxSize(long maxSize) {
            this.maxSize = maxSize;
        }

        public long getCompletedSize() {
            return completedSize;
        }

        public void setCompletedSize(long completedSize) {
            this.completedSize = completedSize;
        }

        public int getUPDATE_SIZE() {
            return UPDATE_SIZE;
        }

        public void setUPDATE_SIZE(int UPDATE_SIZE) {
            this.UPDATE_SIZE = UPDATE_SIZE;
        }

        public int getDownStauts() {
            return downStauts;
        }

        public void setDownStauts(int downStauts) {
            this.downStauts = downStauts;
        }

        public DownloadTaskListener getListener() {
            return listener;
        }

        public void setListener(DownloadTaskListener listener) {
            this.listener = listener;
        }
    }

    public static DownloadTask parse(DownloadBean entity, Context context) {
        return new Builder(context).setDBEntity(entity).build();
    }
}

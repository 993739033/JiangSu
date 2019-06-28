package com.wyw.jiangsu.bean;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by wyw on 2018/1/26.
 */

public class FadingFaguiBean extends BaseArrayObjectEntity<FadingFaguiBean.DataListBean> implements Serializable {


    public static class DataListBean implements Serializable {

        private String menuName;
        private List<FileBean> File;

        public String getMenuName() {
            return menuName;
        }

        public void setMenuName(String menuName) {
            this.menuName = menuName;
        }

        public List<FileBean> getFile() {
            return File;
        }

        public void setFile(List<FileBean> File) {
            this.File = File;
        }

        public static class FileBean implements Serializable {
            /**
             * FileName : 中华人民共和国草原法.doc
             * FilePath : http://192.168.0.221:8886/JSPT/ZFWS/法律/中华人民共和国草原法.doc
             */

            private String FileName;
            private String FilePath;

            public String getFileName() {
                return FileName;
            }

            public void setFileName(String FileName) {
                this.FileName = FileName;
            }

            public String getFilePath() {
                return FilePath;
            }

            public void setFilePath(String FilePath) {
                this.FilePath = FilePath;
            }
        }
    }
}

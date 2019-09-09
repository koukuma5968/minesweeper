package com.model.util;

public class Constant {

    public static final String WHITE = "WHITE_";
    public static final String IMAGE_PATH = Constant.class.getResource("../../image/").getPath();
    public static final String BOM_IMAGE = "BOM.JPG";
    public static final String SHOW_IMAGE = "show.png";

    public enum WHITE_IMAGE {

        WHITE_0("WHITE_0.JPG"),
        WHITE_1("WHITE_1.JPG"),
        WHITE_2("WHITE_2.JPG"),
        WHITE_3("WHITE_3.JPG"),
        WHITE_4("WHITE_4.JPG"),
        WHITE_5("WHITE_5.JPG"),
        WHITE_6("WHITE_6.JPG"),
        WHITE_7("WHITE_7.JPG"),
        WHITE_8("WHITE_8.JPG");

        private WHITE_IMAGE(String keyName) {
            setKeyName(keyName);
        }

        private String keyName = null;

        public void setKeyName(String keyName) {
            this.keyName = keyName;
        }

        public String getKeyName() {
            return this.keyName;
        }
    }

}

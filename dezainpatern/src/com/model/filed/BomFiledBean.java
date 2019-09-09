package com.model.filed;

import java.util.BitSet;
import java.util.LinkedHashMap;

public class BomFiledBean {

    private BitSet boms = null;

    private BitSet sPoint = null;

    private BitSet ePoint = null;

    private LinkedHashMap<Integer, String> filedMap = null;

    public BitSet getBoms() {
        return boms;
    }

    public void setBoms(BitSet boms) {
        this.boms = boms;
    }

    public BitSet getsPoint() {
        return sPoint;
    }

    public void setsPoint(BitSet sPoint) {
        this.sPoint = sPoint;
    }

    public BitSet getePoint() {
        return ePoint;
    }

    public void setePoint(BitSet ePoint) {
        this.ePoint = ePoint;
    }

    public LinkedHashMap<Integer, String> getFiledMap() {
        return filedMap;
    }

    public void setFiledMap(LinkedHashMap<Integer, String> filedMap) {
        this.filedMap = filedMap;
    }

}

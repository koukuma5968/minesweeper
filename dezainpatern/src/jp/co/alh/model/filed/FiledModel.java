package jp.co.alh.model.filed;

import java.util.ArrayList;
import java.util.List;

import jp.co.alh.model.card.CardModel;

public class FiledModel {

    private List<CardModel> cModelList = null;
    private short colCount = 0;
    private short rowCount = 0;

    public List<CardModel> getcModelList() {
        if (this.cModelList == null) {
            this.cModelList = new ArrayList<CardModel>();
        }
        return cModelList;
    }

    public void setcModelList(List<CardModel> cModelList) {
        this.cModelList = cModelList;
    }

    public short getColCount() {
        return colCount;
    }

    public void setColCount(short colCount) {
        this.colCount = colCount;
    }

    public short getRowCount() {
        return rowCount;
    }

    public void setRowCount(short rowCount) {
        this.rowCount = rowCount;
    }

    public void createFiled() {
        List<CardModel> modelList = this.getcModelList();
        for (int i = 0; i < this.getRowCount(); i++) {
            for (int j = 0; j < this.getColCount(); j++) {
                CardModel model = new CardModel();
                model.setNo(i + 1);
                modelList.add(model);
            }
        }
    }
}

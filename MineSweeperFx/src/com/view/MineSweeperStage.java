package com.view;

import java.util.Optional;

import com.controller.MultipleTextInputDialog;
import com.model.filed.BomFiledBean;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class MineSweeperStage extends Application {

    private BomFiledBean bomBean = new BomFiledBean();
    private String[] textId = new String[] {"bom", "col", "row"};
    private String[] labelText = new String[] {"bom数", "列数", "行数"};

    @Override
    public void start(Stage primaryStage) throws Exception {

        MultipleTextInputDialog inputDialog = new MultipleTextInputDialog(3);
        inputDialog.setTitle("難易度設定");
        int i = 0;
        for (Label dialogLabel : inputDialog.getLabels()) {
            dialogLabel.setText(labelText[i]);
            i++;
        }

        int j = 0;
        for (TextField fields : inputDialog.getFields()) {
            fields.setId(textId[j]);
            j++;
        }

        inputDialog.getDialogPane().setPrefSize(150, 200);

        Optional<String[]> result = inputDialog.showAndWait();
        String[] res = result.get();
        this.bomBean = new BomFiledBean();

        bomBean.setBomCount(Integer.valueOf(res[0]));
        bomBean.setRowCount(Integer.valueOf(res[1]));
        bomBean.setColCount(Integer.valueOf(res[2]));

        MinePanel pane = new MinePanel();

        pane.init(bomBean);

        Scene scene = new Scene(pane, 200, 200);
        primaryStage.setScene(scene);
        primaryStage.show();

    }

}

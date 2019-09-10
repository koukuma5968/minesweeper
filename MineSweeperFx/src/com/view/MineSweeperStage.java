package com.view;

import java.util.Optional;

import com.controller.MultipleTextInputDialog;
import com.model.filed.BomFiledBean;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class MineSweeperStage extends Application {

    private BomFiledBean bomBean = new BomFiledBean();

    @Override
    public void init() {
        this.bomBean = new BomFiledBean();
        bomBean.setBomCount(10);
        bomBean.setRowCount(10);
        bomBean.setColCount(10);

    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        MultipleTextInputDialog inputDialog = new MultipleTextInputDialog();
        inputDialog.setTitle("設定");
        DialogPane diPane = inputDialog.getDialogPane();
        diPane.setPrefSize(200, 250);
        ObservableList<Node> nodes = diPane.getChildren();

//        GridPane node3 = (GridPane) nodes.get(3);
//        node3.setAlignment(Pos.TOP_LEFT);
//        TextField nodes3 = (TextField) node3.getChildren().get(1);
//        node3.getChildren().removeAll(node3.getChildren());

//        TextField text1 = new TextField();
//        TextField text2 = new TextField();
//        TextField text3 = new TextField();

//        node3.add(text1, 0, 0);
//        node3.add(text2, 0, 1);
//        node3.add(text3, 0, 2);

        GridPane node0 = (GridPane) nodes.get(0);
        Label label = (Label) node0.getChildren().get(0);
        label.setText("難易度設定");

        Optional<String[]> result = inputDialog.showAndWait();
        String[] res = result.get();
        for (String str : res) {
            System.out.println(str);
        }

        MinePanel pane = new MinePanel();
        pane.init(bomBean);

        Scene scene = new Scene(pane, 500, 500);
        primaryStage.setScene(scene);
        primaryStage.show();

    }

}

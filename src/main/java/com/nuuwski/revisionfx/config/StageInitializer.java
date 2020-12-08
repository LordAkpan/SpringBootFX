package com.nuuwski.revisionfx.config;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class StageInitializer implements ApplicationListener<UiApplication.StageReadyEvent> {

    private String applicationTitle;
    private ApplicationContext applicationContext;

    public StageInitializer(@Value("${spring.application.ui.title}") String applicationTitle,
                            ApplicationContext applicationContext) {
        this.applicationTitle = applicationTitle;
        this.applicationContext = applicationContext;
    }

    @Override
    public void onApplicationEvent(UiApplication.StageReadyEvent stageReadyEvent) {

        try{
            Stage stage = stageReadyEvent.getStage();
            stage.setTitle(applicationTitle);

            Button button = new Button("Say 'Hello World'");
            button.setOnAction(e -> {
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "Hello World!?");
                alert.showAndWait();
            });

            ObservableList<PieChart.Data> valueList =
                    FXCollections.observableArrayList(new PieChart.Data("Cats", 50),
                    new PieChart.Data("Dogs", 50));


            ObservableList<PieChart.Data> valueList2 = FXCollections.observableArrayList(
                    new PieChart.Data("Nitrogen", 7809),
                    new PieChart.Data("Oxygen", 2195),
                    new PieChart.Data("Other", 93)
            );
            PieChart pieChart = new PieChart(valueList2);

            pieChart.getData().forEach(data -> {
                String percentage = String.format("%.2f%%", (data.getPieValue() / 100));
                Tooltip tooltip =  new Tooltip(percentage);
                Tooltip.install(data.getNode(), tooltip);
            });
            StackPane root =  new StackPane();
            root.getChildren().add(pieChart);
            Scene scene = new Scene(root, 500, 300);
            stage.setScene(scene);

            stage.show();

        }catch (Exception e){
            e.printStackTrace();
        }
    }
}

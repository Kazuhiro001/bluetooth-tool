package meisters.tool.bluetooth;

import gnu.io.CommPortIdentifier;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Enumeration;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Bluetooth Tool");

        FlowPane root = new FlowPane();
        Enumeration portIdentifiers =
                CommPortIdentifier.getPortIdentifiers();
        while (portIdentifiers.hasMoreElements()) {
            CommPortIdentifier portId = (CommPortIdentifier)
                    portIdentifiers.nextElement();
            root.getChildren().add(new Label(portId.getName()));
            System.out.print(portId.getName());
        }

        primaryStage.setScene(new Scene(root, 300, 250));
        primaryStage.show();

    }


    public static void main(String[] args) throws IOException {
        launch(args);
    }

}

package meisters.tool.bluetooth;

import gnu.io.CommPortIdentifier;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import meisters.tool.bluetooth.model.COMPort;
import meisters.tool.bluetooth.model.Port;
import meisters.tool.bluetooth.view.MainController;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;

public class Main extends Application {

    private MainController mController;

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Bluetooth Tool");
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("view/main.fxml"));
        Parent root = loader.load();
        mController = loader.getController();
        ArrayList<Port> ports = new ArrayList<>();
        Enumeration portIdentifiers =
                CommPortIdentifier.getPortIdentifiers();
        while (portIdentifiers.hasMoreElements()) {
            CommPortIdentifier portId = (CommPortIdentifier)
                    portIdentifiers.nextElement();
            ports.add(new COMPort(portId));
            System.out.print(portId.getName());
        }
        mController.setPortList(ports);

        primaryStage.setScene(new Scene(root, 600, 400));
        primaryStage.show();
    }


    public static void main(String[] args) throws IOException {
        launch(args);
    }

    @Override
    public void stop() throws Exception {
        super.stop();
        mController.finish();
    }
}

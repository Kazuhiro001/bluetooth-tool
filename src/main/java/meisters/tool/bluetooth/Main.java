package meisters.tool.bluetooth;

import gnu.io.CommPortIdentifier;
import gnu.io.NoSuchPortException;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.util.Duration;
import meisters.tool.bluetooth.model.LogData;
import meisters.tool.bluetooth.view.MainController;

import java.io.IOException;

public class Main extends Application {

    ObservableList<LogData> mLogData = FXCollections.observableArrayList();
    private PortReaderService mPortReaderService;

    public void connectToPort(String portName) {
        CommPortIdentifier portID = null;
        try {
            portID = CommPortIdentifier.getPortIdentifier(portName);
        } catch (NoSuchPortException e) {
            e.printStackTrace();
        }
        mPortReaderService = new PortReaderService(portID, Duration.millis(4));
        mPortReaderService.valueProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                mLogData.add(new LogData(newValue));
            }
        });
        mPortReaderService.start();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Bluetooth Tool");
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("view/main.fxml"));
        Parent root = loader.load();
        MainController controller = loader.getController();
        controller.setMain(this);

        primaryStage.setScene(new Scene(root, 600, 400));
        primaryStage.show();
    }


    public static void main(String[] args) throws IOException {
        launch(args);
    }

    public ObservableList<LogData> getLogData() {
        return mLogData;
    }

    @Override
    public void stop() throws Exception {
        super.stop();
        if (mPortReaderService != null) {
            mPortReaderService.cancel();
        }
    }
}

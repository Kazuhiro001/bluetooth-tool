package meisters.tool.bluetooth.view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import meisters.tool.bluetooth.model.LogData;
import meisters.tool.bluetooth.model.Port;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import static javafx.collections.FXCollections.observableArrayList;

public class MainController implements Initializable {

    @FXML
    private ChoiceBox<Port> portList;
    @FXML
    private TableView<LogData> logTable;
    @FXML
    private TableColumn<LogData, String> firstColumn;
    private Port mConnectedPort;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        firstColumn.setCellValueFactory(log -> log.getValue().logDataProperty());
    }

    public void setPortList(ArrayList<Port> ports) {
        portList.setItems(observableArrayList(ports));
    }

    @FXML
    public void connect(ActionEvent actionEvent) {
        mConnectedPort = portList.getValue();
        mConnectedPort.connect();
        logTable.setItems(mConnectedPort.getLogData());
        System.out.println("connect to " + mConnectedPort.toString());
    }

    @FXML
    public void disconnect(ActionEvent actionEvent) {
        if (mConnectedPort != null) {
            mConnectedPort.disConnect();
        }
    }

    public void finish() {
        if (mConnectedPort != null) {
            mConnectedPort.disConnect();
        }
    }
}

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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        firstColumn.setCellValueFactory(log -> log.getValue().logDataProperty());
    }

    public void setPortList(ArrayList<Port> ports) {
        portList.setItems(observableArrayList(ports));
    }

    @FXML
    public void connect(ActionEvent actionEvent) {
        Port port = portList.getValue();
        port.connect();
        logTable.setItems(port.getLogData());
        System.out.println("connect to " + port.toString());
    }

    public void finish() {
        portList.getValue().disConnect();
    }
}

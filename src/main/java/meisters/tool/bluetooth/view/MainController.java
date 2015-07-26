package meisters.tool.bluetooth.view;

import gnu.io.CommPortIdentifier;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import meisters.tool.bluetooth.Main;
import meisters.tool.bluetooth.model.LogData;

import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.ResourceBundle;

import static javafx.collections.FXCollections.observableArrayList;

public class MainController implements Initializable {

    @FXML
    private ChoiceBox<String> portList;
    @FXML
    private TableView<LogData> logTable;
    @FXML
    private TableColumn<LogData, String> firstColumn;
    private Main mMain;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ArrayList<String> ports = new ArrayList<>();
        Enumeration portIdentifiers =
                CommPortIdentifier.getPortIdentifiers();
        while (portIdentifiers.hasMoreElements()) {
            CommPortIdentifier portId = (CommPortIdentifier)
                    portIdentifiers.nextElement();
            ports.add(portId.getName());
            System.out.print(portId.getName());
        }
        portList.setItems(observableArrayList(ports));

        firstColumn.setCellValueFactory(log -> log.getValue().logDataProperty());
    }

    @FXML
    public void connect(ActionEvent actionEvent) {
        String portName = portList.getValue();
        if (portName != null && mMain != null) {
            mMain.connectToPort(portName);
        }
    }

    public void setMain(Main main) {
        this.mMain = main;
        logTable.setItems(main.getLogData());
    }
}

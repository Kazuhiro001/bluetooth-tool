package meisters.tool.bluetooth;

import gnu.io.CommPortIdentifier;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;

import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.ResourceBundle;

import static javafx.collections.FXCollections.observableArrayList;

public class MainController implements Initializable {

    @FXML
    ChoiceBox<String> portList;

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

    }
}

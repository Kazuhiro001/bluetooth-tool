package meisters.tool.bluetooth.model;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class LogData {
    StringProperty logData;

    public LogData(String data) {
        logData = new SimpleStringProperty(data);
    }

    public StringProperty logDataProperty() {
        return logData;
    }
}

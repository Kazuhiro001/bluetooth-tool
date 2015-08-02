package meisters.tool.bluetooth.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * Created by Kazuhiro on 2015/08/02.
 */
public abstract class Port {
    private ObservableList<LogData> logData = FXCollections.observableArrayList();

    public ObservableList<LogData> getLogData() {
        return logData;
    }

    public abstract void connect();

    public abstract void disConnect();
}

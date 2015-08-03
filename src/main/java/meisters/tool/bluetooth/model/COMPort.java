package meisters.tool.bluetooth.model;

import gnu.io.CommPortIdentifier;
import javafx.util.Duration;
import meisters.tool.bluetooth.PortReaderService;

/**
 * Created by Kazuhiro on 2015/08/03.
 */
public class COMPort extends Port {

    private final CommPortIdentifier mPortId;
    private PortReaderService mPortReaderService;

    public COMPort(CommPortIdentifier portId) {
        mPortId = portId;
    }

    @Override
    public String toString() {
        return mPortId.getName();
    }

    @Override
    public void connect() {
        mPortReaderService = new PortReaderService(mPortId, Duration.millis(4));
        mPortReaderService.valueProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                getLogData().add(new LogData(newValue));
            }
        });
        mPortReaderService.start();
    }

    @Override
    public void disConnect() {
        if (mPortReaderService != null) {
            mPortReaderService.cancel();
        }
    }
}

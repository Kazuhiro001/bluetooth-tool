package meisters.tool.bluetooth;

import gnu.io.CommPortIdentifier;
import gnu.io.PortInUseException;
import gnu.io.SerialPort;
import javafx.concurrent.ScheduledService;
import javafx.concurrent.Task;
import javafx.util.Duration;

import java.io.*;

public class PortReaderService extends ScheduledService<String> {

    private SerialPort mPort;
    private final CommPortIdentifier mPortId;

    public PortReaderService(CommPortIdentifier portId, Duration seconds) {
        mPortId = portId;
        setPeriod(seconds);
    }

    @Override
    protected Task<String> createTask() {
        return new Task<String>() {

            @Override
            protected String call() throws Exception {
                ByteArrayOutputStream bout = new ByteArrayOutputStream();
                try (InputStream inputStream = mPort.getInputStream()) {
                    BufferedInputStream bio = new BufferedInputStream(inputStream);
                    byte[] buffer = new byte[1024];
                    while (true) {
                        int len = bio.read(buffer);
                        if (len < 0) {
                            break;
                        }
                        bout.write(buffer, 0, len);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return bout.toString();
            }
        };
    }

    @Override
    protected void ready() {
        super.ready();
        openPortIfNecessary();
    }

    private void openPortIfNecessary() {
        if (mPort == null) {
            try {
                mPort = (SerialPort) mPortId.open("SirialTest", 5000);
                System.out.println("open: " + mPortId);
            } catch (PortInUseException e) {
                e.printStackTrace();
            }
        }
    }

    private void closePort() {
        System.out.println("close: " + mPortId);
        mPort.close();
        mPort = null;
    }

    @Override
    protected void failed() {
        super.failed();
        closePort();
    }


    @Override
    protected void cancelled() {
        super.cancelled();
        closePort();
    }
}

package meisters.tool.bluetooth;

import gnu.io.CommPortIdentifier;
import gnu.io.PortInUseException;
import gnu.io.SerialPort;
import javafx.concurrent.ScheduledService;
import javafx.concurrent.Task;
import javafx.util.Duration;

public class PortReaderService extends ScheduledService<String> {

    private int i = 0;
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
//                InputStream inputStream = null;
//                byte[] bytes = new byte[2048];
//                try {
//                    inputStream = mPort.getInputStream();
//                    inputStream.read(bytes);
//                } catch (IOException e) {
//                    e.printStackTrace();
//                } finally {
//                    inputStream.close();
//                }
//                return new String(bytes, "UTF-8");
                return "test" + i++;
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

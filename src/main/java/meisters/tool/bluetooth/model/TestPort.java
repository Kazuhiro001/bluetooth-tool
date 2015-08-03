package meisters.tool.bluetooth.model;

import javafx.concurrent.ScheduledService;
import javafx.concurrent.Task;
import javafx.util.Duration;

/**
 * 表示テスト用のポートです。
 * <p>
 * Created by Kazuhiro on 2015/08/03.
 */
public class TestPort extends Port {
    private TestService mTestService;

    @Override
    public void connect() {
        mTestService = new TestService();
        mTestService.setPeriod(Duration.millis(4));
        mTestService.valueProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                getLogData().add(new LogData(newValue));
            }
        });
        mTestService.start();
    }

    @Override
    public void disConnect() {
        if (mTestService != null) {
            mTestService.cancel();
        }
    }

    class TestService extends ScheduledService<String> {

        @Override
        protected Task<String> createTask() {
            return new Task<String>() {
                @Override
                protected String call() throws Exception {
                    return "hoge";
                }
            };
        }
    }

    @Override
    public String toString() {
        return "TestPort";
    }
}

package popo.atlas.framework.utils.listener;

import lombok.extern.log4j.Log4j2;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

@Log4j2
public class CustomListener extends TestListenerAdapter {

    @Override
    public void onTestFailure(ITestResult tr) {
        log.error(tr.getThrowable().getStackTrace(), tr.getThrowable());
    }
}
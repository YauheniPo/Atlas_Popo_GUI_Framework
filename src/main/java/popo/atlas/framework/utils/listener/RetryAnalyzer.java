package popo.atlas.framework.utils.listener;

import lombok.extern.log4j.Log4j2;
import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;
import popo.atlas.framework.utils.configurations.FrameworkConfiguration;

import java.util.Optional;

@Log4j2
public class RetryAnalyzer implements IRetryAnalyzer {

    //Counter to keep track of retry attempts
    int retryAttemptsCounter = 0;

    //The max limit to retry running of failed test cases
    //Set the value to the number of times we want to retry
    private int maxRetryLimit = Integer.parseInt(System.getProperty("maxRerunCount",
            Optional.of(String.valueOf(FrameworkConfiguration.getInstance().getMaxRerunCount())).orElse("0")));

    @Override
    public boolean retry(ITestResult result) {
        if (!result.isSuccess()) {
            log.error(result.getThrowable());

            if(retryAttemptsCounter < maxRetryLimit){
                retryAttemptsCounter++;
                return true;
            }
        }
        return false;
    }
}

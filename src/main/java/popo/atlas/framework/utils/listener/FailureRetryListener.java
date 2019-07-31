package popo.atlas.framework.utils.listener;

import org.testng.IRetryAnalyzer;
import org.testng.annotations.ITestAnnotation;
import org.testng.IAnnotationTransformer;
import popo.atlas.framework.utils.configurations.FrameworkConfiguration;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

public class FailureRetryListener implements IAnnotationTransformer {

    @Override
    public void transform(ITestAnnotation annotation, Class testClass, Constructor testConstructor, Method testMethod) {
        IRetryAnalyzer retry = annotation.getRetryAnalyzer();

        if (retry == null && FrameworkConfiguration.getInstance().isCustomRetryAnalyzer())
            annotation.setRetryAnalyzer(RetryAnalyzer.class);
    }
}

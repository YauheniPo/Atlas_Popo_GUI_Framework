package popo.atlas.framework.util.listener;

import org.testng.IAnnotationTransformer;
import org.testng.IRetryAnalyzer;
import org.testng.annotations.ITestAnnotation;
import popo.atlas.framework.base.BaseEntity;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

public class FailureRetryListener implements IAnnotationTransformer {

    @Override
    public void transform(ITestAnnotation annotation, Class testClass, Constructor testConstructor, Method testMethod) {
        IRetryAnalyzer retry = annotation.getRetryAnalyzer();

        if (retry == null && BaseEntity.testConfig.getBooleanProperties("test.customRetryAnalyzer"))
            annotation.setRetryAnalyzer(RetryAnalyzer.class);
    }
}

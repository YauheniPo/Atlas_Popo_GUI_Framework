package popo.atlas.framework.atlas.extention;

import io.qameta.atlas.core.Atlas;
import io.qameta.atlas.core.api.MethodExtension;
import io.qameta.atlas.core.api.Target;
import io.qameta.atlas.core.internal.Configuration;
import io.qameta.atlas.core.target.LazyTarget;
import io.qameta.atlas.core.util.MethodInfo;
import io.qameta.atlas.webdriver.extension.Name;
import org.openqa.selenium.By;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebElement;

import java.lang.reflect.Method;
import java.util.Map;
import java.util.Optional;

import static io.qameta.atlas.webdriver.util.MethodInfoUtils.getParamValues;
import static io.qameta.atlas.webdriver.util.MethodInfoUtils.processParamTemplate;

public class ContainsClassExtension implements MethodExtension {

    @Override
    public boolean test(final Method method) {
        return method.isAnnotationPresent(ContainsClass.class)
                && WebElement.class.isAssignableFrom(method.getReturnType());
    }

    @Override
    public Object invoke(final Object proxy, final MethodInfo methodInfo, final Configuration configuration) {
        final Method method = methodInfo.getMethod();

        assert proxy instanceof SearchContext;
        assert method.isAnnotationPresent(ContainsClass.class);

        final Map<String, String> parameters = getParamValues(method, methodInfo.getArgs());
        final ContainsClass containsClass = method.getAnnotation(ContainsClass.class);
        final String tag = containsClass.tag();
        final String classValue = containsClass.classValue();
        final String xpath = String.format(".//%s[contains(@class, '%s')]", tag, classValue);

        final String name = Optional.ofNullable(method.getAnnotation(Name.class))
                .map(Name::value)
                .map(template -> processParamTemplate(template, parameters))
                .orElse(method.getName());

        final SearchContext searchContext = (SearchContext) proxy;

        final Configuration childConfiguration = configuration.child();
        final Target elementTarget = new LazyTarget(name, () -> searchContext.findElement(By.xpath(xpath)));
        return new Atlas(childConfiguration).create(elementTarget, method.getReturnType());
    }
}

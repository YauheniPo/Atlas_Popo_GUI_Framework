package popo.atlas.framework.devtools;

import com.github.kklisura.cdt.services.ChromeDevToolsService;
import com.github.kklisura.cdt.services.WebSocketService;
import com.github.kklisura.cdt.services.config.ChromeDevToolsServiceConfiguration;
import com.github.kklisura.cdt.services.exceptions.WebSocketServiceException;
import com.github.kklisura.cdt.services.impl.ChromeDevToolsServiceImpl;
import com.github.kklisura.cdt.services.impl.WebSocketServiceImpl;
import com.github.kklisura.cdt.services.invocation.CommandInvocationHandler;
import com.github.kklisura.cdt.services.utils.ProxyUtils;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.remote.RemoteWebDriver;
import popo.atlas.framework.base.driver.Browser;
import popo.atlas.framework.utils.configurations.BrowserProperties;

import java.lang.reflect.Method;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

//only with docker container (selenoid image)
@Log4j2
public class ChromeDevToolsClient {

    public static ChromeDevToolsService devtools;

    static {
        WebSocketService webSocketService = null;
        try {
            webSocketService = WebSocketServiceImpl.create(
                    new URI(String.format("ws://%s/devtools/%s/page",
                            BrowserProperties.getInstance().getGridServerUrl(),
                            ((RemoteWebDriver) Browser.getDriver().getWrappedDriver()).getSessionId())));
        } catch (WebSocketServiceException | URISyntaxException e) {
            log.error(e);
            e.printStackTrace();
        }
        CommandInvocationHandler commandInvocationHandler = new CommandInvocationHandler();
        Map<Method, Object> commandsCache = new ConcurrentHashMap<>();
        devtools =
                ProxyUtils.createProxyFromAbstract(
                        ChromeDevToolsServiceImpl.class,
                        new Class[]{WebSocketService.class, ChromeDevToolsServiceConfiguration.class},
                        new Object[]{webSocketService, new ChromeDevToolsServiceConfiguration()},
                        (unused, method, args) ->
                                commandsCache.computeIfAbsent(
                                        method,
                                        key -> {
                                            Class<?> returnType = method.getReturnType();
                                            return ProxyUtils.createProxy(returnType, commandInvocationHandler);
                                        }));
        commandInvocationHandler.setChromeDevToolsService(devtools);
    }
}

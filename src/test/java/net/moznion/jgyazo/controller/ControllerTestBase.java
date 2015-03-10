package net.moznion.jgyazo.controller;

import me.geso.mech2.Mech2;
import me.geso.mech2.Mech2WithBase;

import org.apache.catalina.Globals;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.startup.Tomcat;
import org.junit.AfterClass;
import org.junit.BeforeClass;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import javax.servlet.ServletException;

public abstract class ControllerTestBase {
    private static Tomcat tomcat;
    private static Mech2WithBase mech;

    @BeforeClass
    public static void beforeClass() throws ServletException, LifecycleException, URISyntaxException, IOException {
        ControllerTestBase.tomcat = new Tomcat();
        tomcat.setPort(0);
        org.apache.catalina.Context webContext = tomcat.addWebapp("/", new File("src/main/webapp").getAbsolutePath());
        webContext.getServletContext().setAttribute(Globals.ALT_DD_ATTR, "src/main/webapp/WEB-INF/web.xml");
        tomcat.start();

        int port = tomcat.getConnector().getLocalPort();
        String url = "http://127.0.0.1:" + port;
        ControllerTestBase.mech = new Mech2WithBase(Mech2.builder().build(), new URI(url));
    }

    @AfterClass
    public static void afterClass() throws ServletException, LifecycleException, URISyntaxException {
        ControllerTestBase.tomcat.stop();
        ControllerTestBase.tomcat.destroy();
    }

    public Mech2WithBase getMech() {
        return ControllerTestBase.mech;
    }

    public Tomcat getTomcat() {
        return ControllerTestBase.tomcat;
    }
}

package net.moznion.jgyazo.controller;

import me.geso.mech2.Mech2;
import me.geso.mech2.Mech2WithBase;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.webapp.WebAppContext;
import org.junit.AfterClass;
import org.junit.BeforeClass;

import java.net.URI;

public abstract class ControllerTestBase {
  private static Mech2WithBase mech;
  private static Server jetty;

  @BeforeClass
  public static void beforeClass() throws Exception {
    jetty = new Server(0);

    WebAppContext context = new WebAppContext();
    context.setResourceBase(".");
    context.setDescriptor("src/main/webapp/WEB-INF/web.xml");
    context.setContextPath("/");
    context.setParentLoaderPriority(true);
    jetty.setHandler(context);
    jetty.setStopAtShutdown(true);
    jetty.start();

    ServerConnector connector = (ServerConnector) jetty.getConnectors()[0];
    int port = connector.getLocalPort();
    String url = "http://127.0.0.1:" + port;
    ControllerTestBase.mech = new Mech2WithBase(Mech2.builder().build(), new URI(url));
  }

  @AfterClass
  public static void afterClass() throws Exception {
    jetty.stop();
  }

  public Mech2WithBase getMech() {
    return ControllerTestBase.mech;
  }

  public Server getJetty() {
    return ControllerTestBase.jetty;
  }
}

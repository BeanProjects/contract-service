package io.beandev.contracts;

import io.helidon.webserver.WebServer;
import io.helidon.webserver.http.HttpRouting;

import java.net.InetAddress;
import java.net.UnknownHostException;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) throws UnknownHostException {
        //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
        // to see how IntelliJ IDEA suggests fixing it.
        System.out.printf("Template for javaone-generator!");

        for (int i = 1; i <= 5; i++) {
            //TIP Press <shortcut actionId="Debug"/> to start debugging your code. We have set one <icon src="AllIcons.Debugger.Db_set_breakpoint"/> breakpoint
            // for you, but you can always add more by pressing <shortcut actionId="ToggleLineBreakpoint"/>.
            System.out.println("i = " + i);
        }

        WebServer webServer = WebServer.builder()
                .address(InetAddress.getLocalHost())
                .port(8080)
                .routing(Main::routing)
                .build().start();

        System.out.println("WEB server is up! http://localhost:" + webServer.port() + "/greet");
    }

    /**
     * Updates HTTP Routing and registers observe providers.
     */
    static void routing(HttpRouting.Builder routing) {
        routing.register("/greet", new GreetService());
    }
}
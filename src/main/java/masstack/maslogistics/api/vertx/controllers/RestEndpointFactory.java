package masstack.maslogistics.api.vertx.controllers;

import io.vertx.core.Vertx;
import io.vertx.core.http.HttpMethod;
import io.vertx.core.http.HttpServer;
import io.vertx.ext.web.Router;

public class RestEndpointFactory {
    private final HttpServer server;
    private final Vertx vertx;
    private final Router router;

    public RestEndpointFactory() {
        this.vertx = Vertx.vertx();
        this.router = Router.router(vertx);
        this.server = vertx.createHttpServer();

        initialiseRoutes();
    }

    public void build() {
        server.requestHandler(router::accept);
        server.listen(8080);
        System.out.println("Listening on port" + 8080);
    }

    private void initialiseRoutes() {
        router.route(HttpMethod.GET, "/packet").handler(routingContext -> {
            System.out.println("Request received");
        });
    }


}

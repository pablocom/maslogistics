package masstack.maslogistics.api.vertx.controllers;

import io.vertx.core.Vertx;
import io.vertx.core.http.HttpMethod;
import io.vertx.core.http.HttpServer;
import io.vertx.ext.web.Router;

public class RestRouter {
    private final Router router;
    private final Vertx vertx;
    private final HttpServer server;

    public RestRouter() {
        vertx = Vertx.vertx();

        this.server = vertx.createHttpServer();
        this.router = Router.router(vertx);

        initRoutes();
    }

    private void initRoutes() {
        this.router.route(HttpMethod.GET, "/packet").handler(context -> {
            System.console().printf("Request received");
        });
    }

    public void start() {
        this.server.listen(8080);
    }
}

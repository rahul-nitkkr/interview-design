package router;

public class RouteNotFoundException extends Exception {

    public RouteNotFoundException(String api) {
        super(String.format("Route not registered for api : %s" , api));
    }
}

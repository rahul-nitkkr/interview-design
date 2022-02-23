package router;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TestInMemoryRoutingService {

    @Test
    public void testInMemRouting_basic() throws RouteNotFoundException{
        RoutingService router = InMemoryRoutingService.RouteBuilder.builder()
                .withPath("/foo/bar", "foobar")
                .withPath("/foo", "foo")
                .withPath("/bar", "bar")
                .build();

        Assertions.assertEquals("foo" , router.route("/foo"));
        Assertions.assertEquals("bar" , router.route("/bar"));
        Assertions.assertEquals("foobar" , router.route("/foo/bar"));
    }

    @Test
    public void testInMemRouting_throws() throws RouteNotFoundException {
        RoutingService router = InMemoryRoutingService.RouteBuilder.builder()
                .withPath("/foo/bar", "foobar")
                .withPath("/foo", "foo")
                .withPath("/bar", "bar")
                .build();

        Assertions.assertThrows(RouteNotFoundException.class , () -> router.route("/abc"));
    }

    @Test
    public void testInMemRouting_wildcard() throws RouteNotFoundException {
        RoutingService router = InMemoryRoutingService.RouteBuilder.builder()
                .withPath("/foo/*/bar", "foobar")
                .withPath("/foo/*/bar/*", "foobarbar")
                .withPath("/foo", "foo")
                .withPath("/bar", "bar")
                .withPath("/foo/bar", "bar1")
                .withPath("/foo/*", "bar2")
                .build();

        Assertions.assertEquals("foobarbar" , router.route("/foo/bad/bar/good"));
        Assertions.assertEquals("foobar" , router.route("/foo/bad/bar"));
        //Assertions.assertEquals("bar1" , router.route("/foo/bar"));
        Assertions.assertEquals("bar2" , router.route("/foo/bad"));
        Assertions.assertEquals("bar2" , router.route("/foo/a"));
    }

    @Test
    public void testInMemRouting_pathParam() throws RouteNotFoundException {
        RoutingService router = InMemoryRoutingService.RouteBuilder.builder()
                .withPath("/foo/:id/bar/:id2/baz/:id3", "foobar")
                .withPath("/foo", "foo")
                .withPath("/bar", "bar")
                .withPath("/foo/bar", "bar1")
                .build();

        Assertions.assertEquals("foobar" , router.route("/foo/bad/bar/good/baz/best"));
        Assertions.assertEquals("foo" , router.route("/foo"));
        Assertions.assertEquals("bar" , router.route("/bar"));
    }
}

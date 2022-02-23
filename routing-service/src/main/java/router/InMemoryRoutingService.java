package router;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class InMemoryRoutingService implements RoutingService {
    private static final char[] SUPPORTED_PATTERNS = new char[]{'*'};
    private static final String PATH_PARAM_CHAR = ":";

    private final Map<Pattern, String> routes;

    public InMemoryRoutingService(Map<Pattern, String> routes) {
        this.routes = routes;
    }

    @Override
    public String route(String api) throws RouteNotFoundException {
        Pattern matchingPattern = routes.keySet().stream()
                .sorted(Comparator.comparing(p -> ((Pattern)p).pattern().length()).reversed())
                .filter(pattern -> pattern.matcher(api).matches())
                .findFirst()
                .orElseThrow(() -> new RouteNotFoundException(api));

        if(matchingPattern.matcher(api).groupCount() >= 2) {
            Matcher matcher = matchingPattern.matcher(api);
            if(matcher.matches())
                System.out.println(IntStream.range(1 , matcher.groupCount() + 1)
                .mapToObj(matcher::group)
                .collect(Collectors.toList()));
        }
        return routes.get(matchingPattern);
    }

    static class RouteBuilder {
        private final Map<Pattern, String> routes;

        public RouteBuilder() {
            this.routes = new HashMap<>();
        }

        public static RouteBuilder builder() {
            return new RouteBuilder();
        }

        public RouteBuilder withPath(String path, String function) {
            if (hasWildCardChar(path)) {
                path = path.replaceAll("\\*", "([^/].*)");
            } else if(path.contains(PATH_PARAM_CHAR)) {
                path = Arrays.stream(path.split(":[^/]*" , -1))
                        .map(Pattern::quote)
                        .collect(Collectors.joining("([^/]+)"));
            }
            routes.put(Pattern.compile(path), function);
            return this;
        }

        public InMemoryRoutingService build() {
            return new InMemoryRoutingService(routes);
        }

        private boolean hasWildCardChar(String path) {
            for (char ch : path.toCharArray()) {
                if (String.valueOf(SUPPORTED_PATTERNS).contains(String.valueOf(ch)))
                    return true;
            }
            return false;
        }
    }
}

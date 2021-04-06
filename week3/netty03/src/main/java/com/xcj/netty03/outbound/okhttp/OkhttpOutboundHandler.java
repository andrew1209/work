package com.xcj.netty03.outbound.okhttp;

import com.xcj.netty03.filter.HeaderHttpResponseFilter;
import com.xcj.netty03.filter.HttpRequestFilter;
import com.xcj.netty03.filter.HttpResponseFilter;
import com.xcj.netty03.router.HttpEndpointRouter;
import com.xcj.netty03.router.RandomHttpEndpointRouter;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.FullHttpResponse;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import org.apache.http.Header;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import static io.netty.handler.codec.http.HttpResponseStatus.OK;
import static io.netty.handler.codec.http.HttpVersion.HTTP_1_1;

public class OkhttpOutboundHandler {

    private List<String> backendUrls;
    private ExecutorService proxyService;
    private HttpEndpointRouter router = new RandomHttpEndpointRouter();
    HttpResponseFilter filter = new HeaderHttpResponseFilter();

    public OkhttpOutboundHandler(List<String> backends) {

        int cores = Runtime.getRuntime().availableProcessors();
        long keepAliveTime = 1000;
        int queueSize = 2048;
        backendUrls = backends.stream().map(this::formatUrl).collect(Collectors.toList());
        proxyService = new ThreadPoolExecutor(cores,cores,
                keepAliveTime,
                TimeUnit.MILLISECONDS,
                new ArrayBlockingQueue<>(queueSize),
                new NamedThreadFactory("proxyService"));
    }

    public void handle(final FullHttpRequest fullRequest, final ChannelHandlerContext ctx, HttpRequestFilter filter) {
        System.out.println(fullRequest.uri());
        System.out.println("------------");
        //使用路由选择调用哪个服务
        String backendUrl = router.route(this.backendUrls);
        final String url = backendUrl + fullRequest.uri();

        filter.filter(fullRequest, ctx);
        proxyService.submit(()-> {
            try {
                fetchGet(fullRequest, ctx, url);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    private void fetchGet(FullHttpRequest fullRequest, ChannelHandlerContext ctx, String url) throws IOException {


        Request request = new Request.Builder().url(url).header("auth",fullRequest.headers().get("auth")).build();

        Response res = new OkHttpClient().newCall(request).execute();
        FullHttpResponse response = null;

        byte[] body = res.body().bytes();
        response = new DefaultFullHttpResponse(HTTP_1_1, OK, Unpooled.wrappedBuffer(body));
        response.headers().set("Content-Type", "application/json");
        response.headers().setInt("Content-Length", Integer.parseInt(res.headers().get("Content-Length")));
        System.out.println("------");
        System.out.println(new String(body));
        filter.filter(response);

    }

    private String formatUrl(String backend) {
        return backend.endsWith("/") ? backend.substring(0, backend.length() - 1) : backend;
    }

}

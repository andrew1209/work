package com.xcj.netty03.outbound;

import com.xcj.netty03.filter.HeaderHttpRequestFilter;
import com.xcj.netty03.filter.HeaderHttpResponseFilter;
import com.xcj.netty03.filter.HttpRequestFilter;
import com.xcj.netty03.filter.HttpResponseFilter;
import com.xcj.netty03.router.HttpEndpointRouter;
import com.xcj.netty03.router.RandomHttpEndpointRouter;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.FullHttpRequest;

import java.util.List;
import java.util.stream.Collectors;

public class HttpOutboundHandler {

    private List<String> backendUrls;

    private HttpRequestFilter httpRequestFilter = new HeaderHttpRequestFilter();
    private HttpResponseFilter httpResponseFilter = new HeaderHttpResponseFilter();
    private HttpEndpointRouter router = new RandomHttpEndpointRouter();

    public HttpOutboundHandler(List<String> backends) {

        this.backendUrls = backends.stream().map(this::formatUrl).collect(Collectors.toList());

    }

    private String formatUrl(String backend) {
        return backend.endsWith("/")?backend.substring(0,backend.length()-1):backend;
    }


    public void handle(final FullHttpRequest fullRequest, final ChannelHandlerContext ctx, HttpRequestFilter filter) {
        System.out.println(fullRequest.uri());
        //使用路由选择调用哪个服务
        String backendUrl = router.route(this.backendUrls);
        final String url = backendUrl + fullRequest.uri();
        filter.filter(fullRequest, ctx);
        //proxyService.submit(()->fetchGet(fullRequest, ctx, url));
    }

}

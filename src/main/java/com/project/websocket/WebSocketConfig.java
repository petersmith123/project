package com.project.websocket;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;
import org.springframework.web.socket.server.HandshakeInterceptor;

/****
 * 注册
 */
@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(myhandler(), "/socketServer").addInterceptors(myInterceptors()).setAllowedOrigins("*");
        registry.addHandler(myhandler(), "/sockjs/websocket").addInterceptors(myInterceptors()).withSockJS();
    }

    @Bean
    public WebSocketHandler myhandler() {
        return new MyMessageHandler();
    }

    @Bean
    public HandshakeInterceptor myInterceptors() {
        return new WebSocketInterceptor();
    }
}

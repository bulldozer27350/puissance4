package com.moe.puissance4Server.internal.websocket;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfiguration implements WebSocketMessageBrokerConfigurer {

    @Override
    public void configureMessageBroker(MessageBrokerRegistry config) {
    	// permet de créer un topic afin d'informer tous les utilisateurs abonnés à ce topic
    	// ici, le topic serait l'identifiant de la partie par exemple.
        config.enableSimpleBroker("/topic");
        config.setApplicationDestinationPrefixes("/app");
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
    	// Simple (or Streaming) Text Oriented Messaging Protocol
    	// L'ajout de "withSockJS()" permet de rester compatibe avec différents types de navigateurs, 
    	// même ceux qui ne seraient pas compatibles avec les websockets
        registry.addEndpoint("/websocket").withSockJS();
    }
}

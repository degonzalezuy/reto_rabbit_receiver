package com.sofka.broker.adapter;

import com.sofka.broker.model.Message;
import org.reactivecommons.async.api.HandlerRegistry;
import org.reactivecommons.async.impl.config.annotations.EnableCommandListeners;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.reactivecommons.async.api.HandlerRegistry.register;

@Configuration
@EnableCommandListeners
public class UserAsyncAdapter {
    private List<Message> messages = new ArrayList<>();

    @Bean
    public HandlerRegistry commandMasiveMessageSubscription(){
        return register().handleCommand("message.all",

                message -> {
                    messages.add(message.getData());
                    return Mono.empty();
                }, Message.class);
    }

    @Bean
    public HandlerRegistry queryMessageByFloorSubscription(){
        return register().handleCommand("message.forfloor",
                message -> {
            messages.add(message.getData());
                return Mono.empty();
             },Message.class);

    }
}

package org.jnosql.demo.endgame.jakarta;

import jakarta.nosql.mapping.keyvalue.KeyValueTemplate;

import javax.enterprise.inject.se.SeContainer;
import javax.enterprise.inject.se.SeContainerInitializer;
import java.time.Duration;
import java.util.Collections;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

public class KeyValueApp {

    public static void main(String[] args) throws InterruptedException {

        try (SeContainer container = SeContainerInitializer
                .newInstance().initialize()) {

            God hunter = new God(1L, "Ullr", "Hunting");

            KeyValueTemplate template =
                    container.select(KeyValueTemplate.class)
                            .get();

            template.put(hunter);

            final Optional<God> god = template.get(1L, God.class);
            System.out.println("query : " + god);
            template.delete(1L);

            System.out.println("query again: " +
                    template.get(1L, God.class));

        }

        System.exit(0);

    }
}

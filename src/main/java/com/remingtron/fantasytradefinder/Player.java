package com.remingtron.fantasytradefinder;

import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

@RedisHash("playerhashname")
public class Player {
    @Id String id;
    String firstName;
    String lastName;
    int tradeValue;
}

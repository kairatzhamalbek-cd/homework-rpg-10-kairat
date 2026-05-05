package com.narxoz.rpg.guild;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class GuildHall implements GuildMediator {

    private final Map<String, List<GuildMember>> membersByTopic = new HashMap<>();

    @Override
    public void register(GuildMember member) {
        if (member instanceof Captain) {
            addSubscriber("orders", member);
            addSubscriber("urgent", member);
        } else if (member instanceof Quartermaster) {
            addSubscriber("orders", member);
            addSubscriber("supplies", member);
            addSubscriber("rewards", member);
        } else if (member instanceof Scout) {
            addSubscriber("scouting", member);
            addSubscriber("urgent", member);
            addSubscriber("orders", member);
        } else if (member instanceof Healer) {
            addSubscriber("healing", member);
            addSubscriber("urgent", member);
            addSubscriber("orders", member);
        }
        else if (member instanceof Loremaster) {
            addSubscriber("lore", member);
            addSubscriber("scouting", member);
            addSubscriber("urgent", member);
        }
    }

    @Override
    public void dispatch(String topic, GuildMember from, String payload) {
        List<GuildMember> subscribers = subscribersFor(topic);

        System.out.println("[GuildHall] " + from.getName() +
                " -> topic: " + topic + " | " + payload);

        for (GuildMember member : subscribers) {
            if (member != from) {
                member.receive(topic, from, payload);
            }
        }
    }

    protected void addSubscriber(String topic, GuildMember member) {
        membersByTopic.computeIfAbsent(topic, key -> new ArrayList<>()).add(member);
    }

    protected List<GuildMember> subscribersFor(String topic) {
        return membersByTopic.getOrDefault(topic, List.of());
    }
}

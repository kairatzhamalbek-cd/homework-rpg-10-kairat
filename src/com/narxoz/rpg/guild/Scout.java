package com.narxoz.rpg.guild;


public class Scout extends GuildMember {

    public Scout(String name, GuildMediator mediator) {
        super(name, mediator);
    }

    public void reportRoute(String topic, String payload) {
        getMediator().dispatch(topic, this, payload);
    }

    @Override
    public void receive(String topic, GuildMember from, String payload) {
        System.out.println("[Scout " + getName() + "] received from "
                + from.getName() + " | topic: " + topic + " | " + payload);

        if (topic.equals("orders")) {
            System.out.println("[Scout " + getName() + "] preparing reconnaissance.");
        } else if (topic.equals("urgent")) {
            System.out.println("[Scout " + getName() + "] scouting immediately!");
        }    }
}

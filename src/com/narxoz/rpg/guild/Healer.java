package com.narxoz.rpg.guild;

public class Healer extends GuildMember {

    public Healer(String name, GuildMediator mediator) {
        super(name, mediator);
    }

    public void prepareAid(String topic, String payload) {
        getMediator().dispatch(topic, this, payload);
    }

    @Override
    public void receive(String topic, GuildMember from, String payload) {
        System.out.println("[Healer " + getName() + "] received from "
                + from.getName() + " | topic: " + topic + " | " + payload);

        if (topic.equals("orders")) {
            System.out.println("[Healer " + getName() + "] preparing medical support.");
        } else if (topic.equals("urgent")) {
            System.out.println("[Healer " + getName() + "] ready for emergency healing!");
        }    }
}

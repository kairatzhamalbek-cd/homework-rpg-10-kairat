package com.narxoz.rpg.guild;

public class Quartermaster extends GuildMember {

    public Quartermaster(String name, GuildMediator mediator) {
        super(name, mediator);
    }

    public void requestSupplies(String topic, String payload) {
        getMediator().dispatch(topic, this, payload);
    }

    @Override
    public void receive(String topic, GuildMember from, String payload) {
        System.out.println("[Quartermaster " + getName() + "] received from "
                + from.getName() + " | topic: " + topic + " | " + payload);

        if (topic.equals("orders")) {
            System.out.println("[Quartermaster " + getName() + "] preparing supplies.");
        } else if (topic.equals("urgent")) {
            System.out.println("[Quartermaster " + getName() + "] rushing equipment!");
        } else if (topic.equals("rewards")) {
            System.out.println("[Quartermaster " + getName() + "] calculating rewards.");
        }    }
}

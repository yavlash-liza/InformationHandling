package com.company.yavlash.entity;

import java.util.List;

public interface TextComponent {

    void add(TextComponent component);

    void remove(TextComponent component);

    ComponentType getType();

    List<TextComponent> getChildren();

    String toString();
}
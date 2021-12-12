package com.company.yavlash.entity;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class TextComposite implements TextComponent {
    private List<TextComponent> textComponents = new ArrayList<>();
    private ComponentType componentType;

    public TextComposite() {}

    public TextComposite(ComponentType componentType) {
        this.componentType = componentType;
    }

    public List<TextComponent> getTextComponents() {
        return textComponents;
    }

    public void setTextComponents(List<TextComponent> textComponents) {
        this.textComponents = textComponents;
    }

    public ComponentType getComponentType() {
        return componentType;
    }

    public void setComponentType(ComponentType componentType) {
        this.componentType = componentType;
    }

    @Override
    public void add(TextComponent textComponent) {
        textComponents.add(textComponent);
    }

    @Override
    public void remove(TextComponent textComponent) {
        textComponents.remove(textComponent);
    }

    @Override
    public ComponentType getType() {
        return componentType;
    }

    @Override
    public List<TextComponent> getChildren() {
        return List.copyOf(textComponents);
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {return true;}
        if (object == null || getClass() != object.getClass()) {return false;}
        TextComposite aThat = (TextComposite) object;
        if(getTextComponents() == null) {
            if(aThat.getTextComponents() != null){return false;}
        } else if(!getTextComponents().equals(aThat.getTextComponents())){return false;}
        if(getComponentType() == null) {
            return aThat.getComponentType() == null;
        } else return getComponentType().equals(aThat.getComponentType());
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (getComponentType() == null ? 0 : getComponentType().hashCode());
        result = prime * result + (getTextComponents() == null ? 0 : getTextComponents().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        String delimiter = componentType.getDelimiter();
        if (componentType.equals(ComponentType.PARAGRAPH)) {
            sb.append(delimiter);
        }
        for (TextComponent textComponent : textComponents) {
            sb.append(textComponent.toString());
        }
        if (!componentType.equals(ComponentType.PARAGRAPH)) {
            sb.append(delimiter);
        }
        return sb.toString();
    }

    public static class SentenceAmountComparator implements Comparator<TextComponent> {

        @Override
        public int compare(TextComponent textComponent1, TextComponent textComponent2) {
            return Integer.compare(textComponent1.getChildren().size(), textComponent2.getChildren().size());
        }
    }
}
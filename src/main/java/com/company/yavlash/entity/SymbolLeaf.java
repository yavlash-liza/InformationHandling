package com.company.yavlash.entity;

import java.util.List;

public class SymbolLeaf implements TextComponent {
    private ComponentType componentType;
    private char symbol;

    public SymbolLeaf() {

    }

    public SymbolLeaf(ComponentType componentType, char symbol) {
        this.symbol = symbol;
        this.componentType = componentType;
    }

    public ComponentType getComponentType() {
        return componentType;
    }

    public void setComponentType(ComponentType componentType) {
        this.componentType = componentType;
    }

    public char getSymbol() {
        return symbol;
    }

    public void setSymbol(char symbol) {
        this.symbol = symbol;
    }

    @Override
    public void add(TextComponent textComponent) {
        throw new UnsupportedOperationException("Unsupported operation \"add\" on symbol");
    }

    @Override
    public void remove(TextComponent textComponent) {
        throw new UnsupportedOperationException("Unsupported operation \"remove\" on symbol");
    }

    @Override
    public ComponentType getType() {
        return componentType;
    }

    @Override
    public List<TextComponent> getChildren() {
        throw new UnsupportedOperationException("Unsupported operation of getting children on symbol");
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {return true;}
        if (object == null || getClass() != object.getClass()) {return false;}
        SymbolLeaf aThat = (SymbolLeaf) object;
        if(getSymbol() != aThat.getSymbol()){return false;}
        if(getComponentType() == null) {
            return aThat.getComponentType() == null;
        } else return getComponentType().equals(aThat.getComponentType());
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (getComponentType() == null ? 0 : getComponentType().hashCode());
        result = prime * result + Character.hashCode(symbol);
        return result;
    }

    @Override
    public String toString() {
        return Character.toString(symbol);
    }
}
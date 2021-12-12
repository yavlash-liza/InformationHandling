package com.company.yavlash.parser.impl;

import com.company.yavlash.entity.ComponentType;
import com.company.yavlash.entity.TextComponent;
import com.company.yavlash.entity.TextComposite;
import com.company.yavlash.parser.TextParser;

public class ExpressionParser implements TextParser {
    private final TextParser letterParser = new LetterParser();

    @Override
    public TextComposite parse(String expressionValue) {
        TextComposite expressionComposite = new TextComposite(ComponentType.EXPRESSION);
        TextComponent expressionComponent = letterParser.parse(expressionValue);
        expressionComposite.add(expressionComponent);
        return expressionComposite;
    }
}
package com.company.yavlash.parser.impl;

import com.company.yavlash.entity.ComponentType;
import com.company.yavlash.entity.TextComponent;
import com.company.yavlash.entity.TextComposite;
import com.company.yavlash.parser.TextParser;

public class WordParser implements TextParser {
    private final TextParser letterParser = new LetterParser();

    @Override
    public TextComposite parse(String lexemeValue) {
        TextComposite wordComposite = new TextComposite(ComponentType.WORD);
        TextComponent wordComponent = letterParser.parse(lexemeValue);
        wordComposite.add(wordComponent);
        return wordComposite;
    }
}
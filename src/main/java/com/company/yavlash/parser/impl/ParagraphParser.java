package com.company.yavlash.parser.impl;

import com.company.yavlash.entity.ComponentType;
import com.company.yavlash.entity.TextComponent;
import com.company.yavlash.entity.TextComposite;
import com.company.yavlash.parser.TextParser;

public class ParagraphParser implements TextParser {
    private static final String PARAGRAPH_DELIMITER_REGEX = "\\r\\n";
    private final TextParser sentenceParser = new SentenceParser();

    @Override
    public TextComposite parse(String textValue) {
        TextComposite textComposite = new TextComposite(ComponentType.TEXT);
        String[] paragraphs = textValue.split(PARAGRAPH_DELIMITER_REGEX);
        for (String paragraph : paragraphs) {
            TextComponent paragraphComponent = sentenceParser.parse(paragraph);
            textComposite.add(paragraphComponent);
        }
        return textComposite;
    }
}
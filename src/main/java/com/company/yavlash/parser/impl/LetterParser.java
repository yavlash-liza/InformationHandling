package com.company.yavlash.parser.impl;

import com.company.yavlash.entity.ComponentType;
import com.company.yavlash.entity.SymbolLeaf;
import com.company.yavlash.entity.TextComponent;
import com.company.yavlash.entity.TextComposite;
import com.company.yavlash.parser.TextParser;

public class LetterParser implements TextParser {
    private static final String LETTER_DELIMITER_REGEX = "";

    @Override
    public TextComposite parse(String textValue) {
        TextComposite letterComposite = new TextComposite(ComponentType.LETTER);
        String[] symbols = textValue.split(LETTER_DELIMITER_REGEX);
        for (String symbol : symbols) {
            TextComponent letterComponent =
                    new SymbolLeaf(Character.isLetter(symbol.charAt(0)) ? ComponentType.LETTER : ComponentType.SYMBOL, symbol.charAt(0));
            letterComposite.add(letterComponent);
        }
        return letterComposite;
    }
}
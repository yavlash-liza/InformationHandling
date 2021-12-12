package com.company.yavlash.parser.impl;

import com.company.yavlash.entity.ComponentType;
import com.company.yavlash.entity.SymbolLeaf;
import com.company.yavlash.entity.TextComponent;
import com.company.yavlash.entity.TextComposite;
import com.company.yavlash.parser.TextParser;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LexemeParser implements TextParser {
        private static final String LEXEME_DELIMITER_REGEX = "\\S+";
        private static final String WORD_DELIMITER_REGEX = "[А-я\\w]+";
        private final TextParser wordParser = new WordParser();
        private final TextParser expressionParser = new ExpressionParser();

        @Override
        public TextComposite parse(String sentenceValue) {
            TextComposite sentenceComposite = new TextComposite(ComponentType.SENTENCE);
            Pattern lexemePattern = Pattern.compile(LEXEME_DELIMITER_REGEX);
            Matcher lexemes = lexemePattern.matcher(sentenceValue);
            while (lexemes.find()) {
                TextComposite lexemeComponent = new TextComposite(ComponentType.LEXEME);
                String lexeme = lexemes.group();
                if (lexeme.matches(WORD_DELIMITER_REGEX)) {
                    TextComponent wordComponent = wordParser.parse(lexeme);
                    lexemeComponent.add(wordComponent);
                } else {
                    String possibleWord = lexeme.substring(0, lexeme.length() - 1);
                    if (possibleWord.matches(WORD_DELIMITER_REGEX)) {
                        TextComponent wordComponent = wordParser.parse(possibleWord);
                        lexemeComponent.add(wordComponent);
                        lexemeComponent.add(new SymbolLeaf(ComponentType.SYMBOL, lexeme.charAt(possibleWord.length())));
                    } else {
                        TextComponent expressionComponent = expressionParser.parse(lexeme);
                        lexemeComponent.add(expressionComponent);
                    }
                }
                sentenceComposite.add(lexemeComponent);
            }
            return sentenceComposite;
        }
}
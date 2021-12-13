package com.company.yavlash.service.impl;

import com.company.yavlash.entity.ComponentType;
import com.company.yavlash.entity.TextComponent;
import com.company.yavlash.entity.TextComposite;
import com.company.yavlash.service.TextService;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class TextServiceImpl implements TextService {
    private static final String VOWELS = "[AEIOUaeiou]";
    private static final String CONSONANTS = "[[^AEIOUaeiou]&&A-Za-z]";

    @Override
    public List<TextComponent> sortParagraphsBySentences(TextComposite textComposite) {
        return textComposite.getChildren().stream()
                .sorted(new TextComposite.SentenceAmountComparator())
                .toList();
    }

    @Override
    public List<TextComponent> findSentencesWithLongestWord(TextComposite textComposite) {
        int maxLength = findLongestWordLength(textComposite);
        return textComposite.getChildren().stream()
                .flatMap(paragraph -> paragraph.getChildren().stream())
                .flatMap(sentence -> sentence.getChildren().stream())
                .filter(lexeme -> lexeme.getChildren().stream()
                        .anyMatch(word -> word.getType().equals(ComponentType.WORD) && word.toString().length() == maxLength))
                .toList();
    }

    public int findLongestWordLength(TextComposite textComposite) {
        TextComponent textComponent = textComposite.getChildren().stream()
                .flatMap(paragraph -> paragraph.getChildren().stream())
                .flatMap(sentence -> sentence.getChildren().stream())
                .flatMap(lexeme -> lexeme.getChildren().stream())
                .filter(word -> word.getType().equals(ComponentType.WORD))
                .max(Comparator.comparingInt(word -> word.toString().length()))
                .get();
        return textComponent.toString().length();
    }

    @Override
    public List<TextComponent> deleteSentences(TextComposite textComposite, int minWordAmount) {
        return textComposite.getChildren().stream()
                .flatMap(paragraph -> paragraph.getChildren().stream())
                .filter(sentence -> sentence.getChildren().stream()
                        .flatMap(lexeme -> lexeme.getChildren().stream())
                        .filter(word -> word.getType().equals(ComponentType.WORD)).count() >= minWordAmount).toList();
    }

    @Override
    public Map<String, Long> countEqualWords(TextComposite textComposite) {
        Map<String, Long> similarWords = textComposite.getChildren().stream()
                .flatMap(paragraph -> paragraph.getChildren().stream())
                .flatMap(sentence -> sentence.getChildren().stream())
                .flatMap(lexeme -> lexeme.getChildren().stream())
                .filter(word -> word.getType().equals(ComponentType.WORD))
                .collect(Collectors.groupingBy(word -> word.toString().toLowerCase(), Collectors.counting()));
        similarWords.entrySet().removeIf(word -> word.getValue() == 1);
        return similarWords;
    }

    @Override
    public long countConsonants(TextComponent sentenceComponent) {
        return sentenceComponent.getChildren().stream()
                .flatMap(lexeme -> lexeme.getChildren().stream())
                .filter(word -> word.getType().equals(ComponentType.WORD))
                .flatMap(word -> word.getChildren().stream())
                .flatMap(letter -> letter.getChildren().stream())
                .filter(letter -> letter.toString().matches(CONSONANTS))
                .count();
    }

    @Override
    public long countVowels(TextComponent sentenceComponent) {
        return sentenceComponent.getChildren().stream()
                .flatMap(lexeme -> lexeme.getChildren().stream())
                .filter(word -> word.getType().equals(ComponentType.WORD))
                .flatMap(word -> word.getChildren().stream())
                .flatMap(letter -> letter.getChildren().stream())
                .filter(letter -> letter.toString().matches(VOWELS))
                .count();
    }
}
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
                .flatMap(p -> p.getChildren().stream())
                .flatMap(s -> s.getChildren().stream())
                .filter(l -> l.getChildren().stream()
                        .anyMatch(w -> w.getType().equals(ComponentType.WORD) && w.toString().length() == maxLength))
                .toList();
    }

    public int findLongestWordLength(TextComposite textComposite) {
        TextComponent textComponent = textComposite.getChildren().stream()
                .flatMap(p -> p.getChildren().stream())
                .flatMap(s -> s.getChildren().stream())
                .flatMap(l -> l.getChildren().stream())
                .filter(w -> w.getType().equals(ComponentType.WORD))
                .max(Comparator.comparingInt(w -> w.toString().length()))
                .get();
        return textComponent.toString().length();
    }

    @Override
    public List<TextComponent> deleteSentences(TextComposite textComposite, int minWordAmount) {
        return textComposite.getChildren().stream()
                .flatMap(p -> p.getChildren().stream())
                .filter(s -> s.getChildren().stream()
                        .flatMap(l -> l.getChildren().stream())
                        .filter(w -> w.getType().equals(ComponentType.WORD)).count() >= minWordAmount).toList();
    }

    @Override
    public Map<String, Long> countEqualWords(TextComposite textComposite) {
        Map<String, Long> similarWords = textComposite.getChildren().stream()
                .flatMap(p -> p.getChildren().stream())
                .flatMap(s -> s.getChildren().stream())
                .flatMap(l -> l.getChildren().stream())
                .filter(w -> w.getType().equals(ComponentType.WORD))
                .collect(Collectors.groupingBy(w -> w.toString().toLowerCase(), Collectors.counting()));
        similarWords.entrySet().removeIf(w -> w.getValue() == 1);
        return similarWords;
    }

    @Override
    public long countConsonants(TextComponent sentenceComponent) {
        return sentenceComponent.getChildren().stream()
                .flatMap(l -> l.getChildren().stream())
                .filter(w -> w.getType().equals(ComponentType.WORD))
                .flatMap(w -> w.getChildren().stream())
                .flatMap(l -> l.getChildren().stream())
                .filter(l -> l.toString().matches(CONSONANTS))
                .count();
    }

    @Override
    public long countVowels(TextComponent sentenceComponent) {
        return sentenceComponent.getChildren().stream()
                .flatMap(l -> l.getChildren().stream())
                .filter(w -> w.getType().equals(ComponentType.WORD))
                .flatMap(w -> w.getChildren().stream())
                .flatMap(l -> l.getChildren().stream())
                .filter(l -> l.toString().matches(VOWELS))
                .count();
    }
}
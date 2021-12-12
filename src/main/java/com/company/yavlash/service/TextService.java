package com.company.yavlash.service;

import com.company.yavlash.entity.TextComponent;
import com.company.yavlash.entity.TextComposite;

import java.util.List;
import java.util.Map;

public interface TextService {
    List<TextComponent> sortParagraphsBySentences(TextComposite textComposite);
    List<TextComponent> findSentencesWithLongestWord(TextComposite textComposite);
    List<TextComponent> deleteSentences(TextComposite textComposite, int minWordAmount);
    Map<String, Long> countEqualWords(TextComposite textComposite);
    long countConsonants(TextComponent sentenceComponent);
    long countVowels(TextComponent sentenceComponent);
}
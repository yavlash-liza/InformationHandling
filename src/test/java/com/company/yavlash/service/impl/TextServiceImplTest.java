package com.company.yavlash.service.impl;

import com.company.yavlash.entity.TextComponent;
import com.company.yavlash.entity.TextComposite;
import com.company.yavlash.exception.TextException;
import com.company.yavlash.parser.TextParser;
import com.company.yavlash.parser.impl.ParagraphParser;
import com.company.yavlash.reader.TextReader;
import com.company.yavlash.service.TextService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class TextServiceImplTest {
    private TextService textService;
    private TextComposite textComposite;
    private TextComponent sentences;
    private TextReader textReader;
    private TextParser textParser;
    private String filePath;
    private List<TextComponent> textComponents;

    @Before
    public void init() throws TextException {
        textService = new TextServiceImpl();
        textReader = new TextReader();
        textParser = new ParagraphParser();

        filePath = "testdata.txt";
        String text = textReader.readText(filePath);
        textComposite = textParser.parse(text);

        textComponents = textComposite.getChildren().stream()
                .flatMap(p -> p.getChildren().stream())
                .toList();
    }

    @Test
    public void sortParagraphsBySentencesTest_RelevantData() {
        //given
        int expected = 6;

        //when
        List<TextComponent> sortedSentences = textService.sortParagraphsBySentences(textComposite);
        int actual = sortedSentences.get(0).getChildren().size();

        //then
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void findSentencesWithLongestWordTest_RelevantData() {
        //given
        int expected = 1;

        //when
        List<TextComponent> sentences = textService.findSentencesWithLongestWord(textComposite);
        int actual = sentences.size();

        //then
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void deleteSentencesTest_RelevantData() {
        //given
        int expected = 5;

        //when
        List<TextComponent> sentences = textService.deleteSentences(textComposite, 6);
        int actual = sentences.size();

        //then
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void countEqualWordsTest_RelevantData() {
        //given
        int expected = 25;

        //when
        int actual = textService.countEqualWords(textComposite).size();

        //then
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void countConsonantsTest_RelevantData() {
        //given
        long expected = 105;

        //when
        long actual = textService.countConsonants(textComponents.get(1));

        //then
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void countVowelsTest_RelevantData() {
        //given
        long expected = 65;

        //when
        long actual = textService.countVowels(textComponents.get(1));

        //then
        Assert.assertEquals(expected, actual);
    }
}
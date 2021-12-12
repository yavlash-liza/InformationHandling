package com.company.yavlash.reader;

import com.company.yavlash.exception.TextException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class TextReaderTest {
    private TextReader textReader;
    private String filePath;

    @Before
    public void init() {
        textReader = new TextReader();
        filePath = "testdata.txt";
    }

    @Test
    public void readTextTest_RelevantData() throws TextException {
        //given && when
        String text = textReader.readText(filePath);

        //then
        Assert.assertFalse(text.isEmpty());
    }

    @Test(expected = TextException.class)
    public void readTextTest_EmptyData() throws TextException {
        //given && when && then
        textReader.readText("");
    }
}
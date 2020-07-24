package com.quiz.utilities;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class TextFieldUtils {

    public static List<String> parseProbableAnswers(String text){
        List<String> decoy = new ArrayList<>();
        String [] pairs = text.split(",");
        return Arrays.stream(pairs)
                .map(s -> {
                    s = s.replaceAll("\\s+", "");
                    return s;
                }).collect(Collectors.toList());
    }
}

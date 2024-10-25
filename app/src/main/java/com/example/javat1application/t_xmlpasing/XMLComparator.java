package com.example.javat1application.t_xmlpasing;

import org.xmlunit.builder.DiffBuilder;
import org.xmlunit.builder.Input;
import org.xmlunit.diff.Diff;

import java.io.File;

public class XMLComparator {
    public static void main(String[] args) {
        // 비교할 XML 파일 경로
        String xmlFilePath1 = "path/to/first.xml";
        String xmlFilePath2 = "path/to/second.xml";

        // XML 파일 비교
        compareXMLFiles(xmlFilePath1, xmlFilePath2);
    }

    private static void compareXMLFiles(String filePath1, String filePath2) {
        // XML 파일을 입력으로 읽어들임
        Diff diff = DiffBuilder.compare(Input.fromFile(new File(filePath1)))
                .withTest(Input.fromFile(new File(filePath2)))
                .ignoreWhitespace() // 공백 무시
                .checkForSimilar() // 유사성 체크
                .build();

        // 결과 출력
        if (diff.hasDifferences()) {
            System.out.println("차이가 있습니다:");
            diff.getDifferences().forEach(difference ->
                    System.out.println(difference));
        } else {
            System.out.println("차이가 없습니다.");
        }
    }

}

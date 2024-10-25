package com.example.javat1application.t_xmlpasing;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.javat1application.R;

public class XmlCompareActivity extends AppCompatActivity {
    String xml1 = "<root><element>value1</element></root>";
    String xml2 = "<root><element>value2</element></root>";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_xml_compare);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

//        Diff diff = DiffBuilder.compare(Input.fromString(xml1))
//                .withTest(Input.fromString(xml2))
//                .ignoreWhitespace()
//                .checkForSimilar()
//                .build();
//
//        if (diff.hasDifferences()) {
//            System.out.println("차이가 있습니다.");
//        } else {
//            System.out.println("차이가 없습니다.");
//        }
    }
}
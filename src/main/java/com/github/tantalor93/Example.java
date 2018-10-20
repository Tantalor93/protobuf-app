package com.github.tantalor93;

import com.google.protobuf.CodedOutputStream;

import java.io.FileInputStream;
import java.io.FileOutputStream;

import static com.github.tantalor93.PersonOuterClass.*;

public class Example {

    public static final String RESULT_FILE = "result-file";

    public static void main(String[] args) throws Exception {
        final Person person = Person.newBuilder()
                .setName("Petr")
                .setId(1)
                .setEmail("Obenky@gmail.com")
                .build();

        try(FileOutputStream fileOutputStream = new FileOutputStream(RESULT_FILE)) {
            CodedOutputStream output = CodedOutputStream.newInstance(fileOutputStream);
            person.writeTo(output);
            output.flush();
        }

        try(FileInputStream fileInputStream = new FileInputStream(RESULT_FILE)) {
            Person parsedPerson = Person.parseFrom(fileInputStream);
            System.out.println(parsedPerson);
        }
    }
}

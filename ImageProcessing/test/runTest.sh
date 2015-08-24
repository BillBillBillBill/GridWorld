#!/bin/bash
echo 'compiling...'
cd test
javac -classpath .:../lib/junit-4.9.jar:../dist/ImageReaderRunner.jar ImageReaderTest.java
echo 'compile success...'
echo 'Running Test...'
java -classpath .:../lib/junit-4.9.jar:../dist/ImageReaderRunner.jar -ea org.junit.runner.JUnitCore  ImageReaderTest
echo 'Test Complete...'

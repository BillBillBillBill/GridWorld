#!/bin/bash
echo 'compiling...'
cd test
javac -classpath .:junit-4.9.jar:../lib/gridworld.jar:JumperRunner.jar  JumperTest.java
echo 'compile success...'
echo 'Running Test...'
java -classpath .:junit-4.9.jar:../lib/gridworld.jar:JumperRunner.jar -ea org.junit.runner.JUnitCore  JumperTest
echo 'Test Complete...'

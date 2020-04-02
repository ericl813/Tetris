JFLAGS = -g
JC = javac
JVM= java
FILE=
.SUFFIXES: .java .class
.java.class:
    $(JC) $(JFLAGS) $*.java
CLASSES = \
    Main.java \
    Class1.java \
    Class2.java \
    Class3.java \
    Class4.java

MAIN = Main

default: classes

classes: $(CLASSES:.java=.class)

run: $(MAIN).class
    $(JVM) $(MAIN)

clean:
    $(RM) *.class

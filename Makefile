JC = javac
JVM= java
FILE=
.SUFFIXES: .java .class
.java.class:
	$(JC) $*.java

CLASSES = \
    Main.java \
	Configuration.java \
	Figure.java \
	SquareBoard.java \
	Game.java

default: classes

classes: $(CLASSES:.java=.class)

clean:
	$(RM) *.class

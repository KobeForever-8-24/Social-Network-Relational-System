run: compileAll
	java Main

test: TestDatabase.class TestRelationshipDatabase.class
	java -jar junit5.jar --class-path . --scan-classpath

compileAll: Person.java GraphADT.java CS400Graph.java Database.java RelationshipDatabaseInterface.java RelationshipDatabase.java Main.java TestDatabase.java TestRelationshipDatabase.java
	javac Person.java
	javac GraphADT.java
	javac CS400Graph.java
	javac Database.java
	javac RelationshipDatabaseInterface.java
	javac RelationshipDatabase.java
	javac Main.java
	javac -cp .:junit5.jar TestDatabase.java
	javac -cp .:junit5.jar TestRelationshipDatabase.java

Main.class: Person.class RelationshipDatabaseInterface.class RelationshipDatabase.class Main.java
	javac Main.java

Person.class: Person.java
	javac Person.java

RelationshipDatabaseInterface.class: RelationshipDatabaseInterface.java
	javac RelationshipDatabaseInterface.java

RelationshipDatabase.class: RelationshipDatabaseInterface.class Database.class RelationshipDatabase.java
	javac RelationshipDatabase.java

Database.class: Person.class network.txt GraphADT.class CS400Graph.class Database.java
	javac Database.java

GraphADT.class: GraphADT.java
	javac GraphADT.java

CS400Graph.class: GraphADT.class CS400Graph.java
	javac CS400Graph.java

TestDatabase.class: Database.class TestDatabase.java junit5.jar
	javac -cp .:junit5.jar TestDatabase.java

TestRelationshipDatabase.class: RelationshipDatabaseInterface.class RelationshipDatabase.class TestRelationshipDatabase.java junit5.jar
	javac -cp .:junit5.jar TestRelationshipDatabase.java

network.txt:
	touch network.txt

clean:
	$(RM) *.class
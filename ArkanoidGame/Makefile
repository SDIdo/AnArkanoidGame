#idonata
#305727802

compile: bin
	find src -name "*.java" > sources.txt
	javac -cp biuoop-1.4.jar:. -d bin @sources.txt

run: 
	java -cp biuoop-1.4.jar:bin Ass5Game 1 2 3 4

bin:
	mkdir bin

jar:
	jar cfm ass5game.jar Manifest.mf -C bin/ .
	
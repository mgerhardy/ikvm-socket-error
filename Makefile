all:
	ikvmc -target:exe proof-of-concept-send.jar
	ikvmc -target:exe proof-of-concept-recv.jar

send:
	./proof-of-concept-send.exe

recv:
	./proof-of-concept-recv.exe

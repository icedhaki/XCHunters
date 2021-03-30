run: Submitter.class
	java Submitter

Submitter.class: Submitter.java
	javac Submitter.java

clean:
	$(RM) *.class *.ctxt

# Initial target servlet front end
quizschedule : quizschedule.class
quizschedule.class : quizschedule.java servletUtils.class retakesReader.class retakeBean.class retakes.class quizReader.class quizzes.class quizBean.class apptsReader.class apptBean.class courseReader.class courseBean.class
	javac quizretakes/quizschedule.java 

servletUtils.class : servletUtils.java
	javac quizretakes/servletUtils.java

quizReader.class : quizReader.java quizzes.class quizBean.java
	javac quizretakes/quizReader.java

retakesReader.class : retakesReader.java retakes.class retakeBean.java
	javac quizretakes/retakesReader.java

quizzes.class : quizzes.java quizBean.class
	javac quizretakes/quizzes.java

quizBean.class : quizBean.java
	javac quizretakes/quizBean.java

retakes.class : retakes.java retakeBean.class
	javac quizretakes/retakes.java

retakeBean.class : retakeBean.java
	javac quizretakes/retakeBean.java

apptsReader.class : apptsReader.java apptBean.class
	javac quizretakes/apptsReader.java

apptBean.class : apptBean.java
	javac quizretakes/apptBean.java

courseReader.class : courseReader.java courseBean.class
	javac quizretakes/courseReader.java

courseBean.class : courseBean.java
	javac quizretakes/courseBean.java

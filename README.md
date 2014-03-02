NOTICE 
=========

 **This project was done with little knowledge on Scala and Lift! Some parts of the code should not be taken as best practice  examples or examples at all. Refactoring  is planned.**
 
 Thank you for reading!  

 [![Build Status](http://www.commitlog.org:8080/buildStatus/icon?job=Spirit News)](http://www.commitlog.org:8080/job/Spirit%20News/)

##What is Spirit?
 Spirit-News is a simple blogging system for the Faculty of Computer Science of the University of Applied Sciences Schmalkalden!

First lines of code were produced by students during a course on functional
programming by Professor Braun.  Professor Braun is always seeking for students
to improve code and functionality in this open source project!

##Features
* Auth against internal university LDAP Server
* Using Lift 2.5, Scala 2.10.2 and Java 7 and MongoDB
* News will be directly posted on Twitter and Facebook
* Students can be notified via mailinglists
* lots more.....

In the future it is planned to create a plattform for students, where they can
log in, read news ( which is possible now ) and manage their schedule and
hopefully a lot more!

**NOTICE**: You have to install mongodb and start the daemon. Then add the
spirit_news database and the spirit_news user thefollowing way:
```shell
$ mongo
> use spirit_news
> db.addUser('spirit_news','spirit_news')
> exit
```

We know there is lots of room for improvement on the code. But most people
working on it just started with real world programming, so don't be to hard on
us ;)!

Ideas, friendly critisism and bugs are always welcomed!

Live version running at http://spirit.fh-schmalkalden.de

Running it on your local machine requires you to have a working Simple Build
Tool installation. http://www.scala-sbt.org/

Quickstart:
```shell
$ sbt
> update
> container:start
```
Then open http://localhost:8080/ in your favourite browser.

Testing:
```shell
$ sbt
> test
```
This will run the Specs from NewsSpec.scala, you will need a running MongoDB for them. 
Please be aware that this test will drop the Databases for Spirit-News when it is done.

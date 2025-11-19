# ChatSystem

Create an application to communicate in a local network with other users.

## Description

This project is a distributive application where people can log in with a username and see the list of all the other users connected to the application with their username. This application does not allow users to use the same username.

## Functionality

- Scan the network to see the users connected to the application.
- Log in/log out of the application.
- Change the username.

## File tree

    .gitignore
    metadata.yml
    pom.xml
    README.md
    src/
      main/
        java/
          chatsystem2526/
            b1/
              mathieu/
                ferrerehoareau/
                  BroadcastAdressFinder.java
                  ChangeLogin.java
                  ChatSystem.java
                  main_clt.java
                  MainWindow.java
                  UDPBroadcaster.java
                  UDPListenerThread.java
                  User.java
                  WindowLogin.java
      test/
        java/
          chatsystem2526/
            b1/
              mathieu/
                ferrerehoareau/
                  BoradcastTest.java
                  ChatSystem1Test.java
                  ChatSystem2Test.java


## Installation, compilation and test

### Compilation

mvn clean package

### Test

mvn test

### execution

java -cp target/ChatSystem-1.0-SNAPSHOT.jar chatsystem2526.b1.mathieu.ferrerehoareau.main_clt
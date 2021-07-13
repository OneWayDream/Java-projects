<!--
*** I took another user's excellent readme as the basis, 
*** because you have to start somewhere! Many thanks 
*** to him for providing the material 🙂
*** https://github.com/othneildrew/Best-README-Template
-->


<!-- PROJECT SHIELDS -->
[![Contributors][contributors-shield]][contributors-url]
[![Forks][forks-shield]][forks-url]
[![Stargazers][stars-shield]][stars-url]
[![Issues][issues-shield]][issues-url]

<!--ReadME Header-->
# Java-projects
Here you can see my educational and not-so-educational projects with Java as the main language


<details open="open">
  <summary>Full range</summary>
  <ol>
    <li>
      <a>Studying projects</a>
      <ul>
        <li><a href="#entity-manager">Entity Manager</a></li>
        <li><a href="#message-funnel">Message Funnel</a></li>
        <li><a href="#minion-valuation">Minion Valuation</a></li>
        <li><a href="#rarriate">Rarriate</a></li>
        <li><a href="#socket-chat">Socket Chat</a></li>
      </ul>
    </li>
    <li>
      <a>Production projects</a>
      <ul>
         Literally nothing 😕
      </ul>
    </li>
  </ol>
</details>

<--Entity Manager-->
## Entity Manager

> Simple Entity Manager realisation. I tried to use a lot of interfaces from javax.persistance without any tips from internet. Anyway, it was good experience to work with reflection.

### Used stuff:
```diff
- Java [reflection, Lombok]
@@ Databases (for testing operability) [postgres, HikariCP] @@
+ Simple testing (only integration 🙁) [hamcrest, io.zonky.test.embedded-postgres, junit]
# Project's build tool [Maven]
```

<--Message Funnel-->
## Message Funnel

> Not big back-end project, done during summer practice in SimbirSoft company. Main idea of this project - centralize the functionality of several bots in one.

### Used stuff:
```diff
- Java [Lombok, JDA, telegrambots and telegrambotsextensions, Java NIO Sockets]
! Logging [slf4g]
# Project's build tool [Maven]
```

<--Minion Valuation-->
## Minion Valuation

> My university semester work of Second Semester 😐, its just addition site for Minecraft Hypixel Skyblock to facilitate minion's profit calculation.

### Used stuff:
```diff
- Java [Servlets OR Spring (Security, JPA, AOP), Lombok, Swagger, JWT]
@@ Databases [postgres, HikariCP, Redis] @@
! Logging [slf4g]
# Project's build tool [Maven]
```
<--Rarriate-->
## Rarriate

> Also university semester work of Second Semester, it is simple weird 2D game.

### Used stuff:
```diff
- Java [JavaFX, Java NIO Sockets, Lombok]
# Project's build tool [Maven]
```

<--Socket Chat-->
## Socket Chat

> Small application, contains client and server classes for chating.

### Used stuff:
```diff
- Java [Java NIO Sockets, Lombok]
# Project's build tool [Maven]
```



<!-- MARKDOWN LINKS-->
<!-- https://www.markdownguide.org/basic-syntax/#reference-style-links -->
[contributors-shield]: https://img.shields.io/github/contributors/OneWayDream/Java-Projects.svg?style=for-the-badge
[contributors-url]: https://github.com/OneWayDream/Java-projects/graphs/contributors
[forks-shield]: https://img.shields.io/github/forks/OneWayDream/Java-Projects.svg?style=for-the-badge
[forks-url]: https://github.com/OneWayDream/Java-projects/network/members
[stars-shield]: https://img.shields.io/github/stars/OneWayDream/Java-Projects.svg?style=for-the-badge
[stars-url]: https://github.com/OneWayDream/Java-projects/stargazers
[issues-shield]: https://img.shields.io/github/issues/OneWayDream/Java-Projects.svg?style=for-the-badge
[issues-url]: https://github.com/OneWayDream/Java-projects/issues

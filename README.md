# Servis na komunikáciu s databázou Blazegraph #

## Blazegraph ##

Blazegraph wiki [odkaz](https://github.com/blazegraph/database/wiki/About_Blazegraph)

Blazegraph databáza blazegraph.jar [odkaz](https://github.com/blazegraph/database/releases/tag/BLAZEGRAPH_2_1_6_RC)

Spustenie blazegraph databázy [odkaz](https://github.com/blazegraph/database/wiki/Quick_Start)

### Potrebné technológie pre aplikáciu ###

Java 11, Maven, Blazegraph.

### Nastavenie aplikačných nastavení ###

V zložke src/main/resource/aplication.yaml

Vytvorte si nový journal súbor nazov.jnl a nastavte si tam cestu pre tento journal súbor.

Tento nový journal súbor by nemal byť použitý žiadnou ďalšou aplikáciou.

### Ako spustiť aplikáciu? ###

A) Ako maven projekt v IDE programe pre Javu. 

B) Inštalácia a spustenie - príkazom v zložke projektu *testprojekt*
> Poznámka: verzie a snapshot projektu sa budú v budúcnosti meniť!
```sh
$ mvn clean install
$ cd target
$ java -jar testprojekt-0.0.1-SNAPSHOT.jar
```

### Swagger ###

Užitočný na prevolavanie aplikácie pomocou REST API, alternatíva pre Postmana.

Po štarte aplikácie zavolaj URL uvedenú nižšie.

_http://localhost:8080/swagger-ui.html_

_http://\<host>:\<port>/swagger-ui.html_

Aktuálna verzia 2
> Poznámka: viacej informácií o Swagger v docs na tomto [odkaze](https://swagger.io/docs/)

V communication-controller sú API:
1. http://localhost:8080/query?query=SELECT DISTINCT ?property ?value WHERE { <https://cve.mitre.org/cgi-bin/cvename.cgi?name=CVE-2007-1216> ?property ?value. }.
2. http://localhost:8080/search?platform=Linux
3. http://localhost:8080/entity?id=https://cve.mitre.org/cgi-bin/cvename.cgi?name=CVE-2001-1494&props=info

### Vývoj ###

Pre Vaše ďalšie vytvorené funkcionality v tomto projekte si vytvorte novú branchu mimo branche master a develop.
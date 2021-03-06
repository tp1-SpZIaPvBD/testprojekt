# Servis na komunikáciu s databázou Blazegraph #

Funkčné ontológie: Capec, Cybox, CyboxCommon, DataMarking, killchain, Maec, STIX.

Nefunkčné ontológie nejdú importovať do databázy pravdepodobne z dôvodu, že niesú v RDF XML formáte.
Preto ich importovanie je zakomentované.

ERROR pri importovaní v danej ontológií:
org.openrdf.rio.RDFParseException: unqualified attribute 'ontologyIRI' not allowed

## Blazegraph ##

Blazegraph wiki [odkaz](https://github.com/blazegraph/database/wiki/About_Blazegraph)

Blazegraph databáza blazegraph.jar [odkaz](https://github.com/blazegraph/database/releases/tag/BLAZEGRAPH_2_1_6_RC)

Spustenie blazegraph databázy [odkaz](https://github.com/blazegraph/database/wiki/Quick_Start)

### Potrebné technológie pre aplikáciu ###

Java 11, Maven, Blazegraph.

### Nastavenie aplikačných nastavení ###

V zložke src/main/resource/aplication.yaml

Poprosím nastavte si tam cestu k novému journal súboru.

Tento nový journal súbor nemôže byť použitý žiadnou ďalšou aplikáciou.

### Ako spustiť aplikáciu? ###

1. Ako maven projekt v IDE programe pre Javu. 

2. Inštalácia a spustenie - príkazom v zložke projektu *testprojekt*
> Poznámka: verzie a snapshot projektu sa budú v budúcnosti meniť!
```sh
$ mvn clean install
$ cd target
$ java -jar testprojekt-0.0.1-SNAPSHOT.jar
```

### Swagger ###

Užitočné na prevolavanie aplikácie pomocou REST API, alternatíva pre Postmana.

Po štarte aplikácie zavolaj URL uvedenú nižšie.

_http://localhost:8080/swagger-ui.html_

_http://\<host>:\<port>/swagger-ui.html_

Aktuálna verzia 2
> Poznámka: viacej informácií o Swagger v docs na tomto [odkaze](https://swagger.io/docs/)

V communication-controller sú dve API:
1. Create - na vytvorenie ontologií, true/false parametre pre danú ontológiu.
2. Query - na dotazovanie k databáze. 
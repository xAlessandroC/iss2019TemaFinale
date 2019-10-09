# Tema Finale - Ingegneria dei Sistemi Software, 2018/2019

## Istruzioni per il Deployment
Il software è composto da tre componenti (contesti) principali: Butler, Maitre e Fridge. Di seguito le istruzioni per eseguire ognuna delle tre parti.

### Setting del progetto
All'interno del build.gradle occorre settare il percorso con le proprie librerie
```
repositories {
    mavenCentral()
    flatDir { //grazie Mattia Piretti
       dirs '/C:/Workspace_ISS/libs'	<---INSERIRE PROPRIO PERCORSO
   }
}
```
e buildare con 
```
gradle build eclipse
```

### BUTLER
Esistono due tipi di configurazioni a seconda che si voglia eseguire il butler su un DDR in remoto oppure sull'ambiente web virtuale

#### Configurazione DDR
IMPORTANTE: 
Lo zip che si trova già nel progetto ha come IP di riferimento per MQTT e COAP "192.168.43.145", occorre assicurarsi che effettivamente sia questo l'ip su cui girano i due servizi. In caso si vogliano usare degli indirizzi diversi occorre manualmente cambiarli all'interno dei file Rasp.qak(MQTT) e resources/itunibo.robot.fridgeInteraction.kt(COAP) e ribuildare utilizzando il comando 
```
gradle -b build_ctxRasp.gradle distZip
```
in maniera da rigenerare lo zip con le modifiche effettuate.

Per la configurazione di un DDR sul raspberry occorre
- copiare sul raspberry lo zip posto nella cartella it.unibo.ddrbutler.s5\build\distributions\it.unibo.ddrbutler.s5-1.0.zip  
- Estrarre lo zip
- All'interno della cartella it.unibo.ddrbutler.s5-1.0/bin appena generata copiare
  - tutti i file Prolog con estensione .pl
  - file hardwareConfiguration.properties
  - cartella configuration
- Assicurarsi che nel file basicRobotConfig,pl sia commentato tutto tranne la riga relativa al ROBOT NANO
- Assicurasi che nel file movimentConfig.pl siano decommentate le TEMPISTICHE NANO (eventualmente inserendo dei parametri personali)

#### Configurazione Virtuale
Il progetto presente dovrebbe già essere predisposto per il corretto funzionamento del robot virtuale, tuttavia è bene:
- nel file Rasp.qak assicurarsi che sia presente la riga 'mqttBroker "localhost" : 1883'
- Assicurarsi che nel file basicRobotConfig.pl sia commentato tutto tranne la riga relativa al ROBOT VIRTUAL
- Assicurasi che nel file movimentConfig.pl siano decommentate le TEMPISTICHE VIRTUAL (eventualmente inserendo dei parametri personali)

##### Avvio dell'ambiente virtuale
Per avviare l'ambiente web del robot virtuale occorre andare nella cartella nodeServerVirtualRobot ed eseguire i seguenti passi
- nella sottocartella nodeServerVirtualRobot/WEnv/server
  - cancellare la cartella node_modules
  - eseguire
  ```
  npm install
  ```
- nella sottocartella nodeServerVirtualRobot/WEnv/WebGLScene
  - cancellare la cartella node_modules
  - eseguire
  ```
  npm install
  ```
- nella sottocartella nodeServerVirtualRobot/WEnv/server/src
  - eseguire
  ```
  node main.js 8999
  ```
In questo modo il server sarà correttamente avviato e reperibile sulla pagina http://localhost:8090


### FRIDGE E MAITRE
Questi vanno avviati all'interno del progetto stesso.
IMPORTANTE: Per il corretto avvio del maitre è necessario avviare prima il fridge e poi il maitre

#### Istruzioni per il Fridge
Basta eseguire il main in src/it.unibo.ctxFridge

### Istruzioni per il Maitre
Per avviare il maitre bisogna andare nella cartella nodeServerMaitre/frontend
- cancellare la cartella node_modules
- eseguire
```
npm install
```
-eseguire
```
node frontendServer.js
```
A questo punto il maitre sarà partito e lo si potrà trovare sulla pagina http://localhost:3000

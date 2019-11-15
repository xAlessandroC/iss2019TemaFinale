# Tema Finale - Ingegneria dei Sistemi Software, 2018/2019
Il progetto è stato sviluppato assieme a [mustichlorenzo](https://github.com/mustichlorenzo)

## Struttura Repository
Tutti i progetti si trovano all'interno della cartella Workspace, in particolare
- it.unibo.ddrbutler: analisi iniziale
- it.unibo.ddrbutler.z1: analisi iniziale,zooming 1
- it.unibo.ddrbutler.z1-1: analisi iniziale, zooming 1
- it.unibo.ddrbutler.z2: analisi iniziale, zooming 2
- it.unibo.ddrbutler.z2-2: analisi iniziale, zooming 2
- it.unibo.ddrbutler.s1: sprint 1
- it.unibo.ddrbutler.s2: sprint 2
- it.unibo.ddrbutler.s3: sprint 3
- it.unibo.ddrbutler.s4: sprint 4
- it.unibo.ddrbutler.s5: sprint 5


## Istruzioni per il Deployment
Il software è composto da tre componenti (contesti) principali: Butler, Maitre e Fridge. Di seguito le istruzioni per eseguire ognuna delle tre parti.

### Setting del progetto
All'interno del file build.gradle occorre settare il percorso con le proprie librerie
```
repositories {
    mavenCentral()
    flatDir { //grazie Mattia Piretti
       dirs '/C:/Workspace_ISS/libs'	<---INSERIRE PROPRIO PERCORSO
   }
}
```
ed effettuare il build con
```
gradle build eclipse
```

### BUTLER
Esistono due tipi di configurazioni a seconda che si voglia eseguire il butler su un DDR in remoto oppure sull'ambiente web virtuale

#### Configurazione DDR
IMPORTANTE: 
Lo zip presente nel progetto ha come IP di riferimento per MQTT e COAP "192.168.43.145", occorre, quindi, assicurarsi che l'IP su cui girano i due servizi sia effettivamente questo. In caso si vogliano usare degli indirizzi diversi occorre manualmente cambiarli all'interno dei file Rasp.qak(MQTT) e resources/itunibo.robot/fridgeInteraction.kt(COAP) e rieseguire il build utilizzando il comando 
```
gradle -b build_ctxRasp.gradle distZip
```
in modo da generare lo zip con le modifiche effettuate.

Per la configurazione di un DDR sul raspberry occorre
- copiare sul raspberry lo zip posto nella cartella it.unibo.ddrbutler.s5\build\distributions\it.unibo.ddrbutler.s5-1.0.zip  
- estrarre lo zip
- copiare all'interno della cartella it.unibo.ddrbutler.s5-1.0/bin appena generata
  - tutti i file Prolog con estensione .pl
  - file hardwareConfiguration.properties
  - cartella configuration
- assicurarsi che nel file basicRobotConfig,pl sia decommentata esclusivamente la riga relativa al ROBOT NANO
- assicurasi che nel file movimentConfig.pl siano decommentate le TEMPISTICHE NANO (eventualmente inserendo dei parametri personali)

#### Configurazione Virtuale
Le istruzioni di seguito sono per l'esecuzione esclusivamente in locale.
Il presente progetto dovrebbe già essere predisposto per il corretto funzionamento del robot virtuale, tuttavia è bene:
- assicurarsi che nel file Rasp.qak sia presente la riga 'mqttBroker "localhost" : 1883'
- assicurarsi che nel file basicRobotConfig.pl sia decommentata esclusivamente la riga relativa al ROBOT VIRTUAL
- assicurasi che nel file movimentConfig.pl siano decommentate le TEMPISTICHE VIRTUAL (eventualmente inserendo dei parametri personali)
- assicurarsi che nel file resources/itunibo.robot/fridgeInteraction.kt l'indirizzo di rete sia "localhost"

##### Avvio dell'ambiente virtuale
Per avviare l'ambiente web del robot virtuale occorre essere nella cartella nodeServerVirtualRobot ed eseguire i seguenti passi
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
In questo modo il server sarà correttamente avviato ed attivo sulla pagina http://localhost:8090


### FRIDGE E MAITRE
Questi due componenti devono essere avviati all'interno del progetto stesso.
IMPORTANTE: Per il corretto funzionamento del maitre è necessario lanciare prima il fridge e poi il maitre

#### Istruzioni per il Fridge
È necessario eseguire esclusivamente il main in src/it.unibo.ctxFridge

### Istruzioni per il Maitre
Per avviare il maitre bisogna essere nella cartella nodeServerMaitre/frontend
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

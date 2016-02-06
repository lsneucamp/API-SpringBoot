# API-SpringBoot

Para Executar esse projeto é necessário ter instalado as seguintes ferramentas:
* Java 8 JDK
* Maven 3
* MongoDB 3
* 
O arquivo de configuração está em:
```
API-SpringBoot/
  |- src/
  |  |- main/
  |  |  |- resources
  |  |  |  |- application.yml
```
Teoricamente está pronto para ser executado depois que as ferramentas forem instaladas corretamente.
## Instruções para executar o projeto

Depois que instalar as ferramentas execute na pasta raiz do projeto:

```sh
$ mvn package
```
Execute *”$ mvn package”* para verificar, testar e empacotar o codigo em um JAR, modificando o POM.xml você tambpem pode criar um WAR pra realizar o deploy em um tomcat or jetty, porém não há nescessidade disso usando o camando abaixo.

Uma vez que os testes passarem, você pode iniciar seu servidor embutido do tomcat:
```sh
$ mvn spring-boot:run
```
O servidor irá subir no seguinte endereço:
* http://localhost:8080

Para testar se está retornado dados é so abrir no navegador o endereço com os parâmetros http://localhost:8080/api/v1/nodes/





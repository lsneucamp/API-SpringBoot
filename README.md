# API-Logistica-SpringBoot

## Motivação
Esta API foi desenvolvida usando o Spring-boot, uma ferramenta da longa lista de ferramentas do Spring Framework.
Bom, sou desenvolvedor Java Senior, deste de 2008, minha experiência com Java vai deste da definição da Arquitetura do Projeto até sua distribuição na Nuvem, usando a plataforma de nuvem da Google ou da Amazon.

Recentemente uso quase que exclusivamente Java para desenvolvimento de REST API usando o Spring, tenho bastante facilidade e uma boa noção para arquitetar e desenvolver API de fácil escabilidade e elasticidade na nuvem.
Nesse projeto usei as boas práticas de desenvolvimento de REST API.Todas as camadas (Respository, Service, Controllers) têm teste escrito usando também mistura boas práticas de teste TDD e BDD.

Esta API tem as funções da API NodeJS, afim que sejam compatíveis com o Webapp desenvolvido para este teste.
A intenção era desenvolver os 3 projetos paralelamente afim que pudesse mostrar como foi implementado cada recurso em cada plataforma.

Para Executar esse projeto é necessário ter instalado as seguintes ferramentas:
* Java 8 JDK
* Maven 3
* MongoDB 3
* 
O arquivo de configuração está em:
```
API-Logistica-NodeJS-Express/
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





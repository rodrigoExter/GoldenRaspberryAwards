# Golden Raspberry Awards

Para visualizar a lista de indicados e vencedores da categoria de Pior Filme, foi desenvolvida uma API RESTFUL para possibilitar a leitura e manutenção.

# Requisitos

Tem como requisitos do sistema ler um arquivo CSV (movieslist.csv) dos filmes e inserir os dados em uma base de dados (PENDENTE).

# Requisitos da API

1. Obter o(s) vencedor(es), informando um ano;
2. Obter os anos que tiveram mais de um vendedor;
3. Obter a lista de estúdios, ordenada pelo número de premiações;
4. Obter o produtor com maior intervalo entre dois prêmios, e o que obteve dois prêmios mais rápido (PENDENTE);
5. Excluir um filme. Não deve permitir excluir vencedores (PENDENTE);

## Estrutura do Projeto

 Para importar o projeto, basta realizar o cone via https://github.com/rodrigoExter/GoldenRaspberryAwards.git

### Build Automática

 O processo de build automática é realizado por meio da ferramenta [Gradle](http://gradle.org).
 
 
 Exemplo - app módulo:

 ```
 buildscript {
    ext {
        springBootVersion = '2.0.1.RELEASE'
    }
    repositories {
        mavenCentral()
    }
    dependencies {
        classpath("org.springframework.boot:spring-boot-gradle-plugin:${springBootVersion}")
    }
}

group = 'com.feature.filme'
version = '0.0.1-SNAPSHOT'

apply plugin: 'java'
apply plugin: 'eclipse'
apply plugin: 'org.springframework.boot'
apply plugin: 'io.spring.dependency-management'

sourceCompatibility = 1.8

repositories {
    mavenCentral()
}

dependencies {
    compile('org.springframework.boot:spring-boot-starter-web')
    compile('org.springframework.boot:spring-boot-starter-data-jpa')
    compileOnly('org.projectlombok:lombok')
    compile ('org.postgresql:postgresql:42.2.5')
    compile('io.springfox:springfox-swagger2:2.8.0')
    compile('io.springfox:springfox-swagger-ui:2.8.0')
    compile('org.apache.commons:commons-csv:1.5')
    compile ('com.h2database:h2:1.0.60')
    testCompile('org.springframework.boot:spring-boot-starter-test')
}

```

## Banco de dados H2

H2 é um banco de dados escrito em java, muito rápido, free e com codigo fonte incluído.
Para utiliza-lo, deve adicionar na dependência do projeto o seguinte: com.h2database:h2

Após adicionar e baixar as dependências, deve ser configurado o arquivo application:properties, conforme abaixo:

//H2
spring.h2.console.enabled=true
spring.h2.console.path=/h2

//Datasource
spring.datasource.url=jdbc:h2:file:~/testdb
spring.datasource.username=h2sa
spring.datasource.password=admin
spring.datasource.driver-class-name=org.h2.Driver
spring.jpa.hibernate.ddl-auto=update

Em seguida, para acessar o console, basta abrir o browser e digitar o seguinte caminho:

localhost:8080/h2 e será aberta uma tela para acessar o banco e em seguida, terá o devido acesso.

Importante lembrar que ele será executado na execução da aplicação do projeto.

## Projeto

Foram criadas estruturas padrões para controlar o CRUD do projeto.

## Teste Unitário

### Mockito

Teste de controllers são aqueles que validam o comportamento do gerenciamento entre as camadas de modelo (Model) e visão (view), com isso, 
essa camada não deve testar regra de negócio, e sim conversões de formatos do model para view, disponibilização de api, consumo de api, entre outros.
Pensando nisso, o Spring Boot disponibiliza algumas anotações para facilitar os testes dessa camada.

Para utilizar esse recurso, é necessário adicionar a dependência:  org.springframework.boot:spring-boot-starter-test:<VERSAO_RELEASE>;




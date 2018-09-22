# Golden Raspberry Awards

Para visualizar a lista de indicados e vencedores da categoria de Pior Filme, foi desenvolvida uma API RESTFUL para possibilitar a leitura e manuten��o.

# Requisitos

Tem como requisitos do sistema ler um arquivo CSV (movieslist.csv) dos filmes e inserir os dados em uma base de dados (PENDENTE).

# Requisitos da API

1. Obter o(s) vencedor(es), informando um ano;
2. Obter os anos que tiveram mais de um vendedor;
3. Obter a lista de est�dios, ordenada pelo n�mero de premia��es;
4. Obter o produtor com maior intervalo entre dois pr�mios, e o que obteve dois pr�mios mais r�pido (PENDENTE);
5. Excluir um filme. N�o deve permitir excluir vencedores (PENDENTE);

## Estrutura do Projeto

 Para importar o projeto, basta realizar o cone via https://github.com/rodrigoExter/GoldenRaspberryAwards.git

### Build Autom�tica

 O processo de build autom�tica � realizado por meio da ferramenta [Gradle](http://gradle.org).
 
 
 Exemplo - app m�dulo:

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
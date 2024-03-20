# Backend recursos

### **Spring Boot v3.2.2**
### **JDK 17.0.7**

## Instale as dependências Maven no projeto
```bash
mvn install
```

#### Dependências Maven
#### Arquivo pom.xml com dependências adicionadas
```
<dependency>
	<groupId>org.springframework.boot</groupId>
	<artifactId>spring-boot-starter-web</artifactId>
</dependency>

<dependency>
	<groupId>org.springframework.boot</groupId>
	<artifactId>spring-boot-starter-data-jpa</artifactId>
</dependency>

<dependency>
	<groupId>com.h2database</groupId>
	<artifactId>h2</artifactId>
	<scope>runtime</scope>
</dependency>

<dependency>
	<groupId>org.postgresql</groupId>
	<artifactId>postgresql</artifactId>
	<scope>runtime</scope>
	</dependency>

<dependency>
	<groupId>org.springframework.boot</groupId>
	<artifactId>spring-boot-starter-validation</artifactId>
</dependency>

```
#### Dependência de teste
````
<dependency>
	<groupId>org.springframework.boot</groupId>
	<artifactId>spring-boot-starter-test</artifactId>
	<scope>test</scope>
</dependency>
````

## Rode a aplicação spring boot na raiz FormappApplication

## Banco de dados em memória H2
### Para ter acesso ao banco H2 navegue por `http://localhost:8080/h2-console`
### Para ter acesso ao objeto json navegue por `http://localhost:8080/user`


## SQL para seed da base de dados de teste

```sql
INSERT INTO tb_user (name, email) VALUES ('Rafael', 'rafael@email.com');
INSERT INTO tb_user (name, email) VALUES ('Tiago', 'tiago@email.com');
INSERT INTO tb_user (name, email) VALUES ('Marina', 'marina@email.com');
INSERT INTO tb_user (name, email) VALUES ('Natalia', 'natalia@email.com');
```

## Objetos JSON para inserir e atualizar um produto

### Inserir

```javascript
{
  "name": "Pedro",
  "email": "pedro@email.com",
}
```

### Atualizar

```javascript
{
  "name": "Updated name",
  "email": "Updated email",
}
```

## Arquivos de configuração

#### application.properties for test

```
spring.profiles.active=test

spring.jpa.open-in-view=false
```

#### application-test.properties

```
# H2 connection
spring.datasource.url=jdbc:h2:mem:testdb
spring.datasource.username=sa
spring.datasource.password=

# H2 client
spring.h2.console.enabled=true
spring.h2.console.path=/h2-console

# Show sql
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true
```



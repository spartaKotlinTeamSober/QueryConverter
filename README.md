h2에 있는 데이터를 mysql로 옮겨야하는데, 호환이 자꾸 안됨.

1. 명령어로 마이그레이션⇒ sql 문법 오류
2. 문법 오류 수정후 마이그레이션 ⇒ data format 자체 오류
3. convert site 이용⇒ H2 미지원
4. DBeaver export/import 사용⇒H2DB를 못읽어옴(대체왜..?)
5. 마지막 방안으로 직접 데이터를 떠서 옮기는 작업이 있음

### =직접 데이터를 떠서 옮기도록 하였음.

```jsx
@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
    entityManagerFactoryRef = "deviceEntityManagerFactory",
    basePackages = ["org.dbconverter.querysender.h2.repository"],
)
class H2DataSourceConfig {....
```

```jsx
@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
    entityManagerFactoryRef = "entityManagerFactory",
    basePackages = ["org.dbconverter.querysender.mysql.repository"]
)
class MySqlDataSourceConfig {
```

multi database 설정

```jsx
spring:
	...
  datasource:
    h2:
      driver-class-name: org.h2.Driver
      url: ...
      username: ...
      password: ...
    mysql:
      driver-class-name: com.mysql.cj.jdbc.Driver
      url: ...
      username: ...
      password: ...
  h2:
    console:
      enabled: true
```

multi database 연결

```jsx
    fun queryConvert():Boolean{
        return try {
            h2Service.querySend()
                .also { mySqlService.queryRecv(it) }
            true
        }catch (e:Exception){
            false
        }
    }
```

convert 함수 작성

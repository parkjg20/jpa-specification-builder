# 개요

ORM은 개발자로 하여금 SQL을 신경쓰지 않게 하기 위해 만들어졌습니다.

하지만 대부분의 Spring 개발자들은 JPA를 사용하기 위해 여전히 JPQL, QueryDSL 등의 또다른 Query Language를 사용하고 있습니다.

이것이 과연 개발자를 Query로부터 해방시켜주었다고 할 수 있을까요?

## JPA Specification

JPA에서는 Specification이라는 것을 제공해 조회 쿼리를 빌드합니다.

하지만 유저가 이 Specification을 이용하려면 많은 양의 코드를 상황에 맞게 작성해야하기 때문에 재사용성이 떨어지고 라인 수가 길어진다는 이유로 이 방법 대신 간단한 JPQL을 사용하는 것이죠

-> 그럼 Specification을 간단하게 만들 수 있으면 문제 없는 것 아닌가?
그래서 사용자의 입장에서 Specification을 쉽게 만들 수 있도록 SpecificationBuilder를 구현해보았습니다.


## 사용 목적
- Request Parameters를 손쉽게 Specification으로 변환할 수 있습니다.
- Specification을 훨씬 적은 양의 코드로 Specification을 구현할 수 있습니다.

---

Spec
- JPA 2.7.2 기준으로 작성중.


지원 언어
- Java(작성중)
- Kotlin(작성중)

README 작성중...

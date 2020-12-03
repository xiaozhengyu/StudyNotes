# Spring Data JPA 批量保存效率问题

---

SimpleJpaRepository 是 JPA 整个关系数据库的所有 Repository 的接口实现类。

SimpleJpaRepository 通过 EntityManager进行实体的操作，JpaEntityInformation 中保存着实体的相关信息以及 crud 方法的元数据。


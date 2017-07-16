package com.handsome.auction.dao.base;

import com.wang.db2.Dao;
import com.wang.db2.Query;
import com.wang.db2.Where;
import com.wang.java_util.TextUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.sql.Connection;
import java.util.List;

/**
 * by wangrongjun on 2017/6/13.
 */
public abstract class BaseDao<T> implements Dao<T> {

    protected static SessionFactory sessionFactory;
    private Class<T> entityClass;

    public BaseDao() {
        if (sessionFactory == null) {
            Configuration config = new Configuration().configure();
            ServiceRegistry serviceRegistry = new ServiceRegistryBuilder().
                    applySettings(config.getProperties()).buildServiceRegistry();
            sessionFactory = config.buildSessionFactory(serviceRegistry);

//            Configuration config = new Configuration().configure();
//            StandardServiceRegistry registry = new StandardServiceRegistryBuilder().
//                    applySettings(config.getProperties()).build();
//            sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
//            sessionFactory = config.buildSessionFactory(registry);
        }
    }

    public static void closeSessionFactory() {
        if (sessionFactory != null) {
            sessionFactory.close();
            sessionFactory = null;
        }
    }

    /**
     * 获取子类传递给他的具体泛型类型
     */
    private Class<T> getEntityClass() {
        if (entityClass == null) {
            // 1.获取子类的class(在创建子类对象的时候,会返回父类的构造方法)
            Class<? extends BaseDao> cls = this.getClass();
            // 2.获取当前类的带有泛型的父类类型
            ParameterizedType type = (ParameterizedType) cls.getGenericSuperclass();
            // 3.返回实际参数类型(泛型可以写多个)
            Type[] types = type.getActualTypeArguments();
            // 4.获取第一个参数(泛型的具体类) Person.class
            entityClass = (Class<T>) types[0];
        }
        return entityClass;
    }

    protected String getTableName() {
        Entity entityAnno = getEntityClass().getAnnotation(Entity.class);
        if (entityAnno != null) {
            String name = entityAnno.name();
            if (name.length() > 0) {
                return name;
            }
        }
        return getEntityClass().getSimpleName();
    }

    protected String getIdName() {
        Field[] fields = getEntityClass().getDeclaredFields();
        for (Field field : fields) {
            if (field.getAnnotation(Id.class) != null) {
                return field.getName();
            }
        }
        return null;
    }

    @Override
    public boolean createTable() {
        return false;
    }

    @Override
    public boolean dropTable() {
        return false;
    }

    @Override
    public boolean createForeignKey() {
        return false;
    }

    @Override
    public boolean insert(T entity) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(entity);
        session.getTransaction().commit();
        session.close();
        return true;
    }

    @Override
    public boolean delete(Where where) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        String hql = "delete from " + getTableName();
        if (where != null && where.size() > 0) {
            hql += " where " + where;
        }
        session.beginTransaction();
        session.createQuery(hql).executeUpdate();
        session.getTransaction().commit();
        return true;
    }

    @Override
    public boolean deleteById(long id) {
        Session session = sessionFactory.openSession();
        String idName = getIdName();
        try {
            T entity = getEntityClass().newInstance();
            Field idField = getEntityClass().getDeclaredField(idName);
            idField.set(entity, id);
            session.beginTransaction();
            session.delete(entity);
            session.getTransaction().commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            session.close();
        }
    }

    @Override
    public boolean deleteAll() {
        Session session = sessionFactory.openSession();
        String hql = "delete from " + getTableName();
        session.createQuery(hql).executeUpdate();
        session.close();
        return true;
    }

    @Override
    public boolean update(T entity) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.update(entity);
        session.getTransaction().commit();
        session.close();
        return true;
    }

    @Override
    public T queryById(long id) {
        Session session = sessionFactory.openSession();
//        T entity = session.get(getEntityClass(), id);
        T entity = (T) session.get(getEntityClass(), id);
        session.close();
        return entity;
    }

    @Override
    public List<T> queryAll() {
        Session session = sessionFactory.openSession();
        String hql = "from " + getTableName();
//        return session.createSQLQuery(sql).addEntity(getEntityClass()).list();
//        List<T> entityList = session.createQuery(hql, getEntityClass()).list();
        System.out.println("------------------ " + hql + " ----------------------");
        List<T> entityList = session.createQuery(hql).list();
        session.close();
        return entityList;
    }

    @Override
    public List<T> query(Where where) {
        Session session = sessionFactory.openSession();
        String hql = "from " + getTableName();
        if (where != null && where.size() > 0) {
            hql += " where " + where;
        }
//        List<T> entityList = session.createQuery(hql, getEntityClass()).list();
        List<T> entityList = session.createQuery(hql).list();
        session.close();
        return entityList;
    }

    @Override
    public List<T> query(Query query) {
        Session session = sessionFactory.openSession();
        String hql = "from " + getTableName();

        // Where
        Where where = query.getWhere();
        if (where != null && where.size() > 0) {
            hql += " where " + where;
        }

        // 排序
        String[] orderBy = query.getOrderBy();
        if (orderBy != null && orderBy.length > 0) {
            hql += " order by ";
            for (String s : orderBy) {
                if (TextUtil.isEmpty(s)) {//防止orderBy数组不为空，但元素为空的情况
                    continue;
                }
                if (s.startsWith("-")) {
                    hql += s.substring(1) + " desc,";
                } else {
                    hql += s + " asc,";
                }
            }
            hql = hql.substring(0, hql.length() - 1);//去掉最后多余的逗号
        }

        // 分页，返回结果集
        int offset = query.getOffset();
        int count = query.getRowCount();
        List<T> list;
        if (offset >= 0 && count > 0) {
            list = session.createQuery(hql).setFirstResult(offset).setMaxResults(count).list();
        } else {
            list = session.createQuery(hql).list();
        }
        session.close();
        return list;
    }

    @Override
    public long queryCount(Where where) {
        Session session = sessionFactory.openSession();
        String hql = "select count(*) from " + getTableName();
        if (where != null && where.size() > 0) {
            hql += " where " + where;
        }
        return (long) session.createQuery(hql).uniqueResult();
    }

}

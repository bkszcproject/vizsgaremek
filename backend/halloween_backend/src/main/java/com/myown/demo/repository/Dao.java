package com.myown.demo.repository;

import java.util.List;

public interface Dao<T> {


    public T get(int id);

    public T getRandomElement();

    public List<T> getAll();

    public String delete(int id);

    public String update(T t, int id);

    public String add(T t);

    public String deleteAll();

    public String addAll(List<T> t);

}

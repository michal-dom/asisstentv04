package com.example.michal.asisstantv04.DataAccessObject;

import com.example.michal.asisstantv04.Models.Argument;

import java.util.List;

public interface IArgumentDao {

    public Argument fetchArgById(int argId);
    public List<Argument> fetchAllArgs();
    public int addArgs(Argument arg);
    public boolean deleteAllArgs();
}

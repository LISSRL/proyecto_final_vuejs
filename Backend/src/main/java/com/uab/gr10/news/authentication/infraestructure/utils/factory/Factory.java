package com.uab.gr10.news.authentication.infraestructure.utils.factory;

public interface Factory<T, C>
{
    T create(final C context);
}

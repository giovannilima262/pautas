package com.uds.pautas.presenter.db

import com.uds.pautas.presenter.db.repository.UserRepository

interface IDBOpenHelperPresenter {
    fun repositoryUser(): UserRepository
}